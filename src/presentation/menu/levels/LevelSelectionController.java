package presentation.menu.levels;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.ingame.InGameController;

public class LevelSelectionController extends ViewController {
	
	private Button level1;
	private Button level2;
	private Button level3;
	private Label title;
	private BackButtonController back;
	private LevelSelectionView view;
	
	private Main main;
	
	public LevelSelectionController(Main main, int worldNumber) {
		this.main = main;
		view = new LevelSelectionView();
		back = new BackButtonController(main);
		
		level1 = view.level1;
		level2 = view.level2;
		level3 = view.level3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {
		
		title.setText("Level");
		
		HBox top = new HBox();
		top.getChildren().addAll(back.getRootView(), title);
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
			System.out.println("songs/song"+ (1+(actualLevel*3)) +".xml");
			main.getGamePlayer().start("songs/song"+ (1+(actualLevel*3)) +".xml");
		});

		level2.addEventHandler(ActionEvent.ACTION, event -> {
			if (main.sceneExists(Main.Scenes.INGAME)==false)
				main.addScene(new InGameController(main), Main.Scenes.INGAME);
			main.setLastScene(Main.Scenes.LEVELMENU);
			main.switchScene(Main.Scenes.INGAME);
			System.out.println("songs/song"+ (2+(actualLevel*3)) +".xml");
			main.getGamePlayer().start("songs/song"+ (2+(actualLevel*3)) +".xml");
		});

		level3.addEventHandler(ActionEvent.ACTION, event -> {
			if (main.sceneExists(Main.Scenes.INGAME)==false)
				main.addScene(new InGameController(main), Main.Scenes.INGAME);
			main.setLastScene(Main.Scenes.LEVELMENU);
			main.switchScene(Main.Scenes.INGAME);
			System.out.println("songs/song"+ (2+(actualLevel*3)) +".xml");
			main.getGamePlayer().start("songs/song"+ (3+(actualLevel*3)) +".xml");
		});
	}
}
