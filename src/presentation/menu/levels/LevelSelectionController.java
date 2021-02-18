package presentation.menu.levels;

import application.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.backbutton.CloseController;
import presentation.ingame.InGameController;

public class LevelSelectionController extends ViewController {
	
	private Button level1;
	private Button level2;
	private Button level3;
	private Label title;
	private BackButtonController back;
	private CloseController close;
	private LevelSelectionView view;

	
	private Main main;
	
	public LevelSelectionController(Main main) {
		this.main = main;
		view = new LevelSelectionView();
		back = new BackButtonController(main);
		close = new CloseController(main);

		level1 = view.level1;
		level2 = view.level2;
		level3 = view.level3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {


		title.setFont(Font.font("Arial", FontWeight.BOLD,30* main.getSizeMultiplyer()));
		switch (main.getSelectedWorld()){
			case 1:
				title.setText("Welt 1");
				break;
			case 2:
				title.setText("Welt 2");
				break;
			case 3:
				title.setText("Welt 3");
				break;
		}


		close.getRootView().setPadding(new Insets(1,1,1,900* main.getSizeMultiplyer()));
		HBox top = new HBox();
		HBox topRight = new HBox();
		topRight.getChildren().add(close.getRootView());
		topRight.setAlignment(Pos.CENTER_RIGHT);
		top.getChildren().addAll(back.getRootView(), title,topRight);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(10);
		view.setTop(top);
		level1.setText("Level 1");
		level2.setText("Level 2");
		level3.setText("Level 3");

		int actualLevel = (main.getSelectedWorld()-1);

		level1.addEventHandler(ActionEvent.ACTION, event -> {
			if (main.sceneExists(Main.Scenes.INGAME)==false)
				main.addScene(new InGameController(main), Main.Scenes.INGAME);
			main.setLastScene(Main.Scenes.LEVELMENU);
			main.switchScene(Main.Scenes.INGAME);
			main.getGamePlayer().start("songs/song"+ (1+(actualLevel*3)) +".xml");
		});

		level2.addEventHandler(ActionEvent.ACTION, event -> {
			if (main.sceneExists(Main.Scenes.INGAME)==false)
				main.addScene(new InGameController(main), Main.Scenes.INGAME);
			main.setLastScene(Main.Scenes.LEVELMENU);
			main.switchScene(Main.Scenes.INGAME);
			main.getGamePlayer().start("songs/song"+ (2+(actualLevel*3)) +".xml");
		});

		level3.addEventHandler(ActionEvent.ACTION, event -> {
			if (main.sceneExists(Main.Scenes.INGAME)==false)
				main.addScene(new InGameController(main), Main.Scenes.INGAME);
			main.setLastScene(Main.Scenes.LEVELMENU);
			main.switchScene(Main.Scenes.INGAME);
			main.getGamePlayer().start("songs/song"+ (3+(actualLevel*3)) +".xml");
		});
	}
}
