package View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Help {

	private static AnchorPane HelpPane;
	private Scene HelpScene ;
    private Stage HelpStage, MenuStage ;
    private static final int width= 1200;
    private static final int height =700;
    private ImageView closeButton,help,save,Sound,arcadehelpbutton,classichelpbutton;
    private boolean isMusicClicked = false;
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
     
    public Help(){
    	
       inializeStage();
       
     
    }
    private void inializeStage() {
        HelpPane = new AnchorPane();
        HelpScene = new Scene(HelpPane, width,height);
        HelpStage = new Stage ();
        HelpStage.initStyle(StageStyle.TRANSPARENT);

        HelpStage.setScene(HelpScene);
        
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
        	HelpStage.close();
            ViewManger.mainstage.show();
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
        Sound.setOnMouseExited(e->{
        	Sound.setEffect(null);
        	});

//        save.setLayoutX(10);
//        save.setLayoutY(50);
//        save.setOnMouseClicked(e->{
//
//        });
//        save.setOnMouseEntered(e->{ save.setEffect(new Glow()); });
//        save.setOnMouseExited(e->{ save.setEffect(null); });
//        
        
        arcadehelpbutton = new ImageView("View/resources/Help/ArcadeHelp2.png");
        arcadehelpbutton.setLayoutX(110);
        arcadehelpbutton.setLayoutY(height-430);
        arcadehelpbutton.setOnMouseEntered(e->{ 
        	
        	arcadehelpbutton.setEffect(effect2);
//        	if(!HelpPane.getChildren().contains(helptext))
//        		HelpPane.getChildren().add(helptext);
//        	
        });
        arcadehelpbutton.setOnMouseExited(e->{ 
        	arcadehelpbutton.setEffect(null);
        	
//        	if(HelpPane.getChildren().contains(helptext))
//        		HelpPane.getChildren().remove(helptext);
        	});
        arcadehelpbutton.setOnMouseClicked(e->{
        	
        	ArcadeHelpClass arcade= new ArcadeHelpClass();
        	arcade.createNewGame(HelpStage);
        	HelpStage.close();
        	
        	
        	
        });
    
        classichelpbutton = new ImageView("View/resources/Help/ClassicHelp2.png");
        classichelpbutton.setLayoutX(800);
        classichelpbutton.setLayoutY(height-450);
        classichelpbutton.setOnMouseEntered(e->{
        	
        	classichelpbutton.setEffect(effect2);
//        	if(!HelpPane.getChildren().contains(classictext))
//        		HelpPane.getChildren().add(classictext);        
//             	
        	
        });
        classichelpbutton.setOnMouseExited(e->{ 
        	
        	classichelpbutton.setEffect(null); 
//        	if(HelpPane.getChildren().contains(classictext))
//        		HelpPane.getChildren().remove(classictext);
//               	
        	});
        
        classichelpbutton.setOnMouseClicked(e->{
        	
        	ClassicHelp classic = new ClassicHelp();
        	classic.createNewGame(HelpStage);
        	HelpStage.close();
        	        	
        });
    
  /*      helptext = new ImageView("View/resources/Help/Arcadehelptextt.png");
        helptext.setLayoutX(5);
        helptext.setLayoutY(height -480);
        helptext.setOnMouseEntered(e->helptext.setEffect(effect2));
//        helptext.setOnMouseExited(e->{ 
//        	helptext.setEffect(null); 
//        	});
        classictext = new ImageView("View/resources/Help/classichelptextt3.png");
        classictext.setLayoutX(720);
        classictext.setLayoutY(height -470);
        classictext.setOnMouseEntered(e->classictext.setEffect(effect2));
     /*   classictext.setOnMouseExited(e->{ 
        	classictext.setEffect(null);
        */	
        //	});
        
        
        
        
   
        
        HelpPane.getChildren().addAll(closeButton,help,Sound,arcadehelpbutton,classichelpbutton);
        
        
        

    }
 
    
   public static AnchorPane getGamePain(){
       return HelpPane;
   }

  

    public void createHelpScene(Stage menustage){
        this.MenuStage = menustage;
       
        createBackground();
        HelpStage.show();
        this.MenuStage.hide();
        
    }

    private void createBackground (){
        Image backgroundImage = new Image("View/resources/Help/HelpBackGround.jpg",width,height,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        HelpPane.setBackground(new Background(background));
    }

 

  
	
}
