package View;

import Logic.Score;

import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GameViewManger {
    public static AnchorPane gamePane;
    private Scene gameScene ;
    private Stage gameStage ;
    private static final int GAME_WIDTH= 1200;
    private static final int GAME_HEIGHT= 700;
    private Stage menustage;
    private AnimationTimer gametimer;
    private boolean isMusicClicked = false;
    private int Score;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private FRUITS CurrentFruit;
    public int numberOfLifes=3;

    private String FRUIT_PATH,FRUIT_SLICED_PATH, FRUIT_INVERSE_PATH;
    private ImageView fruit,fruitSliced, fruitInverse;
    private ImageView closeButton,help,save,Sound;
//    private Random randomPositionGenerator;
    private Boolean isSliced=false, ReachedMaxHeight=false;

    ClassicTimer time = new ClassicTimer();
    Score score1 = Logic.Score.getInstance();
    GameEngine gameEngine = GameEngine.getInstance();
    private ClassicMode classicMode;
   
    public GameViewManger(){
        inializeStage();
        inializeFruits();
//        randomPositionGenerator = new Random();
    }

    private void inializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
        gameStage = new Stage ();
        gameStage.setScene(gameScene);
        classicMode = new ClassicMode();
        classicMode.setNumberOfLifes(numberOfLifes);
      
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
        gamePane.getChildren().add(time);
        gamePane.getChildren().add(score1);

        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
            gameStage.close();
            ViewManger.mainstage.show();
            gametimer.stop();
            gameEngine.saveScore();
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

        gamePane.getChildren().addAll(closeButton,help,save,Sound);

    }

    private void inializeFruits(){
        //Creating objects.
        CurrentFruit = ((FRUITS)gameEngine.createGameObject().getObjectType());
        FRUIT_PATH = CurrentFruit.getIdle();
        FRUIT_SLICED_PATH = CurrentFruit.getSliced();
        FRUIT_INVERSE_PATH = CurrentFruit.getSlicedInverse();
    }

    public void createNewGame(Stage menustage){
        this.menustage = menustage;
        this.menustage.hide();
        gameEngine.ResetGame();
        createBackground();
        createGameelements();
        createCaseloop();
        gameStage.show();
        time.start();
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
                moveFruitDown();
            }
        };
            gametimer.start();
    }
    private void setNewElementPosition(ImageView image){
        image.setLayoutY(gameEngine.createGameObject().getYlocation());
        image.setLayoutX(gameEngine.createGameObject().getXlocation());
    }

    public static AnchorPane getGamePain(){
        return gamePane;
    }






    /**DON"T ADD ANY FUNCTION UP, ADD BELOW HERE*/

    //Needs Edits...


    private void createGameelements () {
        fruit = new ImageView(FRUIT_PATH);
        fruitSliced = new ImageView(FRUIT_SLICED_PATH);
        fruitInverse = new ImageView(FRUIT_INVERSE_PATH);
        fruit.setFitWidth(100);
        fruit.setFitHeight(100);
        fruitSliced.setFitHeight(100);
        fruitSliced.setFitWidth(100);
        fruitInverse.setFitHeight(100);
        fruitInverse.setFitWidth(100);
        setNewElementPosition(fruit);
        gamePane.getChildren().add(fruit);
    }

    private void moveElements() {
        if(!isSliced) {
            if (!ReachedMaxHeight) {
                if (fruit.getLayoutY() > gameEngine.createGameObject().getMaxHeight()) {
                    ReachedMaxHeight = false;
                    fruit.setLayoutY(fruit.getLayoutY() - 3);
                    fruit.setLayoutX(fruit.getLayoutX() + 1);
                    fruit.setRotate(fruit.getRotate() + 5);
                } else {
                    fruit.setLayoutX(fruit.getLayoutX() + 5);
                    ReachedMaxHeight = true;
                }
            } else {
                fruit.setLayoutY(fruit.getLayoutY() + 3);
                fruit.setLayoutX(fruit.getLayoutX() + 1);
                fruit.setRotate(fruit.getRotate() + 5);
                if (fruit.getLayoutY() > 701 && fruit.getLayoutY() <= 704 && !gamePane.getChildren().contains(fruitSliced)) {
                    numberOfLifes -= 1;
                    classicMode.setNumberOfLifes(numberOfLifes);
                   
                }
            }
        }
}
    


    private void ImageEVENT(){
           /* if(time.getState()==false)
            {
                gameStage.close();
                ViewManger.mainstage.show();
                gametimer.stop();
            }*/
        gamePane.setOnMouseDragged(event -> {
                            if (Math.abs(event.getSceneX() - fruit.getLayoutX()) < 100) {
                                if (Math.abs(event.getSceneY() - fruit.getLayoutY()) < 50) {

                                    FRUIT_SLICED_PATH = CurrentFruit.getSliced();
                                    fruitSliced.setImage(new Image(FRUIT_SLICED_PATH));
                                    FRUIT_INVERSE_PATH = CurrentFruit.getSlicedInverse();
                                    fruitInverse.setImage(new Image(FRUIT_INVERSE_PATH));

                                    fruitSliced.setLayoutY(fruit.getLayoutY());
                                    fruitSliced.setLayoutX(fruit.getLayoutX() + 70);
                                    fruitSliced.setRotate(30);
                                    fruitInverse.setRotate(30);
                                    fruitInverse.setLayoutY(fruit.getLayoutY());
                                    fruitInverse.setLayoutX(fruit.getLayoutX() - 70);

                                    if (!isSliced)
                                    {
                                        gamePane.getChildren().addAll(fruitSliced,fruitInverse);
                                    }
                                    isSliced = true;
                                    gamePane.getChildren().remove(fruit);
                                    moveFruitDown();
                                }
                            }
                            event.setDragDetect(false);
                    });
    }

    private void moveFruitDown(){
        if(isSliced) {

//            if (Case == 0) {
                if(fruitSliced.getLayoutY()>704) {
                    gamePane.getChildren().removeAll(fruitSliced,fruitInverse);
                    CurrentFruit = ((FRUITS)gameEngine.createGameObject().getObjectType());
                    FRUIT_PATH = CurrentFruit.getIdle();
                    fruit.setImage(new Image(FRUIT_PATH));
                    setNewElementPosition(fruit);
                    gamePane.getChildren().addAll(fruit);
                    gameEngine.sliceObjects();
                    isSliced = false;
                }
                else {
                    fruitSliced.setLayoutY(fruitSliced.getLayoutY() + 5);
                    fruitInverse.setLayoutY(fruitInverse.getLayoutY() + 5);
                    fruitInverse.setRotate(fruitInverse.getRotate()-2);
                    fruitSliced.setRotate(fruitInverse.getRotate()+2);
                    fruitSliced.setLayoutX(fruitSliced.getLayoutX() + 2);
                    fruitInverse.setLayoutX(fruitInverse.getLayoutX() - 2);


                }
                
        }
    }
    private void checkIfElementsBelowScreen(){
        if(fruit.getLayoutY()>701 ){
            ReachedMaxHeight = false;
            CurrentFruit = ((FRUITS)gameEngine.createGameObject().getObjectType());
            FRUIT_PATH = CurrentFruit.getIdle();
            fruit.setImage(new Image(FRUIT_PATH));
            setNewElementPosition(fruit);
        }

    }
}

