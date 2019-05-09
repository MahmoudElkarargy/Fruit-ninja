package MainPackage;

public enum FRUITS {
    APPLE("View/resources/Fruits/Apple.png", "View/resources/Fruits/SlicedApple.png" , "View/resources/Fruits/SlicedAppleInverse.png"),
    WATERMILION("View/resources/Fruits/watermelon.png", "View/resources/Fruits/SlicedWatermelon.png" , "View/resources/Fruits/SlicedwatermelonInverse.png"),
    ORANGE("View/resources/Fruits/Orange.png", "View/resources/Fruits/SlicedOrange.png" , "View/resources/Fruits/SlicedOrangeInverse.png"),
    COCONUT("View/resources/Fruits/Coconut.png", "View/resources/Fruits/SlicedCoconut.png" , "View/resources/Fruits/SlicedCoconutInverse.png");

    private String idle, sliced, slicedInverse;
    FRUITS(String idle, String sliced, String slicedInverse) {
        this.idle = idle;
        this.sliced = sliced;
        this.slicedInverse = slicedInverse;
    }

    public String getIdle() {
        return idle;
    }

    public String getSliced() {
        return sliced;
    }

    public String getSlicedInverse() {
        return slicedInverse;
    }
}
