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
    private static DBManager dbManager;
    private static String username, password;
    private static boolean download;

    public static void main(String[] args) {
        if (args.length == 0) {
            username = "postgres";
            password = "postgres";
        } else if (args.length < 4) {
            logger.wrapper.log(Level.SEVERE, "Wrong number of arguments! Input at least username and password");
            throw new IllegalArgumentException();
        } else if (args.length > 5) {
            logger.wrapper.log(Level.SEVERE, "Wrong number of arguments! Too mush input");
            throw new IllegalArgumentException();
        } else {
            processArgs(args);
        }
        dbManager = new DBManager(username, password);
        String xml;
        if (download) {
            xml = getDataSource();
        } else {
            xml = "dblp.xml";
        }
        clearCSV();
        dbManager.createDB();
        XMLParser xmlParser = new XMLParser();
        xmlParser.STAXParse(xml);
        dbManager.copyCSV();
        dbManager.createConstraints();
    }

    private static String getDataSource() {
        URL website;
        try {
            logger.wrapper.log(Level.INFO, "Downloading source...");
            website = new URL(URL + FILE_NAME);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.wrapper.log(Level.INFO, "Downloading successfully complete");
            Compressor compressor = new Compressor();
            compressor.unGunzipFile(FILE_NAME, FILE_NAME.substring(0, FILE_NAME.length() - 3));
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
        File compressedFile = new File(FILE_NAME);
        if (compressedFile.delete()) {
            logger.wrapper.log(Level.INFO, "Deleting successfully complete");
        } else {
            logger.wrapper.log(Level.WARNING, "Deleting failed");
        }
        return FILE_NAME.substring(0, FILE_NAME.length() - 3);
    }

    private static void clearCSV() {
        File csvs = new File(CSVCreator.CSV_PATH);
        if (!csvs.exists()) {
            csvs.mkdir();
        }
        logger.wrapper.log(Level.INFO, "Deleting csv files...");
        for (File csv : csvs.listFiles()) {
            csv.delete();
        }
        logger.wrapper.log(Level.INFO, "Deleting successfully complete");
    }

    private static void processArgs(String args[]) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                username = args[i + 1];
                i++;
            } else if (args[i].equals("-p")) {
                password = args[i + 1];
                i++;
            } else if (args[i].equals("-d")) {
                download = true;
            }
        }
    }
}
