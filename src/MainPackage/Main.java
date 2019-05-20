package MainPackage;

import View.ViewManger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    public void start(Stage primarystage) {
        ViewManger manger =ViewManger.getInstance();
                //new ViewManger();
        primarystage=manger.getMainstage();
        primarystage.initStyle(StageStyle.TRANSPARENT);
        primarystage.show();

//        Canvas canvas = new Canvas(400, 400);
//        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        initDraw(graphicsContext);
//
//        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
//                new EventHandler<MouseEvent>(){
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        graphicsContext.beginPath();
//                        graphicsContext.moveTo(event.getX(), event.getY());
//                        graphicsContext.stroke();
//                    }
//                });
//
//        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>(){
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        graphicsContext.lineTo(event.getX(), event.getY());
//                        graphicsContext.stroke();
//                    }
//                });
//
//        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
//                new EventHandler<MouseEvent>(){
//
//                    @Override
//                    public void handle(MouseEvent event) {
//
//                    }
//                });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(canvas);
//        Scene scene = new Scene(root, 400, 400);
//        primarystage.setTitle("java-buddy.blogspot.com");
//        primarystage.setScene(scene);
//        primarystage.show();


    }

//    private void initDraw(GraphicsContext gc){
//
//        double canvasWidth = gc.getCanvas().getWidth();
//        double canvasHeight = gc.getCanvas().getHeight();
//
//        gc.setFill(Color.LIGHTGRAY);
//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(5);
//
//        gc.fill();
//        gc.strokeRect(
//                0,              //x of the upper left corner
//                0,              //y of the upper left corner
//                canvasWidth,    //width of the rectangle
//                canvasHeight);  //height of the rectangle
//
//        gc.setFill(Color.RED);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(1);
//
//    }

    public static void main(String[] args) {
        launch(args);
    }

}