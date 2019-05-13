package View;

import MainPackage.FRUITS;
import MainPackage.GameObject;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Fruits implements GameObject {

    Random randomPositionGenerator = new Random();
    private boolean slicedFromGui;
    @Override
    public Enum getObjectType() {
        int fruitType = randomPositionGenerator.nextInt(4);
        if(fruitType==0)
            return FRUITS.APPLE;
        else if(fruitType==1)
            return FRUITS.WATERMILION;
        else if(fruitType==2)
            return FRUITS.ORANGE;
        else
            return FRUITS.COCONUT;

    }

    @Override
    public int getXlocation() {
        return randomPositionGenerator.nextInt(600);
    }

    @Override
    public int getYlocation() {
        return randomPositionGenerator.nextInt(200)+700;
    }

    @Override
    public int getMaxHeight() {
        return 150;
    }

    @Override
    public int getInitialVelocity() {
        return 0;
    }

    @Override
    public int getFallingVelocity() {
        return 0;
    }
    public void setSlicedFromGui(boolean slicedFromGui){
        this.slicedFromGui = slicedFromGui;
    }
    @Override
    public Boolean isSliced() {
        return slicedFromGui? true:false;
    }

    @Override
    public Boolean hasMovedOffScreen() {
        return null;
    }

    @Override
    public void slice() {

    }

    @Override
    public void move(double time) {

    }

    @Override
    public BufferedImage[] getBufferedImages() {
        return new BufferedImage[0];
    }
}
