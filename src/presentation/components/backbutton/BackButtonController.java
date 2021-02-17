package presentation.components.backbutton;

import application.Main;
import application.Main.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import presentation.ViewController;


public class BackButtonController extends ViewController {

	private Button back;
	private BackButtonView view;
	private Main main;

	public BackButtonController(Main main) {
		view = new BackButtonView();
		this.main = main;
		back = view.back;

		rootView = view;

		initialize();
	}

	@Override
	public void initialize() {

		back.addEventHandler(ActionEvent.ACTION, event -> {

			if (main.getLastScene() == Scenes.LEVELMENU) {
				main.setLastScene(application.Main.Scenes.STARTSCENE);
				main.switchScene(application.Main.Scenes.WORLDMENU);
			} else {
				main.setLastScene(application.Main.Scenes.WORLDMENU);
				main.switchScene(application.Main.Scenes.STARTSCENE);
			}
		});
	}
}
