package presentation.ingame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameBreakView extends VBox {
    Button levelSelection;
    Button repeatLevel;
    Button nextLevel;
    Rectangle animation;

    public GameBreakView(){
        animation = new Rectangle();
        levelSelection = new Button();
        repeatLevel = new Button();
        nextLevel = new Button();

        HBox bottom = new HBox();
        bottom.setSpacing(20);
        bottom.getChildren().addAll(levelSelection,repeatLevel,nextLevel);
        bottom.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
        this.getChildren().addAll(animation,bottom);

    }
}
