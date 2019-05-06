package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;
import View.ViewManger;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManger manger = new ViewManger();
        primaryStage = manger.getMainStage();

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

