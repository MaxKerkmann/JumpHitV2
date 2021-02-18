package presentation.menu.resize;

import application.Main;
import buisness.gamelogic.GamePlayer;
import buisness.gamelogic.finishedMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.ViewController;
import presentation.uicomponents.FrogSprite;
import presentation.uicomponents.PlatformSprite;

import java.awt.*;
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
    private Label currentSize;
    private Label title;
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
        currentSize = view.currentSize;
        title = view.title;

        sizemulti = main.getSizeMultiplyer();

        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {

        small.getStyleClass().addAll("button-Style-start");
        medium.getStyleClass().addAll("button-Style-start");
        big.getStyleClass().addAll("button-Style-start");
        accept.getStyleClass().addAll("button-Style-start");
        rollback.getStyleClass().addAll("button-Style-start");

        small.setText("582x480");
        medium.setText("1280x720");
        big.setText("1920x1080");

        title.setText("Einstellungen");
        title.setFont(Font.font("Arial", FontWeight.BOLD,30));

        switch (main.getSize()){
            case 1:
                currentSize.setText("582x480");
                break;
            case 2:
                currentSize.setText("1280x720");
                break;
            case 3:
                currentSize.setText("1920x1080");
                break;
        }
        currentSize.setFont(Font.font("Arial", FontWeight.BOLD,15));

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
                        String edit = lines.get(0);
                        edit = edit.split(":")[0] + ":" + size;
                        lines.remove(0);
                        writer = new BufferedWriter(new FileWriter("configV2.txt"));
                        writer.write(edit);
                        writer.newLine();
                        for(String newline : lines){
                            writer.write(newline);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        writer.close();
                        reader.close();
                    } catch (IOException e) {

                    }
                }
            }.start();
            stage.close();
            if(size != main.getSize()) {
                main.setSize(size);
                main.getPrimaryStage().close();
                main.init();
                main.start(new Stage());
            }
        });

        small.addEventHandler(ActionEvent.ACTION,event -> {
            size = 1;
            currentSize.setText("582x480");
        });
        medium.addEventHandler(ActionEvent.ACTION,event -> {
            size = 2;
            currentSize.setText("1280x720");
        });
        big.addEventHandler(ActionEvent.ACTION,event -> {
            size = 3;
            currentSize.setText("1920x1080");
        });

        Image img = null;
        try {
            img = new Image(new FileInputStream("ressources/menus/StartMenu/resize.gif"));
        }catch (Exception e){

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

    public void showStage() {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        Scene newScene = new Scene(this.getRootView(),280,300);
        newScene.getStylesheets().add(getClass().getResource("../../../application/application.css").toExternalForm());
        stage.setScene(newScene);
        stage.show();
    }


}
