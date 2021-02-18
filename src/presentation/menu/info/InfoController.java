package presentation.menu.info;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.backbutton.CloseController;

public class InfoController extends ViewController {

    private Label title;
    private InfoView view;
    private BackButtonController back;
    private CloseController close;

    private Main main;

    public InfoController(Main main){
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
        close.getRootView().setPadding(new Insets(1,1,1,1000* main.getSizeMultiplyer()));
        HBox top = new HBox();
        HBox topright = new HBox();
        topright.getChildren().add(close.getRootView());
        topright.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().addAll(back.getRootView(), title,topright);
        top.setSpacing(10);
        top.setAlignment(Pos.CENTER);
        view.setTop(top);
        title.setText("Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD,30* main.getSizeMultiplyer()));
        title.setTextFill(Color.WHITE);
    }
}
