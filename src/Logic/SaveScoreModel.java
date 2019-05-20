package Logic;

import View.Fruits;

import java.util.List;

public class SaveScoreModel {
    int score;
    int type;
    List<Fruits> fruits;
    public SaveScoreModel(int score,int type,List<Fruits> fruits){
        this.score = score;
        this.type = type;
        this.fruits = fruits;
    }
}
