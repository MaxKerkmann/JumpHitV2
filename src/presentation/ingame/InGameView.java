package presentation.ingame;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;


public class InGameView extends StackPane {
    Rectangle leftBorder;
    Rectangle rightBorder;
    Rectangle centerBackground;
    Rectangle safetyBird;
    Pane playfield;

    public InGameView(){
        playfield = new Pane();


        safetyBird = new Rectangle();

        HBox bottom = new HBox();

        bottom.getChildren().add(safetyBird);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        this.getChildren().addAll(bottom,playfield);
        this.getStyleClass().add("gameWindow-Style");
    }


}
