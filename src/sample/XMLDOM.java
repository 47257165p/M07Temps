package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by 47257165p on 05/11/15.
 */
public class XMLDOM {

    private static ArrayList <String> items = new ArrayList();

    public static ArrayList lectorDOM (File inFile) {
        //Protocolo de entrada
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inFile);

            //normalizar las peticiones
            doc.getDocumentElement().normalize();

            NodeList nl = doc.getElementsByTagName("location");
            Element location = (Element) nl.item(0);
            items.add("INFORMACIÓN DEL TIEMPO EN " + location.getElementsByTagName("name").item(0).getTextContent().toUpperCase());

            nl = doc.getElementsByTagName("time");

            //El siguiente for se encarga de escribir por pantalla la información que extraemos de cada tag "time"
            for (int i = 0; i < nl.getLength(); i++) {
                Element fecha = (Element) nl.item(i);
                items.add(
                        "Fecha -" + fecha.getAttribute("from") + "- a: -" + fecha.getAttribute("to") + "-"
                        +" | Temperatura media " + fecha.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("value").getTextContent()
                        + " | ºC, mínima " + fecha.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getTextContent()
                        + " | ºC, máxima " + fecha.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getTextContent() + "ºC"
                        +" | Velocidad del viento " + traducirViento(fecha)
                        + ", " + fecha.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getTextContent().toString() + "mps"
                        +" | Tiempo: " + traducirTiempo(fecha)
                );
            }
        }
        catch (Exception e)
        {
            System.out.println("Error en la aplicación");
        }
        return items;
    }

    //El siguiente método se encarga de traducir el tiempo que viene dado en inglés
    public static String traducirTiempo (Element fecha)
    {
        switch (fecha.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("value").getTextContent())
        {
            case "scattered clouds": return "Nuves dispersas";
            case "broken clouds": return "Nuves rotas";
            case "overcast clouds": return "Nublado";
            case "few clouds": return "Nuves escasas";
            case "clear sky": return "Cielo limpio";
            default: return "Sin información";
        }
    }
    public static String traducirViento (Element fecha)
    {
        switch (fecha.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("name").getTextContent())
        {
            case "Gentle Breeze": return "Brisa suave";
            case "Light breeze": return "Brisa ligera";
            case "Moderate breeze": return "Brisa moderada";
            case "Calm": return "Calmado";
            default: return "Sin información";
        }
    }
}
