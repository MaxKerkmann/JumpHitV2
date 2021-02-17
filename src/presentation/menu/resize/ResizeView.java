package presentation.menu.resize;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ResizeView extends VBox {
    Button small;
    Button medium;
    Button big;
    Button accept;
    Button rollback;

    public ResizeView(){

        small = new Button();
        medium = new Button();
        big = new Button();
        accept = new Button();
        rollback = new Button();

        HBox center = new HBox();
        center.setSpacing(10);
        center.getChildren().addAll(small,medium,big);
        center.setAlignment(Pos.CENTER);
        HBox bottom = new HBox();
        bottom.setSpacing(20);
        bottom.getChildren().addAll(accept, rollback);
        bottom.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
        this.getChildren().addAll(center,bottom);

    }
}
