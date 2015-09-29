package com.innopolis.courses.dmd.premasters.java4life;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class XMLParser {

    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    private final static String DELIMITER = ";";
    private final static int MAX_ELEMS = 100000;
    private static final int Mb = (int) Math.pow(1024, 2);

    private static List<String> articles = new ArrayList<>();
    private static List<String> articleAuthors = new ArrayList<>();
    private static List<String> books = new ArrayList<>();
    private static List<String> bookAuthors = new ArrayList<>();
    private static List<String> incollections = new ArrayList<>();
    private static List<String> incollectionAuthors = new ArrayList<>();
    private static List<String> inproceedings = new ArrayList<>();
    private static List<String> inproceedingsAuthors = new ArrayList<>();
    private static List<String> mastersthesises = new ArrayList<>();
    private static List<String> mastersthesisAuthors = new ArrayList<>();
    private static List<String> phdthesises = new ArrayList<>();
    private static List<String> phdthesisAuthors = new ArrayList<>();
    private static List<String> proceedings = new ArrayList<>();
    private static List<String> proceedingsAuthors = new ArrayList<>();
    private static List<String> wwws = new ArrayList<>();
    private static List<String> wwwAuthors = new ArrayList<>();


    private static void insertRecordIntoDbUserTable(Record record, String table) {
        try {
            if (table == "article") {
                if (articles.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("article", articles, 4 * Mb);
                }
                articles.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER + record.getEditor()
                        + DELIMITER + record.getTitle() + DELIMITER + record.getPages() + DELIMITER
                        + record.getYear() + DELIMITER + record.getJournal() + DELIMITER + record.getVolume() + DELIMITER
                        + record.getNumber() + DELIMITER + record.getMonth() + DELIMITER + record.getUrl() + DELIMITER
                        + record.getEe() + DELIMITER + record.getCdrom() + DELIMITER + record.getCite() + DELIMITER
                        + record.getPublisher() + DELIMITER + record.getNote() + DELIMITER + record.getCrossref() + "\n");
                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (articleAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("article_author", articleAuthors, 4 * Mb);
                    }
                    articleAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER + record.getAuthors()[i].replaceAll("'", "''") + "\n");
                }
            } else if (table == "book") {
                if (books.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("book", books, 4 * Mb);
                }
                books.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER
                        + record.getEditor() + DELIMITER + record.getTitle()
                        + DELIMITER + record.getPages() + DELIMITER + record.getYear() + DELIMITER
                        + record.getVolume() + DELIMITER + record.getMonth() + DELIMITER
                        + record.getUrl() + DELIMITER + record.getEe() + DELIMITER
                        + record.getCdrom() + DELIMITER + record.getCite() + DELIMITER
                        + record.getPublisher() + DELIMITER + record.getNote() + DELIMITER
                        + record.getIsbn() + DELIMITER + record.getSeries() + DELIMITER
                        + record.getSchool() + DELIMITER + record.getChapter() + "\n");


                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (bookAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("book_author", bookAuthors, 4 * Mb);
                    }
                    bookAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER
                            + record.getAuthors()[i].replaceAll("'", "''") + "\n");

                }
            } else if (table == "incollection") {
                if (incollections.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("incollection", incollections, 4 * Mb);
                }
                incollections.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER
                        + record.getTitle() + DELIMITER + record.getPages() + DELIMITER
                        + record.getYear() + DELIMITER + record.getNumber() + DELIMITER
                        + record.getUrl() + DELIMITER + record.getEe() + DELIMITER
                        + record.getCdrom() + DELIMITER + record.getCite() + DELIMITER
                        + record.getPublisher() + DELIMITER + record.getNote() + DELIMITER
                        + record.getCrossref() + DELIMITER + record.getChapter() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (incollectionAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("incollection_author", incollectionAuthors, 4 * Mb);
                    }
                    incollectionAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER
                            + record.getAuthors()[i].replaceAll("'", "''") + "\n");
                }
            } else if (table == "inproceedings") {
                if (inproceedings.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("inproceedings", inproceedings, 4 * Mb);
                }
                inproceedings.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER +
                        record.getEditor() + DELIMITER + record.getTitle() + DELIMITER +
                        record.getPages() + DELIMITER + record.getYear() + DELIMITER +
                        record.getNumber() + DELIMITER + record.getMonth() + DELIMITER +
                        record.getUrl() + DELIMITER + record.getEe() + DELIMITER + record.getCdrom() + DELIMITER +
                        record.getCite() + DELIMITER + record.getNote() + DELIMITER + record.getCrossref() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (inproceedingsAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("inproceedings_author", inproceedingsAuthors, 4 * Mb);
                    }
                    inproceedingsAuthors.add(record.getKey().replaceAll("'", "''")
                            + DELIMITER + record.getAuthors()[i].replaceAll("'", "''") + "\n");
                }
            } else if (table == "mastersthesis") {
                if (mastersthesises.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("mastersthesis", mastersthesises, 4 * Mb);
                }
                mastersthesises.add(record.getKey() + DELIMITER + record.getMdate()
                        + DELIMITER + record.getTitle() + DELIMITER
                        + record.getPages() + DELIMITER + record.getYear()
                        + DELIMITER + record.getUrl() + DELIMITER + record.getEe()
                        + DELIMITER + record.getSchool() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (mastersthesisAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("mastersthesis_author", mastersthesisAuthors, 4 * Mb);
                    }
                    mastersthesisAuthors.add(record.getKey().replaceAll("'", "''")
                            + DELIMITER + record.getAuthors()[i].replaceAll("'", "''") + "\n");

                }
            } else if (table == "phdthesis") {
                if (phdthesises.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("phdthesis", phdthesises, 4 * Mb);
                }
                phdthesises.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER + record.getTitle()
                        + DELIMITER + record.getPages() + DELIMITER + record.getYear() + DELIMITER
                        + record.getVolume() + DELIMITER + record.getNumber() + DELIMITER + record.getUrl()
                        + DELIMITER + record.getEe() + DELIMITER + record.getPublisher() + DELIMITER + record.getNote() + DELIMITER
                        + record.getIsbn() + DELIMITER + record.getSeries() + DELIMITER + record.getSchool() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (phdthesisAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("phdthesis_author", phdthesisAuthors, 4 * Mb);
                    }
                    phdthesisAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER
                            + record.getAuthors()[i].replaceAll("'", "''") + "\n");

                }
            } else if (table == "proceedings") {
                if (proceedings.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("proceedings", proceedings, 4 * Mb);
                }
                proceedings.add(record.getKey() + DELIMITER + record.getMdate()
                        + DELIMITER + record.getEditor() + DELIMITER + record.getTitle() + DELIMITER
                        + record.getPages() + DELIMITER + record.getYear() + DELIMITER + record.getAddress()
                        + DELIMITER + record.getJournal() + DELIMITER + record.getVolume() + DELIMITER
                        + record.getNumber() + DELIMITER + record.getUrl() + DELIMITER + record.getEe() + DELIMITER
                        + record.getPublisher() + DELIMITER + record.getNote() + DELIMITER
                        + record.getCrossref() + DELIMITER + record.getIsbn() + DELIMITER + record.getSeries() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (proceedingsAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("proceedings_author", proceedingsAuthors, 4 * Mb);
                    }
                    proceedingsAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER
                            + record.getAuthors()[i].replaceAll("'", "''") + "\n");

                }
            } else if (table == "www") {
                if (wwws.size() == MAX_ELEMS) {
                    CSVCreator.writeBuffered("www", wwws, 4 * Mb);
                }
                wwws.add(record.getKey() + DELIMITER + record.getMdate() + DELIMITER + record.getEditor()
                        + DELIMITER + record.getTitle() + DELIMITER + record.getYear() + DELIMITER
                        + record.getUrl() + DELIMITER + record.getCite() + DELIMITER
                        + record.getNote() + DELIMITER + record.getCrossref() + "\n");

                for (int i = 0; i < record.getAuthors().length; i++) {
                    if (wwwAuthors.size() == MAX_ELEMS) {
                        CSVCreator.writeBuffered("www_author", wwwAuthors, 4 * Mb);
                    }
                    wwwAuthors.add(record.getKey().replaceAll("'", "''") + DELIMITER
                            + record.getAuthors()[i].replaceAll("'", "''") + "\n");
                }
            }
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected IO exception: " + e);
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
        try {
            CSVCreator.writeBuffered("article", articles, 4 * Mb);
            CSVCreator.writeBuffered("article_author", articleAuthors, 4 * Mb);
            CSVCreator.writeBuffered("book", books, 4 * Mb);
            CSVCreator.writeBuffered("book_author", bookAuthors, 4 * Mb);
            CSVCreator.writeBuffered("incollection", incollections, 4 * Mb);
            CSVCreator.writeBuffered("incollection_author", incollectionAuthors, 4 * Mb);
            CSVCreator.writeBuffered("inproceedings", inproceedings, 4 * Mb);
            CSVCreator.writeBuffered("inproceedings_author", inproceedingsAuthors, 4 * Mb);
            CSVCreator.writeBuffered("mastersthesis", mastersthesises, 4 * Mb);
            CSVCreator.writeBuffered("mastersthesis_author", mastersthesisAuthors, 4 * Mb);
            CSVCreator.writeBuffered("phdthesis", phdthesises, 4 * Mb);
            CSVCreator.writeBuffered("phdthesis_author", phdthesisAuthors, 4 * Mb);
            CSVCreator.writeBuffered("proceedings", proceedings, 4 * Mb);
            CSVCreator.writeBuffered("proceedings_author", proceedingsAuthors, 4 * Mb);
            CSVCreator.writeBuffered("www", wwws, 4 * Mb);
            CSVCreator.writeBuffered("www_author", wwws, 4 * Mb);
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected IO exception: " + e);
        }
    }
}