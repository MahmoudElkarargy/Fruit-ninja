package View;

import java.awt.event.ActionEvent;

import javafx.animation.Animation;
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

public class ClassicHelp {

	
	private static AnchorPane Classichelppane;
	private Scene Classichelpscene ;
    private Stage classichelp, MenuStage ;
    private static final int width= 1200;
    private static final int height =700;
    private ImageView closeButton,help,save,Sound,bombbutton,bombtext,extratimebutton,extratimetext,poisonbutton,poisontext;
    private boolean isMusicClicked = false;
    private ImageView ninja1,ninja2,ninja3,ninja4,ninja5;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private Boolean isSliced=false, ReachedMaxHeight=false;
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
     private GridPane gridPane1,gridPane2;
     private AnimationTimer classictimer;
     Help helpInst = new Help();
    public ClassicHelp(){
    	
       inializeStage();
      
     
    }
    private void inializeStage() {
    	Classichelppane = new AnchorPane();
    	Classichelpscene = new Scene(Classichelppane, width,height);
        classichelp = new Stage ();
        classichelp.setScene(Classichelpscene);
        effect2.setInput(effect1);
        createIcons();
      
    }
    
    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
      //  save = new ImageView("View/resources/Icons/save.png");
        Sound = new ImageView(soundOn);
       
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
        	classichelp.close();
        //    ViewManger.mainstage.show();
        	helpInst.createHelpScene(classichelp);
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
        
     //   ninja1,ninja2,ninja3,ninja4,ninja5        
        
        
        ninja1 = new ImageView("View/resources/Help/HiNinja.png");
        ninja1.setLayoutX(200);
        ninja1.setLayoutY(height -100);
        ninja1.setOnMouseEntered(e->ninja1.setEffect(new Glow()));
        ninja1.setOnMouseExited(e->{ 
        	ninja1.setEffect(null); 
        	});
        
        
        ninja2 = new ImageView("View/resources/Help/HiNinja2.png");
        ninja2.setLayoutX(400);
        ninja2.setLayoutY(height -100);
        ninja2.setOnMouseEntered(e->ninja2.setEffect(new Glow()));
        ninja2.setOnMouseExited(e->{ 
        	ninja2.setEffect(null); 
        	});
        
        
        
        ninja3 = new ImageView("View/resources/Help/HiNinja3.png");
        ninja3.setLayoutX(600);
        ninja3.setLayoutY(height -100);
        ninja3.setOnMouseEntered(e->ninja3.setEffect(new Glow()));
        ninja3.setOnMouseExited(e->{ 
        	ninja3.setEffect(null); 
        	});
        
        
        ninja4 = new ImageView("View/resources/Help/WinNinja.png");
        ninja4.setLayoutX(780);
        ninja4.setLayoutY(height -90);
        ninja4.setOnMouseEntered(e->ninja4.setEffect(new Glow()));
        ninja4.setOnMouseExited(e->{ 
        	ninja4.setEffect(null); 
        	});
        
        ninja5 = new ImageView("View/resources/Help/LoseNinja.png");
        ninja5.setLayoutX(1000);
        ninja5.setLayoutY(height -95);
        ninja5.setOnMouseEntered(e->ninja5.setEffect(new Glow()));
        ninja5.setOnMouseExited(e->{ 
        	ninja5.setEffect(null); 
        	});
        
    
    
    
       
        
   //slice the criminal and reach the end of the terminal
        
        
       // Classichelppane.getChildren().addAll(closeButton,help,save,Sound,bombbutton,bombtext,extratimebutton,extratimetext);
        
        
        

    }



   public static AnchorPane getGamePain(){
       return Classichelppane;
   }

  

    public void createNewGame(Stage menustage){
        this.MenuStage = menustage;
        this.MenuStage.hide();
        createBackground();
        creatGameLoop();
       // moveBackground();
        classichelp.show();
        
        
    }

    private void createBackground (){
        Image backgroundImage = new Image("View/resources/Help/classicwallpaper.jpg",width,height,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
      
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for(int i=0;i<12;i++){
            ImageView backgroundImage1 = new ImageView("View/resources/Help/classicwallpaper.jpg");
            ImageView backgroundImage2 = new ImageView("View/resources/Help/classicwallpaper.jpg");
            GridPane.setConstraints(backgroundImage1, i%3, 0);
            GridPane.setConstraints(backgroundImage2, i%3, 0);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutX(-1250);
        Classichelppane.setBackground(new Background(background));
     //   Classichelppane.getChildren().addAll(gridPane1,gridPane2);
        Classichelppane.getChildren().addAll(gridPane1,gridPane2,closeButton,help,Sound,bombbutton,bombtext,extratimebutton,extratimetext, ninja1,ninja2,ninja3,ninja4,ninja5);
        
        
   
        
    }
    private void creatGameLoop(){
        classictimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
            }
        };
        classictimer.start();
    }
    private void moveBackground(){
        gridPane1.setLayoutX(gridPane1.getLayoutX() - 0.3);
        gridPane2.setLayoutX(gridPane2.getLayoutX()-0.3);
        if(gridPane1.getLayoutX()>=1250){
            gridPane1.setLayoutX(-1250);
        }
        if(gridPane2.getLayoutX() >= 1250){
            gridPane2.setLayoutX(-1250);
        }
    }
 


  
	
}



