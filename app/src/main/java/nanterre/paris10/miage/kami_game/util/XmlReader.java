package nanterre.paris10.miage.kami_game.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by MAKADJI Mamadou Baba on 04/03/2018.
 */

// Permet de lire les fichiers xml dans Asset et retourne le contenu
public class XmlReader {
    public static HashMap<String, String> getXmlContains(InputStream inputStream){
        HashMap<String, String> contains = new HashMap<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("PuzzleData");
            Node node = nodeList.item(0);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                contains.put("namePuzzle", element.getAttribute("name"));
                contains.put("numColours", element.getAttribute("numColours"));
                contains.put("colours", element.getAttribute("colours"));
            }
            nodeList = doc.getElementsByTagName("Solution");
            node = nodeList.item(0);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                contains.put("NombreTentatives", element.getAttribute("length"));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return contains;
    }
}
