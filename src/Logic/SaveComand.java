package Logic;

import MainPackage.ISaveCommand;

public class SaveComand implements ISaveCommand {
    SaveScoreFILE save;
    public SaveComand(SaveScoreFILE save){
        this.save = save;
    }
    @Override
    public void excute() {
        save.Save_File();
    }
}
