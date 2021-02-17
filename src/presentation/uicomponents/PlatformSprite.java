package presentation.uicomponents;

import application.Main;
import buisness.gameElements.Platform;
import buisness.gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class PlatformSprite extends Circle implements SimpleSprite {
    private SimpleObjectProperty<GameObject> gameObject;

    public PlatformSprite(int worldnumber){
        gameObject = new SimpleObjectProperty<>();
        String addon = "";
        if(worldnumber == 3){
            int r = (int) (Math.random() * (4 - 1)) + 1;
            addon = "_"+r;
        }
        Image img = null;
        try {
            img = new Image(new FileInputStream("ressources/game/platforms/platform"+ worldnumber +""+ addon +".png"));
        }catch (Exception e){

        }


        this.setFill(new ImagePattern(img));


        gameObject.addListener(new ChangeListener<GameObject>() {
            @Override
            public void changed(ObservableValue<? extends GameObject> observable, GameObject oldValue, GameObject newValue) {
                render();
            }
        });
    }

    @Override
    public void render() {
        GameObject model = this.gameObject.get();
        if (model != null) {
            this.setCenterY(model.getY());
            this.setCenterX(model.getX());
        }


    }

    @Override
    public double getYCoord() {
        return this.getCenterY();
    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return gameObject;
    }
}
