package View;

import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static String FRUIT_ONE, FRUIT_ONE_SLICED,FRUIT_ONE_SLICED_INVERSE , FRUIT_TWO, FRUIT_TWO_SLICED, FRUIT_TWO_SLICED_INVERSE,
            FRUIT_THREE, FRUIT_THREE_SLICED, FRUIT_THREE_SLICED_INVERSE, FRUIT_FOUR, FRUIT_FOUR_SLICED, FRUIT_FOUR_SLICED_INVERSE;
    private int numberOfLifes=3, LifeY=80;

    private ImageView [] fruitOne, fruitTwo, fruitThree, fruitFour;
    private ImageView closeButton,help,save,Sound, life;
    private Random randomPositionGenerator ;


    public GameViewManger(){
        inializeStage();
        inializeFruits();
        randomPositionGenerator = new Random();
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
            gametimer.stop();
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

    private void inializeFruits(){
        FRUIT_ONE = FRUITS.APPLE.getIdle();
        FRUIT_ONE_SLICED = FRUITS.APPLE.getSliced();
        FRUIT_ONE_SLICED_INVERSE = FRUITS.APPLE.getSlicedInverse();

        FRUIT_TWO = FRUITS.WATERMILION.getIdle();
        FRUIT_TWO_SLICED = FRUITS.WATERMILION.getSliced();
        FRUIT_TWO_SLICED_INVERSE = FRUITS.WATERMILION.getSlicedInverse();

        FRUIT_THREE = FRUITS.ORANGE.getIdle();
        FRUIT_THREE_SLICED = FRUITS.ORANGE.getSliced();
        FRUIT_THREE_SLICED_INVERSE = FRUITS.ORANGE.getSlicedInverse();

        FRUIT_FOUR = FRUITS.COCONUT.getIdle();
        FRUIT_FOUR_SLICED = FRUITS.COCONUT.getSliced();
        FRUIT_FOUR_SLICED_INVERSE = FRUITS.COCONUT.getSlicedInverse();
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

    private void createCaseloop(){
        gametimer = new AnimationTimer(){
            @Override
            public void handle(long l) {
                moveElements();
                checkIfElementsBelowScreen();
                ImageEVENT();
            }
        };
            gametimer.start();
    }
    private void setNewElementPosition(ImageView image){
        image.setLayoutX(randomPositionGenerator.nextInt(500));
        image.setLayoutY((randomPositionGenerator.nextInt(3200)+600));
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






    /**DON"T ADD ANY FUNCTION UP, ADD BELOW HERE*/

    //Needs Edits...


    private void createGameelements () {
        fruitOne = new ImageView[3];
        for (int i = 0; i < fruitOne.length; i++) {
            fruitOne[i] = new ImageView(FRUIT_ONE);
            fruitOne[i].setFitWidth(100);
            fruitOne[i].setFitHeight(100);
            setNewElementPosition(fruitOne[i]);
            gamePane.getChildren().add(fruitOne[i]);
        }

        fruitTwo = new ImageView[3];
        for (int i = 0; i < fruitTwo.length; i++) {
            fruitTwo[i] = new ImageView(FRUIT_TWO);
            fruitTwo[i].setFitWidth(100);
            fruitTwo[i].setFitHeight(100);
            setNewElementPosition(fruitTwo[i]);

            gamePane.getChildren().addAll(fruitTwo[i]);
        }
//        fruitTwoSliced = new Image(FRUIT_TWO_SLICED);
//        fruitOneSliced = new Image(FRUIT_ONE_SLICED);

//        while (true){   //Will be based on number of life or timer.
//
//        }
    }

    private void moveElements() {
        for (int i = 0; i < fruitOne.length; i++) {
            fruitOne[i].setLayoutY(fruitOne[i].getLayoutY() - 3);
            if(fruitOne[i].getLayoutX()>900) {
                fruitOne[i].setLayoutX(fruitOne[i].getLayoutX() - 5);
                fruitOne[i].setRotate(fruitOne[i].getRotate() - 2);
            }
            else {
                fruitOne[i].setLayoutX(fruitOne[i].getLayoutX() + 1);
                fruitOne[i].setRotate(fruitOne[i].getRotate() + 2);
            }
            //fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
        }
//        for (int i = 0; i < fruitone.length; i++) {
//           // fruitone[i].setLayoutY(fruitone[i].getLayoutY() - 7);
//            fruitone[i].setRotate(fruitone[i].getRotate() + 4);
//            fruitone[i].setLayoutX(fruitone[i].getLayoutX() +1);
//            fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
//        }
        for (int i = 0; i < fruitTwo.length; i++) {
            fruitTwo[i].setLayoutY(fruitTwo[i].getLayoutY() - 3);
            if(fruitTwo[i].getLayoutX()>900) {
                fruitTwo[i].setLayoutX(fruitTwo[i].getLayoutX() - 5);
                fruitTwo[i].setRotate(fruitTwo[i].getRotate() - 2);
            }
            else {
                fruitTwo[i].setLayoutX(fruitTwo[i].getLayoutX() + 1);
                fruitTwo[i].setRotate(fruitTwo[i].getRotate() + 2);
            }

            // fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);
        }

        //      for (int i = 0; i < fruitone.length; i++) {
//            //fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() - 7);
//            fruittwo[i].setRotate(fruittwo[i].getRotate() + 4);
//            fruittwo[i].setLayoutX(fruittwo[i].getLayoutX() + 1);
//           fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);




    }


    private void ImageEVENT(){
//        for (int i = 0; i < fruitTwo.length; i++) {
//            int finalI = i;
//
//            fruitTwo[i].addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
//                int x = finalI;
////                if (e.getSceneY() == fruitTwo[finalI].getLayoutY() && e.getSceneX() == fruitTwo[finalI].getLayoutX()) {
////                System.out.println(e.getSceneY()+"!!!!!!!!!!!!!!"+fruitTwo[finalI].fitHeightProperty().doubleValue());
//                fruitTwo[finalI].setImage(fruitTwoSliced);
////                System.out.println("hii ya lol");
//
//
////                }
//
//            });
//        }
//        for (int i = 0; i < fruitOne.length; i++) {
//            int finalI = i;
//            fruitOne[i].addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
//                int x = finalI;
////                if (e.getSceneY() == fruitTwo[finalI].getLayoutY() && e.getSceneX() == fruitTwo[finalI].getLayoutX()) {
//                fruitOne[finalI].setImage(fruitOneSliced);
//                System.out.println("hii ya lol");
//
////                }
//
//            });
//        }

    }


    private void checkIfElementsBelowScreen(){

        for (int i =0 ; i< fruitOne.length; i++){
            if(fruitOne[i].getLayoutY()<0){
                System.out.println("fe eh b2a??");
                setNewElementPosition(fruitOne[i]);
            }
        }
        for (int i =0 ; i< fruitTwo.length; i++){
            if(fruitTwo[i].getLayoutY()<0){
                setNewElementPosition(fruitTwo[i]);
            }
        }
    }
}
