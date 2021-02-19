package presentation.menu.levels;

import application.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.close.CloseController;
import presentation.ingame.InGameController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LevelSelectionController extends ViewController {
	
	private Button level1;
	private Button level2;
	private Button level3;
	private Label title;
	private BackButtonController back;
	private CloseController close;
	private LevelSelectionView view;
	private double sizemulti;

	private final int baseFontsizeHead = 30;
	private final int baseFontsizeButton = 38;
	private final int baseClosePadding = 900;
	private final int baseButtonHeigth = 100;
	private final int baseButtonWidth = 220;

	
	private Main main;
	
	public LevelSelectionController(Main main) {
		this.main = main;
		view = new LevelSelectionView();
		back = new BackButtonController(main);
		close = new CloseController(main);

		sizemulti = main.getSizeMultiplyer();

		level1 = view.level1;
		level2 = view.level2;
		level3 = view.level3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {




		title.setFont(Font.font("Arial", FontWeight.BOLD,baseFontsizeHead*sizemulti));
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


		close.getRootView().setPadding(new Insets(1,1,1,baseClosePadding*sizemulti));
		HBox top = new HBox();
		HBox topRight = new HBox();
		topRight.getChildren().add(close.getRootView());
		topRight.setAlignment(Pos.CENTER_RIGHT);
		top.getChildren().addAll(back.getRootView(), title,topRight);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(10);
		view.setTop(top);


		int levelMulti = (main.getSelectedWorld()-1);

		level1.addEventHandler(ActionEvent.ACTION, event -> {
			if(1 + (levelMulti * 3) <= main.getCurrentLevel()) {
				sceneSwitcher(1);
			}
		});

		level2.addEventHandler(ActionEvent.ACTION, event -> {
			if(2 + (levelMulti * 3) <= main.getCurrentLevel()) {
				sceneSwitcher(2);
			}
		});

		level3.addEventHandler(ActionEvent.ACTION, event -> {
			if(3 + (levelMulti * 3) <= main.getCurrentLevel()) {
				sceneSwitcher(3);
			}
		});

		if(1 + (levelMulti * 3) > main.getCurrentLevel()) {
			level1.setGraphic(setView("lock1"));
			level1.setText("");
		}else{
			level1.setText("Level 1");
			level1.setFont(new Font("Arial",baseFontsizeButton*sizemulti));
			level1.setPrefHeight(baseButtonHeigth*sizemulti);
			level1.setPrefWidth(baseButtonWidth*sizemulti);
		}

		if(2 + (levelMulti * 3) > main.getCurrentLevel()) {
			level2.setGraphic(setView("lock2"));
			level2.setText("");
		}else{
			level2.setText("Level 2");
			level2.setFont(new Font("Arial",baseFontsizeButton*sizemulti));
			level2.setPrefHeight(baseButtonHeigth*sizemulti);
			level2.setPrefWidth(baseButtonWidth*sizemulti);
		}

		if(3 + (levelMulti * 3) > main.getCurrentLevel()) {
			level3.setGraphic(setView("lock3"));
			level3.setText("");
		}else{
			level3.setText("Level 3");
			level3.setFont(new Font("Arial",baseFontsizeButton*sizemulti));
			level3.setPrefHeight(baseButtonHeigth*sizemulti);
			level3.setPrefWidth(baseButtonWidth*sizemulti);
		}

		Image img = null;
		try {
			if(main.getSelectedWorld() ==3)
				img = new Image(new FileInputStream("ressources/menus/LevelMenu/weltenuebersicht_sternenhimmel.png"));
			else
				img = new Image(new FileInputStream("ressources/menus/LevelMenu/weltenuebersicht_wiese.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		BackgroundImage bgImage = new BackgroundImage(
				img,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false)
		);
		view.setBackground(new Background(bgImage));
	}

	private ImageView setView(String imageName){
		ImageView levelview = null;
		try {
			levelview = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/LevelMenu", imageName))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		levelview.setFitHeight(baseButtonHeigth* sizemulti);
		levelview.setFitWidth(baseButtonWidth* sizemulti);
		return levelview;
	}

	private void sceneSwitcher(int worldNumber){
		int levelMulti = (main.getSelectedWorld()-1);
		if (!main.sceneExists(Main.Scenes.INGAME))
			main.addScene(new InGameController(main), Main.Scenes.INGAME);
		main.setLastScene(Main.Scenes.LEVELMENU);
		main.switchScene(Main.Scenes.INGAME);
		main.getGamePlayer().start("songs/song" + (worldNumber + (levelMulti * 3)) + ".xml");
	}
}
