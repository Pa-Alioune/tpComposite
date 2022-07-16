import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class Main {
    private static int count;

    public static String pathToXml(String path){

        File directory = new File(path);
        String xml = "\n" + "<directory name = " + "\"" + directory.getName() +"\"" + ">" ;
        File [] liste = directory.listFiles();
        for(File item : liste){
            if (item.isFile()) {
                xml = xml + "\n" + "\t" + "<file name = " + "\"" + item.getName() + "\"" + "/>";
            }
            else if (item.isDirectory()) {
                xml = xml + pathToXml(item.getAbsolutePath());
            }
        }

        xml = xml + "\n" + "</directory>" + "\n";
        return xml;

    }

    public static Composant insertion(Element e){
        Dossier home = new Dossier(e.getAttribute("name"), count);
        NodeList nodes = e.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++){
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element el = (Element)nodes.item(i);
                if(el.getNodeName().equals("file")){
                    Composant myFile;
                    myFile = new Fichier(el.getAttribute("name"),  home.getCount() + 1);
                    home.ajouter(myFile);
                } else if (el.getNodeName().equals("directory")){
                    count = home.getCount() + 1;
                    home.ajouter(insertion(el));
                    count--;
                }
            }
        }
        return home;
    } 


    public static Composant xmlToDoc(String xml) throws ParserConfigurationException, SAXException, IOException{
        String xmlStr = "<?xml version=\"1.0\"?>" + xml;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xmlStr);
        ByteArrayInputStream input = new ByteArrayInputStream(
        xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element element = doc.getDocumentElement();
        return insertion(element);
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        // String path = args[0];

        // Dossier directory = new Dossier(path, 0);
        // directory.afficher();

        Composant home = xmlToDoc(pathToXml(args[0]));
        home.afficher();

        // String a = pathToXml(args[0]);
        // System.out.println(a);
    }
}
