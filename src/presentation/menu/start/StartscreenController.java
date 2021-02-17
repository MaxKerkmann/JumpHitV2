package presentation.menu.start;

import application.Main;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import presentation.ViewController;
import presentation.components.backbutton.CloseController;
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
	private ResizeController resize;

	public StartscreenController(Main main) {
		this.main = main;
		view = new StartscreenView();
		close = new CloseController(main);
		resize = new ResizeController(main);
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
		infoButton.setText("Info");
		try {
			ImageView resizeButtonView = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "options"))));
			resizeButtonView.setFitHeight(40);
			resizeButtonView.setFitWidth(40);
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
	}
}
