package presentation.components.backbutton;

import application.Main;
import application.Main.Scenes;
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

    public CloseController(Main main) {
        view = new CloseView();
        this.main = main;
        close = view.close;

        rootView = view;

        initialize();
    }

    @Override
    public void initialize() {

        close.getStyleClass().addAll("button-Style");
        try {
            ImageView closeview = new ImageView(
                    new Image(new FileInputStream(String.format("%s/%s.png", "ressources/menus/icons", "close"))));
            closeview.setFitHeight(40);
            closeview.setFitWidth(40);
            close.setGraphic(closeview);
        }catch (Exception e){

        }

        close.addEventHandler(ActionEvent.ACTION, event -> {
           main.getPrimaryStage().close();
        });
    }
}
