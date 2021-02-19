package buisness.gamelogic;

//Inferface für alle Objekte des Spiels (Frosch, Blätter etc.)
public interface GameObject {
    double getX();
    double getY();

    void update(double multi);
}
