package buisness.gameElements;

import application.Main;
import buisness.gamelogic.GameObject;


//Plattformen mit Attributen
public class Platform implements GameObject {
    private String note;
    private int duration;
    private int position;
    private int passTime;
    private double pauseTime;

    private double fallingSpeed;
    private boolean clicked;

    private double x;
    private double y;
    private int rotation;

    private double sizemulti;

    private final double baseHeight = 1280.0;

    public Platform(String note, int duration, int position, int passTime, double pauseTime, double sizemulti) {
        this.note = note;
        this.duration = duration;
        this.position = position;
        this.pauseTime = pauseTime;
        this.passTime = passTime;
        this.sizemulti = sizemulti;
        fallingSpeed = calculateFallingSpeed();

    }

    private double calculateFallingSpeed() {
        double speed = (baseHeight * sizemulti) * (1000.0 / passTime);
        return speed;
//        return 200;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(double newPauseTime) {
        pauseTime = newPauseTime;
    }

    public String getNote() {
        return note;
    }

    public int getDuration() {
        return duration;
    }

    public void setClicked() {
        clicked = true;
    }

    public boolean getClicked() {
        return clicked;
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
        y = y + (fallingSpeed * multi);
    }
}
