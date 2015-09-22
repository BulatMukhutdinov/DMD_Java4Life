package com.innopolis.courses.dmd.premasters.java4life;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;

public class XMLParser {

    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static final String DB_NAME = "DBLP";
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    private static Connection connection;
    private static Statement statement;

    private static void createConnection(){
        try {
            connection = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
        }
    }

    private static void insertRecordIntoDbUserTable(Record record, String table) throws SQLException {
        String insertTableSQL = "";
        if  (table == "article"){
            insertTableSQL = "INSERT INTO " + "dblp."+table+" "
                    + "(key, mdate, publtype, reviewid, rating, editor, title, booktitle, pages, year, address, journal, volume, number, month, url, ee, cdrom, cite, publisher, note, crossref, isbn, series, school, chapter) " + "VALUES" +
                    "('"+ record.getKey() +"', '"+ record.getMdate() +"', '"+ record.getPubltype() +"', '"+ record.getReviewid() +"', '"+ record.getRating() +"', '"+ record.getEditor() +"', '"+ record.getTitle() +"', '"+ record.getBooktitle() +"', '"+ record.getPages() +"', '"+ record.getYear() +"', '"+ record.getAddress() +"', '"+ record.getJournal() +"', '"+ record.getVolume() +"', '"+ record.getNumber() +"', '"+ record.getMonth() +"', '"+ record.getUrl() +"', '"+ record.getEe() +"', '"+ record.getCdrom() +"', '"+ record.getCite() +"', '"+ record.getPublisher() +"', '"+ record.getNote() +"', '"+ record.getCrossref() +"', '"+ record.getIsbn() +"', '"+ record.getSeries() +"', '"+ record.getSchool() +"', '"+ record.getChapter()+"')";
        } else {
            insertTableSQL = "INSERT INTO " + "dblp."+table+" "
                    + "(key, mdate, publtype, editor, title, booktitle, pages, year, address, journal, volume, number, month, url, ee, cdrom, cite, publisher, note, crossref, isbn, series, school, chapter) " + "VALUES" +
                    "('"+ record.getKey() +"', '"+ record.getMdate() +"', '"+ record.getPubltype() +"', '"+ record.getEditor() +"', '"+ record.getTitle() +"', '"+ record.getBooktitle() +"', '"+ record.getPages() +"', '"+ record.getYear() +"', '"+ record.getAddress() +"', '"+ record.getJournal() +"', '"+ record.getVolume() +"', '"+ record.getNumber() +"', '"+ record.getMonth() +"', '"+ record.getUrl() +"', '"+ record.getEe() +"', '"+ record.getCdrom() +"', '"+ record.getCite() +"', '"+ record.getPublisher() +"', '"+ record.getNote() +"', '"+ record.getCrossref() +"', '"+ record.getIsbn() +"', '"+ record.getSeries() +"', '"+ record.getSchool() +"', '"+ record.getChapter()+"')";
        }
        try {

            //System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);

            for (int i = 0; i < record.getAuthors().size(); i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp.authors " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") +"', '"+ record.getAuthors().get(i).replaceAll("'", "''")+"')";

                statement.executeUpdate(insertTableSQL2);
                insertTableSQL2 = null;
            }

            //System.out.println("Record is inserted into " + table + " table!");

        } catch (SQLException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
        }
    }

    public void STAXParse(String path) {
        createConnection();

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
                        if ("article".equals(reader.getLocalName())){
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("book".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("incollection".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("inproceedings".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("masterthesis".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("phdthesis".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("proceedings".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if("www".equals(reader.getLocalName())) {
                            currRec = new Record();
                            currRec.setMdate(reader.getAttributeValue(0)); //set mdate
                            currRec.setKey(reader.getAttributeValue(1)); //set key
                        } else if ("dblp".equals(reader.getLocalName())) {
                            logger.wrapper.log(Level.INFO, "XMLParser started in " + LocalDateTime.now());
                        } else if ("sup".equals(reader.getLocalName())){
                                currRec.appendTitle(tagContent);
                                break;
                        } else if ("sub".equals(reader.getLocalName())){
                            currRec.appendTitle(tagContent);
                            break;
                        } else if ("tt".equals(reader.getLocalName())){
                            currRec.appendTitle(tagContent);
                            break;
                        } else if ("i".equals(reader.getLocalName())){
                            currRec.appendTitle(tagContent);
                            break;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "article":
                                insertRecordIntoDbUserTable(currRec, "article");
                                break;
                            case "book":
                                insertRecordIntoDbUserTable(currRec, "book");
                                break;
                            case "incollection":
                                insertRecordIntoDbUserTable(currRec, "incollection");
                                break;
                            case "inproceedings":
                                insertRecordIntoDbUserTable(currRec, "inproceedings");
                                break;
                            case "masterthesis":
                                insertRecordIntoDbUserTable(currRec, "masterthesis");
                                break;
                            case "phdthesis":
                                insertRecordIntoDbUserTable(currRec, "phdthesis");
                                break;
                            case "proceedings":
                                insertRecordIntoDbUserTable(currRec, "proceedings");
                                break;
                            case "www":
                                insertRecordIntoDbUserTable(currRec, "www");
                                break;
                            case "author":
                                currRec.getAuthors().add(tagContent);
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
        } catch (SQLException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
        }
    }
}