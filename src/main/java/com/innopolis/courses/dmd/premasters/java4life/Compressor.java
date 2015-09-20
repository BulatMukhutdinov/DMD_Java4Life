package com.innopolis.courses.dmd.premasters.java4life;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compressor {
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();

    public void gzipFile(String sourceFilename, String destinationZipFilename) {
        byte[] buffer = new byte[1024];
        try {
            logger.wrapper.log(Level.INFO, "Compressing file...");
            FileOutputStream fileOutputStream = new FileOutputStream(destinationZipFilename);
            GZIPOutputStream gzipOuputStream = new GZIPOutputStream(fileOutputStream);
            FileInputStream fileInput = new FileInputStream(sourceFilename);
            int bytes_read;
            while ((bytes_read = fileInput.read(buffer)) > 0) {
                gzipOuputStream.write(buffer, 0, bytes_read);
            }
            fileInput.close();
            gzipOuputStream.finish();
            gzipOuputStream.close();
            logger.wrapper.log(Level.INFO, "Compressing successfully complete");
        } catch (IOException ex) {
            logger.wrapper.log(Level.SEVERE, "Compressing failed: ", ex);
        }
    }

    public void unGunzipFile(String compressedFile, String decompressedFile) {
        byte[] buffer = new byte[1024];
        try {
            logger.wrapper.log(Level.INFO, "Decompressing file...");
            FileInputStream fileIn = new FileInputStream(compressedFile);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);
            int bytes_read;
            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
            gZIPInputStream.close();
            fileOutputStream.close();
            logger.wrapper.log(Level.INFO, "Decompressing successfully complete");
        } catch (IOException ex) {
            logger.wrapper.log(Level.SEVERE, "Decompressing failed: ", ex);
        }

    }
}
