package buisness.gamelogic;

import application.Main;
import buisness.gameElements.Platform;
import buisness.gamelogic.GameObject;

import java.util.ArrayList;

//Verwaltung der einzelen GameObjekte
public class GameWorld {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private double sizemulti;

    public GameWorld(Main main){
        sizemulti = main.getSizeMultiplyer();
    }

    public void add(GameObject object) {
        gameObjects.add(object);
    }

    public void remove(int index){
        gameObjects.remove(index);
    }

    public void removeAll(){
        for(int i = 0;i < gameObjects.size(); i++){
            gameObjects.remove(i);
        }
    }

    //Führt bei jedem GameObjekt in der Liste, die Update aus und schaut ob eine Platform ungeklickt den Bildschirm verlässt und gibt dementsprechend den neuen GameState zurück
    public finishedMode update(double multi, Platform p) {
        finishedMode gameFinished = finishedMode.PLAYING;
        boolean couldWin = true;
        for(int i = 0;i<gameObjects.size();i++) {
            GameObject gameObject = gameObjects.get(i);
            gameObject.update(multi);

            if (gameObject instanceof Platform) {
                Platform temp = (Platform) gameObject;
                if (gameObject.getY() > 780*sizemulti && !temp.getClicked()) {
                    gameFinished = finishedMode.LOST;
                    couldWin = false;
                } else if (gameObject.getY() < 780*sizemulti) {
                    couldWin = false;
                }
                if (gameObject.getY() > 780*sizemulti && temp.getClicked()) {
                    remove(i);
                }
            }
        }
        if(couldWin && p == null)
            gameFinished = finishedMode.WON;
        return gameFinished;
    }
}
