package Logic;

import View.Fruits;

import java.util.Random;

public class Difficuly {
    private int score;
    private int difficulyLevel;
    Random randomPositionGenerator = new Random();

    public void setScore( int score){
        this.score = score;
        setDifficuly(score);
    }

    public int setDifficuly(int score){
        if(score<5){
            difficulyLevel =1;
        }
        else if(score<15){
            difficulyLevel =  randomPositionGenerator.nextInt(3)+1;
//            System.out.println("Score is less than 10- - Diff is: "+difficulyLevel);
        }
        else if(score<30){
            difficulyLevel =  randomPositionGenerator.nextInt(3)+2;
            new Fruits().setInitiSpeed(5);
            new Fruits().setfallSpeed(9);
//            System.out.println("Bigger than 10- - Diff is: "+difficulyLevel);
        }
        else if(score<40){
            difficulyLevel =  randomPositionGenerator.nextInt(3)+4;
            new Fruits().setInitiSpeed(8);
            new Fruits().setfallSpeed(12);

        }
        return difficulyLevel;
    }
    public int getDifficulyLevel(){
        return difficulyLevel;
    }
}
