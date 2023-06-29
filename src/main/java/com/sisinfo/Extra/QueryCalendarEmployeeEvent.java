package com.sisinfo.Extra;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.*;
import java.io.File;

public class QueryCalendarEmployeeEvent {
    public static void main(String[] args) {
        String filePath = "path_del_tuo_file.xml";
        String parameter = "Calendar";
        NodeList queryResult = queryXML(filePath, parameter);

        if (queryResult != null) {
            for (int i = 0; i < queryResult.getLength(); i++) {
                Node node = queryResult.item(i);
                System.out.println(nodeToString(node));
            }
        } else {
            System.out.println("Parametro non valido");
        }
    }

    public static NodeList queryXML(String filePath, String parameter) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Effettua la query in base al parametro specificato
            if (parameter.equals("Calendar")) {
                return doc.getElementsByTagName("calendar");
            } else if (parameter.equals("Employee")) {
                return doc.getElementsByTagName("employee");
            } else if (parameter.equals("Event")) {
                return doc.getElementsByTagName("event");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String nodeToString(Node node) {
        try {
            DOMImplementationLS ls = (DOMImplementationLS) node.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
            LSSerializer serializer = ls.createLSSerializer();
            serializer.getDomConfig().setParameter("xml-declaration", false);
            return serializer.writeToString(node);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
