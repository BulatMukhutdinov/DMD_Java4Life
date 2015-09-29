package com.innopolis.courses.dmd.premasters.java4life;

import java.io.*;
import java.util.List;

public class CSVCreator {

    public static final String CSV_PATH = "src/main/resources/csv/";

    public static void writeBuffered(String fileName, List<String> records, int bufSize) throws IOException {
        try (PrintWriter bufferedWriter = new PrintWriter(new BufferedWriter(new FileWriter(CSV_PATH + fileName, true), bufSize))) {
            write(records, bufferedWriter);
        }
        records.clear();
    }

    private static void write(List<String> records, Writer writer) throws IOException {
        for (String record : records) {
            writer.write(record);
        }
        writer.flush();
        writer.close();
    }
}