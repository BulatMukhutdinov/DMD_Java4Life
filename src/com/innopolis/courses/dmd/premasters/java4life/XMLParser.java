package com.innopolis.courses.dmd.premasters.java4life;
/**
 * @author Nikolay Yushkevich,
 * created on 12.09.2015
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    public static void main(String argv[]) {

        try { //TODO: Rewrite whole class, it's not working yet
            /*File file = new File("c:\\MyXMLFile.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("employee");
            System.out.println("Information of all employees");
            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    System.out.println("First Name : " + ((Node) fstNm.item(0)).getNodeValue());
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                    System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}