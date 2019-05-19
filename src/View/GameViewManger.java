package View;

import Logic.Difficuly;
import Logic.LineDrawing;
import Logic.Score;
import MainPackage.BOOM;
import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private AnimationTimer gametimer, boomTimer;
    private boolean isMusicClicked = false, youLostHAHA=false;
    private int Score=0;
    private ImageView lose = new ImageView();
    private int sign = -1;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private List<FRUITS> CurrentFruit = new LinkedList<FRUITS>();
    private List<BOOM> CurrentBoom = new LinkedList<BOOM>();
    public int numberOfLifes=3,flag=0;;

    private int difficltyLevel=1;
    private  int boomsLevel=0;
    private Difficuly difficuly;

    private ImageView closeButton,help,save,Sound, playAgian;
    private String BOOM_PATH, EXPL1, EXPL2, EXPL3, txtEXPL;
    private List<String> FRUIT_PATH = new LinkedList<String>();
    private List<String> FRUIT_SLICED_PATH = new LinkedList<String>();
    private List<String> FRUIT_INVERSE_PATH = new LinkedList<String>();

    private List<ImageView> boom = new LinkedList<ImageView>();
    private List<ImageView> fruit = new LinkedList<ImageView>();
    private List<ImageView> fruitSliced = new LinkedList<ImageView>();
    private List<ImageView> fruitInverse = new LinkedList<ImageView>();
    private List<Fruits> fruitsObjects = new LinkedList<Fruits>();
<<<<<<< HEAD
    private List<Booms> boomObject = new LinkedList<Booms>();
    private int Case;
    private Clock time = new Clock();
    private Score score1 = Logic.Score.getInstance();

    private GameEngine gameEngine = GameEngine.getInstance();
=======
     int Case;
    ClockTimer time = new ClockTimer();
    Score score1 = Logic.Score.getInstance();
    ClockStopWatch watch = new ClockStopWatch();
    LineDrawing lineDrawing ;
    GameEngine gameEngine = GameEngine.getInstance();
>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305
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
        playAgian = new ImageView("View/resources/Icons/playAgian.png");
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
        save.setLayoutY(90);
        save.setOnMouseClicked(e->{

        });
        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
        save.setOnMouseExited(e->{ save.setEffect(null); });



        playAgian.setLayoutX(10);
        playAgian.setLayoutY(50);

        playAgian.setOnMouseEntered(e->{ playAgian.setEffect(new Glow()); });
        playAgian.setOnMouseExited(e->{ playAgian.setEffect(null); });
        playAgian.setOnMouseClicked(e->{
            resetFunction();
        });

        gamePane.getChildren().addAll(closeButton,help,save,Sound,playAgian);

    }

    private void resetFunction(){
        gameEngine.ResetGame();
        if(gamePane.getChildren().contains(lose))
            gamePane.getChildren().remove(lose);
        for(int i=0; i<fruit.size();i++) {
            gamePane.getChildren().remove(fruit.get(i));
            fruit.remove(fruit.get(i));
            CurrentFruit.remove(CurrentFruit.get(i));
            FRUIT_PATH.remove(FRUIT_PATH.get(i));
            fruitsObjects.remove(fruitsObjects.get(i));
        }
        for(int i=0; i<boom.size();i++) {
            gamePane.getChildren().remove(boom.get(i));
            boom.remove(boom.get(i));
            CurrentBoom.remove(CurrentBoom.get(i));
            boomObject.remove(boomObject.get(i));
        }
        for(int i=0; i<fruitSliced.size();i++) {
            gamePane.getChildren().remove(fruitSliced.get(i));
            gamePane.getChildren().remove(fruitInverse.get(i));
            fruitSliced.remove(fruitSliced.get(i));
            fruitInverse.remove(fruitInverse.get(i));
            FRUIT_SLICED_PATH.remove(FRUIT_SLICED_PATH.get(i));
            FRUIT_INVERSE_PATH.remove(FRUIT_INVERSE_PATH.get(i));
        }
        numberOfLifes = 3;
        classicMode.setNumberOfLifes(numberOfLifes);
        difficuly.setScore(Score);
        setDiffculty();
        createGameelements();
        createBooms();
        gametimer.stop();
        gametimer.start();
        if(youLostHAHA)
            boomTimer.stop();
        youLostHAHA = false;

        if(gamePane.getChildren().contains(lose))
             gamePane.getChildren().remove(lose);
        if(!gamePane.getChildren().contains(save))
            gamePane.getChildren().addAll(save);
    }

    private void setDiffculty(){
        difficltyLevel = difficuly.getDifficulyLevel();
        boomsLevel =1;
    }

    public void createNewGame(Stage menustage,int Case) {
        this.menustage = menustage;
        this.menustage.hide();
        this.Case = Case;

        gameEngine.ResetGame();
        lineDrawing = new LineDrawing(gameScene);
        gamePane.getChildren().add(lineDrawing);
        lineDrawing.Draw();
        createBackground();
        setDiffculty();
        createGameelements();
        createBooms();
        createCaseloop(Case);
        gameStage.show();
        if(this.Case==1) {
            gameEngine.ReseetClockTimer(time,1);
            gameEngine.ResertStopWatch(watch);
            watch.stopAnimation();
//            time.reset();
//            time.startAnimation();


            classicMode.removeLifes();
            this.numberOfLifes=3;
            classicMode.reset_space();
            gamePane.getChildren().remove(watch);
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
            gameEngine.ReseetClockTimer(time,0);
            gameEngine.ResertStopWatch(watch);
            classicMode.removeLifes();
            watch.startAnimation();
            gamePane.getChildren().remove(watch);
            gamePane.getChildren().add(watch);

            this.numberOfLifes=3;
           classicMode.reset_space();
            classicMode.creatLifeNumbers(numberOfLifes);
            gamePane.getChildren().remove(time);
            gamePane.getChildren().remove(closeButton);
            gamePane.getChildren().add(closeButton);
            gamePane.getChildren().remove(save);
            gamePane.getChildren().add(save);
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
<<<<<<< HEAD
                boomExplosion();
=======

>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305
                if(Case ==0){
                checkLife();
                }
            }
        };
            gametimer.start();
    }

    private void setNewElementPosition(ImageView image){
        image.setLayoutY(gameEngine.createGameObject(0).getYlocation());
        image.setLayoutX(gameEngine.createGameObject(0).getXlocation());
    }
    private void setNewElementPositionBOOM(ImageView image){
        image.setLayoutY(gameEngine.createGameObject(1).getYlocation());
        image.setLayoutX(gameEngine.createGameObject(1).getXlocation());
    }

    public static AnchorPane getGamePain(){
        return gamePane;
    }



    private void createGameelements () {
            for (int i = 0; i < difficltyLevel; i++) {
                fruitsObjects.add(i, (Fruits) gameEngine.createGameObject(0));
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

    private void createBooms(){
        System.out.println("Number of Booms: "+boomsLevel);
        if(boomsLevel!=0){
            for (int i=0; i<boomsLevel; i++){
                boomObject.add(i, (Booms) gameEngine.createGameObject(1));
                boomObject.get(i).setSlicedFromGui(false);
                CurrentBoom.add(i, ((BOOM) boomObject.get(i).getObjectType()));

                BOOM_PATH = CurrentBoom.get(i).getIdle();
                EXPL1 = CurrentBoom.get(i).getExplosion1();
                EXPL2 = CurrentBoom.get(i).getExplosion2();
                EXPL3 = CurrentBoom.get(i).getExplosion3();
                txtEXPL = CurrentBoom.get(i).getTxt();
                boom.add(i, new ImageView(BOOM_PATH));

                boom.get(i).setFitWidth(100);
                boom.get(i).setFitHeight(150);
                boomObject.get(i).setIsReachedMaxHeight(false);
                boomObject.get(i).setSlicedFromGui(false);
                setNewElementPositionBOOM(boom.get(i));
                gamePane.getChildren().add(boom.get(i));
            }
        }
    }
    private void moveElements() {

            for (int i = 0; i < fruit.size(); i++) {
                if (!fruitsObjects.get(i).isSliced()) {
                    if (!fruitsObjects.get(i).getIsReachedMaxHeight()) {

                        if (fruit.get(i).getLayoutY() > gameEngine.createGameObject(0).getMaxHeight()) {
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



        if(boomsLevel!=0){
            for (int i = 0; i < boomObject.size(); i++) {
                if (!boomObject.get(i).isSliced()) {
                    if (!boomObject.get(i).getIsReachedMaxHeight()) {

                        if (boom.get(i).getLayoutY() > gameEngine.createGameObject(1).getMaxHeight()) {
                            boomObject.get(i).setIsReachedMaxHeight(false);
                            boom.get(i).setLayoutY(boom.get(i).getLayoutY() - boomObject.get(i).getInitialVelocity());
                            boom.get(i).setLayoutX(boom.get(i).getLayoutX() + 1);
                            boom.get(i).setRotate(boom.get(i).getRotate() + 5);
                        } else {
                            boom.get(i).setLayoutX(boom.get(i).getLayoutX() + 5);
                            boomObject.get(i).setIsReachedMaxHeight(true);
                        }
                    } else {
                        boom.get(i).setLayoutY(boom.get(i).getLayoutY() + boomObject.get(i).getInitialVelocity());
                        boom.get(i).setLayoutX(boom.get(i).getLayoutX() + 1);
                        boom.get(i).setRotate(boom.get(i).getRotate() + 5);
                        if (boom.get(i).getLayoutY() > 705 ) {
                            boom.remove(boom.get(i));
                            CurrentBoom.remove(CurrentBoom.get(i));
                            boomObject.remove(boomObject.get(i));
                            i=0;
                        }
                    }
                }
            }

        }

    }

    public void checkLife(){
        if(numberOfLifes==0){
            gametimer.stop();
            time.stopAnimation();
<<<<<<< HEAD
            boomTimer = new AnimationTimer(){
                @Override
                public void handle(long l) {
                    boomExplosion();
                    for (int i=0; i<boom.size(); i++) {
                        if (boomObject.get(i).isSliced())
                            flag = 1;
                    }
                    if (flag==0)
                        youLostHAHA = true;
                    lostWindow();
                }
            };
            boomTimer.start();
=======
            watch.stopAnimation();
//            System.out.println("GameOver");
>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305
        }

    }

    private void lostWindow(){
        if(youLostHAHA){
            for (int i=0; i<boom.size();i++)
                gamePane.getChildren().remove(boom.get(i));

            lose.setImage(new Image("View/resources/Ninja/NinjaLose.png"));
            lose.setLayoutX(400);
            lose.setLayoutY(200);
            if(!gamePane.getChildren().contains(lose))
                gamePane.getChildren().addAll(lose);
        }
    }

    private void ImageEVENT(){
<<<<<<< HEAD
//        Canvas canvas = new Canvas(1200, 700);
//        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        initDraw(graphicsContext);
=======

>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305

            if(time.getState()==false)
            {
                System.out.println(score1.getTmp());
                gameStage.close();
                ViewManger.mainstage.show();
                gametimer.stop();
            }

//            gamePane.setOnMousePressed(e->{
//                graphicsContext.beginPath();
//                graphicsContext.moveTo(e.getX(), e.getY());
//                graphicsContext.stroke();
//            });
//            gamePane.setOnMouseReleased(e->{
//                graphicsContext.clearRect(0,0,1200,700);
//            });
<<<<<<< HEAD

        gamePane.setOnMouseDragged(event -> {
=======
>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305

        gamePane.setOnMouseDragged(event -> {

<<<<<<< HEAD

//                            System.out.println("Drrrrr");
//                            graphicsContext.lineTo(event.getX(), event.getY());
//                            graphicsContext.stroke();

=======
//            System.out.println("Drrrrr");
//                            graphicsContext.lineTo(event.getX(), event.getY());
//                            graphicsContext.stroke();
//
>>>>>>> 1cbd80f80388534decf24555199f347f23bd8305

//            StackPane root = new StackPane();
//            root.getChildren().add(canvas);
//            gamePane.getChildren().add(root);


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


            for(int i=0; i<boom.size(); i++) {
                if (Math.abs(event.getSceneX() - boom.get(i).getLayoutX()) < 100) {
                    if (Math.abs(event.getSceneY() - boom.get(i).getLayoutY()) < 50) {

                        boom.get(i).setImage(new Image(EXPL1));
                        boomObject.get(i).setSlicedFromGui(true);
                        if(!fruit.isEmpty()){
                            for(int j=0; j<fruit.size(); j++){
                                gamePane.getChildren().removeAll(fruit.get(j));
                            }
                        }
                        for(int j=0; j<fruitSliced.size(); j++){
                            gamePane.getChildren().removeAll(fruitSliced.get(j));
                            gamePane.getChildren().removeAll(fruitInverse.get(j));
                        }

                        classicMode.removeLifes();
                        numberOfLifes =0;
                        classicMode.setNumberOfLifes(numberOfLifes);
                        gamePane.getChildren().remove(save);
                    }
                }
            }
                            event.setDragDetect(false);
                    });
    }

    private void boomExplosion() {
        for (int i = 0; i < boom.size(); i++) {
            if (boomObject.get(i).isSliced()) {
                if(boom.get(i).getFitHeight() < 200){
                    boom.get(i).setFitHeight(boom.get(i).getFitHeight() + 2);
                    boom.get(i).setFitWidth(boom.get(i).getFitWidth() + 1);
                }
                if(boom.get(i).getFitHeight() >= 200 && boom.get(i).getFitHeight() < 300){
                    boom.get(i).setRotate(0);
                    boom.get(i).setImage( new Image(EXPL2));
                    boom.get(i).setFitHeight(boom.get(i).getFitHeight() + 2);
                    boom.get(i).setFitWidth(boom.get(i).getFitWidth() + 1);
                }
                if(boom.get(i).getFitHeight() >= 300 && boom.get(i).getFitHeight() < 500){
                    boom.get(i).setImage( new Image(EXPL3));
                    boom.get(i).setLayoutY(boom.get(i).getLayoutY() - 5);
                    boom.get(i).setFitHeight(boom.get(i).getFitHeight() + 5);
                    boom.get(i).setFitWidth(boom.get(i).getFitWidth() + 3);
                }

                if(boom.get(i).getFitHeight() >= 500 && boom.get(i).getFitHeight() <= 700){

                    boom.get(i).setImage( new Image(txtEXPL));
                    boom.get(i).setLayoutX(200);
                    boom.get(i).setLayoutY(50);
                    boom.get(i).setFitHeight(boom.get(i).getFitHeight() + 2);
                    boom.get(i).setFitWidth(boom.get(i).getFitWidth() + 5);
                }
                if(boom.get(i).getFitHeight() >= 700)
                    youLostHAHA =true;
            }
        }
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


        for (int i = 0; i < boom.size(); i++) {
            if (boom.get(i).getLayoutY() >= 810) {
                CurrentBoom.remove(CurrentBoom.get(i));
                boom.remove(boom.get(i));
                boomObject.remove(boomObject.get(i));
            }
        }
        if(boom.isEmpty()) {
            createBooms();
        }
    }


//    private void initDraw(GraphicsContext gc){
//
//        double canvasWidth = gc.getCanvas().getWidth();
//        double canvasHeight = gc.getCanvas().getHeight();
//
//        gc.setFill(Color.LIGHTGRAY);
////        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(5);
//
//        gc.fill();
//        gc.strokeRect(
//                0,              //x of the upper left corner
//                0,              //y of the upper left corner
//                canvasWidth,    //width of the rectangle
//                canvasHeight);  //height of the rectangle
//
//        gc.setFill(Color.RED);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(1);
//
//    }
}

