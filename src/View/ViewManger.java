package View;

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
    }

    public Stage getMainStage(){
        return mainStage;
    }
}