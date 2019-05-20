package View;

import java.awt.event.ActionEvent;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcadeHelpClass {

	
	private static AnchorPane Arcadehelppane;
	private Scene Arcadehelpscene ;
    private Stage ArcadehelpStage, MenuStage ;
    private static final int width= 1200;
    private static final int height =700;
    private ImageView closeButton,help,save,Sound,bombbutton,bombtext,extratimebutton,extratimetext,poisonbutton,poisontext;
    private ImageView ninja1,ninja2,ninja3,ninja4,ninja5;
    
    private boolean isMusicClicked = false;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private Boolean isSliced=false, ReachedMaxHeight=false;
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
    private GridPane gridPane1,gridPane2; 
    private AnimationTimer arcadehelptimer;
    Help helpInst = new Help();

    public ArcadeHelpClass(){
    	
       inializeStage();
      
     
    }
    private void inializeStage() {
    	Arcadehelppane = new AnchorPane();
    	Arcadehelpscene = new Scene(Arcadehelppane, width,height);
        ArcadehelpStage = new Stage ();
        ArcadehelpStage.setScene(Arcadehelpscene);
        effect2.setInput(effect1);
        createIcons();
      
    }
    
    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
       // save = new ImageView("View/resources/Icons/save.png");
        Sound = new ImageView(soundOn);
       
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
        	ArcadehelpStage.close();
        	helpInst.createHelpScene(ArcadehelpStage);

        	//  ViewManger.mainstage.show();
         });
        closeButton.setOnMouseEntered(e->{ closeButton.setEffect(new Glow()); });
        closeButton.setOnMouseExited(e->{ closeButton.setEffect(null); });


        help.setLayoutX(width-60);
        help.setLayoutY(10);
        help.setOnMouseClicked(e->{

        });
        help.setOnMouseEntered(e->{ help.setEffect(new Glow()); });
        help.setOnMouseExited(e->{ help.setEffect(null); });


        Sound.setLayoutX(width-101);
        Sound.setLayoutY(10);
        Sound.setOnMouseClicked(e->{
            if(!isMusicClicked) {
                Sound.setImage(mutedSound);
                isMusicClicked = true;
            }
            else {
                Sound.setImage(soundOn);
                isMusicClicked = false;
            }
        });
        Sound.setOnMouseEntered(e->{ Sound.setEffect(new Glow()); });
        Sound.setOnMouseExited(e->{ Sound.setEffect(null); });

//        save.setLayoutX(10);
//        save.setLayoutY(50);
//        save.setOnMouseClicked(e->{
//
//        });
//        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
//        save.setOnMouseExited(e->{ save.setEffect(null); });
//        
//        
        
        bombbutton = new ImageView("View/resources/Help/bomb.png");
        bombbutton.setLayoutX(100);
        bombbutton.setLayoutY(height-670);
        bombbutton.setOnMouseEntered(e->{
        		bombbutton.setEffect(effect2);
        });
        bombbutton.setOnMouseExited(e->{ bombbutton.setEffect(null); });
        bombbutton.setOnMouseClicked(e->{ 
        	
        });
        
        bombtext = new ImageView("View/resources/Help/bombtext2.png");
        bombtext.setLayoutX(160);
        bombtext.setLayoutY(height -690);
        bombtext.setOnMouseEntered(e->bombtext.setEffect(effect2));
        bombtext.setOnMouseExited(e->{ 
        	bombtext.setEffect(null); 
        	});
        
        
        extratimebutton = new ImageView("View/resources/Help/BonusTime.png");
        extratimebutton.setLayoutX(100);
        extratimebutton.setLayoutY(height -470);
        extratimebutton.setOnMouseEntered(e->extratimebutton.setEffect(effect2));
        extratimebutton.setOnMouseExited(e->{ 
        	extratimebutton.setEffect(null); 
        	});
        
        extratimetext = new ImageView("View/resources/Help/timetext.png");
        extratimetext.setLayoutX(250);
        extratimetext.setLayoutY(height -510);
        extratimetext.setOnMouseEntered(e->extratimetext.setEffect(effect2));
        extratimetext.setOnMouseExited(e->{ 
        	extratimetext.setEffect(null); 
        	});
        
        
        poisonbutton = new ImageView("View/resources/Help/poison2.png");
        poisonbutton.setLayoutX(100);
        poisonbutton.setLayoutY(height -310);
        poisonbutton.setOnMouseEntered(e->poisonbutton.setEffect(effect2));
        poisonbutton.setOnMouseExited(e->{ 
        	poisonbutton.setEffect(null); 
        	});
    
        
        poisontext = new ImageView("View/resources/Help/poisontext22.png");
        poisontext.setLayoutX(320);
        poisontext.setLayoutY(height -320);
        poisontext.setOnMouseEntered(e->poisontext.setEffect(effect2));
        poisontext.setOnMouseExited(e->{ 
        	poisontext.setEffect(null); 
        	});
    
        
         ninja1 = new ImageView();
        Image image = new Image("View/resources/Help/HiNinja.png");
        
        ninja1.setImage(image);
        ninja1.setX(200);
        ninja1.setY(height-110);
        FadeTransition fade1 = new FadeTransition();
        fade1.setNode(ninja1);
        fade1.setDuration(new Duration(1500));
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);
        fade1.setCycleCount(50);
        fade1.setAutoReverse(true);
        fade1.play();
        
        
         ninja2 = new ImageView();
        Image image2 = new Image("View/resources/Help/HiNinja2.png");
        
        ninja2.setImage(image2);
        ninja2.setX(400);
        ninja2.setY(height-110);
        FadeTransition fade2 = new FadeTransition();
        fade2.setNode(ninja2);
        fade2.setDuration(new Duration(1000));
        fade2.setFromValue(1.0);
        fade2.setToValue(0.0);
        fade2.setCycleCount(50);
        fade2.setAutoReverse(true);
        fade2.play();
   
         ninja3 = new ImageView();
        Image image3 = new Image("View/resources/Help/HiNinja3.png");
        
        ninja3.setImage(image3);
        ninja3.setX(600);
        ninja3.setY(height-110);
        FadeTransition fade3 = new FadeTransition();
        fade3.setNode(ninja3);
        fade3.setDuration(new Duration(1500));
        fade3.setFromValue(1.0);
        fade3.setToValue(0.0);
        fade3.setCycleCount(50);
        fade3.setAutoReverse(true);
        fade3.play();
        
        
         ninja4 = new ImageView();
        Image image4 = new Image("View/resources/Help/WinNinja.png");
        
        ninja4.setImage(image4);
        ninja4.setX(770);
        ninja4.setY(height-100);
        FadeTransition fade4 = new FadeTransition();
        fade4.setNode(ninja4);
        fade4.setDuration(new Duration(1000));
        fade4.setFromValue(1.0);
        fade4.setToValue(0.0);
        fade4.setCycleCount(50);
        fade4.setAutoReverse(true);
        fade4.play();
        

         ninja5 = new ImageView();
        Image image5 = new Image("View/resources/Help/LoseNinja.png");
        
        ninja5.setImage(image5);
        ninja5.setX(1000);
        ninja5.setY(height-100);
        FadeTransition fade5 = new FadeTransition();
        fade5.setNode(ninja5);
        fade5.setDuration(new Duration(1500));
        fade5.setFromValue(1.0);
        fade5.setToValue(0.0);
        fade5.setCycleCount(50);
        fade5.setAutoReverse(true);
        fade5.play();
        
    

      
        
   //slice the criminal and reach the end of the terminal
        
        
       // Arcadehelppane.getChildren().addAll(closeButton,help,save,Sound,bombbutton,bombtext,extratimebutton,extratimetext,poisonbutton,poisontext,ninja1,ninja2,ninja3,ninja4,ninja5);
        
        
        

    }



   public static AnchorPane getGamePain(){
       return Arcadehelppane;
   }

  

    public void createNewGame(Stage menustage){
        this.MenuStage = menustage;
        this.MenuStage.hide();
        createBackground();
        creatGameLoop();
        ArcadehelpStage.show();
        
    }

    private void createBackground (){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for(int i=0;i<12;i++){
            ImageView backgroundImage1 = new ImageView("View/resources/Help/ArcadeHelpBackground.jpg");
            ImageView backgroundImage2 = new ImageView("View/resources/Help/ArcadeHelpBackground.jpg");
            GridPane.setConstraints(backgroundImage1, i%3, 0);
            GridPane.setConstraints(backgroundImage2, i%3, 0);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
            
        }
        gridPane2.setLayoutX(-1250);
        
          Arcadehelppane.getChildren().addAll(gridPane1,gridPane2,closeButton,help,Sound,bombbutton,bombtext,extratimebutton,extratimetext,poisonbutton,poisontext,ninja1,ninja2,ninja3,ninja4,ninja5);

        
        
    }
    private void creatGameLoop(){
        arcadehelptimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
                
            }
        };
        arcadehelptimer.start();
    }
    private void moveBackground(){
        gridPane1.setLayoutX(gridPane1.getLayoutX() + 0.2);
        gridPane2.setLayoutX(gridPane2.getLayoutX()+ 0.2);
        if(gridPane1.getLayoutX()>=1250){
            gridPane1.setLayoutX(-1250);
        //    gridPane2.setLayoutX(-1250);
        }
        if(gridPane2.getLayoutX() >= 1250){
            gridPane2.setLayoutX(-1250);
            
        }
 

	
}
}



