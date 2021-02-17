package presentation.menu.start;

import application.Main;
import buisness.gamelogic.SoundPlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.jfugue.player.Player;
import presentation.ViewController;
import presentation.menu.info.InfoController;
import presentation.menu.worlds.WorldSelectionController;

import javax.sound.midi.*;

public class StartscreenController extends ViewController {
	private Button startButton;
	private Button infoButton;
	private StartscreenView view;
	private Main main;

	public StartscreenController(Main main) {
		this.main = main;
		view = new StartscreenView();

		startButton = view.startButton;
		infoButton = view.infoButton;

		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {
		startButton.setText("Start");
		infoButton.setText("Info");
		
		startButton.addEventHandler(ActionEvent.ACTION, event -> {



			main.addScene(new WorldSelectionController(main), application.Main.Scenes.WORLDMENU);
			main.setLastScene(application.Main.Scenes.STARTSCENE);
			main.switchScene(application.Main.Scenes.WORLDMENU);

//			Player testplayer = new Player();
//			testplayer.play("C");
//			SoundPlayer sound = new SoundPlayer();
//			new Thread(() ->
//			sound.toPlay("6C",800)
//			).start();
		});

		infoButton.addEventHandler(ActionEvent.ACTION, event -> {
			main.addScene(new InfoController(main), application.Main.Scenes.INFO);
			main.setLastScene(application.Main.Scenes.STARTSCENE);
			main.switchScene(application.Main.Scenes.INFO);
		});
	}
}
