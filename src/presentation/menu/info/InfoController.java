package presentation.menu.info;

import application.Main;
import javafx.scene.control.Label;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;

public class InfoController extends ViewController {

    private Label title;
    private InfoView view;

    private Main main;

    public InfoController(Main main){
        this.main = main;
        view = new InfoView();

        title = view.title;

        rootView = view;
        initialize();
    }

    @Override
    public void initialize() {

        title.setText("Info");
    }
}
