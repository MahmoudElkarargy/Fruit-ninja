package MainPackage;

import View.ViewManger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    public void start(Stage primarystage) {
        ViewManger manger = new ViewManger();
        primarystage=manger.getMainstage();
        primarystage.initStyle(StageStyle.TRANSPARENT);
        primarystage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }

}