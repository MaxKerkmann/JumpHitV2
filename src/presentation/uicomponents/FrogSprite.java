package presentation.uicomponents;

import application.Main;
import buisness.gamelogic.GameObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;


public class FrogSprite extends Rectangle implements SimpleSprite {
    private SimpleObjectProperty<GameObject> gameObject;
    private SimpleBooleanProperty jumpActive;
    private Image img = null;

    public FrogSprite(Main main){
        gameObject = new SimpleObjectProperty<>();
        jumpActive = main.getGamePlayer().getFrog().getJumpActive();
        int worldnumber = main.getSelectedWorld();
        try {
            img = new Image(new FileInputStream("ressources/game/character/frog"+ worldnumber +".png"));
        }catch (Exception e){

        }
        this.setFill(new ImagePattern(img));
        this.autosize();


        gameObject.addListener(new ChangeListener<GameObject>() {
            @Override
            public void changed(ObservableValue<? extends GameObject> observable, GameObject oldValue, GameObject newValue) {
                render();
            }
        });


        jumpActive.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    img = null;
                    try {
                        img = new Image(new FileInputStream("ressources/game/character/frog"+ worldnumber +"_jump.gif"));
                    } catch (Exception e) {

                    }

                }else {
                    img = null;
                    try {
                        img = new Image(new FileInputStream("ressources/game/character/frog"+ worldnumber +".png"));
                    } catch (Exception e) {

                    }

                }
                setImage(new ImagePattern(img),newValue);
            }
        });
    }

    private void setImage(ImagePattern imgPattern,Boolean jumping){
        if(jumping) {
            this.setHeight(128);
            this.setWidth(96);
        }
        else{
            this.setHeight(64);
            this.setWidth(64);
        }
        this.setFill(imgPattern);
    }

    @Override
    public void render() {
        GameObject model = this.gameObject.get();
        if (model != null) {
            this.setX(model.getX());
            this.setY(model.getY());
        }
    }
    @Override
    public double getYCoord() {
        return this.getY();
    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return gameObject;
    }
}

