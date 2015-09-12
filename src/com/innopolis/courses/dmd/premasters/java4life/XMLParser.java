package com.innopolis.courses.dmd.premasters.java4life;
/**
 * @author Nikolay Yushkevich,
 * created on 12.09.2015
 */

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
//
//private String mdate;
//private String key;
//private String author;
//private String title; // could be long
//private String pages;
//private int year;
//private int volume;
//private String journal;
//private int number;
//private String path;
//private String doi;

public class XMLParser {
    public void parseXML (String filePath) {
        try { //TODO: Rewrite whole class, it's not working yet
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("article");
            System.out.println("Information of all articles");
            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    String mdate = fstElmnt.getAttribute("mdate");
                    String key = fstElmnt.getAttribute("key");
                    System.out.println("Mdate : " + mdate);
                    System.out.println("Key : " + key);
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("title");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    System.out.println("Title : " + ((Node) fstNm.item(0)).getNodeValue());
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("author");
                    for (int i = 0; i <lstNmElmntLst.getLength() ; i++) {
                        Element lstNmElmnt = (Element) lstNmElmntLst.item(i);
                        NodeList lstNm = lstNmElmnt.getChildNodes();
                        for (int j = 0; j < lstNm.getLength(); j++) {
                            System.out.println("Author : " + ((Node) lstNm.item(j)).getNodeValue());
                        }
                    }
                    NodeList pgsLst = fstElmnt.getElementsByTagName("pages");
                    Element pgsElmt = (Element) pgsLst.item(0);
                    NodeList pgs = pgsElmt.getChildNodes();
                    System.out.println("Pages : " + ((Node) pgs.item(0)).getNodeValue());

                    NodeList yearLst = fstElmnt.getElementsByTagName("year");
                    Element yearElmt = (Element) yearLst.item(0);
                    NodeList year = yearElmt.getChildNodes();
                    System.out.println("Year : " + ((Node) year.item(0)).getNodeValue());

                    NodeList vlmLst = fstElmnt.getElementsByTagName("volume");
                    Element vlmElmt = (Element) vlmLst.item(0);
                    NodeList volume = vlmElmt.getChildNodes();
                    System.out.println("Volume : " + ((Node) volume.item(0)).getNodeValue());

                    NodeList jrnLst = fstElmnt.getElementsByTagName("journal");
                    Element jrnElmt = (Element) jrnLst.item(0);
                    NodeList journal = jrnElmt.getChildNodes();
                    System.out.println("Journal : " + ((Node) journal.item(0)).getNodeValue());

                    NodeList nbrLst = fstElmnt.getElementsByTagName("number");
                    Element nbrElmt = (Element) nbrLst.item(0);
                    if (nbrElmt == null) {
                        System.out.println("Number : null");
                    } else {
                        NodeList number = nbrElmt.getChildNodes();
                        System.out.println("Number : " + ((Node) number.item(0)).getNodeValue());
                    }
                    NodeList doiLst = fstElmnt.getElementsByTagName("ee");
                    Element doiElmt = (Element) doiLst.item(0);
                    NodeList doi = doiElmt.getChildNodes();
                    System.out.println("DOI : " + ((Node) doi.item(0)).getNodeValue());

                    NodeList pathLst = fstElmnt.getElementsByTagName("url");
                    Element pathElmt = (Element) pathLst.item(0);
                    NodeList path = pathElmt.getChildNodes();
                    System.out.println("Path : " + ((Node) path.item(0)).getNodeValue());
                    System.out.println("________________");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
