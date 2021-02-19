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
import javafx.scene.text.Text;
import presentation.ViewController;
import presentation.components.backbutton.BackButtonController;
import presentation.components.close.CloseController;

import java.io.FileInputStream;

public class InfoController extends ViewController {

    private Label title;
    private InfoView view;
    private BackButtonController back;
    private CloseController close;
    private Text text;
    private VBox center;
    private double sizemulti;

    private final int baseLeftPadding = 900;
    private final int baseHeadFontsize = 30;
    private final int baseTextSize = 20;
    private final int spacing = 10;
    private final int baseCenterPadding = 340;

    private Main main;

    public InfoController(Main main) {
        this.main = main;
        view = new InfoView();
        back = new BackButtonController(main);
        close = new CloseController(main);
        text = view.text;
        sizemulti = main.getSizeMultiplier();
        title = view.title;
        center = view.center;

        rootView = view;
        initialize();
    }

    @Override
    public void initialize() {
        close.getRootView().setPadding(new Insets(1, 1, 1, baseLeftPadding * sizemulti));
        close.invertColor();
        back.invertColor();

        HBox top = new HBox();
        HBox topright = new HBox();
        topright.getChildren().add(close.getRootView());
        topright.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().addAll(back.getRootView(), title, topright);
        top.setSpacing(spacing);
        top.setAlignment(Pos.CENTER);
        view.setTop(top);

        center.setPadding(new Insets(1, 1, 1, baseCenterPadding * sizemulti));

        title.setText("Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD, baseHeadFontsize * sizemulti));
        title.setTextFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, baseTextSize * sizemulti));
        text.setFill(Color.WHITE);
        text.setText(
                "Herzlich Willkommen!\n\n" +
                        "Schön, dass du hierher gefunden hast.'Jump Hit' ist ein Musikspiel,\n" +
                        "bei dem Du Peter, einem Frosch, hilfst, alle Noten zu sammeln.\n" +
                        "Um alle Noten zu fangen, hüpft Peter auf verschiedene Platformen.\n" +
                        "Mit jeder gefangenen Note erklingt der jeweilige Ton und somit entsteht\n" +
                        "eine Melodie. Ziel des Spiels ist es, dass Du Peter zu allen Noten führst,\n" +
                        "damit das ganze Lied erklingt.\n\n" +
                        "Mithilfe von Mausklicks kannst du Peter steuern. Es müssen alle Noten gefangen werden\n" +
                        "Wenn Du eine Note nicht erwischst oder falsch klickst, wird das Spiel beendet\n" +
                        "und Du musst das Level von vorne starten. Keine Panik, wenn Dir anfangs die Levels\n" +
                        "zu schwer erscheinen. Du hast unendlich viele Versuche - Übung macht den Meister.\n" +
                        "Wenn Du möchtest, kannst Du zu Beginn die Spielgröße anpassen. Diese lässt sich über\n" +
                        "den Einstellungsbutton auf der Startansicht verändern.\n\n" +
                        "Über den \"Start\"-Button gelangst Du in die Welten-Ansicht.  Wenn Du die jeweilige\n" +
                        "Welt auswählst, gelangst Du in die Level-Ansicht dieser Welt. Die verschiedenen\n" +
                        "Levels kannst Du dir nach und nach freischalten.\n\n" +
                        "Viel Spaß! Peter hüpft nicht nur in seiner bekannten Heimat, der Seerosenlandschaft,\n" +
                        "sondern erkundet auch die Weite des Himmels und die Unendlichkeit des Weltraums.\n" +
                        "Sei gespannt und führe ihn mit gutem Taktgefühl ans Ziel.");
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
