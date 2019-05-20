package View;

import java.awt.Label;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Logic.Difficuly;
import Logic.Score;
import MainPackage.FRUITS;
import MainPackage.GameObject;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import static View.ViewManger.mainstage;


public class CreditsScene {

    private static AnchorPane CreditsPane;
    private Scene CreditsScene ;
    private Stage CreditsStage, MenuStage ;
    private static final int width= 1200;
    private static final int height =700;
    private AnimationTimer CreditTimer;
    private ImageView closeButton,help,save,Sound;
    
    private boolean isMusicClicked = false;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private Boolean isSliced=false, ReachedMaxHeight=false;
    private CreditsScene gameManger;
   

    public CreditsScene(){

        inializeStage();
       

    }
    private void inializeStage() {
    	CreditsPane = new AnchorPane();
    	CreditsScene = new Scene(CreditsPane, width,height);
    	CreditsStage = new Stage ();
    	CreditsStage.setScene(CreditsScene);
        
        createIcons();

    }


    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
       // save = new ImageView("View/resources/Icons/save.png");
        Sound = new ImageView(soundOn);
        
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
        	CreditsStage.close();
            mainstage.show();
            CreditTimer.stop();
           
        });
        closeButton.setOnMouseEntered(e->{ closeButton.setEffect(new Glow()); });
        closeButton.setOnMouseExited(e->{ closeButton.setEffect(null); });


        help.setLayoutX(width-60);
        help.setLayoutY(10);
        help.setOnMouseClicked(e->{

        });
        help.setOnMouseEntered(e->{ help.setEffect(new Glow()); });
        help.setOnMouseExited(e->{ help.setEffect(null); });


        Sound.setLayoutX(width-101);
        Sound.setLayoutY(10);
        Sound.setOnMouseClicked(e->{
            if(!isMusicClicked) {
                Sound.setImage(mutedSound);
                isMusicClicked = true;
            }
            else {
                Sound.setImage(soundOn);
                isMusicClicked = false;
            }
        });
        Sound.setOnMouseEntered(e->{ Sound.setEffect(new Glow()); });
        Sound.setOnMouseExited(e->{ Sound.setEffect(null); });

//        save.setLayoutX(10);
//        save.setLayoutY(50);
//        save.setOnMouseClicked(e->{
//
//        });
//        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
//        save.setOnMouseExited(e->{ save.setEffect(null); });


        Text msg = new Text("\t\t\t\t\t\tWelcome NINJA " +
                "\n\n\n\t" +
                "Get ready to show us some Ninja Skills and slice some fruits." +
                "\n\n\t\tYou may want to watch out for your life. our game  " +
                "\n\n \t\t\t\t\t is tricky & all is fair in war." +

                "\n\n\n\t\t\t\t\t\tGood luck Ninja");


        msg.setTextOrigin(VPos.CENTER);
        msg.setFont(Font.font("Arial",FontPosture.REGULAR,35));
        msg.setX(10);
        msg.setY(20);



        Pane root = new Pane(msg);
        msg.setFill(Color.LIGHTBLUE);
        double sceneWidth = CreditsScene.getWidth();
        double msgWidth = msg.getLayoutBounds().getWidth();

        KeyValue initKeyValue = new KeyValue(msg.translateYProperty(), sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        KeyValue endKeyValue = new KeyValue(msg.translateYProperty(), -3.0
                * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(40), endKeyValue);


        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        CreditsPane.getChildren().addAll(root,closeButton,help,Sound);




    }
    private void createCaseloop(){
    	CreditTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                
            }


        };

        CreditTimer.start();
    }

    public static AnchorPane getGamePain(){
        return CreditsPane;
    }

    private void createKeyListeners() {
    	CreditsScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.LEFT){}
                else if (event.getCode()== KeyCode.RIGHT){};
            }
        });

    }
 
    public void createCreditsScene(Stage menustage){
        this.MenuStage = menustage;
        this.MenuStage.hide();
        createBackground();
        createCaseloop();
        CreditsStage.show();
        
    }

    private void createBackground (){
        Image backgroundImage = new Image("View/resources/CreditBackGround.jpg",width,height,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        CreditsPane.setBackground(new Background(background));
    }



   


}