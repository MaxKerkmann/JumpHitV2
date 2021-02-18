package buisness.gamelogic;


import application.Main;
import buisness.gameElements.Frog;
import buisness.gameElements.Platform;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

//Lädt und erstellt die einzelnen GameObjekte
public class GameWorldGenerator {

    private String soundFile;
    private File file;
    private Builder builder;
    private nu.xom.Document doc;
    private Element root;
    private nu.xom.Elements platforms;
    private Main main;
    private double sizemulti;

    private final int frogStartpointX = 610;
    private final int frogStartpointY = 630;
    private final int firstPlatformColum = 400;
    private final int secondPlatformColum = 550;
    private final int thirdPlatformColum = 750;
    private final int fourthtPlatformColum = 900;


    public GameWorldGenerator(Main main){
        this.main = main;
        sizemulti = main.getSizeMultiplyer();
    }
    //Setzt zu ladene SoundFile
    public void setCurrentSoundFile(String soundFile){
        this.soundFile = soundFile;
        builder = new Builder();
        file = new File(soundFile);
        try {
            doc = builder.build(file);
        }catch(IOException | ParsingException e){
            e.printStackTrace();
        }

        root = doc.getRootElement();
        platforms = root.getChildElements("platform");
    }

    public Frog createFrog(){
        Frog frog = new Frog(main);
        frog.setY(frogStartpointY*sizemulti);
        frog.setX(frogStartpointX*sizemulti);
        return frog;
    }

    //Erstellt eine neue Platform nach mitgebenen Index
    public Platform createPlatformByIndex(int index){

        if(index < platforms.size()) {
            Element platformXML = platforms.get(index);
            String newNote = platformXML.getFirstChildElement("note").getValue();

            int newPosition = DatatypeConverter.parseInt(platformXML.getFirstChildElement("position").getValue());
            int newPassTime = DatatypeConverter.parseInt(platformXML.getFirstChildElement("passTime").getValue());
            int newPauseTime = DatatypeConverter.parseInt(platformXML.getFirstChildElement("pauseTime").getValue());
            int newDuration = DatatypeConverter.parseInt(platformXML.getFirstChildElement("duration").getValue());
            Platform platform = new Platform(newNote, newDuration, newPosition, newPassTime, newPauseTime,sizemulti);

            platform.setY(0);
            switch (newPosition) {
                case 1:
                    platform.setX((firstPlatformColum*sizemulti));
                    break;
                case 2:
                    platform.setX((secondPlatformColum*sizemulti));
                    break;
                case 3:
                    platform.setX((thirdPlatformColum*sizemulti));
                    break;
                case 4:
                    platform.setX((fourthtPlatformColum*sizemulti));
                    break;
            }
            return platform;
        }
        else {
            return null;
        }
    }
}
