package presentation.menu.info;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InfoView extends BorderPane {
    Button back;
    Label title;

    public InfoView(){
        title = new Label();

        VBox boxTitle = new VBox();
        boxTitle.getChildren().addAll(title);
        boxTitle.setAlignment(Pos.TOP_CENTER);

        this.setTop(boxTitle);
        this.getStyleClass().add("window-Style");
        this.setId("info");
    }
}
