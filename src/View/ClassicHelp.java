package View;

import java.awt.event.ActionEvent;

import Logic.BackgroundSound;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ClassicHelp {

	
	private static AnchorPane Classichelppane;
	private Scene Classichelpscene ;
    private Stage classichelp, MenuStage ;
    private static final int width= 1200;
    private static final int height =700;
    private ImageView closeButton,help,save,Sound,bombbutton,poisonbutton,starbutton,backarrow,heartbutton;
    private ImageView bombtext,hearttext,poisontext,startext;
    private ImageView button= new ImageView();
    private boolean moveimage=false;
    private boolean isMusicClicked = false;
    private ImageView ninja,ninja1,ninja2,ninja3,ninja4,ninja5;
    private String musicSlice = "src/View/resources/Slice.mp3";
    private Boolean isSliced=false, ReachedMaxHeight=false;
    private Reflection effect1 = new Reflection();
    private MotionBlur effect2 = new MotionBlur();
    private GridPane gridPane1,gridPane2;
    private AnimationTimer classictimer,imagetimer;
    Help helpInst = new Help();
    private BackgroundSound backgroundSound = new BackgroundSound();

    public ClassicHelp(){
    	
       inializeStage();
      
     
    }
    private void inializeStage() {
    	Classichelppane = new AnchorPane();
    	Classichelpscene = new Scene(Classichelppane, width,height);
        classichelp = new Stage ();
        classichelp.setScene(Classichelpscene);
        effect2.setInput(effect1);
        classichelp.initStyle(StageStyle.TRANSPARENT);

        createIcons();
        starttimer();
      
    }
    
    private void createIcons(){
        Image soundOn = new Image("View/resources/Icons/Sound.png");
        Image mutedSound = new Image("View/resources/Icons/mutedSound.png");
        closeButton = new ImageView("View/resources/Icons/exit.png");
        help = new ImageView("View/resources/Icons/help.png");
        Sound = new ImageView(soundOn);
       
        closeButton.setLayoutX(10);
        closeButton.setLayoutY(10);
        closeButton.setOnMouseClicked(e->{
        	classichelp.close();
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
                backgroundSound.setSound(isMusicClicked);
                backgroundSound.Music_BackGround();

            }
            else {
                Sound.setImage(soundOn);
                isMusicClicked = false;
                backgroundSound.setSound(isMusicClicked);
                backgroundSound.Music_BackGround();
            }
        });
        Sound.setOnMouseEntered(e->{ Sound.setEffect(new Glow()); });
        Sound.setOnMouseExited(e->{ Sound.setEffect(null); });

  
        ninja = new ImageView("View/resources/Help/write.png");
        ninja.setFitHeight(600);
        ninja.setFitWidth(1100);
        ninja.setLayoutX(300);
        ninja.setLayoutY(height -700);
        
        bombbutton = new ImageView("View/resources/Help/bomb.png");
        bombbutton.setFitHeight(190);
        bombbutton.setFitWidth(150);
        bombbutton.setLayoutX(200);
        bombbutton.setLayoutY(height-475);
        bombbutton.setOnMouseEntered(e->{
    
        bombbutton.setEffect(effect2);
        		
        		
        });
        bombbutton.setOnMouseExited(e->{
        	    
        	bombbutton.setEffect(null);
        	
             	
        
        });
        bombbutton.setOnMouseClicked(e->{ 
        	
        	button=bombbutton;
        	moveimage=true;	
        	
        	
        });
        
        bombtext = new ImageView("View/resources/Help/bombinfo.png");
        bombtext.setLayoutX(380);
        bombtext.setLayoutY(height -580);
        bombtext.setOnMouseEntered(e->bombtext.setEffect(new Glow()));
        bombtext.setOnMouseExited(e->{ 
        	bombtext.setEffect(null); 
        	});
        
        
        
        heartbutton = new ImageView("View/resources/BonusObjects/Life.png");
        heartbutton.setFitHeight(160);
        heartbutton.setFitWidth(170);
        heartbutton.setLayoutX(450);
        heartbutton.setLayoutY(height -440);
        heartbutton.setOnMouseEntered(e->heartbutton.setEffect(effect2));
        heartbutton.setOnMouseClicked(e-> {
        	button=heartbutton;
        	moveimage=true;
        	
        });
        heartbutton.setOnMouseExited(e->{ 
        	heartbutton.setEffect(null); 
        	});

        
        hearttext = new ImageView("View/resources/Help/hearttext1.png");
        hearttext.setLayoutX(380);
        hearttext.setLayoutY(height -580);
        hearttext.setOnMouseEntered(e->hearttext.setEffect(effect2));
        hearttext.setOnMouseExited(e->{ 
        	hearttext.setEffect(null); 
        	});
        
        
        poisonbutton = new ImageView("View/resources/Help/poison2.png");
        poisonbutton.setFitHeight(160);
        poisonbutton.setFitWidth(120);
        
        poisonbutton.setLayoutX(690);
        poisonbutton.setLayoutY(height -440);
        poisonbutton.setOnMouseEntered(e->poisonbutton.setEffect(effect2));
        poisonbutton.setOnMouseClicked(e->{

        	button=poisonbutton;
        	moveimage=true;	
        });
        poisonbutton.setOnMouseExited(e->{ 
        	poisonbutton.setEffect(null); 
        	});
        
        
        poisontext = new ImageView("View/resources/Help/poisontextclassic1.png");
        poisontext.setLayoutX(380);
        poisontext.setLayoutY(height -580);
        poisontext.setOnMouseEntered(e->poisontext.setEffect(effect2));
        poisontext.setOnMouseExited(e->{ 
        	poisontext.setEffect(null); 
        	});    
        
        starbutton = new ImageView("View/resources/BonusObjects/Bonus.png");
        starbutton.setFitHeight(150);
        starbutton.setFitWidth(150);
        starbutton.setLayoutX(900);
        starbutton.setLayoutY(height -445);
        starbutton.setOnMouseEntered(e->starbutton.setEffect(effect2));
        starbutton.setOnMouseClicked(e->{

        	button=starbutton;
        	moveimage=true;	
        });
        starbutton.setOnMouseExited(e->{ 
        	starbutton.setEffect(null); 
        	});
        
        
        startext = new ImageView("View/resources/Help/arcadestartext2.png");
        startext.setLayoutX(355);
        startext.setLayoutY(height -620);
        startext.setOnMouseEntered(e->startext.setEffect(effect2));
        startext.setOnMouseExited(e->{ 
        	startext.setEffect(null); 
        	});    

        backarrow = new ImageView("View/resources/Help/backbutton.png");
        backarrow.setFitHeight(180);
        backarrow.setFitWidth(230);
        backarrow.setLayoutX(620);
        
        
       
        backarrow.setLayoutY(height -330);
        backarrow.setOnMouseEntered(e->backarrow.setEffect(new Glow()));
        backarrow.setOnMouseExited(e->{ 
        	
        	backarrow.setEffect(null); 
        	
        });
        backarrow.setOnMouseClicked(e->{
        	
        	 if (button==bombbutton) {
        		 Classichelppane.getChildren().removeAll(ninja,bombtext,backarrow);
        	 Classichelppane.getChildren().addAll(heartbutton,poisonbutton,starbutton);
        	
        	 bombbutton.setRotate(bombbutton.getRotate()+1.5);	 
        	 bombbutton.setLayoutX(200);
             bombbutton.setLayoutY(height-475);
        	 }
          
        	 else if (button==heartbutton) {
        		 Classichelppane.getChildren().removeAll(ninja,hearttext,backarrow);//add heart text
        		 Classichelppane.getChildren().addAll(bombbutton,poisonbutton,starbutton);
        		 
        		 heartbutton.setLayoutX(450);
        		 heartbutton.setLayoutY(height -440);
        	       
        	 }
        	 else if (button==poisonbutton) {
        		 Classichelppane.getChildren().removeAll(ninja,poisontext,backarrow);//add poison text
        		 Classichelppane.getChildren().addAll(bombbutton,heartbutton,starbutton);
        		 

        		  poisonbutton.setLayoutX(690);
        	      poisonbutton.setLayoutY(height -440);
        	      
        		 
        	 }
        	 else if(button==starbutton) {
        		 Classichelppane.getChildren().removeAll(ninja,startext,backarrow);//add star text
        		 Classichelppane.getChildren().addAll(bombbutton,heartbutton,poisonbutton);
        		 
        		 starbutton.setRotate(starbutton.getRotate()-1.2);
        		 starbutton.setLayoutX(900);
        	     starbutton.setLayoutY(height -445);
        	        
        	 }
        	 
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
        fade1.setCycleCount(100);
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
        fade2.setCycleCount(100);
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
        fade3.setCycleCount(100);
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
        fade4.setCycleCount(100);
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
        fade5.setCycleCount(100);
        fade5.setAutoReverse(true);
        fade5.play();
        

        
        
        
        
        
        
    }

    
    public void starttimer() {
    	imagetimer= new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				//add method
				imagerotation(button);
			}

		};
		imagetimer.start();
		
    }
    
    //if true

    public void imagerotation(ImageView button) {
    	if (moveimage) {
    		if (button.getLayoutY() > 100 && button==bombbutton) {
				button.setRotate(button.getRotate()+14.3);
				button.setLayoutX(button.getLayoutX()-5);
				button.setLayoutY(button.getLayoutY() -5);
    			Classichelppane.getChildren().removeAll(heartbutton,poisonbutton,starbutton);
    			
    		
            	if(!Classichelppane.getChildren().contains(ninja))
            		Classichelppane.getChildren().addAll(ninja,bombtext,backarrow);      //add bomb text   
			
    		}
    		else if (button.getLayoutY() > 100 && button==heartbutton) {
    	button.setRotate(button.getRotate()+18);
		button.setLayoutX(button.getLayoutX()-8);
		button.setLayoutY(button.getLayoutY() -4);
		Classichelppane.getChildren().removeAll(bombbutton,poisonbutton,starbutton);

	
    	if(!Classichelppane.getChildren().contains(ninja))
    		Classichelppane.getChildren().addAll(ninja,hearttext,backarrow);//add heart text
    	
    		
    }


    		else if (button.getLayoutY() > 100 && button==poisonbutton) {
    	    	button.setRotate(button.getRotate()+18);
    			button.setLayoutX(button.getLayoutX()-14);
    			button.setLayoutY(button.getLayoutY() -4);
    			Classichelppane.getChildren().removeAll(bombbutton,heartbutton,starbutton);

    		
    	    	if(!Classichelppane.getChildren().contains(ninja))
    	    		Classichelppane.getChildren().addAll(ninja,poisontext,backarrow);  //add posion text      
    	    
    	    }

    		else if (button.getLayoutY() > 100 && button==starbutton) {
    	    	button.setRotate(button.getRotate()+11.7);
    			button.setLayoutX(button.getLayoutX()-27);
    			button.setLayoutY(button.getLayoutY() -5);
    			Classichelppane.getChildren().removeAll(bombbutton,heartbutton,poisonbutton);

    		
    	    	if(!Classichelppane.getChildren().contains(ninja))
    	    		Classichelppane.getChildren().addAll(ninja,startext,backarrow);  //star text      
    	    }

    		
    		
    		
    		else
    			moveimage = false;
    	}
    }
    		    
    		
	
    	


   public static AnchorPane getGamePain(){
       return Classichelppane;
   }

  

    public void createNewGame(Stage menustage){
        this.MenuStage = menustage;
     
        createBackground();
        creatGameLoop();
        classichelp.show();
        this.MenuStage.hide();
        
        
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
        Classichelppane.getChildren().addAll(gridPane1,gridPane2,closeButton,help,Sound,bombbutton,heartbutton,poisonbutton,starbutton,ninja1,ninja2,ninja3,ninja4,ninja5);
        
        
   
        
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



