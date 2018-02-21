package nanterre.paris10.miage.kami_game.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Mamadou BABA on 14/02/2018.
 */

public class XMLreader {

    private static String file;

    public XMLreader(String file) {
        this.file = file;
    }

    public static String getColorMatrice(){
        String colours = "vide";
        int matrice[][] = null;
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("PuzzleData");
            Node node = nodeList.item(0);
            Element element = (Element) node;
            colours = doc.getDocumentElement().getAttribute("colours");//element.getAttribute("colours");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return colours;
    }
}
