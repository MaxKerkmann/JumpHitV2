package buisness.gamelogic;


import application.Main;
import buisness.gameElements.Frog;
import buisness.gameElements.Platform;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import presentation.uicomponents.SimpleSprite;
import sun.awt.Symbol;

import java.util.ArrayList;

//Führt die tatsächliche Spielmechanik aus
public class GamePlayer {

    private GameWorld gameWorld;
    private GameWorldGenerator gameWorldGenerator;
    private ArrayList<SimpleSprite> sprites = new ArrayList<>();
    private SimpleObjectProperty<Platform> currentPlatform;
    private AnimationTimer timer;
    private Main main;
    private int index;
    private SimpleObjectProperty<finishedMode> gameState;
    private SimpleBooleanProperty gameStarted;
    private String currentSoundFile;
    private Frog frog;
    private boolean wasStartedBefore;
    private double sizemulti;

    public GamePlayer(Main main){
        gameStarted = new SimpleBooleanProperty();
        currentPlatform = new SimpleObjectProperty();
        gameState = new SimpleObjectProperty<finishedMode>();
        this.main = main;
        sizemulti = main.getSizeMultiplyer();

        //Listener der drauf hört ob sich der Spielstatus ändert: Zum Beispiel auf von "Spiel läuft" auf "Verloren"
        gameState.addListener(new ChangeListener<finishedMode>() {
            @Override
            public void changed(ObservableValue<? extends finishedMode> observable, finishedMode oldValue, finishedMode newValue) {
                if(newValue == finishedMode.LOST){
                    timer.stop();
                    System.out.println("Game Over");
                }else if(newValue == finishedMode.WON){
                    timer.stop();
                    System.out.println("You Won");
                }
            }
        });
    }

    //Methode die ausgeführt wird, wenn das Level wiederholt wird
    public void restart(){
        gameStarted.set(false);
        cleanPlayField();
        gameState.set(finishedMode.PLAYING);

    }

    //Methode zum Starten des Spiels
    public void start(String soundFile) {
        if(wasStartedBefore)
            restart();
        wasStartedBefore = true;

        currentSoundFile = soundFile;
        index = 1;
        gameWorld = new GameWorld(main);
        gameWorldGenerator = new GameWorldGenerator(main);
        gameWorldGenerator.setCurrentSoundFile(soundFile);

        frog = gameWorldGenerator.createFrog();
        gameWorld.add(frog);

        Platform p = gameWorldGenerator.createPlatformByIndex(0); //Erste Platform wird erstellt
        gameWorld.add(p);
        currentPlatform.set(p);
        gameStarted.set(true);

         timer = new AnimationTimer() {

            private long lastUpdate;
            private Platform p;

             @Override
            public void start() {

                lastUpdate = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) { //Methode die vom Timer jedes Mal ausgeführt wird
                long elapsedNanoSeconds = now - lastUpdate;
                if(p == null)
                    p = currentPlatform.get();
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0; //Berechnet die verstrichene Zeit seit dem letzten Update
                double elapsedMilisecounds = elapsedNanoSeconds / 1_000_000.0;

                currentPlatform.get().setPauseTime(currentPlatform.get().getPauseTime()-elapsedMilisecounds); //Berechnet die übrige Zeit die bis zur nächsten Platform gewartet wird
                if(currentPlatform.get().getPauseTime() <= 0){ //Wenn Zeit gleich 0 wird die nächste Platform erstellt
                   p = gameWorldGenerator.createPlatformByIndex(index);
                    if(p != null) {
                        gameWorld.add(p);
                        currentPlatform.set(p);
                        index++;
                    }
                }
                finishedMode temp = gameWorld.update(elapsedSeconds,p); // Updated alle GameObjekte und gibt zurück, ob das Spiel beendet ist
                if (temp==finishedMode.LOST)
                    gameState.set(finishedMode.LOST);
                else if(temp==finishedMode.WON)
                    gameState.set(finishedMode.WON);

                ArrayList<SimpleSprite> spritesToDel = new ArrayList<>();
                for (SimpleSprite sprite : sprites) { // Führt für alle Sprites die render-Methode aus
                    sprite.render();
                    if(sprite.getYCoord()>800*sizemulti)//Löscht nicht mehr zu sehende Sprites aus der Update-Liste
                        spritesToDel.add(sprite);
                }
                for(SimpleSprite sprite : spritesToDel){
                    sprites.remove(sprite);
                }

                lastUpdate = now;
            }
        };
        timer.start();

    }

    //Leert die Listen der GameObjekte und Sprites
    public void cleanPlayField(){
        gameWorld.removeAll();
        for(int i = 0;i<sprites.size();i++){
            sprites.remove(i);
        }
    }
    public Frog getFrog(){
        return frog;
    }

    public void addToSprites(SimpleSprite object){
        sprites.add(object);
    }

    public SimpleObjectProperty getPlatformCounter(){
        return currentPlatform;
    }

    public SimpleBooleanProperty getGameStarted(){ return gameStarted;}

    public SimpleObjectProperty<finishedMode> getGameState(){ return gameState;}

    public AnimationTimer getTimer(){
        if(timer != null)
            return timer;
        return null;
    }

    public String getCurrentSoundFile(){
        return currentSoundFile;
    }


}