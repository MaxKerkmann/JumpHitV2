package presentation.components.backbutton;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CloseView extends VBox {

    Button back;
    public CloseView() {
        back = new Button();
        back.setId("back-button");
        this.getChildren().add(back);
    }
}