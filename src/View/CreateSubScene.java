package View;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import javafx.scene.Parent;
import javafx.scene.SubScene;

public class CreateSubScene extends SubScene {
     boolean isHidden;

    public CreateSubScene(BackgroundImage Image) {
        super(new AnchorPane(), 600, 300);
        prefWidth(600);
        prefHeight(300);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(Image));
        isHidden = true;

        setLayoutX(600);
        setLayoutY(180);

    }
    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(-400);
            isHidden = false;
        }else{
            transition.setToX(500);
            isHidden = true;
        }
        transition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
