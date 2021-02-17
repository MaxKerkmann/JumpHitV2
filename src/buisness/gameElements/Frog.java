package buisness.gameElements;

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

    public Frog(){
        jumpActive = new SimpleBooleanProperty();
        jumpActive.set(false);
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
            double vektorX = (currentPlatform.getX()-32) - x;
            double vektorY = (currentPlatform.getY()-42) - y;
            if(jumpActive.get()) {
                vektorX = Math.sqrt(vektorX*vektorX);
                vektorY = Math.sqrt(vektorY*vektorY);
                if(vektorX < 30 && vektorY < 30){
                    jumpActive.set(false);
                }else{
                     vektorX = (currentPlatform.getX()-32) - x;
                     vektorY = (currentPlatform.getY()-42) - y;
                    if(vektorX > 0)
                        x = x + 20;
                    else
                        x = x - 20;
                    if(vektorY > 0)
                        y = y +20;
                    else
                        y = y -20;
                }
            }else{
                if (currentPlatform.getY() >= 660)
                    currentPlatform = null;
                else {
                    x = currentPlatform.getX() - 32;
                    y = currentPlatform.getY() - 42;
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
