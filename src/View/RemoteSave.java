package View;

import Logic.SaveComand;

public class RemoteSave {
    private SaveComand comand;
    public void setComand(SaveComand comand){
        this.comand = comand;
    }
    public void press(){
        comand.excute();
    }
}
