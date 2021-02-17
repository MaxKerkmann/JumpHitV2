package presentation.menu.levels;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelSelectionView extends BorderPane {
	Button level1;
	Button level2;
	Button level3;
	Label title;
	
	public LevelSelectionView() {
		VBox center = new VBox();
		HBox bottumCenter = new HBox();
		
		title = new Label();
		
		level1 = new Button();
		level2 = new Button();
		level3 = new Button();
		level1.getStyleClass().add("button-Style-start");
		level2.getStyleClass().add("button-Style-start");
		level3.getStyleClass().add("button-Style-start");
		bottumCenter.setSpacing(150);
		bottumCenter.getChildren().addAll(level2,level3);
		center.setAlignment(Pos.CENTER);
		center.setSpacing(150);
		center.getChildren().addAll(level1,bottumCenter);
		this.setCenter(center);
		this.getStyleClass().add("window-Style");
	}
}
