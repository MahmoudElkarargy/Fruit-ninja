package View;

import java.awt.Label;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Logic.Score;
import MainPackage.FRUITS;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//bombs aren't added correctly yet.., they appear in the x direction only and nothing happens when clicked (should be when sliced too)


public class ArcadeGame extends Application{

	private static AnchorPane ArcadePane;
	private Scene ArcadeScene ;
    private Stage ArcadeStage, MenuStage ;
    private static final int width= 1000;
    private static final int height =700;
    private GridPane gridpane1;
    private GridPane gridpane2;
    private int space=0;
    private final String PATH = "View/resources/BonusObjects/Life.png";
    private final static String Game_Background = "View/resources/GameBackground.jpg";

    private AnimationTimer Arcadetimer;

    private static String FruitOne = "View/resources/Arcade.png";
    private final static String FruitTwo = "View/resources/Classic.png";
    private ImageView Bomb;
    private static String BOMB_PATH= ("View/resources/BonusObjects/boom.png");
    //= "View/resources/BonusObjects/boom.png";
    private ImageView [] fruitone;
    private ImageView [] fruittwo;
    private ImageView [] bombs;
    private ImageView [] lives;
    private ImageView closeButton,help,save,Sound;
    Random randompositiongenerator ;
    Alert alert = new Alert(AlertType.CONFIRMATION);
    ButtonType buttonTypeOne = new ButtonType("MainMenu");
    ButtonType buttonTypeTwo = new ButtonType("AnotherGame");
    GameEngine gameEngine = GameEngine.getInstance();
    Clock timer = new Clock();
    private boolean isMusicClicked = false;
    private int Score;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private FRUITS CurrentFruit;
    public int numberOfLifes=3;

    private String FRUIT_PATH,FRUIT_SLICED_PATH, FRUIT_INVERSE_PATH;
    private ImageView fruit,fruitSliced, fruitInverse;
    private Boolean isSliced=false, ReachedMaxHeight=false;
 
  
    
    public ArcadeGame(){
    	
    	//game.getChildren().addAll(gameover);
    	ArcadePane= new AnchorPane();
    	this.ArcadePane = GameViewManger.getGamePain();
       inializeStage();
       inializeFruits();
       createKeyListeners();
        randompositiongenerator = new Random();
        
    }
    private void inializeStage() {
        ArcadePane = new AnchorPane();
        ArcadeScene = new Scene(ArcadePane, width,height);
        ArcadeStage = new Stage ();
        ArcadeStage.setScene(ArcadeScene);
     
        createIcons();
      
    }
    
    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
        save = new ImageView("View/resources/Icons/save.png");
        Sound = new ImageView(soundOn);
        ArcadePane.getChildren().add(timer);
      //  ArcadePane.getChildren().add(score1);

        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
        	ArcadeStage.close();
            //ViewManger.mainstage.show();
            //Arcadetimer.stop();
          //  gameEngine.saveScore();
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

        save.setLayoutX(10);
        save.setLayoutY(50);
        save.setOnMouseClicked(e->{

        });
        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
        save.setOnMouseExited(e->{ save.setEffect(null); });
        
        ArcadePane.getChildren().addAll(closeButton,help,save,Sound);

    }

    
 
   private void createCaseloop(){
        Arcadetimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
               // moveBackground();
            	  moveElements();
                  checkIfElementsBelowScreen();
                  ImageEVENT();
                
                  moveFruitDown();
            }


        };
      
        Arcadetimer.start();
    }
    
   private void setNewElementPosition(ImageView image){
       image.setLayoutY(gameEngine.createGameObject().getYlocation());
       image.setLayoutX(gameEngine.createGameObject().getXlocation());
   }
   public static AnchorPane getGamePain(){
       return ArcadePane;
   }

    private void createKeyListeners() {
        ArcadeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.LEFT){}
                else if (event.getCode()== KeyCode.RIGHT){};
            }
         });
      
    }
    private void ImageEVENT(){
        if(timer.getState()==false)
        {
            ArcadeStage.close();
            ViewManger.mainstage.show();
            Arcadetimer.stop();
        }
        ArcadePane.setOnMouseDragged(event -> {
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
                                	ArcadePane.getChildren().addAll(fruitSliced,fruitInverse);
                                }
                                isSliced = true;
                                ArcadePane.getChildren().remove(fruit);
                                moveFruitDown();
                            }
                        }
                        event.setDragDetect(false);
                });
}
    

    private void createGameelements () {
    	
        fruit = new ImageView(FRUIT_PATH);
        Bomb = new ImageView(BOMB_PATH);
        fruitSliced = new ImageView(FRUIT_SLICED_PATH);
        fruitInverse = new ImageView(FRUIT_INVERSE_PATH);
        fruit.setFitWidth(100);
        fruit.setFitHeight(100);
        fruitSliced.setFitHeight(100);
        fruitSliced.setFitWidth(100);
        fruitInverse.setFitHeight(100);
        fruitInverse.setFitWidth(100);
        setNewElementPosition(fruit);
        ArcadePane.getChildren().addAll(fruit,Bomb);
       

    }
    

    private void moveElements() {
        if(!isSliced) {
            if (!ReachedMaxHeight) {
                if (fruit.getLayoutY() > gameEngine.createGameObject().getMaxHeight() && Bomb.getLayoutY() > gameEngine.createGameObject().getMaxHeight()) {
                    ReachedMaxHeight = false;
                    fruit.setLayoutY(fruit.getLayoutY() - 3);
                    fruit.setLayoutX(fruit.getLayoutX() + 1);
                    fruit.setRotate(fruit.getRotate() + 5);
                    Bomb.setLayoutY(Bomb.getLayoutY() - 3);
                    Bomb.setLayoutX(Bomb.getLayoutX() + 1);
                    Bomb.setRotate(Bomb.getRotate() + 5);
                    
                    
                } else {
                    fruit.setLayoutX(fruit.getLayoutX() + 5);
                    Bomb.setLayoutX(Bomb.getLayoutX() + 5);
                    ReachedMaxHeight = true;
                }
            }
            else {
                fruit.setLayoutY(fruit.getLayoutY() + 3);
                fruit.setLayoutX(fruit.getLayoutX() + 1);
                fruit.setRotate(fruit.getRotate() + 5);
                Bomb.setLayoutY(Bomb.getLayoutY() + 3);
                Bomb.setLayoutX(Bomb.getLayoutX() + 1);
                Bomb.setRotate(Bomb.getRotate() + 5);
                if (fruit.getLayoutY() > 701 && fruit.getLayoutY() <= 704 && !ArcadePane.getChildren().contains(fruitSliced)) {
                	{
                		numberOfLifes -= 1;
                		// arcadeMode.setNumberOfLifes(numberOfLifes);
                	}
                   // if (Bomb.getLayoutY() > 701 && Bomb.getLayoutY() <= 704 && !ArcadePane.getChildren().contains(fruitSliced))
                //.   System.out.println("byee");
                 //   arcadeGame.setNumberOfLifes(numberOfLifes);
                    
                }
            }
        }
    }
        
      

    private void moveFruitDown(){
        if(isSliced) {

//            if (Case == 0) {
                if(fruitSliced.getLayoutY()>704) {
                    ArcadePane.getChildren().removeAll(fruitSliced,fruitInverse);
                    CurrentFruit = ((FRUITS)gameEngine.createGameObject().getObjectType());
                    FRUIT_PATH = CurrentFruit.getIdle();
                    fruit.setImage(new Image(FRUIT_PATH));
                    setNewElementPosition(fruit);
                    ArcadePane.getChildren().addAll(fruit);
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

private void inializeFruits(){
    //Creating objects.
	CurrentFruit = ((FRUITS)gameEngine.createGameObject().getObjectType());
    FRUIT_PATH = CurrentFruit.getIdle();
    FRUIT_SLICED_PATH = CurrentFruit.getSliced();
    FRUIT_INVERSE_PATH = CurrentFruit.getSlicedInverse();
    
   
}

   

   

    public void createNewGame(Stage menustage){
        this.MenuStage = menustage;
        this.MenuStage.hide();
        createBackground();
        createGameelements();
        createCaseloop();
        ArcadeStage.show();
        timer.start();
    }

    private void createBackground (){
        Image backgroundImage = new Image("View/resources/GameBackground.jpg",width,height,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        ArcadePane.setBackground(new Background(background));
    }

 

@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	
}

  
	
}
