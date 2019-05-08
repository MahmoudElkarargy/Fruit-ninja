package View;

<<<<<<< HEAD
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewManger {
    private static final int WIDTH = 1024, HEIGHT=700;
    private AnchorPane mainPane;
    public Scene mainScene;
    public static Stage mainStage;

    //Constructor
    public ViewManger(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        // hello pushed from me
        //kkkk
    }

    public Stage getMainStage(){
        return mainStage;
    }
}
=======
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import View.*;
import View.fruitNinjaButton;
import javafx.scene.image.Image;

public class ViewManger {
    private AnchorPane mainpane ;
    private Scene mainscene;
    private Stage mainstage;

    private final static int menu_button_start_x= 100;
    private final static int menu_button_start_y= 150;

    List<fruitNinjaButton> menubuttons ;

    public ViewManger(){
        mainpane=new AnchorPane();
        mainscene= new Scene(mainpane, 1024,700);
        mainstage= new Stage();
        mainstage.setScene(mainscene);
        createBackground();
        createButton();
    }

    public Stage getMainstage() {
        return mainstage;
    }
    private void addMenuButtons(fruitNinjaButton button){
        button.setLayoutX(menu_button_start_x);
        button.setLayoutY(menu_button_start_y + menubuttons.size()*100);
        menubuttons.add(button);
        mainpane.getChildren().add(button);

    }

    private  void createButton(){

        createClassicbutton();
        createArcadebutton();
        createGameZonesbutton();
        createHelpbutton();
        createClosebutton();

    }

    private void createClassicbutton(){
        fruitNinjaButton classic_button = new fruitNinjaButton("Classsic ");
        addMenuButtons(classic_button);

    } private void createArcadebutton(){
        fruitNinjaButton arcade_button = new fruitNinjaButton("Arcade");
        addMenuButtons(arcade_button);

    } private void createGameZonesbutton(){
        fruitNinjaButton GameZone_button = new fruitNinjaButton("Game Zone");
        addMenuButtons(GameZone_button);

    } private void createClosebutton(){
        fruitNinjaButton close_button = new fruitNinjaButton("X");
        addMenuButtons(close_button);

    } private void createHelpbutton(){
        fruitNinjaButton help_button = new fruitNinjaButton("Help");
        addMenuButtons(help_button);

    }






    private void createBackground(){
        Image backgroundImage = new Image("View/resources/background-scene---dojo.png",1024,700,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,null);
        mainpane.setBackground(new Background(background));
    }

}
>>>>>>> origin/master
