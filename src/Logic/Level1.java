package Logic;

import MainPackage.IStartegy;
import View.Fruits;

import java.util.Random;

public class Level1 implements IStartegy {

    Random randomPositionGenerator = new Random();

    @Override
    public int increaseDifficuilty() {
     return randomPositionGenerator.nextInt(3)+2;
    }

    @Override
    public void getInts() {
        new Fruits().setInitiSpeed(5);

    }

    @Override
    public void getFalls() {
        new Fruits().setfallSpeed(9);
    }
    public Level1(){
        getFalls();
        getInts();
    }
}
