package Logic;

import MainPackage.IStartegy;
import View.BonusObjects;
import View.Booms;
import View.Fruits;

import java.util.Random;

public class Level1 implements IStartegy {

    Random randomPositionGenerator = new Random();

    @Override
    public int increaseDifficuilty() {
     return randomPositionGenerator.nextInt(3)+2;
    }

    @Override
    public int increaseBonus() {
        return randomPositionGenerator.nextInt(2);
    }

    @Override
    public void getIntsBonus() {
        new BonusObjects().setInitiSpeed(3);
    }

    @Override
    public void getFallsBonus() {
        new BonusObjects().setfallSpeed(3);
    }
    @Override
    public void getInts() {
        new Fruits().setInitiSpeed(5);

    }

    @Override
    public void getFalls() {
        new Fruits().setfallSpeed(5);
    }
    public Level1(){
        getFalls();
        getInts();
    }

    @Override
    public int increaseBooms() {
        return randomPositionGenerator.nextInt(3);
    }

    @Override
    public void getIntsBomms() {
        new Booms().setInitiSpeed(5);
    }

    @Override
    public void getFallsBooms() {
        new Booms().setInitiSpeed(8);
    }
}
