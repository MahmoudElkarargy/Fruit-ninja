package View;

import MainPackage.FRUITS;
import MainPackage.GameObject;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Fruits implements GameObject {

    Random randomPositionGenerator = new Random();
    private boolean slicedFromGui;
    private boolean isReachedMaxHeight;
    private static int initiSpeed = 3;
    private static int fallSpeed = 3;

    @Override
    public Enum getObjectType() {
        int fruitType = randomPositionGenerator.nextInt(4);
//        int fruitType =0;
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
        return 700;
    }

    @Override
    public int getMaxHeight() {
        return 150;
    }

    @Override
    public int getInitialVelocity() {
        return initiSpeed;
    }
    public void setInitiSpeed(int initiSpeed){
        this.initiSpeed = initiSpeed;
    }
//    public int getFallSpeed() {return fallSpeed;}
    public void setfallSpeed(int fallSpeed){
        this.fallSpeed = fallSpeed;
    }
    @Override
    public int getFallingVelocity() {
        return fallSpeed;
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

    public void setIsReachedMaxHeight(boolean isReachedMaxHeight){
        this.isReachedMaxHeight = isReachedMaxHeight;
    }
    public Boolean getIsReachedMaxHeight(){
        return isReachedMaxHeight? true:false;
    }
}
