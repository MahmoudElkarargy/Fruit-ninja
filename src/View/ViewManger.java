package View;

import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ViewManger {
    private AnchorPane mainpane ;
    private Scene mainscene;
    private Stage mainstage;
    private final static int GAMEWIDTH = 1024;
    private final static int GAMEHIGHT = 700;
    private AnimationTimer gameTimer;
    private GridPane gridPane1,gridPane2;
    ImageView classicButton, arcadeButton, GameZoneButton, closeButton, helpButton, logo;

    public ViewManger(){
        mainpane=new AnchorPane();
        mainscene= new Scene(mainpane, 1024,700);
        mainstage= new Stage();
        mainstage.setScene(mainscene);
        createButton();
        creatLogo();
        createBackground();
        creatGameLoop();
    }

    public Stage getMainstage() {
        return mainstage;
    }

    private  void createButton(){
        createClassicbutton();
       createArcadebutton();
        createGameZonebutton();
        createHelpbutton();
//       createClosebutton();
    }

    private void createClassicbutton(){
        classicButton = new ImageView("View/resources/Classic.png");
        classicButton.setLayoutX(50);
        classicButton.setLayoutY(GAMEHIGHT-500);
        classicButton.setOnMouseEntered(e->{ classicButton.setEffect(new DropShadow()); });
        classicButton.setOnMouseExited(e->{ classicButton.setEffect(null); });
        classicButton.setOnMouseClicked(e->{ GameViewManger gamemanger = new GameViewManger();
                        gamemanger.createNewGame(mainstage);
            System.out.println("Classic Game will open here");
        });
//        mainpane.getChildren().add(classicButton);
    }

    private void createArcadebutton(){
        arcadeButton = new ImageView("View/resources/Arcade.png");
        arcadeButton.setLayoutX(50);
        arcadeButton.setLayoutY(GAMEHIGHT-220);
        arcadeButton.setOnMouseEntered(e->{ arcadeButton.setEffect(new DropShadow()); });
        arcadeButton.setOnMouseExited(e->{ classicButton.setEffect(null); });
        arcadeButton.setOnMouseClicked(e->{
            System.out.println("arcade Game will open here");
        });
//        mainpane.getChildren().add(classicButton);
    }

    private void createGameZonebutton(){
        GameZoneButton = new ImageView("View/resources/GameZone.png");
        GameZoneButton.setLayoutX(750);
        GameZoneButton.setLayoutY(GAMEHIGHT-500);
        GameZoneButton.setOnMouseEntered(e->{ GameZoneButton.setEffect(new DropShadow()); });
        GameZoneButton.setOnMouseExited(e->{ GameZoneButton.setEffect(null); });
        GameZoneButton.setOnMouseClicked(e->{
            System.out.println("Game zone will open here");
        });
//        mainpane.getChildren().add(classicButton);
    } private void createHelpbutton(){
        helpButton = new ImageView("View/resources/Help.png");
        helpButton.setLayoutX(800);
        helpButton.setLayoutY(GAMEHIGHT-220);
        helpButton.setOnMouseEntered(e->{ helpButton.setEffect(new DropShadow()); });
        helpButton.setOnMouseExited(e->{ helpButton.setEffect(null); });
        helpButton.setOnMouseClicked(e->{
            System.out.println("help will open here");
        });
//        mainpane.getChildren().add(classicButton);
    }

    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for(int i=0;i<12;i++){
            ImageView backgroundImage1 = new ImageView("View/resources/backgroundLarge.png");
            ImageView backgroundImage2 = new ImageView("View/resources/backgroundLarge.png");
            GridPane.setConstraints(backgroundImage1, i%3, 0);
            GridPane.setConstraints(backgroundImage2, i%3, 0);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutX(-1250);
        mainpane.getChildren().addAll(gridPane1,gridPane2,classicButton,arcadeButton,GameZoneButton,helpButton,logo);
    }

    private void creatLogo(){
        logo = new ImageView("View/resources/logo.png");
        logo.setLayoutX(GAMEWIDTH/2-180);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(e->{ logo.setEffect(new DropShadow()); });
        logo.setOnMouseExited(e->{ logo.setEffect(null); });
        logo.setOnMouseClicked(e->{
            System.out.println("Credit will open here");
        });
    }

    private void creatGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
            }
        };
        gameTimer.start();
    }
    private void moveBackground(){
        gridPane1.setLayoutX(gridPane1.getLayoutX() + 0.5);
        gridPane2.setLayoutX(gridPane2.getLayoutX()+0.5);
        if(gridPane1.getLayoutX()>=1250){
            gridPane1.setLayoutX(-1250);
        }
        if(gridPane2.getLayoutX() >= 1250){
            gridPane2.setLayoutX(-1250);
        }
    }
}
