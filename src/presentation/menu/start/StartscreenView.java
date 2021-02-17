package presentation.menu.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StartscreenView extends BorderPane {
	Button startButton;
	Button infoButton;
	Button resizeButton;

	public StartscreenView() {
		startButton = new Button();
		infoButton = new Button();
		resizeButton = new Button();

		VBox buttons = new VBox();
		buttons.getChildren().addAll(startButton,infoButton);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(200,1,1,1));
		buttons.setSpacing(30);
		startButton.getStyleClass().add("button-Style-start");
		infoButton.getStyleClass().add("button-Style-start");
		resizeButton.getStyleClass().addAll("button-Style");


		this.setCenter(buttons);
		this.getStyleClass().add("window-Style");
		this.setId("startscreen");
	}
}
