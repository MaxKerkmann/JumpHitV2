package presentation.uicomponents;

import buisness.gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;

public interface SimpleSprite {
    public void render();
    public double getYCoord();
    public SimpleObjectProperty<GameObject> gameObjectProperty();
}
