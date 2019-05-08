package MainPackage;

import View.ViewManger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public void start(Stage primarystage) {
        ViewManger manger = new ViewManger();
        primarystage=manger.getMainstage();
        primarystage.show();



    }

    public static void main(String[] args) {

        launch(args);
    }

}