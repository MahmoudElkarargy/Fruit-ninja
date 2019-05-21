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
    private boolean movable = false;
    private AnimationTimer viewTimer;
    private CreateSubScene newSubScene;
    private static CreateSubScene ScoreLeaderBoard;
    private CreateSubScene sceneToHide;
    private ImageView classicButton, arcadeButton, GameZoneButton, closeButton, helpButton, logo;
    private GameViewManger gameManger = new GameViewManger();
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
    private int Case =0;
    private ImageView ChoosenIcon = new ImageView();
    private static ViewManger viewManger = null;
    private int caseButton;
    private AnchorPane gameZonePane;
    private Scene gameZoneScene;
    private Scene BackgroundZoneScene;
    private AnchorPane backgroundZonePane;
    private ImageView ChangeBackground, ChangeBoom, ChangeCursor, ChangeLight, BackButton;
    private ImageView background1, background2, background3;

    public static ViewManger getInstance(){
        if (viewManger==null)
            viewManger = new ViewManger();
        return viewManger;
    }
    private ViewManger(){
        gameZonePane = new AnchorPane();
        gameZoneScene = new Scene(gameZonePane, 1024, 700);
        backgroundZonePane = new AnchorPane();
        BackgroundZoneScene = new Scene(backgroundZonePane, 1024, 700);

        mainpane=new AnchorPane();
        mainscene= new Scene(mainpane, 1024,700);
        mainstage= new Stage();
        mainstage.setScene(mainscene);
        effect2.setInput(effect1);
        createSubScene();
        createBackground();
        createButton();


        creatGameLoop();
        createGameZoneBackground();
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
        createCaseloop();
        creatLogo();
        creatGameLoop();
        createChangeBackground();
        createChangeBooms();
        createBackButton();
        createChangeCursor();
        createChangeLight();
        createGameZoneBackground();
        createBackgroundZoneBackground();
    }
    private void createCaseloop(){
        viewTimer = new AnimationTimer(){
            @Override
            public void handle(long l) {
                moveIcon(ChoosenIcon);
            }
        };
        viewTimer.start();
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
            ChoosenIcon = classicButton;
            mainpane.getChildren().remove(newSubScene);
            this.Case = 0;
            createSubScene();
            showSubScene(newSubScene);
            caseButton =3;
        });
        mainpane.getChildren().addAll(classicButton);

    }

    private void moveIcon(ImageView ChoosenIcon){
        this.ChoosenIcon = ChoosenIcon;
        if(movable){
            if(mainpane.getChildren().contains(helpButton)){
                mainpane.getChildren().removeAll(helpButton,logo,arcadeButton,GameZoneButton,classicButton);
                mainpane.getChildren().add(ChoosenIcon);
            }
            if(ChoosenIcon.getLayoutX() < 400 && ChoosenIcon.getFitHeight()==0){
                if(mainpane.getChildren().contains(helpButton)){
                	
                    mainpane.getChildren().removeAll(helpButton,logo,arcadeButton,GameZoneButton,classicButton);
                    mainpane.getChildren().add(ChoosenIcon);
                }
                ChoosenIcon.setLayoutX(ChoosenIcon.getLayoutX()+7);
                ChoosenIcon.setLayoutY(ChoosenIcon.getLayoutY()+2);
                ChoosenIcon.setRotate(ChoosenIcon.getRotate()+20);
//                ChoosenIcon.setFitHeight();
            }
            else if(ChoosenIcon.getLayoutX() > 400 && ChoosenIcon.getFitHeight()==0){
                ChoosenIcon.setLayoutX(ChoosenIcon.getLayoutX()-10);
                ChoosenIcon.setLayoutY(ChoosenIcon.getLayoutY()-2);
                ChoosenIcon.setRotate(ChoosenIcon.getRotate()+20);
            }
            else {
                if(ChoosenIcon.getFitHeight()<1500){
                    ChoosenIcon.setLayoutX(ChoosenIcon.getLayoutX() -9);
                    ChoosenIcon.setLayoutY(ChoosenIcon.getLayoutY() - 7);
                    ChoosenIcon.setRotate(0);
                    ChoosenIcon.setFitHeight(ChoosenIcon.getFitHeight()+20);
                    ChoosenIcon.setFitWidth(ChoosenIcon.getFitWidth()+20);

                }
                else {

                    if(caseButton==0){
                        Help helpscene = new Help();
                        helpscene.createHelpScene(mainstage);
                        movable = false;
                        createButton();
                    }
                    else if(caseButton==1){
                        mainstage.setScene(gameZoneScene);
                        movable = false;
                    }
                    else{
                        movable = false;
                        gameManger.createNewGame(mainstage, Case);
                        createButton();
                    }
                    mainpane.getChildren().remove(ChoosenIcon);
                    gameTimer.stop();
                }
            }
        }
    }
    private void createArcadebutton(){
        arcadeButton = new ImageView("View/resources/Arcade.png");
        arcadeButton.setLayoutX(110);
        arcadeButton.setLayoutY(GAMEHIGHT-250);
        arcadeButton.setOnMouseEntered(e->{ arcadeButton.setEffect(effect2);});
        arcadeButton.setOnMouseExited(e->{ arcadeButton.setEffect(null); });
        arcadeButton.setOnMouseClicked(e->{
            mainpane.getChildren().remove(newSubScene);
            this.Case =1;
            ChoosenIcon = arcadeButton;
            createSubScene();
            System.out.println("arcade Game will open here");
            showSubScene(newSubScene);
            caseButton = 3;
        });
        mainpane.getChildren().addAll(arcadeButton);
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
            }
            else if(Case==0){
                s.OverWrite(textField.getText());
            }
            mainpane.getChildren().remove(newSubScene);
            movable= true;
            moveIcon(ChoosenIcon);
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
        GameZoneButton.setLayoutY(GAMEHIGHT - 600);
        GameZoneButton.setOnMouseEntered(e -> {
            GameZoneButton.setEffect(effect2);
        });
        GameZoneButton.setOnMouseExited(e -> {
            GameZoneButton.setEffect(null);
        });
        GameZoneButton.setOnMouseClicked(e -> {

            System.out.println("Game zone will open here");
            ChoosenIcon = GameZoneButton;
            caseButton =1;
            movable= true;
            moveIcon(ChoosenIcon);


        });
        mainpane.getChildren().add(GameZoneButton);

    } private void createHelpbutton(){
        helpButton = new ImageView("View/resources/Help.png");
        helpButton.setLayoutX(700);
        helpButton.setLayoutY(GAMEHIGHT-250);
        helpButton.setOnMouseEntered(e->{ helpButton.setEffect(effect2); });
        helpButton.setOnMouseExited(e->{ helpButton.setEffect(null); });
        helpButton.setOnMouseClicked(e->{
            ChoosenIcon = helpButton;
            caseButton =0;
//            Save_File_name s = new Save_File_name(Case);
//            s.ReadFile1();
//            showingScores();
            System.out.println("help will open here");
            System.out.println(ChoosenIcon.getLayoutY()+" "+ChoosenIcon.getLayoutX());
            movable= true;
            moveIcon(ChoosenIcon);
        });

        mainpane.getChildren().addAll(helpButton);
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
        mainpane.getChildren().addAll(gridPane1,gridPane2);
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
        mainpane.getChildren().addAll(closeButton);
    }
    private void creatLogo(){
        logo = new ImageView("View/resources/logo.png");
        logo.setLayoutX(GAMEWIDTH/2-180);
        logo.setLayoutY(200);
        logo.setOnMouseEntered(e->{ logo.setEffect(new Bloom()); });
        logo.setOnMouseExited(e->{ logo.setEffect(null); });
        logo.setOnMouseClicked(e->{
        	CreditsScene credits = new CreditsScene();
        	credits.createCreditsScene(mainstage);
        	System.out.println("Credit will open here");
        });
        mainpane.getChildren().addAll(logo);
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

    private void createChangeBackground() {

        ChangeBackground = new ImageView("View/resources/GameZone/ChangeBackground.png");
        ChangeBackground.setLayoutX(100);
        ChangeBackground.setLayoutY(GAMEHIGHT - 600);
        ChangeBackground.setOnMouseEntered(e -> {
            ChangeBackground.setEffect(new MotionBlur());
        });
        ChangeBackground.setOnMouseExited(e -> {
            ChangeBackground.setEffect(null);
        });
        ChangeBackground.setOnMouseClicked(e -> {
            System.out.println("change background image ");
            mainstage.setScene(BackgroundZoneScene);

        });

    }

    private void createChangeBooms() {

        ChangeBoom = new ImageView("View/resources/GameZone/ChangeBooms.png");
        ChangeBoom.setLayoutX(620);
        ChangeBoom.setLayoutY(GAMEHIGHT - 300);
        ChangeBoom.setOnMouseEntered(e -> {
            ChangeBoom.setEffect(new MotionBlur());
        });
        ChangeBoom.setOnMouseExited(e -> {
            ChangeBoom.setEffect(null);
        });
        ChangeBoom.setOnMouseClicked(e -> {
            System.out.println("change booms image ");

        });

    }

    private void createChangeCursor() {

        ChangeCursor = new ImageView("View/resources/GameZone/ChangeCursor.png");
        ChangeCursor.setLayoutX(620);
        ChangeCursor.setLayoutY(GAMEHIGHT - 600);
        ChangeCursor.setOnMouseEntered(e -> {
            ChangeCursor.setEffect(new MotionBlur());
        });
        ChangeCursor.setOnMouseExited(e -> {
            ChangeCursor.setEffect(null);
        });
        ChangeCursor.setOnMouseClicked(e -> {
            System.out.println("change Cursor image ");
        });

    }


    private void createChangeLight() {

        ChangeLight = new ImageView("View/resources/GameZone/ChangeLight.png");
        ChangeLight.setLayoutX(130);
        ChangeLight.setLayoutY(GAMEHIGHT - 300);
        ChangeLight.setOnMouseEntered(e -> {
            ChangeLight.setEffect(new MotionBlur());
        });
        ChangeLight.setOnMouseExited(e -> {
            ChangeLight.setEffect(null);
        });
        ChangeLight.setOnMouseClicked(e -> {
            System.out.println("change light image ");
        });


    }



    private void createGameZoneBackground() {

        Image backgroundImage2 = new Image("View/resources/GameZone/GameZoneBackground.png", 1200, 700, false, true);
        BackgroundImage background2 = new BackgroundImage(backgroundImage2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gameZonePane.setBackground(new Background(background2));
        gameZonePane.getChildren().addAll(ChangeLight, ChangeCursor, ChangeBoom, BackButton, ChangeBackground);

    }

    private void createBackgroundZoneBackground() {

        Image backgroundImage2 = new Image("View/resources/GameZone/GameZoneBackground.png", 1200, 700, false, true);
        BackgroundImage background2 = new BackgroundImage(backgroundImage2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        backgroundZonePane.setBackground(new Background(background2));
        backgroundZonePane.getChildren().addAll(ChangeLight, ChangeCursor, ChangeBoom, BackButton, ChangeBackground);

    }


    private void createBackButton() {

        BackButton = new ImageView("View/resources/Icons/exit.png");
        BackButton.setLayoutX(10);
        BackButton.setLayoutY(GAMEHIGHT - 690);
        BackButton.setOnMouseEntered(e -> {
            BackButton.setEffect(new Glow());
        });
        BackButton.setOnMouseExited(e -> {
            BackButton.setEffect(null);
        });
        BackButton.setOnMouseClicked(e -> {
            mainstage.setScene(mainscene);
            if(!mainpane.getChildren().contains(arcadeButton)){
                createButton();
            }
        });


    }
}







/*



package View;

        import java.util.List;


        import javafx.animation.AnimationTimer;
        import javafx.event.EventHandler;
        import javafx.scene.Scene;
        import javafx.scene.effect.*;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.*;
        import javafx.stage.Stage;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;


public class ViewManger {
    private AnchorPane mainpane;
    private Scene mainscene;
    ///////////////////
    private AnchorPane gameZonePane;
    private Scene gameZoneScene;
    private Scene BackgroundZoneScene;
    private AnchorPane backgroundZonePane;
    ////////////////////
    public static Stage mainstage;
    private final static int GAMEWIDTH = 1024;
    private final static int GAMEHIGHT = 700;
    private AnimationTimer gameTimer;
    private GridPane gridPane1, gridPane2;
    private ImageView classicButton, arcadeButton, GameZoneButton, closeButton, helpButton, logo;
    ////////////////////////////////////
    private ImageView ChangeBackground, ChangeBoom, ChangeCursor, ChangeLight, BackButton;
    private ImageView background1, background2, background3;
    ////////////////////////////////////
    private GameViewManger gameManger = new GameViewManger();
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();


    public ViewManger() {
        ///////////////////////////////////
        gameZonePane = new AnchorPane();
        gameZoneScene = new Scene(gameZonePane, 1024, 700);
        backgroundZonePane = new AnchorPane();
        BackgroundZoneScene = new Scene(backgroundZonePane, 1024, 700);
        ////////////////////////////////////////////
        mainpane = new AnchorPane();
        mainscene = new Scene(mainpane, 1024, 700);
        mainstage = new Stage();
        mainstage.setScene(mainscene);
        effect2.setInput(effect1);
        createButton();
        creatLogo();
        createBackground();
        creatGameLoop();
        ///////////////////////
        createGameZoneBackground();
/////////////////////////

    }

    public Stage getMainstage() {
        return mainstage;
    }

    private void createButton() {
        createClassicbutton();
        createArcadebutton();
        createGameZonebutton();
        createHelpbutton();
        createClosebutton();
        createChangeBackground();
        createChangeBooms();
        createBackButton();
        createChangeCursor();
        createChangeLight();
        createGameZoneBackground();
        createBackgroundZoneBackground();
    }

    private void createClosebutton() {
        closeButton = new ImageView("View/resources/Icons/exit.png");
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(GAMEHIGHT - 690);
        closeButton.setOnMouseEntered(e -> {
            closeButton.setEffect(new Glow());
        });
        closeButton.setOnMouseExited(e -> {
            closeButton.setEffect(null);
        });
        closeButton.setOnMouseClicked(e -> {
            mainstage.close();

        });
    }

    private void createClassicbutton() {

//        gameManger = new GameViewManger();
        classicButton = new ImageView("View/resources/Classic.png");
        classicButton.setLayoutX(80);
        classicButton.setLayoutY(GAMEHIGHT - 600);
        classicButton.setOnMouseEntered(e -> {
            classicButton.setEffect(effect2);
        });
        classicButton.setOnMouseExited(e -> {
            classicButton.setEffect(null);
        });
        classicButton.setOnMouseClicked(e -> {
            gameManger.createNewGame(mainstage, 0);
            System.out.println("Classic Game will open here");
        });
    }

    private void createArcadebutton() {
//        gameManger = new GameViewManger();

        arcadeButton = new ImageView("View/resources/Arcade.png");
        arcadeButton.setLayoutX(110);
        arcadeButton.setLayoutY(GAMEHIGHT - 250);
        arcadeButton.setOnMouseEntered(e -> {
            arcadeButton.setEffect(effect2);
        });
        arcadeButton.setOnMouseExited(e -> {
            arcadeButton.setEffect(null);
        });
        arcadeButton.setOnMouseClicked(e -> {
            System.out.println("arcade Game will open here");
            gameManger.createNewGame(mainstage, 1);
        });
    }

    private void createGameZonebutton() {

        GameZoneButton = new ImageView("View/resources/GameZone.png");
        GameZoneButton.setLayoutX(650);
        GameZoneButton.setLayoutY(GAMEHIGHT - 600);
        GameZoneButton.setOnMouseEntered(e -> {
            GameZoneButton.setEffect(effect2);
        });
        GameZoneButton.setOnMouseExited(e -> {
            GameZoneButton.setEffect(null);
        });
        GameZoneButton.setOnMouseClicked(e -> {
            mainstage.setScene(gameZoneScene);

            System.out.println("Game zone will open here");
        });
    }

    private void createHelpbutton() {
        helpButton = new ImageView("View/resources/Help.png");
        helpButton.setLayoutX(700);
        helpButton.setLayoutY(GAMEHIGHT - 250);
        helpButton.setOnMouseEntered(e -> {
            helpButton.setEffect(effect2);
        });
        helpButton.setOnMouseExited(e -> {
            helpButton.setEffect(null);
        });
        helpButton.setOnMouseClicked(e -> {
            System.out.println("help will open here");
        });
    }





    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView("View/resources/backgroundLarge.png");
            ImageView backgroundImage2 = new ImageView("View/resources/backgroundLarge.png");
            GridPane.setConstraints(backgroundImage1, i % 3, 0);
            GridPane.setConstraints(backgroundImage2, i % 3, 0);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutX(-1250);
        mainpane.getChildren().addAll(gridPane1, gridPane2, classicButton, arcadeButton, GameZoneButton, helpButton, logo, closeButton);
    }


    private void creatLogo() {
        logo = new ImageView("View/resources/logo.png");
        logo.setLayoutX(GAMEWIDTH / 2 - 180);
        logo.setLayoutY(200);
        logo.setOnMouseEntered(e -> {
            logo.setEffect(new Bloom());
        });
        logo.setOnMouseExited(e -> {
            logo.setEffect(null);
        });
        logo.setOnMouseClicked(e -> {
            System.out.println("Credit will open here");
        });
    }

    private void creatGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
            }
        };
        gameTimer.start();
    }

    private void moveBackground() {
        gridPane1.setLayoutX(gridPane1.getLayoutX() + 0.5);
        gridPane2.setLayoutX(gridPane2.getLayoutX() + 0.5);
        if (gridPane1.getLayoutX() >= 1250) {
            gridPane1.setLayoutX(-1250);
        }
        if (gridPane2.getLayoutX() >= 1250) {
            gridPane2.setLayoutX(-1250);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////

    private void createChangeBackground() {

        ChangeBackground = new ImageView("View/resources/GameZone/ChangeBackground.png");
        ChangeBackground.setLayoutX(80);
        ChangeBackground.setLayoutY(GAMEHIGHT - 600);
        ChangeBackground.setOnMouseEntered(e -> {
            ChangeBackground.setEffect(effect2);
        });
        ChangeBackground.setOnMouseExited(e -> {
            ChangeBackground.setEffect(null);
        });
        ChangeBackground.setOnMouseClicked(e -> {
            System.out.println("change background image ");
            mainstage.setScene(BackgroundZoneScene);

        });

    }

    private void createChangeBooms() {

        ChangeBoom = new ImageView("View/resources/GameZone/ChangeBooms.png");
        ChangeBoom.setLayoutX(650);
        ChangeBoom.setLayoutY(GAMEHIGHT - 250);
        ChangeBoom.setOnMouseEntered(e -> {
            ChangeBoom.setEffect(effect2);
        });
        ChangeBoom.setOnMouseExited(e -> {
            ChangeBoom.setEffect(null);
        });
        ChangeBoom.setOnMouseClicked(e -> {
            System.out.println("change booms image ");
            gameManger.createNewGame(mainstage, 1);
        });


    }

    private void createChangeCursor() {

        ChangeCursor = new ImageView("View/resources/GameZone/ChangeCursor.png");
        ChangeCursor.setLayoutX(650);
        ChangeCursor.setLayoutY(GAMEHIGHT - 600);
        ChangeCursor.setOnMouseEntered(e -> {
            ChangeCursor.setEffect(effect2);
        });
        ChangeCursor.setOnMouseExited(e -> {
            ChangeCursor.setEffect(null);
        });
        ChangeCursor.setOnMouseClicked(e -> {
            System.out.println("change Cursor image ");
            gameManger.createNewGame(mainstage, 1);
        });

    }


    private void createChangeLight() {

        ChangeLight = new ImageView("View/resources/GameZone/ChangeLight.png");
        ChangeLight.setLayoutX(110);
        ChangeLight.setLayoutY(GAMEHIGHT - 250);
        ChangeLight.setOnMouseEntered(e -> {
            ChangeLight.setEffect(effect2);
        });
        ChangeLight.setOnMouseExited(e -> {
            ChangeLight.setEffect(null);
        });
        ChangeLight.setOnMouseClicked(e -> {
            System.out.println("change light image ");
            gameManger.createNewGame(mainstage, 1);
        });


    }



    private void createGameZoneBackground() {

        Image backgroundImage2 = new Image("View/resources/GameZone/GameZoneBackground.png", 1200, 700, false, true);
        BackgroundImage background2 = new BackgroundImage(backgroundImage2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gameZonePane.setBackground(new Background(background2));
        gameZonePane.getChildren().addAll(ChangeLight, ChangeCursor, ChangeBoom, ChangeBackground, BackButton);

    }

    private void createBackgroundZoneBackground() {

        Image backgroundImage2 = new Image("View/resources/GameZone/GameZoneBackground.png", 1200, 700, false, true);
        BackgroundImage background2 = new BackgroundImage(backgroundImage2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        backgroundZonePane.setBackground(new Background(background2));
        backgroundZonePane.getChildren().addAll(ChangeLight, ChangeCursor, ChangeBoom, ChangeBackground, BackButton);

    }

    private void createBackButton() {

        BackButton = new ImageView("View/resources/Icons/exit.png");
        BackButton.setLayoutX(10);
        BackButton.setLayoutY(GAMEHIGHT - 690);
        BackButton.setOnMouseEntered(e -> {
            BackButton.setEffect(new Glow());
        });
        BackButton.setOnMouseExited(e -> {
            BackButton.setEffect(null);
        });
        BackButton.setOnMouseClicked(e -> {
            mainstage.setScene(mainscene);

        });


    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////


 */