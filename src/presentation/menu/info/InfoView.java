package presentation.menu.info;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoView extends BorderPane {
    Label title;
    Text text;
    VBox center;
    public InfoView(){
        title = new Label();
        text = new Text();
        VBox boxTitle = new VBox();
        center = new VBox();
        center.getChildren().add(text);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(1,1,1,300));
        boxTitle.getChildren().addAll(title);
        boxTitle.setAlignment(Pos.TOP_CENTER);

        this.setTop(boxTitle);
        this.setCenter(center);
        this.getStyleClass().add("window-Style");
    }
}
