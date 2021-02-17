package presentation.components.backbutton;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BackButtonView extends VBox {

	Button back;
	public BackButtonView() {
		back = new Button();
		back.setId("back-button");
		this.getChildren().add(back);
	}
}