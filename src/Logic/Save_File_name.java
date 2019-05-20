package Logic;
import javafx.scene.control.Label;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Save_File_name {
    int Case;
    String fileName = null;
    public Save_File_name(int Case){
        this.Case = Case;
        if(Case == 1)
            fileName = "src/Logic/playersArcade.txt";
        else if (Case == 0)
            fileName = "src/Logic/playersClassic.txt";
    }
    public void OverWrite(String Name)  {
        FileWriter fileWriter = null;



        try {
            fileWriter = new FileWriter(fileName,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(Name+"         ");
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
    public ArrayList<Label> ReadFile1() {
        ArrayList<Label> labels = new ArrayList<>();
        String line = null;
        try {
            FileReader fileReader =
                    new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {

                String REGEX = "[\n]+";
                Pattern pattern = Pattern.compile(REGEX);
                int limit = 0;
                String tmp[] = pattern.split(line, limit);
                for (int i=0;i<tmp.length;i++){
                labels.add(new Label(tmp[i]));
                }
            }
        }
        catch (Exception e){
            System.out.println("Reading 3'lat");
        }
        System.out.println(labels.size());

        return labels;
    }

}
