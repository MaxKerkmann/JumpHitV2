package buisness.gamelogic;

import javafx.scene.Node;

//Inferface für alle Objekte des Spiels (Frosch, Blätter etc.)
public interface GameObject {
    double getX();
    double getY();
    double getRotation();

    void update(double multi);
}