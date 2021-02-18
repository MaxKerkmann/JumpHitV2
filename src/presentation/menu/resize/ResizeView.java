package presentation.menu.resize;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ResizeView extends BorderPane {
    Button small;
    Button medium;
    Button big;
    Button accept;
    Button rollback;
    Label currentSize;
    Label title;

    public ResizeView(){

        small = new Button();
        medium = new Button();
        big = new Button();
        accept = new Button();
        rollback = new Button();
        currentSize = new Label();
        title = new Label();

        VBox center = new VBox();
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(small,medium,big);
        buttons.setAlignment(Pos.CENTER);
        HBox bottom = new HBox();
        center.getChildren().addAll(buttons,currentSize);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(50);
        bottom.setSpacing(20);
        bottom.getChildren().addAll(accept, rollback);
        bottom.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(20));
        this.setTop(title);
        this.setCenter(center);
        this.setBottom(bottom);


    }
}
