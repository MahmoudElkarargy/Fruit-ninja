package Logic;

import View.Fruits;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class SaveScoreFILE {
//    GameEngine Game = GameEngine.getInstance();
   private SaveScoreModel save;
    public SaveScoreFILE(SaveScoreModel save){
        this.save = save;
    }
    public void Save_File(){
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
             Document doc = icBuilder.newDocument();

            Element mainRootElement;
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Logic/Score.xml", false));

            mainRootElement = doc.createElementNS("alyelshwahy@yahoo.com", "Data");

            doc.appendChild(mainRootElement);
            mainRootElement.appendChild(createUser(doc,save.type,save.score,save.fruits));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);


            StreamResult file = new StreamResult(bw);
            transf.transform(source, file);

        }


        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static Node createUser(Document doc, int type , int score, List<Fruits> fruits){
        Element user = doc.createElement("user1");
        user.setAttribute("TYPE", Integer.toString(type));
        user.appendChild(createUserElement(doc, "Score", Integer.toString(score)) );
        for (int i =0; i<fruits.size();i++){
            user.appendChild(createUserElement(doc, "Location", fruits.get(i).getXlocation() +"  " + fruits.get(i).getYlocation()));
        }

return user;
    }

    private static Node createUserElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
}
