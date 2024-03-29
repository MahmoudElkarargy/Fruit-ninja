package Logic;

import MainPackage.IStartegy;
import View.Fruits;

import java.util.Random;

public class Difficuly {
    private int score;
    private int difficulyLevel;
    private int boomLevel;
    private int BonusLevel;
    Random randomPositionGenerator = new Random();
    IStartegy Game_level;
    public void setScore( int score){
        this.score = score;
        setDifficuly(score);
    }

    public int setDifficuly(int score){
        if(score<5){
            difficulyLevel =1;
            boomLevel = 0;
            BonusLevel =0;
        }
        else if(score<25){
            Game_level = new Level0();
            difficulyLevel = Game_level.increaseDifficuilty();
            boomLevel = Game_level.increaseBooms();
            BonusLevel = Game_level.increaseBonus();
//            difficulyLevel =  randomPositionGenerator.nextInt(3)+1;
//            System.out.println("Score is less than 10- - Diff is: "+difficulyLevel);
        }
        else if(score<50){
            Game_level = new Level1();
            difficulyLevel = Game_level.increaseDifficuilty();
            boomLevel = Game_level.increaseBooms();
            BonusLevel = Game_level.increaseBonus();
//            difficulyLevel =  randomPositionGenerator.nextInt(3)+2;
//            new Fruits().setInitiSpeed(5);
//            new Fruits().setfallSpeed(9);



//            System.out.println("Bigger than 10- - Diff is: "+difficulyLevel);
        }
        else if(score<100){
            Game_level = new Level2();
            difficulyLevel = Game_level.increaseDifficuilty();
            boomLevel = Game_level.increaseBooms();
            BonusLevel = Game_level.increaseBonus();
//            difficulyLevel =  randomPositionGenerator.nextInt(3)+4;
//            new Fruits().setInitiSpeed(8);
//            new Fruits().setfallSpeed(12);

        }
        return difficulyLevel;
    }
    public int getDifficulyLevel(){
        return difficulyLevel;
    }
    public int getBoomsLevel(){
        return boomLevel;
    }

    public int getBonusLevel() {
        return BonusLevel;
    }
}
