package View;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class GameViewManger {
    private AnchorPane gamePane;
    private Scene gameScene ;
    private Stage gameStage ;
    private static final int GAME_WIDTH= 1024;
    private static final int GAME_HEIGHT= 600;
    private Stage menustage;

    private GridPane gridpane1;
    private GridPane gridpane2;
    private final static String Game_Background = "View/resources/GameBackground.jpg";

    private AnimationTimer gametimer;

    private final static String FRUIT_ONE = "View/resources/Arcade.png";
    private final static String FRUIT_TWO = "View/resources/GameZone.png";
    private ImageView [] fruitone;
    private ImageView [] fruittwo;
    Random randomPositionGenerator ;


    public GameViewManger(){
        inializeStage();
        randomPositionGenerator = new Random();
    }
    private void createCaseloop(){
        gametimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                moveElements();
                checkIfElementsBelowScreen();
            }


        };
            gametimer.start();
    }


    private void createGameelements () {
        fruitone = new ImageView[3];
        for (int i = 0; i < fruitone.length; i++) {
            fruitone[i] = new ImageView(FRUIT_ONE);
            setNewElementPosition(fruitone[i]);
            gamePane.getChildren().add(fruitone[i]);

        }


        fruittwo = new ImageView[3];
        for (int i = 0; i < fruittwo.length; i++) {
            fruittwo[i] = new ImageView(FRUIT_TWO);
            setNewElementPosition(fruittwo[i]);
            gamePane.getChildren().add(fruittwo[i]);

        }
    }
    private void moveElements() {
        for (int i = 0; i < fruitone.length; i++) {
            fruitone[i].setLayoutY(fruitone[i].getLayoutY() - 5);
            fruitone[i].setRotate(fruitone[i].getRotate() + 4);
            fruitone[i].setLayoutX(fruitone[i].getLayoutX() +1);
            //fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
        }
//        for (int i = 0; i < fruitone.length; i++) {
//           // fruitone[i].setLayoutY(fruitone[i].getLayoutY() - 7);
//            fruitone[i].setRotate(fruitone[i].getRotate() + 4);
//            fruitone[i].setLayoutX(fruitone[i].getLayoutX() +1);
//            fruitone[i].setLayoutY(fruitone[i].getLayoutY() + 7);
//        }
        for (int i = 0; i < fruitone.length; i++) {
            fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() - 5);
            fruittwo[i].setRotate(fruittwo[i].getRotate() + 4);
            fruittwo[i].setLayoutX(fruittwo[i].getLayoutX() + 1);
           // fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);
        }
 //      for (int i = 0; i < fruitone.length; i++) {
//            //fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() - 7);
//            fruittwo[i].setRotate(fruittwo[i].getRotate() + 4);
//            fruittwo[i].setLayoutX(fruittwo[i].getLayoutX() + 1);
//           fruittwo[i].setLayoutY(fruittwo[i].getLayoutY() + 7);
//        }
    }
private void checkIfElementsBelowScreen(){

             for (int i =0 ; i< fruitone.length; i++){
                 if(fruitone[i].getLayoutY()>600){
                     setNewElementPosition(fruitone[i]);
                 }
             }
                  for (int i =0 ; i< fruittwo.length; i++){
                 if(fruittwo[i].getLayoutY()>600){
                     setNewElementPosition(fruittwo[i]);
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
        gridpane1 = new GridPane();
        gridpane2 = new GridPane();
        for (int i = 0 ; i<12 ;i++){
            ImageView backgroundimage1 = new ImageView(Game_Background);
            ImageView backgroundimage2 = new ImageView(Game_Background);
            GridPane.setConstraints(backgroundimage1,i%3,i/3);
            GridPane.setConstraints(backgroundimage2,i%3,i/3);
            gridpane1.getChildren().add(backgroundimage1);
            gridpane2.getChildren().add(backgroundimage2);
        }
            gridpane2.setLayoutY(-600);
            gamePane.getChildren().addAll(gridpane1,gridpane2);
}

}
