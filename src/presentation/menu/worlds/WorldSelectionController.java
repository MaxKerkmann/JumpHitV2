package presentation.menu.worlds;

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

	private final int basetileFontsize = 50;
	private final int baseButtonSize = 220;
	private final int baseClosePadding = 900;

	private Main main;
	
	public WorldSelectionController(Main main) {
		view = new WorldSelectionView();
		this.main = main;
		back = new BackButtonController(main);
		close = new CloseController(main);

		sizemulti = main.getSizeMultiplier();

		world1 = view.buttonToWorld1;
		world2 = view.buttonToWorld2;
		world3 = view.buttonToWorld3;
		title = view.title;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {

		title.setFont(Font.font("Arial", FontWeight.BOLD,basetileFontsize* sizemulti));
		title.setText("Welten");
		close.getRootView().setPadding(new Insets(1,1,1,baseClosePadding* sizemulti));
		HBox top = new HBox();
		HBox topright = new HBox();
		topright.getChildren().add(close.getRootView());
		topright.setAlignment(Pos.CENTER_RIGHT);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(10);
		top.getChildren().addAll(back.getRootView(), title,topright);
		view.setTop(top);

		world1.setGraphic(getImageView( "weltenbutton_welt1"));
		world2.setGraphic(getImageView( "weltenbutton_welt2"));
		world3.setGraphic(getImageView( "weltenbutton_welt3"));

		world1.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(1);
			sceneSwitcher();
		});

		world2.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(2);
			sceneSwitcher();
		});

		world3.addEventHandler(ActionEvent.ACTION, event -> {
			main.setSelectedWorld(3);
			sceneSwitcher();
		});

		Image img = null;
		try {
			img = new Image(new FileInputStream("ressources/menus/WorldMenu/weltenuebersicht_wiese.png"));
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

	private ImageView getImageView(String imageName){
		ImageView worldView = null;
		try {
			worldView = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/WorldMenu", imageName))));
			worldView.setFitHeight(baseButtonSize* sizemulti);
			worldView.setFitWidth(baseButtonSize* sizemulti);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return worldView;
	}

	private void sceneSwitcher(){
		if(!main.sceneExists(Main.Scenes.LEVELMENU))
			main.addScene(new LevelSelectionController(main), Main.Scenes.LEVELMENU);
		else
			main.resetScene(new LevelSelectionController(main),Main.Scenes.LEVELMENU);
		main.setLastScene(Main.Scenes.WORLDMENU);
		main.switchScene(Main.Scenes.LEVELMENU);
	}
}
