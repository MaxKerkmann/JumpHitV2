package application;
	
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import buisness.gamelogic.GamePlayer;
import buisness.gamelogic.SoundPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfugue.player.Player;
import presentation.ViewController;
import presentation.menu.start.StartscreenController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.swing.text.View;


public class Main extends Application {

	public enum Scenes {
		INFO, INGAME, LEVELMENU, STARTSCENE, WORLDMENU

	}
	
	private Stage primaryStage;
	private Map<Scenes, Pane> scenes;
	private Scenes lastScene;
	private GamePlayer gamePlayer = new GamePlayer();
	private AnimationTimer timer;
	private int selectedWorld;
	private int size;
	private int currentLevel;
	
	
	public void init() {

		scenes = new HashMap<>();

		ViewController controller;
		controller = new StartscreenController(this);
		scenes.put(Scenes.STARTSCENE, controller.getRootView());

		BufferedReader reader = null;
		try {
			String currentline;
			int line =0;
			reader = new BufferedReader(
					new FileReader("configV2.txt"));
			while ((currentline = reader.readLine()) != null) {
				if(line == 0)
					size = Integer.parseInt(currentline.split(":")[1]);
				else
					currentLevel = Integer.parseInt(currentline.split(":")[1]);
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {

		}
	}
	
	
	@Override
	public void start(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			primaryStage.initStyle(StageStyle.UNDECORATED);
			BorderPane root = new BorderPane();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			
			switchScene(Scenes.STARTSCENE);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


	public void stop(){
		if(gamePlayer.getTimer() != null)
			gamePlayer.getTimer().stop();
		Platform.exit();
	}
	
	public void addScene(ViewController newSceneController, Scenes scene) {
		if(!sceneExists(scene)){
			// wenn Szene noch nicht in Map enthalten, fuege Szene hinzu
			scenes.put(scene, newSceneController.getRootView());
		}
		// wenn bereits vorhanden, tue nichts
		
	}

	public void resetScene(ViewController controller, Scenes scene){
		scenes.remove(scene);
		scenes.put(scene,controller.getRootView());
	}
	
	public boolean sceneExists(Scenes scene){
		if (!scenes.containsKey(scene)) {
			return false;
		}
		return true;
	}
	
	//Switched die Scene zur mitgebenen View
	public void switchScene(Scenes toScene) {
		Scene scene = primaryStage.getScene();

		if (scenes.containsKey(toScene)) {
			scene.setRoot(scenes.get(toScene));
		}
	}

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}

	public void setGamePlayer(GamePlayer player){
		gamePlayer = player;
	}

	public void setLastScene(Scenes lastScene) {
		this.lastScene = lastScene;
	}

	public Scenes getLastScene() {
		return lastScene;
	}

	public void setTimer(AnimationTimer timer){
		this.timer = timer;
	}

	public int getSelectedWorld(){
		return selectedWorld;
	}

	public void setSelectedWorld(int newWorld){
		selectedWorld = newWorld;
	}

	public int getSize(){
		return size;
	}

	public void setSize(int size){
		this.size = size;
	}
	public int getCurrentLevel(){
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel){
		this.currentLevel = currentLevel;
	}
}