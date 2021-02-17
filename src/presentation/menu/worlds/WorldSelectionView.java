package presentation.menu.worlds;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.File;
import java.net.MalformedURLException;

public class WorldSelectionView extends BorderPane {
	Button buttonToWorld1;
	Button buttonToWorld2;
	Button buttonToWorld3;

	Label title;

	VBox vbox_world1;
	VBox vbox_world2;
	VBox vbox_world3;
	HBox hbox_allWorlds;

	ImageView image;
	//ImageViewPane imagePane;
	final String pathname_worldPicture = "ressources/menus/StartMenu/welt.png";

	public WorldSelectionView() {
		hbox_allWorlds = new HBox();

		title = new Label();

		buttonToWorld1 = new Button();
		buttonToWorld2 = new Button();
		buttonToWorld3 = new Button();

		buttonToWorld1.getStyleClass().add("button-Style-worldSelection");
	//	buttonToWorld1.setId("worldSelection-button-world1");
		buttonToWorld2.getStyleClass().add("button-Style-worldSelection");
	//	buttonToWorld2.setId("wordSelection-button-world2");
		buttonToWorld3.getStyleClass().add("button-Style-worldSelection");
	//	buttonToWorld3.setId("wordSelection-button-world3");

		hbox_allWorlds.setAlignment(Pos.CENTER);
		hbox_allWorlds.setSpacing(100);

		hbox_allWorlds.getChildren().addAll(buttonToWorld1, buttonToWorld2, buttonToWorld3);

		this.setCenter(hbox_allWorlds);
		this.getStyleClass().add("window-Style");
		this.setId("worldSelection");
	}
}
