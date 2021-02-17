package presentation.ingame;

import application.Main;
import buisness.gamelogic.GamePlayer;
import buisness.gamelogic.finishedMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.ViewController;
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

    public GameBreakController(Main main, InGameView gameView){
        this.main = main;
        view = new GameBreakView();
        this.gameView = gameView;
        levelSelection = view.levelSelection;
        repeatLevel =view.repeatLevel;
        nextLevel = view.nextLevel;
        animation = view.animation;

        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {


        repeatLevel.getStyleClass().addAll("button-Style");

        levelSelection.getStyleClass().addAll("button-Style");

        nextLevel.getStyleClass().addAll("button-Style");

        if(main.getGamePlayer().getGameState().get() != finishedMode.WON){
            nextLevel.setDisable(true);
        }

        try {
            ImageView repeatLevelview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "repeat"))));
            repeatLevelview.setFitHeight(40);
            repeatLevelview.setFitWidth(40);
            repeatLevel.setGraphic(repeatLevelview);
            ImageView levelSelectionview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "level"))));
            levelSelectionview.setFitHeight(40);
            levelSelectionview.setFitWidth(40);
            levelSelection.setGraphic(levelSelectionview);
            ImageView nextLevelview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/game/window", "nextlevel"))));
            nextLevelview.setFitHeight(40);
            nextLevelview.setFitWidth(40);
            nextLevel.setGraphic(nextLevelview);



        } catch (Exception e) {
            e.printStackTrace();
        }

        nextLevel.addEventHandler(ActionEvent.ACTION, event -> {
            for(Node node : gameView.getChildren()) {
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
            String newSoundFile = main.getGamePlayer().getCurrentSoundFile();
            int songnumber = Integer.parseInt(newSoundFile.substring((newSoundFile.length()-5),(newSoundFile.length()-4)));
            main.getGamePlayer().start("songs/song" + (songnumber+1) +".xml");
        });

        levelSelection.addEventHandler(ActionEvent.ACTION, event -> {
            for(Node node : gameView.getChildren()) {
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
            main.switchScene(Main.Scenes.LEVELMENU);

            stage.close();
        });

        repeatLevel.addEventHandler(ActionEvent.ACTION, event -> {
            for(Node node : gameView.getChildren()) {
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



    }

    public void showStage() {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        Scene newScene = new Scene(this.getRootView());
        stage.setScene(newScene);

        stage.show();
    }


}
