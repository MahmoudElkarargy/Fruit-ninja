package Logic;

import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Score extends Pane {
    private int tmp;
    public static Score instance = null;

    Label label = new Label("0");
    private Score(){
        label.setFont(Font.font(50));
        label.setStyle("-fx-text-fill:white;");
        label.setTranslateX(600);
        label.setTranslateY(600);
        label.setEffect(new Glow(80));
        getChildren().add(label);
    }
    public static Score getInstance(){
        if(instance==null){
            instance = new Score();
            return instance;
        }
        else return instance;
    }
    public void add(){
        tmp ++;
        label.setText(tmp+"");
    }
    public void add3(){
        tmp += 3;
        label.setText(tmp+"");
    }
    public void add4(){
        tmp += 4;
        label.setText(tmp+"");
    }
    public void add5(){
        tmp += 5;
        label.setText(tmp+"");
    }
    public void add6(){
        tmp += 6;
        label.setText(tmp+"");
    }

    public void addBonus(){
        tmp += 5;
        label.setText(tmp+"");
    }
    public void reset(){
        tmp = 0;
        label.setText(tmp+"");


    }
    public int getTmp(){
        return tmp;
    }
}
