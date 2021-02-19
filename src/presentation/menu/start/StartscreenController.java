package presentation.menu.start;

import application.Main;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import presentation.ViewController;
import presentation.components.close.CloseController;
import presentation.menu.info.InfoController;
import presentation.menu.resize.ResizeController;
import presentation.menu.worlds.WorldSelectionController;

import java.io.FileInputStream;


public class StartscreenController extends ViewController {
	private Button startButton;
	private Button infoButton;
	private Button resizeButton;
	private StartscreenView view;
	private Main main;
	private CloseController close;
	private double sizemulti;

	private final int baseButtonFontsize = 38;
	private final int baseIconSize = 40;
	private final int baseButtonHeigth = 100;
	private final int baseButtonWidth = 220;


	public StartscreenController(Main main) {
		this.main = main;
		view = new StartscreenView();
		close = new CloseController(main);
		sizemulti = main.getSizeMultiplier();

		startButton = view.startButton;
		infoButton = view.infoButton;
		resizeButton = view.resizeButton;
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {

		HBox top = new HBox();

		top.getChildren().addAll(resizeButton,close.getRootView());
		top.setAlignment(Pos.CENTER_RIGHT);
		view.setTop(top);
		startButton.setText("Start");
		startButton.setFont(new Font("Arial",baseButtonFontsize*sizemulti));
		startButton.setPrefHeight(baseButtonHeigth*sizemulti);
		startButton.setPrefWidth(baseButtonWidth*sizemulti);

		infoButton.setText("Info");
		infoButton.setFont(new Font("Arial",baseButtonFontsize*sizemulti));
		infoButton.setPrefHeight(baseButtonHeigth*sizemulti);
		infoButton.setPrefWidth(baseButtonWidth*sizemulti);

		try {
			ImageView resizeButtonView = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "options"))));
			resizeButtonView.setFitHeight(baseIconSize);
			resizeButtonView.setFitWidth(baseIconSize);
			resizeButton.setGraphic(resizeButtonView);
		}catch (Exception e){

		}

		resizeButton.addEventHandler(ActionEvent.ACTION, event -> {
			ResizeController resizeController = new ResizeController(main);
			resizeController.showStage();
		});
		
		startButton.addEventHandler(ActionEvent.ACTION, event -> {
			main.addScene(new WorldSelectionController(main), application.Main.Scenes.WORLDMENU);
			main.setLastScene(application.Main.Scenes.STARTSCENE);
			main.switchScene(application.Main.Scenes.WORLDMENU);
		});

		infoButton.addEventHandler(ActionEvent.ACTION, event -> {
			main.addScene(new InfoController(main), application.Main.Scenes.INFO);
			main.setLastScene(application.Main.Scenes.STARTSCENE);
			main.switchScene(application.Main.Scenes.INFO);
		});

		Image img = null;
		try {
			img = new Image(new FileInputStream("ressources/menus/StartMenu/startseite.png"));
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
}
