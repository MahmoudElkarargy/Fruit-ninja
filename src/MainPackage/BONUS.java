package MainPackage;

public enum BONUS {

    Bonus("View/resources/BonusObjects/Bonus.png","View/resources/Texts/Yes.png"),
    Time("View/resources/BonusObjects/BonusTime.png","View/resources/Texts/Crack.png"),
    Life("View/resources/BonusObjects/Life.png","View/resources/Texts/Crack.png"),
    Poison("View/resources/BonusObjects/poison.png","View/resources/Texts/Oops.png");

    private String idle, txt;
    BONUS(String idle, String txt) {
        this.idle = idle;
        this.txt = txt;
    }
    public String getIdle() {
        return idle;
    }

    public String getTxt() {
        return txt;
    }
}  
