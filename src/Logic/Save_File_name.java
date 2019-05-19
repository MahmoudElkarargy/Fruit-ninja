package Logic;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Save_File_name {
    int Case;
    public Save_File_name(int Case){
        this.Case = Case;
    }
    public void OverWrite(String Name)  {
        FileWriter fileWriter = null;
        String fileName = null;
        if(Case == 1)
             fileName = "src/Logic/playersArcade.txt";
        else if (Case == 0)
            fileName = "src/Logic/playersClassic.txt";

        try {
            fileWriter = new FileWriter(fileName,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(Name+",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OverWrite_scores(int Scores)  {
        FileWriter fileWriter = null;
        String fileName = null;
        if(Case == 1)
            fileName = "src/Logic/playersArcade.txt";
        else if (Case == 0)
            fileName = "src/Logic/playersClassic.txt";

        try {
            fileWriter = new FileWriter(fileName,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(Scores+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
