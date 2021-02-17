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

	
	private Main main;
	
	public WorldSelectionController(Main main) {
		view = new WorldSelectionView();
		this.main = main;
		back = new BackButtonController(main);
		close = new CloseController(main);
		
		world1 = view.buttonToWorld1;
		world2 = view.buttonToWorld2;
		world3 = view.buttonToWorld3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {
		
		title.setText("Welten");
		close.getRootView().setPadding(new Insets(1,1,1,1000* main.getSizeMultiplyer()));
		HBox top = new HBox();
		HBox topright = new HBox();
		topright.getChildren().add(close.getRootView());
		topright.setAlignment(Pos.CENTER_RIGHT);
		top.getChildren().addAll(back.getRootView(), title,topright);
		view.setTop(top);

		try {
			ImageView world1View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt1"))));
			world1View.setFitHeight(220);
			world1View.setFitWidth(220);
			world1.setGraphic(world1View);

			ImageView world2View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt2"))));
			world2View.setFitHeight(220);
			world2View.setFitWidth(220);
			world2.setGraphic(world2View);

			ImageView world3View = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/StartMenu", "weltenbutton_welt3"))));
			world3View.setFitHeight(220);
			world3View.setFitWidth(220);
			world3.setGraphic(world3View);
		} catch (Exception e) {
			e.printStackTrace();
		}
		world1.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(1);
			main.addScene(new LevelSelectionController(main,1), Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

		world2.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(2);
			main.addScene(new LevelSelectionController(main,2), Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

		world3.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(3);
			main.addScene(new LevelSelectionController(main,3), Main.Scenes.LEVELMENU);
			main.setLastScene(Main.Scenes.WORLDMENU);
			main.switchScene(Main.Scenes.LEVELMENU);
		});

	}
}
