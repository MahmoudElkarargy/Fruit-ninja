package View;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class fruitNinjaButton extends Button {
    private final String font_Path = "out/production/Fruit-ninja/View/resources/kenvector_future.ttf";
    private final String Button_PRESSED = "-fx-background-color transparent ; -fx-background-image:url('View/resources/green_button00.png')";
    private final String Button_RELEASED = "-fx-background-color transparent ; -fx-background-image:url('View/resources/green_button01.png')";

    public fruitNinjaButton(String text){
        setText(text);
        setButtonfont();
        setPrefHeight(45);
        setPrefWidth(190);
        setStyle(Button_RELEASED);
        inializebuttonlisteners();
    }

    private void setButtonfont() {
        try {


            setFont(Font.loadFont(new FileInputStream(font_Path), 18));

        } catch (FileNotFoundException e) {
            setFont(Font.font("verdana", 23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(Button_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);

    }private void setButtonReleasedStyle(){
        setStyle(Button_RELEASED);
        setPrefHeight(45);
        setLayoutY(getLayoutY()-4);

    }

    private void inializebuttonlisteners(){
        setOnMousePressed(event -> {
            System.out.println("pressed");
            setButtonPressedStyle();
        });

        setOnMouseReleased(event -> {
            System.out.println("released");
            setButtonReleasedStyle();
        });
        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
        });
        setOnMouseExited(event -> {
            setEffect(null);
        });





    }


}
