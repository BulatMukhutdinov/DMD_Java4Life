package com.innopolis.courses.dmd.premasters.java4life;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;

public class XMLParser {

    private final static LoggerWrapper logger = LoggerWrapper.getInstance();


    private static void insertRecordIntoDbUserTable(Record record, String table) {
        String insertTableSQL = "";
        if (table == "article") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, editor, title, pages, year, journal, volume, number, month, url, ee, cdrom, cite, publisher, note, crossref) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getEditor() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getJournal() + "', '" + record.getVolume() + "', '" + record.getNumber() + "', '" + record.getMonth() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getCdrom() + "', '" + record.getCite() + "', '" + record.getPublisher() + "', '" + record.getNote() + "', '" + record.getCrossref() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp.article_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "book") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, editor, title, pages, year, volume, month, url, ee, cdrom, cite, publisher, note, isbn, series, school, chapter) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate()  + "', '" + record.getEditor() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getVolume() + "', '" + record.getMonth() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getCdrom() + "', '" + record.getCite() + "', '" + record.getPublisher() + "', '" + record.getNote() + "', '" + record.getIsbn() + "', '" + record.getSeries() + "', '" + record.getSchool() + "', '" + record.getChapter() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "incollection") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, title, pages, year, number, url, ee, cdrom, cite, publisher, note, crossref, chapter) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getTitle()  + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getNumber() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getCdrom() + "', '" + record.getCite() + "', '" + record.getPublisher() + "', '" + record.getNote() + "', '" + record.getCrossref() + "', '" + record.getChapter() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "inproceedings") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, editor, title, pages, year, number, month, url, ee, cdrom, cite, note, crossref) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getEditor() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getNumber() + "', '" + record.getMonth() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getCdrom() + "', '" + record.getCite() + "', '" + record.getNote() + "', '" + record.getCrossref() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "mastersthesis") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, title, pages, year, url, ee, school) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getSchool() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "phdthesis") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, title, pages, year, volume, number, url, ee, publisher, note, isbn, series, school) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getVolume() + "', '" + record.getNumber() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getPublisher() + "', '" + record.getNote() + "', '" + record.getIsbn() + "', '" + record.getSeries() + "', '" + record.getSchool() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "proceedings") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, editor, title, pages, year, address, journal, volume, number, url, ee, publisher, note, crossref, isbn, series) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getEditor() + "', '" + record.getTitle() + "', '" + record.getPages() + "', '" + record.getYear() + "', '" + record.getAddress() + "', '" + record.getJournal() + "', '" + record.getVolume() + "', '" + record.getNumber() + "', '" + record.getUrl() + "', '" + record.getEe() + "', '" + record.getPublisher() + "', '" + record.getNote() + "', '" + record.getCrossref() + "', '" + record.getIsbn() + "', '" + record.getSeries() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
        } else if (table == "www") {
            insertTableSQL = "INSERT INTO " + "dblp." + table + " "
                    + "(key, mdate, editor, title, year, url, cite, note, crossref ) " + "VALUES" +
                    "('" + record.getKey() + "', '" + record.getMdate() + "', '" + record.getEditor() + "', '" + record.getTitle() + "', '" + record.getYear() + "', '" + record.getUrl() + "', '" + record.getCite() + "', '" + record.getNote() + "', '" + record.getCrossref() + "')";
            try {
                DBManager.stmt.executeUpdate(insertTableSQL);
            } catch (SQLException e) {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
            }
            for (int i = 0; i < record.getAuthors().length; i++) {
                String insertTableSQL2 = "INSERT INTO " + "dblp." + table + "_author " +
                        "(\"key\", \"author\")" + " VALUES " + "('" + record.getKey().replaceAll("'", "''") + "', '" + record.getAuthors()[i].replaceAll("'", "''") + "')";
                try {
                    DBManager.stmt.executeUpdate(insertTableSQL2);
                } catch (SQLException e) {
                    logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
                }
            }
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
                        if ("article".equals(reader.getLocalName()) || "book".equals(reader.getLocalName()) || "incollection".equals(reader.getLocalName()) ||  "inproceedings".equals(reader.getLocalName()) || "mastersthesis".equals(reader.getLocalName()) || "phdthesis".equals(reader.getLocalName()) || "proceedings".equals(reader.getLocalName()) || "www".equals(reader.getLocalName())) {
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
                        if ("article".equals(str) || "book".equals(str) || "incollection".equals(str) || "mastersthesis".equals(str) || "phdthesis".equals(str) || "proceedings".equals(str) || "inproceedings".equals(reader.getLocalName()) ||  "www".equals(str)) {
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
    }
}