package com.innopolis.courses.dmd.premasters.java4life;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

public class App {
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    public static final String URL = "http://dblp.uni-trier.de/xml/";
    public static final String FILE_NAME = "dblp.xml.gz";

    public static void main(String[] args) {
        //getDataSource();
        DBManager.createDB();
        DBManager.createTables();
        XMLParser xmlParser = new XMLParser();
        xmlParser.STAXParse("example.xml");
    }

    private static void getDataSource() {
        URL website;
        try {
            logger.wrapper.log(Level.INFO, "Downloading source...");
            website = new URL(URL + FILE_NAME);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("resources/" + FILE_NAME);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.wrapper.log(Level.INFO, "Downloading successfully complete");
            Compressor compressor = new Compressor();
            compressor.unGunzipFile("resources/" + FILE_NAME, "resources/" + FILE_NAME.substring(0, FILE_NAME.length() - 3));
        } catch (MalformedURLException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
        } catch (FileNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
        }
        logger.wrapper.log(Level.INFO, "Deleting compressed file...");
        File compressedFile = new File("resources/" + FILE_NAME);
        if (compressedFile.delete()){
            logger.wrapper.log(Level.INFO, "Deleting successfully complete");
        }else{
            logger.wrapper.log(Level.WARNING, "Deleting failed");
        }
    }
}
