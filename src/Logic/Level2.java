package Logic;

import MainPackage.IStartegy;
import View.Fruits;

import java.util.Random;

public class Level2 implements IStartegy {
    Random randomPositionGenerator = new Random();

    @Override
    public int increaseDifficuilty() {
        return randomPositionGenerator.nextInt(3)+4;
    }

    @Override
    public void getInts() {
        new Fruits().setInitiSpeed(8);
    }

    @Override
    public void getFalls() {
        new Fruits().setfallSpeed(9);
    }
    public Level2(){
        getFalls();
        getInts();
    }
}
