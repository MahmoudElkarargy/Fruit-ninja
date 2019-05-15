package MainPackage;

import View.ViewManger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    public void start(Stage primarystage) {
        ViewManger manger = new ViewManger();
        primarystage=manger.getMainstage();
        primarystage.initStyle(StageStyle.TRANSPARENT);
        primarystage.show();

//        FlowPane flowPane = new FlowPane();
//        Canvas canvas = new Canvas(300, 300);
//        flowPane.getChildren().add(canvas);
//        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//
//        graphicsContext.setFill(Color.WHITE);
//        graphicsContext.fillRect(0, 0, 300, 300);
//
//        canvas.setOnMouseDragged((event) -> {
//            graphicsContext.setFill(Color.BLACK);
//            graphicsContext.fillRect(event.getX(), event.getY(), 1, 1);
//        });
//
//        primarystage.setScene(new Scene(flowPane));
//        primarystage.show();


    }


    public static void main(String[] args) {
//        launch(Main.class);
        launch(args);
    }

}