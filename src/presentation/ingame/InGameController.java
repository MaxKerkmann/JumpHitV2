package presentation.ingame;

import application.Main;
import buisness.gameElements.Platform;
import buisness.gamelogic.GameObject;
import buisness.gamelogic.GamePlayer;
import buisness.gamelogic.SoundPlayer;
import buisness.gamelogic.finishedMode;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import presentation.ViewController;
import presentation.uicomponents.FrogSprite;
import presentation.uicomponents.PlatformSprite;

import java.io.FileInputStream;

public class InGameController extends ViewController {
    private Rectangle leftBorder;
    private Rectangle rightBorder;
    private Rectangle centerBackground;
    private Rectangle safetyBird;
    private InGameView view;
    private Pane playfield;
    private Main main;
    private GamePlayer player;
    private GameBreakController gameBreakController;
    private FrogSprite frogSprite;
    private SoundPlayer soundPlayer;
    private String note;
    private int duration;
    private double sizemulti;

    private final int baseSpriteSize = 64;
    private final int baseGameBreakCoverHeight = 720;
    private final int baseGameBreakCoverWidth = 1280;
    private final int baseSavetyBirdHeigth = 100;
    private final int baseSavetyBirdWidth = 700;

    private SimpleObjectProperty currentPlatform;
    private SimpleObjectProperty<finishedMode> gameState;
    private SimpleBooleanProperty gameStarted;


    public InGameController(Main main) {
        soundPlayer = new SoundPlayer();
        view = new InGameView();
        this.main = main;
        player = main.getGamePlayer();
        currentPlatform = player.getPlatformCounter();
        gameState = player.getGameState();
        gameStarted = player.getGameStarted();

        sizemulti = main.getSizeMultiplier();

        playfield = view.playfield;
        leftBorder = view.leftBorder;
        rightBorder = view.rightBorder;
        centerBackground = view.centerBackground;
        safetyBird = view.safetyBird;

        rootView = view;


        initialize();

        playfield.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getTarget() instanceof Circle) {
                    PlatformSprite tempSprite = (PlatformSprite) mouseEvent.getTarget();
                    player.getFrog().setCurrentPlatform((Platform) tempSprite.gameObjectProperty().get());
                    playfield.getChildren().remove(frogSprite);
                    playfield.getChildren().add(frogSprite);
                    Platform platformObject = (Platform) tempSprite.gameObjectProperty().get();
                    Image img = null;
                    try {
                        switch (main.getSelectedWorld()){
                            case 1:
                                img = new Image(new FileInputStream("ressources/game/platforms/platform1.png"));
                                break;
                            case 2:
                                img = new Image(new FileInputStream("ressources/game/platforms/platform2.png"));
                                break;
                            case 3:
                                img = new Image(new FileInputStream("ressources/game/platforms/platform3.png"));
                        }
                    } catch (Exception e) {

                    }
                    if (!platformObject.getClicked()) {
                        note = platformObject.getNote();
                        duration = platformObject.getDuration();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (player.getFrog().getJumpActive().get() == true) {

                                }
                                soundPlayer.toPlay(note, duration);

                            }
                        }).start();
                        main.getGamePlayer().getFrog().getJumpActive().set(true);
                        platformObject.setClicked();
                        tempSprite.setFill(new ImagePattern(img));
                    }

                } else {
                    player.getGameState().set(finishedMode.LOST);
                }

            }
        });


        currentPlatform.addListener(new ChangeListener<GameObject>() {
            @Override
            public void changed(ObservableValue<? extends GameObject> observable, GameObject oldValue, GameObject newValue) {
                PlatformSprite pSprite = new PlatformSprite(main.getSelectedWorld());
                pSprite.setRadius(baseSpriteSize * sizemulti);
                pSprite.gameObjectProperty().set(newValue);
                player.addToSprites(pSprite);
                playfield.getChildren().add(pSprite);
            }
        });

        gameState.addListener(new ChangeListener<finishedMode>() {
            @Override
            public void changed(ObservableValue<? extends finishedMode> observable, finishedMode oldValue, finishedMode newValue) {
                if (newValue == finishedMode.PLAYING) {
                    view.getChildren().remove(view.getChildren().size() - 1);
                } else {
                    Rectangle cover = new Rectangle(baseGameBreakCoverWidth * sizemulti, baseGameBreakCoverHeight * sizemulti);
                    cover.setFill(Color.GRAY);
                    cover.setOpacity(0.5);
                    view.getChildren().add(cover);
                    gameBreakController = new GameBreakController(main, view);
                    gameBreakController.showStage();
                }
            }
        });

        gameStarted.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    frogSprite = new FrogSprite(main);
                    frogSprite.setHeight(baseSpriteSize * sizemulti);
                    frogSprite.setWidth(baseSpriteSize * sizemulti);
                    frogSprite.gameObjectProperty().setValue(player.getFrog());
                    player.addToSprites(frogSprite);
                    playfield.getChildren().add(frogSprite);

                    int worldNumber = main.getSelectedWorld();
                    Image img = null;
                    try {
                        img = new Image(new FileInputStream("ressources/game/background/world" + worldNumber + ".png"));
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

                    img = null;
                    try {
                        if (worldNumber == 3)
                            img = new Image(new FileInputStream("ressources/game/platforms/savebird_space.png"));
                        else
                            img = new Image(new FileInputStream("ressources/game/platforms/savebird.png"));
                    } catch (Exception e) {

                    }
                    safetyBird.setFill(new ImagePattern(img));
                }
            }
        });
    }

    @Override
    public void initialize() {

        safetyBird.setHeight(baseSavetyBirdHeigth * sizemulti);
        safetyBird.setWidth(baseSavetyBirdWidth * sizemulti);

        int worldNumber = main.getSelectedWorld();
        Image img = null;
        try {
            img = new Image(new FileInputStream("ressources/game/background/world" + worldNumber + ".png"));
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


        img = null;
        try {
            if (worldNumber == 3)
                img = new Image(new FileInputStream("ressources/game/platforms/savebird_space.png"));
            else
                img = new Image(new FileInputStream("ressources/game/platforms/savebird.png"));
        } catch (Exception e) {

        }
        safetyBird.setFill(new ImagePattern(img));
    }
}
