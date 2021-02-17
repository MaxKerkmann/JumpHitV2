package presentation.menu.start;

import application.Main;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import presentation.ViewController;
import presentation.components.backbutton.CloseController;
import presentation.menu.info.InfoController;
import presentation.menu.worlds.WorldSelectionController;



public class StartscreenController extends ViewController {
	private Button startButton;
	private Button infoButton;
	private StartscreenView view;
	private Main main;
	private CloseController close;

	public StartscreenController(Main main) {
		this.main = main;
		view = new StartscreenView();
		close = new CloseController(main);

		startButton = view.startButton;
		infoButton = view.infoButton;

		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {

		HBox top = new HBox();

		top.getChildren().add(close.getRootView());
		top.setAlignment(Pos.CENTER_RIGHT);
		view.setTop(top);
		startButton.setText("Start");
		infoButton.setText("Info");
		
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
