package View;

import Logic.Difficuly;
import Logic.Score;
import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private int Score=0;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private FRUITS[] CurrentFruit;
    public int numberOfLifes=3;

    private int difficltyLevel;
    private Difficuly difficuly;
    private String[] FRUIT_PATH,FRUIT_SLICED_PATH, FRUIT_INVERSE_PATH;
    private ImageView[] fruit,fruitSliced, fruitInverse;
    private ImageView closeButton,help,save,Sound;
    private Fruits[] fruitsObjects;

    Clock time = new Clock();
    Score score1 = Logic.Score.getInstance();
    GameEngine gameEngine = GameEngine.getInstance();
    private ClassicMode classicMode;

    public GameViewManger(){
        inializeStage();
        inializeFruits();
        difficuly.setScore(Score);
        setDiffculty();
    }

    private void inializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
        gameStage = new Stage ();
        gameStage.setScene(gameScene);
        classicMode = new ClassicMode();
        classicMode.setNumberOfLifes(numberOfLifes);
        difficuly = new Difficuly();
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
        fruitsObjects = new Fruits[10];
        CurrentFruit = new FRUITS[10];
        FRUIT_PATH = new String[10];
        FRUIT_SLICED_PATH = new String[10];
        FRUIT_INVERSE_PATH = new String[10];

        for(int i=0; i<10; i++) {
            fruitsObjects[i] = (Fruits) gameEngine.createGameObject();
            fruitsObjects[i].setSlicedFromGui(false);
            CurrentFruit[i] = ((FRUITS) fruitsObjects[i].getObjectType());
            FRUIT_PATH[i] = CurrentFruit[i].getIdle();
            FRUIT_SLICED_PATH[i] = CurrentFruit[i].getSliced();
            FRUIT_INVERSE_PATH[i] = CurrentFruit[i].getSlicedInverse();
        }
    }
    private void setDiffculty(){
        difficltyLevel = difficuly.getDifficulyLevel();
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
                setDiffculty();
                checkIfElementsBelowScreen();
                ImageEVENT();
                moveFruitDown();
                //checkLife();
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
        fruit = new ImageView[10];
        fruitSliced = new ImageView[10];
        fruitInverse = new ImageView[10];

        for(int i=0;i<10;i++) {
            fruit[i] = new ImageView(FRUIT_PATH[i]);
            fruitSliced[i] = new ImageView(FRUIT_SLICED_PATH[i]);
            fruitInverse[i] = new ImageView(FRUIT_INVERSE_PATH[i]);
            fruit[i].setFitWidth(100);
            fruit[i].setFitHeight(100);
            fruitSliced[i].setFitHeight(100);
            fruitSliced[i].setFitWidth(100);
            fruitInverse[i].setFitHeight(100);
            fruitInverse[i].setFitWidth(100);
            setNewElementPosition(fruit[i]);
            gamePane.getChildren().add(fruit[i]);
        }
    }

    private void moveElements() {
        for (int i=0; i<difficltyLevel; i++) {
            if (!fruitsObjects[i].isSliced()) {
                if (!fruitsObjects[i].getIsReachedMaxHeight()) {

                    if (fruit[i].getLayoutY() > gameEngine.createGameObject().getMaxHeight()) {
                        fruitsObjects[i].setIsReachedMaxHeight(false);
                        fruit[i].setLayoutY(fruit[i].getLayoutY() - fruitsObjects[i].getInitialVelocity());
                        fruit[i].setLayoutX(fruit[i].getLayoutX() + 1);
                        fruit[i].setRotate(fruit[i].getRotate() + 5);
                    } else {
                        fruit[i].setLayoutX(fruit[i].getLayoutX() + 5);
                        fruitsObjects[i].setIsReachedMaxHeight(true);
                    }
                } else {
                    fruit[i].setLayoutY(fruit[i].getLayoutY() + fruitsObjects[i].getInitialVelocity());
                    fruit[i].setLayoutX(fruit[i].getLayoutX() + 1);
                    fruit[i].setRotate(fruit[i].getRotate() + 5);
                    if (fruit[i].getLayoutY() > 701 && fruit[i].getLayoutY() <= 704 && !gamePane.getChildren().contains(fruitSliced[i])) {
                        numberOfLifes -= 1;
                        classicMode.setNumberOfLifes(numberOfLifes);
                    }
                }
            }
        }
}

    public void checkLife(){
        if(numberOfLifes==0){
            gametimer.stop();
            time.stopAnimation();
            System.out.println("gameover");
        }
}

    private void ImageEVENT(){
            if(time.getState()==false)
            {
                gameStage.close();
                ViewManger.mainstage.show();
                gametimer.stop();
            }
        gamePane.setOnMouseDragged(event -> {

            for(int i=0; i<difficltyLevel; i++) {
                if (Math.abs(event.getSceneX() - fruit[i].getLayoutX()) < 100) {
                    if (Math.abs(event.getSceneY() - fruit[i].getLayoutY()) < 50) {

                        FRUIT_SLICED_PATH[i] = CurrentFruit[i].getSliced();
                        fruitSliced[i].setImage(new Image(FRUIT_SLICED_PATH[i]));
                        FRUIT_INVERSE_PATH[i] = CurrentFruit[i].getSlicedInverse();
                        fruitInverse[i].setImage(new Image(FRUIT_INVERSE_PATH[i]));

                        fruitSliced[i].setLayoutY(fruit[i].getLayoutY());
                        fruitSliced[i].setLayoutX(fruit[i].getLayoutX() + 70);
                        fruitSliced[i].setRotate(30);
                        fruitInverse[i].setRotate(30);
                        fruitInverse[i].setLayoutY(fruit[i].getLayoutY());
                        fruitInverse[i].setLayoutX(fruit[i].getLayoutX() - 70);

                        if (!fruitsObjects[i].isSliced()) {
                            gamePane.getChildren().addAll(fruitSliced[i], fruitInverse[i]);
                        }
                        fruitsObjects[i].setSlicedFromGui(true);
//                        isSliced = true;
                        gamePane.getChildren().remove(fruit[i]);
                        moveFruitDown();
                    }
                }
            }
                            event.setDragDetect(false);
                    });
    }

    private void moveFruitDown(){
        for(int i=0; i<difficltyLevel;i++) {
            if(fruitsObjects[i].isSliced()) {
                if (fruitSliced[i].getLayoutY() > 704) {
                    gamePane.getChildren().removeAll(fruitSliced[i], fruitInverse[i]);
                    CurrentFruit[i] = ((FRUITS) gameEngine.createGameObject().getObjectType());
                    FRUIT_PATH[i] = CurrentFruit[i].getIdle();
                    fruit[i].setImage(new Image(FRUIT_PATH[i]));
                    gameEngine.sliceObjects();
                    Score = gameEngine.getScore();
                    difficuly.setScore(Score);
//                    isSliced = false;
                    fruitsObjects[i].setSlicedFromGui(false);
                    setNewElementPosition(fruit[i]);
                    gamePane.getChildren().addAll(fruit[i]);
                } else {
                    fruitSliced[i].setLayoutY(fruitSliced[i].getLayoutY() + fruitsObjects[i].getFallingVelocity());
                    fruitInverse[i].setLayoutY(fruitInverse[i].getLayoutY() + fruitsObjects[i].getFallingVelocity());
                    fruitInverse[i].setRotate(fruitInverse[i].getRotate() - 2);
                    fruitSliced[i].setRotate(fruitInverse[i].getRotate() + 2);
                    fruitSliced[i].setLayoutX(fruitSliced[i].getLayoutX() + 2);
                    fruitInverse[i].setLayoutX(fruitInverse[i].getLayoutX() - 2);
                }
            }
        }
    }
    private void checkIfElementsBelowScreen(){
        for (int i=0; i<difficltyLevel; i++) {
            if (fruit[i].getLayoutY() > 705) {
//                ReachedMaxHeight = false;
                fruitsObjects[i].setIsReachedMaxHeight(false);
                CurrentFruit[i] = ((FRUITS) gameEngine.createGameObject().getObjectType());
                FRUIT_PATH[i] = CurrentFruit[i].getIdle();
                fruit[i].setImage(new Image(FRUIT_PATH[i]));
                setNewElementPosition(fruit[i]);
            }
        }
    }
}

