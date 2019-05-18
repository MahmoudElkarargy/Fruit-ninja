package Logic;

import MainPackage.IStartegy;

import java.util.Random;

public class Level0 implements IStartegy {
    Random randomPositionGenerator = new Random();

    @Override
    public int increaseDifficuilty() {
        return randomPositionGenerator.nextInt(3)+1;
    }

    @Override
    public void getInts() {

    }

    @Override
    public void getFalls() {

    }
}
