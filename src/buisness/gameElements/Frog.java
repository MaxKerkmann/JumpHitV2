package buisness.gameElements;

import application.Main;
import buisness.gamelogic.GameObject;
import javafx.beans.property.SimpleBooleanProperty;


//Frosch-Klasse
public class Frog implements GameObject {

    SimpleBooleanProperty jumpActive;
    Platform currentPlatform;
    private double x;
    private double y;
    private double rotation;
    private double sizemulti;
    private final int baseOffsetX = 32;
    private final int baseOffsetY = 42;
    private final int basefrogCatchRange = 30;
    private final int basefrogJumpSpeed = 15;
    private final int baseBirdSaveZone = 660;

    public Frog(Main main) {
        jumpActive = new SimpleBooleanProperty();
        jumpActive.set(false);
        sizemulti = main.getSizeMultiplyer();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
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
    public void update(double multi) {

        if (currentPlatform != null) {
            double vektorX = (currentPlatform.getX() - baseOffsetX * sizemulti) - x;
            double vektorY = (currentPlatform.getY() - baseOffsetY * sizemulti) - y;
            if (jumpActive.get()) {
                vektorX = Math.sqrt(vektorX * vektorX);
                vektorY = Math.sqrt(vektorY * vektorY);
                if (vektorX < basefrogCatchRange * sizemulti && vektorY < basefrogCatchRange * sizemulti) {
                    jumpActive.set(false);
                } else {
                    vektorX = (currentPlatform.getX() - baseOffsetX * sizemulti) - x;
                    vektorY = (currentPlatform.getY() - baseOffsetY * sizemulti) - y;
                    if (vektorX > 0)
                        x = x + basefrogJumpSpeed * sizemulti;
                    else
                        x = x - basefrogJumpSpeed * sizemulti;
                    if (vektorY > 0)
                        y = y + basefrogJumpSpeed * sizemulti;
                    else
                        y = y - basefrogJumpSpeed * sizemulti;
                }
            } else {
                if (currentPlatform.getY() >= baseBirdSaveZone * sizemulti) {
                    currentPlatform = null;
                    y = (baseBirdSaveZone-baseOffsetY) * sizemulti;
                }
                else {
                    x = currentPlatform.getX() - baseOffsetX * sizemulti;
                    y = currentPlatform.getY() - baseOffsetY * sizemulti;
                }
            }
        }
    }

    public void setCurrentPlatform(Platform platform) {
        currentPlatform = platform;
    }

    public SimpleBooleanProperty getJumpActive() {
        return jumpActive;
    }
}
