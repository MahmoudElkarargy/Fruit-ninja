package Logic;

import MainPackage.IStartegy;
import View.BonusObjects;
import View.Booms;
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
        new Fruits().setInitiSpeed(4);
    }

    @Override
    public void getFalls() {
        new Fruits().setfallSpeed(5);
    }

    @Override
    public int increaseBooms() {
        return randomPositionGenerator.nextInt(4);
    }

    @Override
    public void getIntsBomms() {
        new Booms().setInitiSpeed(9);
    }

    @Override
    public int increaseBonus() {
        return randomPositionGenerator.nextInt(3);
    }

    @Override
    public void getIntsBonus() {
        new BonusObjects().setInitiSpeed(5);
    }

    @Override
    public void getFallsBonus() {
        new BonusObjects().setfallSpeed(8);
    }
    @Override
    public void getFallsBooms() {
        new Booms().setInitiSpeed(12);
    }

    public Level2(){
        getFalls();
        getInts();
    }
}
