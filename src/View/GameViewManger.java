package View;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class GameViewManger {
    private AnchorPane gamePane;
    private Scene gameScene ;
    private Stage gameStage ;
    private static final int GAME_WIDTH= 1200;
    private static final int GAME_HEIGHT= 700;
    private Stage menustage;
    private AnimationTimer gametimer;
    private boolean isMusicClicked = false;
    private int Score;
    private final static String FRUIT_ONE = "View/resources/Fruits/Apple.png";
    private final static String FRUIT_ONE_SLICED = "View/resources/Fruits/SlicedApple.png";

    private final static String FRUIT_TWO = "View/resources/Fruits/watermelon.png";
    private final static String FRUIT_TWO_SLICED = "View/resources/Fruits/slicedWatermelon.png";
    private final static String FRUIT_THREE = "View/resources/Fruits/Orange.png";
    private final static String FRUIT_FOUR = "View/resources/Fruits/dk.png";
    private int numberOfLifes=3, LifeY=80;
    private Image fruitTwoSliced;
    private Image fruitOneSliced;
    private ImageView [] fruitOne;
    private ImageView [] fruitTwo;
    private ImageView [] fruitThree;
    private ImageView [] fruitFour;

    Random randomPositionGenerator ;
    private ImageView closeButton,help,save,Sound, life;

    public GameViewManger(){
        inializeStage();
        randomPositionGenerator = new Random();
    }
    private void createCaseloop(){

//        gametimer.start();
        gametimer = new AnimationTimer(){

            @Override
            public void handle(long l) {
                moveElements();
                checkIfElementsBelowScreen();
                ImageEVENT();

            }


        };
//        gametimer.stop();
            gametimer.start();
    }


    private void createGameelements () {
        fruitOne = new ImageView[10];
        for (int i = 0; i < fruitOne.length; i++) {
            fruitOne[i] = new ImageView(FRUIT_ONE);
            fruitOne[i].setFitWidth(100);
            fruitOne[i].setFitHeight(100);
            setNewElementPosition(fruitOne[i]);
            gamePane.getChildren().add(fruitOne[i]);
        }

        fruitTwo = new ImageView[10];
        for (int i = 0; i < fruitTwo.length; i++) {
            fruitTwo[i] = new ImageView(FRUIT_TWO);
            fruitTwo[i].setFitWidth(100);
            fruitTwo[i].setFitHeight(100);
            setNewElementPosition(fruitTwo[i]);

            gamePane.getChildren().addAll(fruitTwo[i]);
        }
        fruitTwoSliced = new Image(FRUIT_TWO_SLICED);
        fruitOneSliced = new Image(FRUIT_ONE_SLICED);
//        gamePane.getChildren().add(fruitTwoSliced);


    }
    private void moveElements() {
        for (int i = 0; i < fruitOne.length; i++) {
            fruitOne[i].setLayoutY(fruitOne[i].getLayoutY() - 5);
            fruitOne[i].setRotate(fruitOne[i].getRotate() + 4);
            fruitOne[i].setLayoutX(fruitOne[i].getLayoutX() + 1);
            //fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
        }
//        for (int i = 0; i < fruitone.length; i++) {
//           // fruitone[i].setLayoutY(fruitone[i].getLayoutY() - 7);
//            fruitone[i].setRotate(fruitone[i].getRotate() + 4);
//            fruitone[i].setLayoutX(fruitone[i].getLayoutX() +1);
//            fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
//        }
        for (int i = 0; i < fruitTwo.length; i++) {
            fruitTwo[i].setLayoutY(fruitTwo[i].getLayoutY() - 5);
            fruitTwo[i].setRotate(fruitTwo[i].getRotate() + 4);
            fruitTwo[i].setLayoutX(fruitTwo[i].getLayoutX() + 1);


            // fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);
        }

        //      for (int i = 0; i < fruitone.length; i++) {
//            //fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() - 7);
//            fruittwo[i].setRotate(fruittwo[i].getRotate() + 4);
//            fruittwo[i].setLayoutX(fruittwo[i].getLayoutX() + 1);
//           fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);




    }
    private void ImageEVENT(){
        for (int i = 0; i < fruitTwo.length; i++) {
            int finalI = i;

            fruitTwo[i].addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                int x = finalI;
//                if (e.getSceneY() == fruitTwo[finalI].getLayoutY() && e.getSceneX() == fruitTwo[finalI].getLayoutX()) {
//                System.out.println(e.getSceneY()+"!!!!!!!!!!!!!!"+fruitTwo[finalI].fitHeightProperty().doubleValue());
                fruitTwo[finalI].setImage(fruitTwoSliced);
//                System.out.println("hii ya lol");


//                }

            });
        }
        for (int i = 0; i < fruitOne.length; i++) {
            int finalI = i;
            fruitOne[i].addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                int x = finalI;
//                if (e.getSceneY() == fruitTwo[finalI].getLayoutY() && e.getSceneX() == fruitTwo[finalI].getLayoutX()) {
                fruitOne[finalI].setImage(fruitOneSliced);
                System.out.println("hii ya lol");

//                }

            });
        }

    }
    private void checkIfElementsBelowScreen(){

        for (int i =0 ; i< fruitOne.length; i++){
            if(fruitOne[i].getLayoutY()>700){
                setNewElementPosition(fruitOne[i]);
            }
        }
        for (int i =0 ; i< fruitTwo.length; i++){
            if(fruitTwo[i].getLayoutY()>700){
                setNewElementPosition(fruitTwo[i]);
            }
        }
    }


     private void setNewElementPosition(ImageView image){
        image.setLayoutX(randomPositionGenerator.nextInt(500));
        image.setLayoutY((randomPositionGenerator.nextInt(3200)+600));
     }


    private void inializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
        gameStage = new Stage ();
        gameStage.setScene(gameScene);
        gameStage.initStyle(StageStyle.TRANSPARENT);
        createIcons();
    }

    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
        save = new ImageView("View/resources/Icons/save.png");
        Sound = new ImageView(soundOn);

        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
            gameStage.close();
            ViewManger.mainstage.show();
        });
        closeButton.setOnMouseEntered(e->{ closeButton.setEffect(new Glow()); });
        closeButton.setOnMouseExited(e->{ closeButton.setEffect(null); });


        help.setLayoutX(GAME_WIDTH-60);
        help.setLayoutY(10);
        help.setOnMouseClicked(e->{

        });
        help.setOnMouseEntered(e->{ help.setEffect(new Glow()); });
        help.setOnMouseExited(e->{ help.setEffect(null); });


        Sound.setLayoutX(GAME_WIDTH-101);
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

        save.setLayoutX(10);
        save.setLayoutY(50);
        save.setOnMouseClicked(e->{

        });
        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
        save.setOnMouseExited(e->{ save.setEffect(null); });

        creatLifeNumbers();
        gamePane.getChildren().addAll(closeButton,help,save,Sound);
    }
    public void creatLifeNumbers(){
        life = new ImageView("View/resources/BonusObjects/Life.png");
        life.setLayoutY(LifeY);
        life.setFitHeight(50);
        life.setFitWidth(50);
        for (int i=0; i<numberOfLifes; i++){
            life.setLayoutX(GAME_WIDTH-60);
        }
        gamePane.getChildren().add(life);
    }

    public void createNewGame(Stage menustage){
        this.menustage = menustage;
        this.menustage.hide();
        createBackground();
        createGameelements();
        createCaseloop();
        gameStage.show();
    }

    private void createBackground (){
        Image backgroundImage = new Image("View/resources/GameBackground.jpg",GAME_WIDTH,GAME_HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        gamePane.setBackground(new Background(background));
    }

}
