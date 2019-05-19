package MainPackage;

public enum BOOM {

    boom1("View/resources/booms/boom.png","View/resources/booms/Explosion1.png","View/resources/booms/Explosion2.png","View/resources/booms/Explosion3.png","View/resources/Texts/KABOOOM.png"),
    boom2("View/resources/booms/boom2.png","View/resources/booms/Explosion1.png","View/resources/booms/Explosion2.png","View/resources/booms/Explosion3.png","View/resources/Texts/KABOOOM.png"),
    boom3("View/resources/booms/boom3.png","View/resources/booms/Explosion1.png","View/resources/booms/Explosion2.png","View/resources/booms/Explosion3.png","View/resources/Texts/KABOOOM.png"),
    boom4("View/resources/booms/boom4.png","View/resources/booms/Explosion1.png","View/resources/booms/Explosion2.png","View/resources/booms/Explosion3.png","View/resources/Texts/KABOOOM.png");

    private String idle, explosion1,explosion2,explosion3,txt;
    BOOM(String idle, String explosion1, String explosion2, String explosion3, String txt) {
        this.idle = idle;
        this.explosion1 = explosion1;
        this.explosion2 = explosion2;
        this.explosion3 = explosion3;
        this.txt = txt;
    }
    public String getIdle() {
        return idle;
    }

    public String getExplosion1() {
        return explosion1;
    }

    public String getExplosion2() {
        return explosion2;
    }

    public String getExplosion3() {
        return explosion3;
    }

    public String getTxt() {
        return txt;
    }
}
