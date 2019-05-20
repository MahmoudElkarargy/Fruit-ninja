package View;

import java.util.ArrayList;
import java.util.List;


import Logic.Save_File_name;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ViewManger {
    private AnchorPane mainpane ;
    private Scene mainscene;
    public static Stage mainstage;
    private final static int GAMEWIDTH = 1024;
    private final static int GAMEHIGHT = 700;
    private AnimationTimer gameTimer;
    private GridPane gridPane1,gridPane2;
    private CreateSubScene newSubScene;
    private static CreateSubScene ScoreLeaderBoard;
    private CreateSubScene sceneToHide;
    private ImageView classicButton, arcadeButton, GameZoneButton, closeButton, helpButton, logo;
    private GameViewManger gameManger = new GameViewManger();
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
    private int Case =0;
    private static ViewManger viewManger = null;
    public static ViewManger getInstance(){
        if (viewManger==null)
            viewManger = new ViewManger();
        return viewManger;
    }
    private ViewManger(){
        mainpane=new AnchorPane();
        mainscene= new Scene(mainpane, 1024,700);
        mainstage= new Stage();
        mainstage.setScene(mainscene);
        effect2.setInput(effect1);
        createButton();
        creatLogo();
        createSubScene();

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
       createClosebutton();
    }

    private void createClassicbutton(){

//        gameManger = new GameViewManger();
        classicButton = new ImageView("View/resources/Classic.png");
        classicButton.setLayoutX(80);
        classicButton.setLayoutY(GAMEHIGHT-600);
        classicButton.setOnMouseEntered(e->{ classicButton.setEffect(effect2); });
        classicButton.setOnMouseExited(e->{ classicButton.setEffect(null); });
        classicButton.setOnMouseClicked(e->{
//            gameManger.createNewGame(mainstage,0);
            System.out.println("Classic Game will open here");
            mainpane.getChildren().remove(newSubScene);
            this.Case = 0;
            createSubScene();
            showSubScene(newSubScene);
        });
    }

    private void createArcadebutton(){
//        gameManger = new GameViewManger();
        arcadeButton = new ImageView("View/resources/Arcade.png");
        arcadeButton.setLayoutX(110);
        arcadeButton.setLayoutY(GAMEHIGHT-250);
        arcadeButton.setOnMouseEntered(e->{ arcadeButton.setEffect(effect2);});
        arcadeButton.setOnMouseExited(e->{ arcadeButton.setEffect(null); });
        arcadeButton.setOnMouseClicked(e->{
            mainpane.getChildren().remove(newSubScene);
            this.Case =1;
            createSubScene();
            System.out.println("arcade Game will open here");
            showSubScene(newSubScene);

        });
    }
    private void createSubScene(){

        newSubScene = new CreateSubScene(createBackground1());
        Label  n = new Label("Enter your Name");
        TextField textField = new TextField();
        textField.setLayoutX(100);
        textField.setLayoutY(170);
        Button button = new Button("OK");
        button.setLayoutX(400);
        button.setLayoutY(250);
        Save_File_name s = new Save_File_name(Case);
        button.setOnAction(e->{
            if(Case==1) {
                s.OverWrite(textField.getText());
                gameManger.createNewGame(mainstage, Case);
            }
            else if(Case==0){
                s.OverWrite(textField.getText());
                gameManger.createNewGame(mainstage, Case);
            }


        });
        n.setLayoutX(100);
        n.setLayoutY(50);
        n.setStyle("-fx-text-fill: #fff;-fx-font-size: 50px; font-weight: bold");
        newSubScene.getPane().getChildren().addAll(n,button,textField);
        mainpane.getChildren().add(newSubScene);


    }
      void  showingScores(){
        createSubSceneLeaderBoard();
        showSubScene(ScoreLeaderBoard);
     }
    private void createSubSceneLeaderBoard(){
        ImageView  closeButton1 = new ImageView("View/resources/Icons/exit.png");
        mainpane.getChildren().remove(newSubScene);
        ScoreLeaderBoard = new CreateSubScene(createBackground2());
        closeButton1.setLayoutX(10);
        closeButton1.setLayoutY(10);
        closeButton1.setOnMouseClicked(e->{
            mainpane.getChildren().remove(ScoreLeaderBoard);
            mainpane.getChildren().remove(newSubScene);
        });
//        int x = 100;
        Label l =null;
        if(Case==0)
        l = new Label("Classic Scores");
        else
            l = new Label("Arcade Scores");

        l.setLayoutX(170);
        l.setLayoutY(10);
        l.setStyle("-fx-text-fill: #FFF;-fx-font-size: 45px; font-weight: bold");
        l.setEffect(new Glow(90));
        int y = 70;
        int Increase = 30 ;
        ScoreLeaderBoard.getPane().getChildren().add(l);

        ScoreLeaderBoard.getPane().getChildren().add(closeButton1);

        ArrayList<Label> tmp ;
        Label tmplabel;
        Save_File_name s = new Save_File_name(Case);
        tmp = s.ReadFile1();
        for (int i = 0;i<tmp.size();i++){
            tmplabel = new Label((i+1) + " - ");
            tmplabel.setText(tmplabel.getText()+tmp.get(i).getText());
//            tmplabel = tmp.get(i);
            tmplabel.setLayoutX(170);
            tmplabel.setLayoutY(y);
            tmplabel.setStyle("-fx-text-fill: #FFF;-fx-font-size: 30px; font-weight: bold");
            ScoreLeaderBoard.getPane().getChildren().add(tmplabel);
            y+=Increase;
        }





//        newSubScene.getPane().getChildren().addAll(n,button,textField);
        mainpane.getChildren().add(ScoreLeaderBoard);


    }

    private void createGameZonebutton(){
        GameZoneButton = new ImageView("View/resources/GameZone.png");
        GameZoneButton.setLayoutX(650);
        GameZoneButton.setLayoutY(GAMEHIGHT-600);
        GameZoneButton.setOnMouseEntered(e->{ GameZoneButton.setEffect(effect2); });
        GameZoneButton.setOnMouseExited(e->{ GameZoneButton.setEffect(null); });
        GameZoneButton.setOnMouseClicked(e->{
            System.out.println("Game zone will open here");
            mainpane.getChildren().remove(newSubScene);
        });
    } private void createHelpbutton(){
        helpButton = new ImageView("View/resources/Help.png");
        helpButton.setLayoutX(700);
        helpButton.setLayoutY(GAMEHIGHT-250);
        helpButton.setOnMouseEntered(e->{ helpButton.setEffect(effect2); });
        helpButton.setOnMouseExited(e->{ helpButton.setEffect(null); });
        helpButton.setOnMouseClicked(e->{

//            Save_File_name s = new Save_File_name(Case);
//            s.ReadFile1();
//            showingScores();
            System.out.println("help will open here");
        });
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
        mainpane.getChildren().addAll(gridPane1,gridPane2,classicButton,arcadeButton,GameZoneButton,helpButton,logo,closeButton);
    }

    private void createClosebutton(){
        closeButton = new ImageView("View/resources/Icons/exit.png");
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(GAMEHIGHT-690);
        closeButton.setOnMouseEntered(e->{ closeButton.setEffect(new Glow()); });
        closeButton.setOnMouseExited(e->{ closeButton.setEffect(null); });
        closeButton.setOnMouseClicked(e->{
            mainstage.close();
        });
    }
    private void creatLogo(){
        logo = new ImageView("View/resources/logo.png");
        logo.setLayoutX(GAMEWIDTH/2-180);
        logo.setLayoutY(200);
        logo.setOnMouseEntered(e->{ logo.setEffect(new Bloom()); });
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
    private BackgroundImage createBackground1 (){
        Image backgroundImage = new Image("View/resources/backgroundLarge.png",700,300,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        return background;
    }
    private BackgroundImage createBackground2 (){
        Image backgroundImage = new Image("View/resources/Leaderboard-background-1.jpg",700,300,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        return background;
    }

    public void showSubScene(CreateSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;



    }

}
