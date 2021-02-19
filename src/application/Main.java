package application;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import buisness.gamelogic.GamePlayer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import presentation.ViewController;
import presentation.menu.start.StartscreenController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {

    public enum Scenes {
        INFO, INGAME, LEVELMENU, STARTSCENE, WORLDMENU

    }

    private Stage primaryStage;
    private Map<Scenes, Pane> scenes;
    private Scenes lastScene;
    private GamePlayer gamePlayer = new GamePlayer(this);
    private int selectedWorld;
    private int size;
    private int currentLevel;
    private double sizemulti;
    private final int baseWidth = 1280;
    private final int baseHeight = 720;


    public void init() {

        scenes = new HashMap<>();

        ViewController controller;
        controller = new StartscreenController(this);
        scenes.put(Scenes.STARTSCENE, controller.getRootView());

        BufferedReader reader = null;
        try {
            String currentline;
            int line = 0;
            reader = new BufferedReader(
                    new FileReader("configV2.txt"));
            while ((currentline = reader.readLine()) != null) {
                if (line == 0)
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
        sizemulti = getSizeMultiplyer();
        gamePlayer = new GamePlayer(this);
    }


    @Override
    public void start(Stage primaryStage) {

        try {
            this.primaryStage = primaryStage;
            BorderPane root = new BorderPane();

            Scene scene = new Scene(root, baseWidth * sizemulti, baseHeight * sizemulti);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);

            switchScene(Scenes.STARTSCENE);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void stop() {
        if (gamePlayer.getTimer() != null)
            gamePlayer.getTimer().stop();

        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> lines = new ArrayList<>();
        try {
            String currentline;

            reader = new BufferedReader(
                    new FileReader("configV2.txt"));
            while ((currentline = reader.readLine()) != null) {
                lines.add(currentline);
            }
            String edit = lines.get(1);
            edit = edit.split(":")[0] + ":" + currentLevel;
            lines.remove(1);
            writer = new BufferedWriter(new FileWriter("configV2.txt"));
            for (String newline : lines) {
                writer.write(newline);
                writer.newLine();
            }
            writer.write(edit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            writer.close();
            reader.close();
        } catch (IOException e) {

        }

    }

    public void addScene(ViewController newSceneController, Scenes scene) {
        if (!sceneExists(scene)) {
            // wenn Szene noch nicht in Map enthalten, fuege Szene hinzu
            scenes.put(scene, newSceneController.getRootView());
        }
        // wenn bereits vorhanden, tue nichts

    }

    public void resetScene(ViewController controller, Scenes scene) {
        scenes.remove(scene);
        scenes.put(scene, controller.getRootView());
    }

    public boolean sceneExists(Scenes scene) {
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

    public void setLastScene(Scenes lastScene) {
        this.lastScene = lastScene;
    }

    public Scenes getLastScene() {
        return lastScene;
    }

    public int getSelectedWorld() {
        return selectedWorld;
    }

    public void setSelectedWorld(int newWorld) {
        selectedWorld = newWorld;
    }

    public double getSizeMultiplyer() {
        switch (size) {
            case 1:
                return (2.0 / 3.0);
            case 3:
                return (3.0 / 2.0);
            default:
                return 1;
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
