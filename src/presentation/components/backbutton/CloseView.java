package presentation.components.backbutton;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CloseView extends VBox {

    Button close;
    public CloseView() {
        close = new Button();
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().add(close);
    }
}