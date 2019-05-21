package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;

import MainPackage.Observer;

public class Clock extends Pane {
    private Timeline animation;
    private int tmp = 60;
    private String S = "";
     boolean close;
     ArrayList<Observer> obs = new ArrayList<Observer>();


    Label label = new Label("60");
    public Clock(){
        close = true;
        label.setFont(Font.font(50));
        label.setStyle("-fx-text-fill:white;");
        label.setTranslateX(600);
        label.setTranslateY(50);
        getChildren().add(label);


        animation= new Timeline(new KeyFrame(Duration.seconds(1),e->timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    
    public void attach(Observer o) {
    	obs.add(o);
    	
    }
public void notifyAllObservers() {
	for(int i=0;i<obs.size();i++)
	{
		obs.get(i).update();
	}
}
    

    private void timelabel() {
        if(tmp>0){
            tmp--;
            notifyAllObservers();
        this.close=true;
        }

        S=tmp +"";
      //  label.setText(S);
        if(tmp == 0)
            this.close = false ;
    }
    void start(){
        this.tmp = 60;
        this.label.setText(tmp+"");
    }
    boolean getState(){
        if(this.tmp==0)
            return false;
        else return true;
    }
    void stopAnimation(){
        animation.stop();
    }
    void startAnimation(){
        animation.play();
    }
    void reset(){
        tmp = 60;
        this.label.setText(tmp+"");
    }
    
    

	public int getTmp() {
		return tmp;
	}
	public void setTmp(int tmp) {
		this.tmp = tmp;
	}
}
