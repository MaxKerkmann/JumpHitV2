package presentation.menu.info;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.close.CloseController;

import java.io.FileInputStream;

public class InfoController extends ViewController {

    private Label title;
    private InfoView view;
    private BackButtonController back;
    private CloseController close;

    private final int baseLeftPadding = 900;
    private final int baseFontsize = 30;

    private Main main;

    public InfoController(Main main) {
        this.main = main;
        view = new InfoView();
        back = new BackButtonController(main);
        close = new CloseController(main);

        title = view.title;

        rootView = view;
        initialize();
    }

    @Override
    public void initialize() {
        close.getRootView().setPadding(new Insets(1, 1, 1, baseLeftPadding * main.getSizeMultiplyer()));
        HBox top = new HBox();
        HBox topright = new HBox();
        topright.getChildren().add(close.getRootView());
        topright.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().addAll(back.getRootView(), title, topright);
        top.setSpacing(10);
        top.setAlignment(Pos.CENTER);
        view.setTop(top);
        title.setText("Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD, baseFontsize * main.getSizeMultiplyer()));
        title.setTextFill(Color.WHITE);

        Image img = null;
        try {
            img = new Image(new FileInputStream("ressources/menus/Info/info.png"));
        } catch (Exception e) {
            e.printStackTrace();
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
}
