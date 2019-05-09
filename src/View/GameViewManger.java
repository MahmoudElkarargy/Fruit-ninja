package View;

import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
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
    private static int CASE;
    private ImageView fruitOne, fruitTwo, fruitThree, fruitFour;
    private ImageView fruitOneSliced, fruitOneInverse, fruitTwoSliced, fruitTwoInverse;
    private ImageView fruitThreeSliced, fruitThreeInverse, fruitFourSliced, fruitFourInverse;
    private ImageView closeButton,help,save,Sound, life;
    private Random randomPositionGenerator;
    private Boolean isSliced=false;


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
//        gameStage.initStyle(StageStyle.TRANSPARENT);
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
                moveFruitDown(CASE);
            }
        };
            gametimer.start();
    }
    private void setNewElementPosition(ImageView image){
        image.setLayoutX(randomPositionGenerator.nextInt(700));
        image.setLayoutY((randomPositionGenerator.nextInt(600)+700));
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
        fruitOne = new ImageView(FRUIT_ONE);
        fruitOneSliced = new ImageView(FRUIT_ONE_SLICED);
        fruitOneInverse = new ImageView(FRUIT_ONE_SLICED_INVERSE);
        fruitOne.setFitWidth(100);
        fruitOne.setFitHeight(100);
        setNewElementPosition(fruitOne);
        gamePane.getChildren().add(fruitOne);


        fruitTwo = new ImageView(FRUIT_TWO);
        fruitTwoSliced = new ImageView(FRUIT_TWO_SLICED);
        fruitTwoInverse = new ImageView(FRUIT_TWO_SLICED_INVERSE);
        fruitTwo.setFitWidth(100);
        fruitTwo.setFitHeight(100);
        setNewElementPosition(fruitTwo);
        gamePane.getChildren().addAll(fruitTwo);


        fruitThree = new ImageView(FRUIT_THREE);
        fruitThreeSliced = new ImageView(FRUIT_THREE_SLICED);
        fruitThreeInverse = new ImageView(FRUIT_THREE_SLICED_INVERSE);
        fruitThree.setFitWidth(100);
        fruitThree.setFitHeight(100);
        setNewElementPosition(fruitThree);
        gamePane.getChildren().addAll(fruitThree);

        fruitFour = new ImageView(FRUIT_FOUR);
        fruitFourSliced = new ImageView(FRUIT_FOUR_SLICED);
        fruitFourInverse = new ImageView(FRUIT_FOUR_SLICED_INVERSE);
        fruitFour.setFitWidth(100);
        fruitFour.setFitHeight(100);
        setNewElementPosition(fruitFour);
        gamePane.getChildren().addAll(fruitFour);

    }

    private void moveElements() {
        fruitOne.setLayoutY(fruitOne.getLayoutY() - 3);
        fruitOne.setLayoutX(fruitOne.getLayoutX() + 1);

        fruitTwo.setLayoutY(fruitTwo.getLayoutY() - 3);
        fruitTwo.setLayoutX(fruitTwo.getLayoutX() + 1);

        fruitThree.setLayoutY(fruitThree.getLayoutY() - 3);
        fruitThree.setLayoutX(fruitThree.getLayoutX() + 1);

        fruitFour.setLayoutY(fruitFour.getLayoutY() - 3);
        fruitFour.setLayoutX(fruitFour.getLayoutX() + 1);
    }


    private void ImageEVENT(){

        gamePane.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                if(Math.abs(event.getSceneX() - fruitOne.getLayoutX()) < 100){
                    if(Math.abs(event.getSceneY() - fruitOne.getLayoutY()) <50){

                        fruitOneSliced.setLayoutY(fruitOne.getLayoutY());
                        fruitOneSliced.setLayoutX(fruitOne.getLayoutX()+50);
                        fruitOneSliced.setFitWidth(100);
                        fruitOneSliced.setFitHeight(100);
                        if(!isSliced)
                            gamePane.getChildren().add(fruitOneSliced);
                        isSliced = true;
                        gamePane.getChildren().remove(fruitOne);
                        CASE = 0;
                        moveFruitDown(CASE);
                }
            }

                if(Math.abs(event.getSceneX() - fruitTwo.getLayoutX()) < 100){
                    if(Math.abs(event.getSceneY() - fruitTwo.getLayoutY()) <50){

                        fruitTwoSliced.setLayoutY(fruitTwo.getLayoutY());
                        fruitTwoSliced.setLayoutX(fruitTwo.getLayoutX()+50);
                        fruitTwoSliced.setFitWidth(100);
                        fruitTwoSliced.setFitHeight(100);
                        if(!isSliced)
                            gamePane.getChildren().add(fruitTwoSliced);
                        isSliced = true;
                        gamePane.getChildren().remove(fruitTwo);
                        CASE = 1;
                        moveFruitDown(CASE);
                    }
                }


                event.setDragDetect(false);
            }
        });

    }

    private void moveFruitDown(int Case){
        if(isSliced) {

            if (Case == 0) {
                if(fruitOneSliced.getLayoutY()>700) {
                    gamePane.getChildren().remove(fruitOneSliced);
                    setNewElementPosition(fruitOne);
                    gamePane.getChildren().add(fruitOne);
                    isSliced = false;
                }
                else
                    fruitOneSliced.setLayoutY(fruitOneSliced.getLayoutY() +5);
            }

            if (Case == 1) {
                if(fruitTwoSliced.getLayoutY()>700) {
                    gamePane.getChildren().remove(fruitTwoSliced);
                    setNewElementPosition(fruitTwo);
                    gamePane.getChildren().add(fruitTwo);
                    isSliced = false;
                }
                else
                    fruitTwoSliced.setLayoutY(fruitTwoSliced.getLayoutY() +5);
            }
        }
    }
    private void checkIfElementsBelowScreen(){

        if(fruitOne.getLayoutY()<0){
            setNewElementPosition(fruitOne);
        }
        if(fruitTwo.getLayoutY()<0){
            setNewElementPosition(fruitTwo);
        }
        if(fruitThree.getLayoutY()<0){
            setNewElementPosition(fruitThree);
        }
        if(fruitFour.getLayoutY()<0){
            setNewElementPosition(fruitFour);
        }

    }

}
