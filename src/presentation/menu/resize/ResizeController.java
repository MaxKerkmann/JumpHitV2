package presentation.menu.resize;

import application.Main;
import buisness.gamelogic.GamePlayer;
import buisness.gamelogic.finishedMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.ViewController;
import presentation.uicomponents.FrogSprite;
import presentation.uicomponents.PlatformSprite;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ResizeController extends ViewController {

    private Button small;
    private Button medium;
    private Button big;
    private Button accept;
    private Button rollback;
    private Main main;
    private Stage stage;
    private ResizeView view;
    private int size;
    private double sizemulti;

    public ResizeController(Main main){
        this.main = main;
        view = new ResizeView();
        small = view.small;
        medium =view.medium;
        big = view.big;
        accept = view.accept;
        rollback = view.rollback;

        sizemulti = main.getSizeMultiplyer();

        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {
        small.setText("582x480");
        medium.setText("1280x720");
        big.setText("1920x1080");

        accept.setText("Annehmen");
        rollback.setText("Abbrechen");

        rollback.addEventHandler(ActionEvent.ACTION,event -> {
            stage.close();
        });

        accept.addEventHandler(ActionEvent.ACTION,event -> {
            new Thread() {
                BufferedReader reader = null;
                BufferedWriter writer = null;
                ArrayList<String> lines = new ArrayList<>();
                public void run() {
                    try {
                        String currentline;

                        reader = new BufferedReader(
                                new FileReader("configV2.txt"));
                        while ((currentline = reader.readLine()) != null) {
                            lines.add(currentline);
                        }
                        //lines.
                        writer = new BufferedWriter(new FileWriter("configV2.txt"));
                        for(String newline : lines){
                            writer.write(newline);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        reader.close();
                    } catch (IOException e) {

                    }
                }
            }.start();
            stage.close();
            main.getPrimaryStage().close();
            main.init();
            main.start(new Stage());
        });

        small.addEventHandler(ActionEvent.ACTION,event -> {
            size = 1;
        });
        medium.addEventHandler(ActionEvent.ACTION,event -> {
            size = 2;
        });
        big.addEventHandler(ActionEvent.ACTION,event -> {
            size = 3;
        });

    }

    public void showStage() {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        Scene newScene = new Scene(this.getRootView(),430*sizemulti,500*sizemulti);
        stage.setScene(newScene);
        stage.show();
    }


}
