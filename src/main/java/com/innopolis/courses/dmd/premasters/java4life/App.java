package com.innopolis.courses.dmd.premasters.java4life;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.mapdb.*;

import java.util.Map;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.logging.Level;


public class App {
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    public static final String URL = "http://dblp.uni-trier.de/xml/";
    public static final String FILE_NAME = "dblp.xml.gz";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DBManager.parseAndExecute("update article set month = ;XUILA; where key = ;conf/www/BeszteriV07;");
        System.out.println(DBManager.parseAndExecute("select * from article where key = ;conf/www/BeszteriV07; 1 0"));
        DBManager.getDb().close();
        // GROUP BY
        // LinkedHashMap<String, Record> map = DBManager.groupBy(articles, "mdate", 10);
        // SELECT
        //String result = DBManager.parseAndExecute("select * from article where key = ;conf/www/BeszteriV07; 1 0");
        // PROJECTION
        // String result = DBManager.parseAndExecute("select key mdate rating from article 10 50");
        // INSERT
        // String result = DBManager.parseAndExecute("insert into article values " +
        //       "test test test test test test test test test test test test test test test test test test test test test test test test test test test");
        // UPDATE
        //String result = DBManager.parseAndExecute("update article set month = ;1; where key = ;conf/www/BeszteriV07;");
        //result = DBManager.parseAndExecute("select * from article where key = ;conf/www/BeszteriV07; 1 0");
        //System.out.println(result);
        // SORTED SELECT
        //String result = DBManager.parseAndExecute("select * from book where title = ;The no-nonsense guide to computing careers.; 1 0");
        //System.out.println(result);
        // XMLParser xmlParser = new XMLParser();
        // xmlParser.STAXParse("src/main/resources/dblp.xml");
        // Server server = new Server();
        //server.start(6666);
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
            compressor.unGunzipFile("src/main/resources/" + FILE_NAME, "src/main/resources/" + FILE_NAME.substring(0, FILE_NAME.length() - 3));
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
