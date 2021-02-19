package presentation.ingame;

import application.Main;
import buisness.gamelogic.finishedMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.ViewController;
import presentation.menu.levels.LevelSelectionController;
import presentation.uicomponents.FrogSprite;
import presentation.uicomponents.PlatformSprite;

import java.io.FileInputStream;

public class GameBreakController extends ViewController {

    private Button levelSelection;
    private Button repeatLevel;
    private Button nextLevel;
    private Rectangle animation;
    private Main main;
    private GameBreakView view;
    private Stage stage;
    private InGameView gameView;

    private final int endAnimationSize = 350;
    private final int baseIconSize = 40;
    private final int baseStageHeigth = 500;
    private final int baseStageWidth = 430;

    private double sizemulti;

    public GameBreakController(Main main, InGameView gameView) {
        this.main = main;
        view = new GameBreakView();
        this.gameView = gameView;
        levelSelection = view.levelSelection;
        repeatLevel = view.repeatLevel;
        nextLevel = view.nextLevel;
        animation = view.animation;

        sizemulti = main.getSizeMultiplyer();

        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {

        animation.setWidth(endAnimationSize * sizemulti);
        animation.setHeight(endAnimationSize * sizemulti);

        repeatLevel.getStyleClass().addAll("button-Style");

        levelSelection.getStyleClass().addAll("button-Style");

        nextLevel.getStyleClass().addAll("button-Style");

        nextLevel.setDisable(true);
        String newSoundFile = main.getGamePlayer().getCurrentSoundFile();
        int songnumber = Integer.parseInt(newSoundFile.substring((newSoundFile.length() - 5), (newSoundFile.length() - 4)));
        if (main.getGamePlayer().getGameState().get() == finishedMode.WON || main.getCurrentLevel() > songnumber) {
            nextLevel.setDisable(false);
            if(main.getCurrentLevel() == songnumber-1)
            main.setCurrentLevel(main.getCurrentLevel() + 1);
        }
        if(main.getGamePlayer().getGameState().get() == finishedMode.WON){
            Image img = null;
            try {
                img = new Image(new FileInputStream("ressources/game/window/frog_win.png"));
            } catch (Exception e) {

            }
            animation.setFill(new ImagePattern(img));

        }else{
            Image img = null;
            try {
                img = new Image(new FileInputStream("ressources/game/window/frog_loss.png"));
            } catch (Exception e) {

            }
            animation.setFill(new ImagePattern(img));
        }

        try {
            ImageView repeatLevelview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "repeat"))));
            repeatLevelview.setFitHeight(baseIconSize * sizemulti);
            repeatLevelview.setFitWidth(baseIconSize * sizemulti);
            repeatLevel.setGraphic(repeatLevelview);
            ImageView levelSelectionview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "level"))));
            levelSelectionview.setFitHeight(baseIconSize * sizemulti);
            levelSelectionview.setFitWidth(baseIconSize * sizemulti);
            levelSelection.setGraphic(levelSelectionview);
            ImageView nextLevelview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "nextlevel"))));
            nextLevelview.setFitHeight(baseIconSize * sizemulti);
            nextLevelview.setFitWidth(baseIconSize * sizemulti);
            nextLevel.setGraphic(nextLevelview);


        } catch (Exception e) {
            e.printStackTrace();
        }

        nextLevel.addEventHandler(ActionEvent.ACTION, event -> {
            for (Node node : gameView.getChildren()) {
                if (node instanceof Pane) {
                    for (Node platform : (((Pane) node).getChildren())) {
                        if (platform instanceof PlatformSprite || platform instanceof FrogSprite) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((Pane) node).getChildren().remove(platform);
                                }
                            });

                        }
                    }
                }
            }
            stage.close();

            if ((songnumber) % 3 == 0)
                main.setSelectedWorld(main.getSelectedWorld() + 1);
            main.getGamePlayer().start("songs/song" + (songnumber+1) + ".xml");
        });

        levelSelection.addEventHandler(ActionEvent.ACTION, event -> {
            for (Node node : gameView.getChildren()) {
                if (node instanceof Pane) {
                    for (Node platform : (((Pane) node).getChildren())) {
                        if (platform instanceof PlatformSprite || platform instanceof FrogSprite) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((Pane) node).getChildren().remove(platform);
                                }
                            });

                        }
                    }
                }
            }
            main.getGamePlayer().getGameStarted().set(false);
            main.getGamePlayer().getGameState().set(finishedMode.PLAYING);
            main.getGamePlayer().getTimer().stop();

            if (songnumber <= 3) {
                main.setSelectedWorld(1);
            } else if (songnumber > 3 && songnumber <= 6) {
                main.setSelectedWorld(2);
            } else {
                main.setSelectedWorld(3);
            }

            main.resetScene(new LevelSelectionController(main), Main.Scenes.LEVELMENU);
            main.switchScene(Main.Scenes.LEVELMENU);

            stage.close();
        });

        repeatLevel.addEventHandler(ActionEvent.ACTION, event -> {
            for (Node node : gameView.getChildren()) {
                if (node instanceof Pane) {
                    for (Node platform : (((Pane) node).getChildren())) {
                        if (platform instanceof PlatformSprite || platform instanceof FrogSprite) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((Pane) node).getChildren().remove(platform);
                                }
                            });

                        }
                    }
                }
            }
            main.getGamePlayer().start(main.getGamePlayer().getCurrentSoundFile());

            stage.close();
        });

        Image img = null;
        try {
            img = new Image(new FileInputStream("ressources/menus/Options/resize.gif"));
        } catch (Exception e) {

        }

        BackgroundImage bgImage = new BackgroundImage(
                img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        view.setBackground(new Background(bgImage));


    }

    public void showStage() {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        Scene newScene = new Scene(this.getRootView(), baseStageWidth * sizemulti, baseStageHeigth * sizemulti);
        newScene.getStylesheets().add(getClass().getResource("../../application/application.css").toExternalForm());
        stage.setScene(newScene);

        stage.show();
    }


}
