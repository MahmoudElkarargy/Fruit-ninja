package Logic;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class LineDrawing extends Group {
        Path path;
        Scene scene;
        public LineDrawing(Scene scene){
            this.scene=scene;
        }
    public void Draw() {
        path = new Path();
        path.setStrokeWidth(10);
        path.setStroke(Color.SILVER);

        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);
        getChildren().add(path);

    }
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                path.getElements().clear();
                path.getElements()
                        .add(new MoveTo(mouseEvent.getX(), mouseEvent.getY()));
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                path.getElements()
                        .add(new LineTo(mouseEvent.getX(), mouseEvent.getY()));
            }

        }

    };
}



//
//
//
//
//    Canvas canvas = new Canvas(1200, 700);
//    final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//
//    initDraw(graphicsContext);
//
//       canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
//               new EventHandler<MouseEvent>()
//
//        {
//
//@Override
//public void handle (MouseEvent event){
//        graphicsContext.beginPath();
//        graphicsContext.moveTo(event.getX(), event.getY());
//        graphicsContext.stroke();
//        }
//        });
//
//
//        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//        new EventHandler<MouseEvent>()
//
//        {
//
//@Override
//public void handle (MouseEvent event){
//        graphicsContext.lineTo(event.getX(), event.getY());
//        graphicsContext.stroke();
////                        render();
//
//        }
//        });
//
//        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
//        new EventHandler<MouseEvent>()
//
//        {
//
//@Override
//public void handle (MouseEvent event){
//        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
//
//        }
//        });
//        canvas.setOnDragExited(e->
//
//        {
////            clearSignature(graphicsContext);
//        });
//        getChildren().add(canvas);
//        }
//private void initDraw(GraphicsContext gc){
//        double canvasWidth = gc.getCanvas().getWidth();
//        double canvasHeight = gc.getCanvas().getHeight();
//
//        gc.setFill(Color.BLUE);
////        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(10);
//
//        gc.fill();
////        gc.strokeRect(
////                0,              //x of the upper left corner
////                0,              //y of the upper left corner
////                canvasWidth,    //width of the rectangle
////                canvasHeight);  //height of the rectangle
//
//        gc.setFill(Color.RED);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(1);
//
//        }
