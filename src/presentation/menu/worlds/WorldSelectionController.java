package presentation.menu.worlds;

import application.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.backbutton.CloseController;
import presentation.menu.levels.LevelSelectionController;

import java.io.FileInputStream;

public class WorldSelectionController extends ViewController {
	private Button world1;
	private Button world2;
	private Button world3;
	private Label title;
	private BackButtonController back;
	private CloseController close;
	private WorldSelectionView view;
	private double sizemulti;

	
	private Main main;
	
	public WorldSelectionController(Main main) {
		view = new WorldSelectionView();
		this.main = main;
		back = new BackButtonController(main);
		close = new CloseController(main);

		sizemulti = main.getSizeMultiplyer();

		world1 = view.buttonToWorld1;
		world2 = view.buttonToWorld2;
		world3 = view.buttonToWorld3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {

		title.setFont(Font.font("Arial", FontWeight.BOLD,30* sizemulti));
		title.setText("Welten");
		close.getRootView().setPadding(new Insets(1,1,1,900* sizemulti));
		HBox top = new HBox();
		HBox topright = new HBox();
		topright.getChildren().add(close.getRootView());
		topright.setAlignment(Pos.CENTER_RIGHT);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(10);
		top.getChildren().addAll(back.getRootView(), title,topright);
		view.setTop(top);

		try {
			ImageView world1View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt1"))));
			world1View.setFitHeight(220* sizemulti);
			world1View.setFitWidth(220* sizemulti);
			world1.setGraphic(world1View);

			ImageView world2View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt2"))));
			world2View.setFitHeight(220* sizemulti);
			world2View.setFitWidth(220* sizemulti);
			world2.setGraphic(world2View);

			ImageView world3View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt3"))));
			world3View.setFitHeight(220* sizemulti);
			world3View.setFitWidth(220* sizemulti);
			world3.setGraphic(world3View);
		} catch (Exception e) {
			e.printStackTrace();
		}
		world1.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(1);
			if(!main.sceneExists(Main.Scenes.LEVELMENU))
				main.addScene(new LevelSelectionController(main), Main.Scenes.LEVELMENU);
			else
				main.resetScene(new LevelSelectionController(main),Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

		world2.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(2);
			if(!main.sceneExists(Main.Scenes.LEVELMENU))
				main.addScene(new LevelSelectionController(main), Main.Scenes.LEVELMENU);
			else
				main.resetScene(new LevelSelectionController(main),Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

		world3.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(3);
			if(!main.sceneExists(Main.Scenes.LEVELMENU))
				main.addScene(new LevelSelectionController(main), Main.Scenes.LEVELMENU);
			else
				main.resetScene(new LevelSelectionController(main),Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

	}
}
