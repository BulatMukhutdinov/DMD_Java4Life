package com.innopolis.courses.dmd.premasters.java4life;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.logging.Level;

public class XMLParser {

    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    private ConcurrentNavigableMap<String, Record> articles = DBManager.getDb().treeMap("article");
    private ConcurrentNavigableMap<String, Record> books = DBManager.getDb().treeMap("book");
    private ConcurrentNavigableMap<String, Record> incollections = DBManager.getDb().treeMap("incollection");
    private ConcurrentNavigableMap<String, Record> inproceedings = DBManager.getDb().treeMap("inproceeding");
    private ConcurrentNavigableMap<String, Record> mastersthesises = DBManager.getDb().treeMap("mastersthesis");
    private ConcurrentNavigableMap<String, Record> phdthesises = DBManager.getDb().treeMap("phdthesis");
    private ConcurrentNavigableMap<String, Record> proceedings = DBManager.getDb().treeMap("proceeding");
    private ConcurrentNavigableMap<String, Record> wwws = DBManager.getDb().treeMap("www");


    private void insertRecordIntoDbUserTable(Record record, String table) {
        if (table == "article") {
            articles.put(record.getKey(), record);

        } else if (table == "book") {
            books.put(record.getKey(), record);

        } else if (table == "incollection") {
            incollections.put(record.getKey(), record);

        } else if (table == "inproceedings") {
            inproceedings.put(record.getKey(), record);

        } else if (table == "mastersthesis") {
            mastersthesises.put(record.getKey(), record);

        } else if (table == "phdthesis") {
            phdthesises.put(record.getKey(), record);

        } else if (table == "proceedings") {
            proceedings.put(record.getKey(), record);

        } else if (table == "www") {
            wwws.put(record.getKey(), record);

        }
    }

    public void STAXParse(String path) {
        Record currRec = null;
        String tagContent = "";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, true);
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new FileInputStream(new File(path)));
        } catch (XMLStreamException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected XML Stream exception: " + e);
        } catch (FileNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "XML file was not found: " + e);
        }

        try {
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("article".equals(reader.getLocalName()) || "book".equals(reader.getLocalName()) || "incollection".equals(reader.getLocalName()) || "inproceedings".equals(reader.getLocalName()) || "mastersthesis".equals(reader.getLocalName()) || "phdthesis".equals(reader.getLocalName()) || "proceedings".equals(reader.getLocalName()) || "www".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if ("dblp".equals(reader.getLocalName())) {
                            logger.wrapper.log(Level.INFO, "XMLParser started in " + LocalDateTime.now());
                        } else if ("sup".equals(reader.getLocalName()) || "sub".equals(reader.getLocalName()) || "tt".equals(reader.getLocalName()) || "i".equals(reader.getLocalName())) {
                            currRec.appendTitle(tagContent);
                            break;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        String str = reader.getLocalName();
                        if ("article".equals(str) || "book".equals(str) || "incollection".equals(str) || "mastersthesis".equals(str) || "phdthesis".equals(str) || "proceedings".equals(str) || "inproceedings".equals(reader.getLocalName()) || "www".equals(str)) {
                            insertRecordIntoDbUserTable(currRec, str);
                        }
                        switch (str) {
                            case "author":
                                currRec.addAuthor(tagContent);
                                break;
                            case "publtype":
                                currRec.setPubltype(tagContent);
                                break;
                            case "reviewid":
                                currRec.setReviewid(tagContent);
                                break;
                            case "rating":
                                currRec.setRating(tagContent);
                                break;
                            case "editor":
                                currRec.setEditor(tagContent);
                                break;
                            case "title":
                                currRec.appendTitle(tagContent);
                                break;
                            case "sup":
                                currRec.appendTitle(tagContent);
                                break;
                            case "sub":
                                currRec.appendTitle(tagContent);
                                break;
                            case "i":
                                currRec.appendTitle(tagContent);
                                break;
                            case "tt":
                                currRec.appendTitle(tagContent);
                                break;
                            case "booktitle":
                                currRec.setTitle(tagContent);
                                break;
                            case "pages":
                                currRec.setPages(tagContent);
                                break;
                            case "year":
                                currRec.setYear(tagContent);
                                break;
                            case "volume":
                                currRec.setVolume(tagContent);
                                break;
                            case "address":
                                currRec.setAddress(tagContent);
                                break;
                            case "number":
                                currRec.setNumber(tagContent);
                                break;
                            case "month":
                                currRec.setMonth(tagContent);
                                break;
                            case "journal":
                                currRec.setJournal(tagContent);
                                break;
                            case "url":
                                currRec.setUrl(tagContent);
                                break;
                            case "ee":
                                currRec.setEe(tagContent);
                                break;
                            case "cdrom":
                                currRec.setCdrom(tagContent);
                                break;
                            case "cite":
                                currRec.setCite(tagContent);
                                break;
                            case "publisher":
                                currRec.setPublisher(tagContent);
                                break;
                            case "note":
                                currRec.setNote(tagContent);
                                break;
                            case "crossref":
                                currRec.setCrossref(tagContent);
                                break;
                            case "isbn":
                                currRec.setIsbn(tagContent);
                                break;
                            case "series":
                                currRec.setSeries(tagContent);
                                break;
                            case "school":
                                currRec.setSchool(tagContent);
                                break;
                            case "chapter":
                                currRec.setChapter(tagContent);
                                break;
                            case "dblp":
                                logger.wrapper.log(Level.INFO, "XMLParser finished in " + LocalDateTime.now());
                                break;
                        }
                        break;

                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected XML exception during parsing: " + e);
        }
        System.out.println("START commit");
        DBManager.getDb().commit();
        System.out.println("END commit");
    }
}