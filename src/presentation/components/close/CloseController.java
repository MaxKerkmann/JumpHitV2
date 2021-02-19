package presentation.components.close;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.ViewController;

import java.io.FileInputStream;


public class CloseController extends ViewController {

    private Button close;
    private CloseView view;
    private Main main;
    private double sizemulti;

    private final int buttonSize = 40;

    public CloseController(Main main) {
        view = new CloseView();
        this.main = main;
        close = view.close;

        sizemulti = main.getSizeMultiplyer();
        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {

        close.getStyleClass().addAll("button-Style");
        try {
            ImageView closeview = new ImageView(new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "close"))));
            closeview.setFitHeight(buttonSize*sizemulti);
            closeview.setFitWidth(buttonSize*sizemulti);
            close.setGraphic(closeview);
        }catch (Exception e){

        }

        close.addEventHandler(ActionEvent.ACTION, event -> {
            main.getPrimaryStage().close();
        });
    }

    public void invertColor(){
        try {
            ImageView closeview = new ImageView(new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "close_white"))));
            closeview.setFitHeight(buttonSize*sizemulti);
            closeview.setFitWidth(buttonSize*sizemulti);
            close.setGraphic(closeview);
        }catch (Exception e){

        }
    }
}
