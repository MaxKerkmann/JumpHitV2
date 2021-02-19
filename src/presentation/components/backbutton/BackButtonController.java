package presentation.components.backbutton;

import application.Main;
import application.Main.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.ViewController;

import java.io.FileInputStream;


public class BackButtonController extends ViewController {

	private Button back;
	private BackButtonView view;
	private Main main;
	private double sizemulti;

	private final int buttonSize = 40;

	public BackButtonController(Main main) {
		view = new BackButtonView();
		this.main = main;
		back = view.back;
		sizemulti = main.getSizeMultiplyer();

		rootView = view;

		initialize();
	}

	@Override
	public void initialize() {

		try {
			ImageView backView = new ImageView(
					new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "back"))));
			backView.setFitHeight(buttonSize*sizemulti);
			backView.setFitWidth(buttonSize*sizemulti);
			back.setGraphic(backView);
		}catch (Exception e){

		}

		back.addEventHandler(ActionEvent.ACTION, event -> {

			if (main.getLastScene() == Scenes.WORLDMENU) {
				main.setLastScene(application.Main.Scenes.STARTSCENE);
				main.switchScene(application.Main.Scenes.WORLDMENU);
			} else {
				main.switchScene(application.Main.Scenes.STARTSCENE);
			}
		});
	}

	public void invertColor(){
		try {
			ImageView backview = new ImageView(new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "back_white"))));
			backview.setFitHeight(buttonSize*sizemulti);
			backview.setFitWidth(buttonSize*sizemulti);
			back.setGraphic(backview);
		}catch (Exception e){

		}
	}
}
