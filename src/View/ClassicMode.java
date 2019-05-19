package View;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ClassicMode {
    private AnchorPane gamePane;
    private ImageView life[];
    private int space=0, numberOfLifes;
    private static ClassicMode Instance=null;

    private final String PATH = "View/resources/BonusObjects/Life.png";
    private ClassicMode(){
        gamePane = new AnchorPane();
        this.gamePane = GameViewManger.getGamePain();
    }
    public static ClassicMode getInstance(){
        if(Instance==null)
        {
            Instance=new ClassicMode();
        }
        return Instance;
    }
    public void reset_space(){
        this.space=0;
    }
    public void setNumberOfLifes(int numberOfLifes){
        this.numberOfLifes = numberOfLifes;
        space =0;
        if(!gamePane.getChildren().isEmpty()) {
            if(numberOfLifes==3)
                for (int i=0;i<numberOfLifes;i++)
                    gamePane.getChildren().removeAll(life[i]);
                else
            for (int i=0;i<numberOfLifes+1;i++)
                gamePane.getChildren().removeAll(life[i]);
        }
        creatLifeNumbers(numberOfLifes);
    }
    public void removeLifes() {
        if (!gamePane.getChildren().isEmpty()) {
            for (int i = 2; i >=0; i--)
                gamePane.getChildren().removeAll(life[i]);
        }
    }
    public void creatLifeNumbers(int numberOfLifes){
        this.numberOfLifes = numberOfLifes;
        life = new ImageView[3];
        for (int i=0; i<numberOfLifes; i++){
            life[i] = new ImageView(PATH);
            life[i].setLayoutX(1120-space);
            life[i].setLayoutY(80);
            life[i].setFitHeight(50);
            life[i].setFitWidth(50);
            gamePane.getChildren().add(life[i]);
            space+=60;
        }
//        if (numberOfLifes==0){
//            //GAMEOVER FUNC!
//            System.out.println("GAMEEEOVEEERR!!!");
//        }
    }


}
