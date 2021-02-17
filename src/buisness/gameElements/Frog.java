package buisness.gameElements;

import application.Main;
import buisness.gamelogic.GameObject;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.*;

//Frosch-Klasse
public class Frog implements GameObject {

    SimpleBooleanProperty jumpActive;
    Platform currentPlatform;
    private double x;
    private double y;
    private double rotation;
    private double sizemulti;

    public Frog(Main main){
        jumpActive = new SimpleBooleanProperty();
        jumpActive.set(false);
        sizemulti = main.getSizeMultiplyer();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void update(double multi) {

        if(currentPlatform != null){
            double vektorX = (currentPlatform.getX()-32*sizemulti) - x;
            double vektorY = (currentPlatform.getY()-42*sizemulti) - y;
            if(jumpActive.get()) {
                vektorX = Math.sqrt(vektorX*vektorX);
                vektorY = Math.sqrt(vektorY*vektorY);
                if(vektorX < 30*sizemulti && vektorY < 30*sizemulti){
                    jumpActive.set(false);
                }else{
                     vektorX = (currentPlatform.getX()-32*sizemulti) - x;
                     vektorY = (currentPlatform.getY()-42*sizemulti) - y;
                    if(vektorX > 0)
                        x = x + 15*sizemulti;
                    else
                        x = x - 15*sizemulti;
                    if(vektorY > 0)
                        y = y +15*sizemulti;
                    else
                        y = y -15*sizemulti;
                }
            }else{
                if (currentPlatform.getY() >= 660*sizemulti)
                    currentPlatform = null;
                else {
                    x = currentPlatform.getX() - 32*sizemulti;
                    y = currentPlatform.getY() - 42*sizemulti;
                }
            }
        }
    }

    public void setCurrentPlatform(Platform platform){
        currentPlatform = platform;
    }

    public SimpleBooleanProperty getJumpActive(){
        return jumpActive;
    }
}
