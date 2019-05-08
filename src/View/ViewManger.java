package View;

import java.util.List;


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

    ImageView classicButton, arcadeButton, GameZoneButton, closeButton, helpButton;

    public ViewManger(){
        mainpane=new AnchorPane();
        mainscene= new Scene(mainpane, 1024,700);
        mainstage= new Stage();
        mainstage.setScene(mainscene);
        createButton();
        createBackground();
        creatLogo();
    }

    public Stage getMainstage() {
        return mainstage;
    }

    private  void createButton(){
        createClassicbutton();
//        createArcadebutton();
//        createGameZonesbutton();
//        createHelpbutton();
//        createClosebutton();
    }

    private void createClassicbutton(){
        classicButton = new ImageView("View/resources/Classic.png");
        classicButton.setLayoutX(50);
        classicButton.setLayoutY(GAMEHIGHT-500);
        classicButton.setOnMouseEntered(e->{ classicButton.setEffect(new DropShadow()); });
        classicButton.setOnMouseExited(e->{ classicButton.setEffect(null); });
        classicButton.setOnMouseClicked(e->{
            System.out.println("Classic Game will open here");
        });
        mainpane.getChildren().add(classicButton);
    }

    private void createBackground(){
        Image backgroundImage = new Image("View/resources/backgroundLarge.png",GAMEWIDTH,GAMEHIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,null);
        mainpane.setBackground(new Background(background));
    }

    private void creatLogo(){
        ImageView logo = new ImageView("View/resources/logo.png");
        logo.setLayoutX(GAMEWIDTH/2-180);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(e->{ logo.setEffect(new DropShadow()); });
        logo.setOnMouseExited(e->{ logo.setEffect(null); });
        logo.setOnMouseClicked(e->{
            System.out.println("Credit will open here");
        });
        mainpane.getChildren().add(logo);
    }

}
