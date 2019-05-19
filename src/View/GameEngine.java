package View;

import Logic.*;
import MainPackage.GameActions;
import MainPackage.GameObject;


public class GameEngine implements GameActions {
    Score score = Score.getInstance();
    private static GameEngine gameEngine = null;
    private GameEngine(){}

    public static GameEngine getInstance(){
        if(gameEngine==null){
            gameEngine = new GameEngine();
            return gameEngine;
        }
        else
            return gameEngine;
        }
    public GameObject createGameObject(int type) {
        if(type==0) {
            Fruits fruits = new Fruits();
            return fruits;
        }
        else {
            Booms booms = new Booms();
            return booms;
        }
    }

    public void updateObjectsLocations() {

    }

    public void sliceObjects() {
        score.add();

    }
    public int getScore(){
        return score.getTmp();
    }
    public void saveGame() {

    }
    public void saveScore(int Case){
        SaveScoreModel save = new SaveScoreModel(score.getTmp(),1);
        SaveScoreFILE file = new SaveScoreFILE(save);
        RemoteSave control = new RemoteSave();
        SaveComand SAVE = new SaveComand(file);
        control.setComand(SAVE);
        control.press();
        Save_File_name s = new Save_File_name(Case);
        s.OverWrite_scores(score.getTmp());

    }

    public void loadGame() {

    }

    public void ResetGame() {
        score.reset();

    }
    public void ReseetClockTimer(ClockTimer clock, int Case){
        clock.reset();
        if(Case==1)
        clock.startAnimation();
        else if(Case == 0)
            clock.stopAnimation();
    }

    public void ResertStopWatch(ClockStopWatch watch){
        watch.reset();
    }
}
