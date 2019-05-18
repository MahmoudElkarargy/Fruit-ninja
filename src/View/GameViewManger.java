package View;

import Logic.Difficuly;
import Logic.Score;
import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.LinkedList;
import java.util.List;


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
    private int sign = -1;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private List<FRUITS> CurrentFruit = new LinkedList<FRUITS>();
//    private FRUITS[] CurrentFruit;
    public int numberOfLifes=3;

    private int startArray=-20;
    private int difficltyLevel=1;
    private Difficuly difficuly;

    private ImageView closeButton,help,save,Sound;
    private List<String> FRUIT_PATH = new LinkedList<String>();
    private List<String> FRUIT_SLICED_PATH = new LinkedList<String>();
    private List<String> FRUIT_INVERSE_PATH = new LinkedList<String>();

    private List<ImageView> fruit = new LinkedList<ImageView>();
    private List<ImageView> fruitSliced = new LinkedList<ImageView>();
    private List<ImageView> fruitInverse = new LinkedList<ImageView>();
    private boolean IAlreadyCreated;
    private List<Fruits> fruitsObjects = new LinkedList<Fruits>();
     int Case;
    Clock time = new Clock();
    Score score1 = Logic.Score.getInstance();

    GameEngine gameEngine = GameEngine.getInstance();
    private ClassicMode classicMode ;


    public GameViewManger(){
        inializeStage();
        difficuly.setScore(Score);
        setDiffculty();
    }

    private void inializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
        gameStage = new Stage ();
        gameStage.setScene(gameScene);
         classicMode = ClassicMode.getInstance();

        classicMode.setNumberOfLifes(numberOfLifes);
        difficuly = new Difficuly();
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

        gamePane.getChildren().add(score1);

        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
            gameStage.close();
            ViewManger.mainstage.show();
            gametimer.stop();
            gameEngine.saveScore();
            time.reset();
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

    private void setDiffculty(){
        difficltyLevel = difficuly.getDifficulyLevel();
    }

    public void createNewGame(Stage menustage,int Case) {
        this.menustage = menustage;
        this.menustage.hide();
        this.Case = Case;

        gameEngine.ResetGame();

        createBackground();
        setDiffculty();
        createGameelements();
        createCaseloop(Case);
        gameStage.show();
        if(this.Case==1) {
            gameEngine.ReseetClock(time,1);
//            time.reset();
//            time.startAnimation();
            classicMode.removeLifes();
            this.numberOfLifes=3;
            classicMode.reset_space();
            gamePane.getChildren().remove(time);
            gamePane.getChildren().add(time);
            gamePane.getChildren().remove(closeButton);
            gamePane.getChildren().add(closeButton);
            gamePane.getChildren().remove(save);
            gamePane.getChildren().add(save);
//            time.reset();

        }

        if(Case==0){
//            time.reset();
            gameEngine.ReseetClock(time,0);
            classicMode.removeLifes();
            this.numberOfLifes=3;
           classicMode.reset_space();
            classicMode.creatLifeNumbers(numberOfLifes);
            gamePane.getChildren().remove(time);
//            time.stopAnimation();
//            time.reset();
        }
    }
    private void createBackground (){
        Image backgroundImage = new Image("View/resources/GameBackground.jpg",GAME_WIDTH,GAME_HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        gamePane.setBackground(new Background(background));
    }


    private void createCaseloop(int Case){

            gametimer = new AnimationTimer(){
            @Override
            public void handle(long l) {
                setDiffculty();
                moveElements();
                setDiffculty();
                checkIfElementsBelowScreen();
                ImageEVENT();
                moveFruitDown();
                if(Case ==0){
                checkLife();
                }
            }
        };
            gametimer.start();
    }

    private void setNewElementPosition(ImageView image){
        image.setLayoutY(gameEngine.createGameObject().getYlocation());
        image.setLayoutX(gameEngine.createGameObject().getXlocation() + 100*sign*-1);
    }

    public static AnchorPane getGamePain(){
        return gamePane;
    }



    private void createGameelements () {
            for (int i = 0; i < difficltyLevel; i++) {
                fruitsObjects.add(i, (Fruits) gameEngine.createGameObject());
                fruitsObjects.get(i).setSlicedFromGui(false);
                CurrentFruit.add(i, ((FRUITS) fruitsObjects.get(i).getObjectType()));

                FRUIT_PATH.add(i, CurrentFruit.get(i).getIdle());
                FRUIT_SLICED_PATH.add(i, CurrentFruit.get(i).getSliced());
                FRUIT_INVERSE_PATH.add(i, CurrentFruit.get(i).getSlicedInverse());

                fruit.add(i, new ImageView(FRUIT_PATH.get(i)));
                fruitSliced.add(i, new ImageView(FRUIT_SLICED_PATH.get(i)));
                fruitInverse.add(i, new ImageView(FRUIT_INVERSE_PATH.get(i)));

                fruit.get(i).setFitWidth(100);
                fruit.get(i).setFitHeight(100);
                fruitSliced.get(i).setFitWidth(100);
                fruitSliced.get(i).setFitHeight(100);
                fruitInverse.get(i).setFitWidth(100);
                fruitInverse.get(i).setFitHeight(100);
                fruitsObjects.get(i).setIsReachedMaxHeight(false);
                fruitsObjects.get(i).setSlicedFromGui(false);
                setNewElementPosition(fruit.get(i));
                gamePane.getChildren().add(fruit.get(i));
            }
    }

    private void moveElements() {

            for (int i = 0; i < fruit.size(); i++) {
                if (!fruitsObjects.get(i).isSliced()) {
                    if (!fruitsObjects.get(i).getIsReachedMaxHeight()) {

                        if (fruit.get(i).getLayoutY() > gameEngine.createGameObject().getMaxHeight()) {
                            fruitsObjects.get(i).setIsReachedMaxHeight(false);
                            fruit.get(i).setLayoutY(fruit.get(i).getLayoutY() - fruitsObjects.get(i).getInitialVelocity());
                            fruit.get(i).setLayoutX(fruit.get(i).getLayoutX() + 1);
                            fruit.get(i).setRotate(fruit.get(i).getRotate() + 5);
                        } else {
                            fruit.get(i).setLayoutX(fruit.get(i).getLayoutX() + 5);
                            fruitsObjects.get(i).setIsReachedMaxHeight(true);
                        }
                    } else {
                        fruit.get(i).setLayoutY(fruit.get(i).getLayoutY() + fruitsObjects.get(i).getInitialVelocity());
                        fruit.get(i).setLayoutX(fruit.get(i).getLayoutX() + 1);
                        fruit.get(i).setRotate(fruit.get(i).getRotate() + 5);
                        if (fruit.get(i).getLayoutY() > 701 && fruit.get(i).getLayoutY() <= 704 && !gamePane.getChildren().contains(fruitSliced.get(i))) {
                            fruit.remove(fruit.get(i));
                            CurrentFruit.remove(CurrentFruit.get(i));
                            FRUIT_PATH.remove(FRUIT_PATH.get(i));
                            fruitsObjects.remove(fruitsObjects.get(i));
                            i=0;
                        }
                    }
                }
            }
    }

    public void checkLife(){
        if(numberOfLifes==0){
            gametimer.stop();
            time.stopAnimation();
//            System.out.println("GameOver");
        }
}

    private void ImageEVENT(){
        Canvas canvas = new Canvas(1200, 700);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
            if(time.getState()==false)
            {
                System.out.println(score1.getTmp());
                gameStage.close();
                ViewManger.mainstage.show();
                gametimer.stop();
            }

            gamePane.setOnMousePressed(e->{
                graphicsContext.beginPath();
                graphicsContext.moveTo(e.getX(), e.getY());
                graphicsContext.stroke();
            });
            gamePane.setOnMouseReleased(e->{
                graphicsContext.clearRect(0,0,1200,700);
            });
        gamePane.setOnMouseDragged(event -> {



//            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>(){

//                        @Override
//                        public void handle(MouseEvent event) {
//                            graphicsContext.beginPath();
//                            graphicsContext.moveTo(event.getX(), event.getY());
//                            graphicsContext.stroke();
//                        }
//                    });

//            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                    new EventHandler<MouseEvent>(){

//                        @Override
//                        public void handle(MouseEvent event) {
                            System.out.println("Drrrrr");
                            graphicsContext.lineTo(event.getX(), event.getY());
                            graphicsContext.stroke();

//                        }
//                    });

//            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
//                    new EventHandler<MouseEvent>(){
//
//                        @Override
//                        public void handle(MouseEvent event) {
//
//                        }
//                    });

            StackPane root = new StackPane();
            root.getChildren().add(canvas);
            gamePane.getChildren().add(root);
//            Scene scene = new Scene(root, 400, 400);
//            gameStage.setTitle("java-buddy.blogspot.com");
//            gameStage.setScene(scene);
//            gameStage.show();




            for(int i=0; i<fruit.size(); i++) {
                if (Math.abs(event.getSceneX() - fruit.get(i).getLayoutX()) < 100) {
                    if (Math.abs(event.getSceneY() - fruit.get(i).getLayoutY()) < 50) {



                        FRUIT_SLICED_PATH.set(i, CurrentFruit.get(i).getSliced());
                        fruitSliced.get(i).setImage(new Image(FRUIT_SLICED_PATH.get(i)));
                        FRUIT_INVERSE_PATH.set(i, CurrentFruit.get(i).getSlicedInverse());
                        fruitInverse.get(i).setImage(new Image(FRUIT_INVERSE_PATH.get(i)));

                        fruitSliced.get(i).setLayoutY(fruit.get(i).getLayoutY());
                        fruitSliced.get(i).setLayoutX(fruit.get(i).getLayoutX() + 70);
                        fruitSliced.get(i).setRotate(30);
                        fruitInverse.get(i).setRotate(30);
                        fruitInverse.get(i).setLayoutY(fruit.get(i).getLayoutY());
                        fruitInverse.get(i).setLayoutX(fruit.get(i).getLayoutX() - 70);

                        if (!fruitsObjects.get(i).isSliced()) {
                            gamePane.getChildren().addAll(fruitSliced.get(i), fruitInverse.get(i));
                        }
                        fruitsObjects.get(i).setSlicedFromGui(true);
                        for(int j=0; j<fruit.size(); j++)
                        gamePane.getChildren().remove(fruit.get(i));
                        moveFruitDown();

                    }
                }
            }
                            event.setDragDetect(false);
                    });
    }

    private void moveFruitDown(){
        for(int i=0; i<fruit.size();i++) {
            if(fruitsObjects.get(i).isSliced()) {

                if (fruitSliced.get(i).getLayoutY() > 750) {
                    fruit.remove(fruit.get(i));
                    fruitsObjects.remove(fruitsObjects.get(i));

                    CurrentFruit.remove(CurrentFruit.get(i));
                    FRUIT_PATH.remove(FRUIT_PATH.get(i));
                    gamePane.getChildren().removeAll(fruitSliced.get(i), fruitInverse.get(i));
                    fruitSliced.remove(fruitSliced.get(i));
                    fruitInverse.remove(fruitInverse.get(i));
                    i=0;

                    gameEngine.sliceObjects();
                    Score = gameEngine.getScore();
                    difficuly.setScore(Score);
                } else {
                    fruitSliced.get(i).setLayoutY(fruitSliced.get(i).getLayoutY() + fruitsObjects.get(i).getFallingVelocity());
                    fruitInverse.get(i).setLayoutY(fruitInverse.get(i).getLayoutY() + fruitsObjects.get(i).getFallingVelocity());
                    fruitInverse.get(i).setRotate(fruitInverse.get(i).getRotate() - 2);
                    fruitSliced.get(i).setRotate(fruitInverse.get(i).getRotate() + 2);
                    fruitSliced.get(i).setLayoutX(fruitSliced.get(i).getLayoutX() + 2);
                    fruitInverse.get(i).setLayoutX(fruitInverse.get(i).getLayoutX() - 2);
                }
            }
        }
        if(fruitSliced.isEmpty() && fruitInverse.isEmpty()) {
            createGameelements();
            IAlreadyCreated = true;
        }
    }
    private void checkIfElementsBelowScreen() {
            for (int i = 0; i < fruit.size(); i++) {
                if (fruit.get(i).getLayoutY() >= 700) {
                    CurrentFruit.remove(CurrentFruit.get(i));
                    FRUIT_PATH.remove(FRUIT_PATH.get(i));
                    fruit.remove(fruit.get(i));
                    fruitsObjects.remove(fruitsObjects.get(i));
                    if(Case==0) {
                        numberOfLifes -= 1;
                        classicMode.setNumberOfLifes(numberOfLifes);
                    }
                }
            }
        if(fruit.isEmpty()) {
            for(int j=0; j<fruitSliced.size(); j++){
                fruitSliced.remove(fruitSliced.get(j));
                fruitInverse.remove(fruitInverse.get(j));

            }
            createGameelements();
        }
        }


    private void initDraw(GraphicsContext gc){

        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
//        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

    }
}

