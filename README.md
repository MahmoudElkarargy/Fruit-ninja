# Fruit-ninja
A college project using Java.

**Game Description

  It is a single player game in which player slices fruits with a blade controlled by mouse swipes
  (finger swipes on touch screen in case of smartphones). As the fruits are thrown onto the
  screen, the player swipes the mouse across the screen to create a slicing motion, attempting to
  slice the fruit into two halves.

**Rules and Tasks

  ● You have at least the following:
  
    o 3 types of Fruits.
    o 2 types of Bombs:
      ▪ Fatal: ends the game immediately.
      ▪ Dangerous: makes you lose one life.
    o 2 types of Special Fruits that add special extra credit points upon slicing.
    
  ● Your goal is to beat your highest score we will implement the classic version of the
    game.
    i.e when you play for the first time you have an initial score of zero, as you play you try to
    beat this score, best_score = max(best_score, current_game_score)
    
  ● You need to support 3 levels of difficulty, but you are free to choose any criteria for
    difficulty (different fruit speed, increase number of bombs, increase number of fruits, ... etc.).
    
  ● You should have a label to show your score. each time you slice a fruit, the score
    increases.
    
  ● You should also have a label to show how long you have been playing the game.
  
  ● You should also show a label with the highest score you are trying to beat.
  
  ● You should also show your remaining lives: you have 3 lives in the game, you lose one
    life in one of those two cases:
    
      o The fruit drops off the screen without slicing it.
      o Slicing Dangerous bomb.
      
  ● You lose the game in one of those cases:
  
    o Slicing Fatal bomb.
    o Losing all your lives ( happens when dropping a fruit or slicing a dangerous bomb).
    
  ● You play until you lose all your lives, so you win the game if you beat your last saved
    high score, initially your score is zero.
    
  ● You should also support saving and loading the game on specific state (score, remaining
    lives and fruits shown on the screen) using XML files.
    
  ● You should use at least 5 of the following design patterns:
  
    o Factory
    o Singleton
    o Command
    o Observer
    o State
    o Decorator
    o Memento
    o Strategy
    o Adapter
    
  ● You must use MVC.
  
**Bonus
  
  ● Playing audio when the game starts and playing slicing audio when fruit is sliced.
  
  ● Supporting Combo swipe: extra points are awarded for slicing multiple fruits with one
    swipe.
    
  ● Supporting Arcade mode: player has only 60 seconds to achieve a high score, lives don’t
    matter just slice till time is out.
    
  ● Supporting multiple players with players score board.
  
  ● Adding special fruits ex( fruits that can make the game slower for a certain amount of
    time, fruits that auto slice all fruits in the screen, .... etc)
    
  ● Any exceptional design or outstanding GUI.
  
  
