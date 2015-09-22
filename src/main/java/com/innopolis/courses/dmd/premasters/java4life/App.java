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
        DBManager.createDB();
        XMLParser xmlParser = new XMLParser();
        xmlParser.STAXParse(getDataSource());
    }

    private static String getDataSource() {
        URL website;
        try {
            logger.wrapper.log(Level.INFO, "Downloading source...");
            website = new URL(URL + FILE_NAME);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("src/main/resources/" + FILE_NAME);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.wrapper.log(Level.INFO, "Downloading successfully complete");
            Compressor compressor = new Compressor();
            compressor.unGunzipFile("src/main/resources/" + FILE_NAME, "resources/" + FILE_NAME.substring(0, FILE_NAME.length() - 3));
            fos.flush();
            fos.close();
        } catch (MalformedURLException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
            System.exit(1);
        } catch (FileNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
            System.exit(1);
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Downloading failed: ", e);
            System.exit(1);
        }
        logger.wrapper.log(Level.INFO, "Deleting compressed file...");
        File compressedFile = new File("src/main/resources/" + FILE_NAME);
        if (compressedFile.delete()) {
            logger.wrapper.log(Level.INFO, "Deleting successfully complete");
        } else {
            logger.wrapper.log(Level.WARNING, "Deleting failed");
        }
        return "src/main/resources/" + FILE_NAME.substring(0, FILE_NAME.length() - 3);
    }
}
