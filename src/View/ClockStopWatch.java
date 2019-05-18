package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ClockStopWatch extends Pane {
    private Timeline animation;
    private int tmp = 0;
    private String S = "";

    Label label = new Label("0");
    public ClockStopWatch(){

        label.setFont(Font.font(50));
        label.setStyle("-fx-text-fill:white;");
        label.setTranslateX(600);
        label.setTranslateY(50);
        getChildren().add(label);

        animation= new Timeline(new KeyFrame(Duration.seconds(1), e->timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private void timelabel() {
    tmp++;
        S=tmp +"";
        label.setText(S);
    }
    void reset(){
        tmp = 0;
        this.label.setText(tmp+"");
    }
    void stopAnimation(){
        animation.stop();
    }
    void startAnimation(){
        animation.play();
    }
    }
