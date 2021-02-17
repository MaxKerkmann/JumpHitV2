package buisness.gamelogic;


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
    private Document doc;
    private Element root;
    Elements platforms;

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
        Frog frog = new Frog();
        frog.setY(800);
        frog.setX(600);
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
            Platform platform = new Platform(newNote, newDuration, newPosition, newPassTime, newPauseTime);

            platform.setY(0);
            switch (newPosition) {
                case 1:
                    platform.setX((400));
                    break;
                case 2:
                    platform.setX((550));
                    break;
                case 3:
                    platform.setX((750));
                    break;
                case 4:
                    platform.setX((900));
                    break;
            }
            return platform;
        }
        else {
            return null;
        }
    }
}
