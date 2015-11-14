package com.innopolis.courses.dmd.premasters.java4life;

import org.json.simple.JSONObject;
import org.mapdb.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DBManager {
    public static final String DB_NAME = "src/main/resources/dblp";
    private static final DB db = DBMaker.newFileDB(new File(DB_NAME))
            .mmapFileEnable()
            .transactionDisable()
            .asyncWriteEnable()
            .closeOnJvmShutdown()
            .make();
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();

    public static LinkedHashMap<String, Record> groupBy(ConcurrentNavigableMap<String, Record> table,
                                                        String field, int limit) throws NoSuchFieldException, IllegalAccessException {
        LinkedHashMap<String, Record> result = new LinkedHashMap<>();
        List<HashMap<String, ConcurrentNavigableMap<String, Record>>> groups = new ArrayList<>();
        for (Map.Entry<String, Record> entry1 : table.entrySet()) {
            if (limit == 0) {
                break;
            }
            limit--;
            boolean added = false;
            Field f = entry1.getValue().getClass().getDeclaredField(field);
            f.setAccessible(true);
            String key = f.get(entry1.getValue()).toString();
            for (HashMap<String, ConcurrentNavigableMap<String, Record>> group :
                    groups) {
                if (group.entrySet().iterator().next().getKey().equals(key)) {
                    group.get(key).put(entry1.getKey(), entry1.getValue());
                    added = true;
                }
            }
            if (!added) {
                HashMap<String, ConcurrentNavigableMap<String, Record>> v1 = new HashMap<>();
                ConcurrentNavigableMap<String, Record> v2 = new ConcurrentSkipListMap<>();
                v2.put(entry1.getKey(), entry1.getValue());
                v1.put(key, v2);
                groups.add(v1);
            }
        }

        for (HashMap<String, ConcurrentNavigableMap<String, Record>> group :
                groups) {
            result.putAll(group.entrySet().iterator().next().getValue());
        }
        return result;
    }

    public static ConcurrentNavigableMap<String, Record> join(ConcurrentNavigableMap<String, Record> table1,
                                                              ConcurrentNavigableMap<String, Record> table2,
                                                              int limit, int offset, String... joinFields) throws NoSuchFieldException, IllegalAccessException {
        ConcurrentNavigableMap<String, Record> result = new ConcurrentSkipListMap<>();
        for (Map.Entry<String, Record> entry1 : table1.entrySet()) {
            if (offset > 0) {
                offset--;
                continue;
            }
            for (Map.Entry<String, Record> entry2 : table2.entrySet()) {
                if (limit == 0) {
                    return result;
                }
                boolean join = true;
                for (String joinField : joinFields) {
                    Field field = entry1.getValue().getClass().getDeclaredField(joinField);
                    field.setAccessible(true);

                    if (!field.get(entry1.getValue()).toString().equals(field.get(entry2.getValue()).toString())) {
                        join = false;
                        break;
                    }
                }
                if (join) {
                    if (limit > 0) {
                        limit--;
                    }
                    result.put(entry1.getValue().getKey() + entry2.getValue().getKey(),
                            joinRecords(entry1.getValue(), entry2.getValue()));
                }
            }
        }
        return result;
    }

    // Джоин по данному полю у первой мапы и по первому параметру второй
    public static ConcurrentNavigableMap<String, Record> join(ConcurrentNavigableMap<String, Record> table1,
                                                              ConcurrentNavigableMap<String, String> table2,
                                                              int limit, int offset, String joinField) throws NoSuchFieldException, IllegalAccessException {

        ConcurrentNavigableMap<String, Record> result = new ConcurrentSkipListMap<>();
        for (Map.Entry<String, Record> entry1 : table1.entrySet()) {
            if (offset > 0) {
                offset--;
                continue;
            }
            for (Map.Entry<String, String> entry2 : table2.entrySet()) {
                if (limit == 0) {
                    return result;
                }
                Field field = entry1.getValue().getClass().getDeclaredField(joinField);
                field.setAccessible(true);
                if (field.get(entry1.getValue()).toString().equals(entry2.getKey())) {
                    Record record = new Record(entry1.getValue());
                    record.addAuthor(entry2.getValue());
                    if (limit > 0) {
                        limit--;
                    }
                    result.put(entry1.getValue().getKey() + entry2.getKey(),
                            entry1.getValue());

                }
            }
        }
        return result;
    }

    public static String parseAndExecute(String line) {
        String args[] = line.split(" ");
        String result = "";
        Map<String, Record> resultTable = null;
        if (args[0].equalsIgnoreCase("join")) {
            ConcurrentNavigableMap<String, Record> table1 = DBManager.getDb().treeMap(args[1].toLowerCase());
            ConcurrentNavigableMap<String, Record> table2 = DBManager.getDb().treeMap(args[2].toLowerCase());
            try {
                resultTable = join(table1, table2, Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                return null;
            }
        } else if (args[0].equalsIgnoreCase("groupBy")) {
            ConcurrentNavigableMap<String, Record> table = DBManager.getDb().treeMap(args[1]);
            try {
                resultTable = groupBy(table, args[2], Integer.parseInt(args[3]));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (Record rec : resultTable.values()) {
            result += toJSON(rec) + "\n";
        }
        return result;
    }

    public static DB getDb() {
        return db;
    }

    private static Record joinRecords(Record r1, Record r2) {
        Set<String> authors = new HashSet<String>();
        Collections.addAll(authors, r1.getAuthors());
        Collections.addAll(authors, r2.getAuthors());
        Record record = new Record(r1.getMdate() + ";" + r2.getMdate(),
                r1.getKey() + ";" + r2.getKey(), r1.getPubltype() + ";" + r2.getPubltype(),
                r1.getReviewid() + ";" + r2.getReviewid(), r1.getRating() + ";" + r2.getRating(),
                authors, r1.getEditor() + ";" + r2.getEditor(), r1.getTitle() + ";" + r2.getTitle(),
                r1.getBooktitle() + ";" + r2.getBooktitle(), r1.getPages() + ";" + r2.getPages(), r1.getYear() + ";" + r2.getYear(),
                r1.getAddress() + ";" + r2.getAddress(), r1.getVolume() + ";" + r2.getVolume(), r1.getJournal() + ";" + r2.getJournal(),
                r1.getNumber() + ";" + r2.getNumber(), r1.getMonth() + ";" + r2.getMonth(), r1.getUrl() + ";" + r2.getUrl(),
                r1.getEe() + ";" + r2.getEe(), r1.getCdrom() + ";" + r2.getCdrom(), r1.getCite() + ";" + r2.getCite(), r1.getPublisher() + ";" + r2.getPublisher(),
                r1.getNote() + ";" + r2.getNote(), r1.getCrossref() + ";" + r2.getCrossref(), r1.getIsbn() + ";" + r2.getIsbn(), r1.getSeries() + ";" + r2.getSeries(),
                r1.getSchool() + ";" + r2.getSchool(), r1.getChapter() + ";" + r2.getChapter());
        return record;
    }

    public static String toJSON(Record rec) {
        JSONObject obj = new JSONObject();
        JSONObject auths = new JSONObject();
        int num = 0;
        for (String a : rec.getAuthors()) {
            num++;
            auths.put(num, a);
        }
        if (rec.getMdate() != null) {
            obj.put("mdate", rec.getMdate());
        }
        if (rec.getKey() != null) {
            obj.put("key", rec.getKey());
        }
        if (rec.getPubltype() != null) {
            obj.put("publtype", rec.getPubltype());
        }
        if (rec.getReviewid() != null) {
            obj.put("reviewid", rec.getReviewid());
        }
        if (rec.getRating() != null) {
            obj.put("rating", rec.getRating());
        }
        if (rec.getAuthors() != null) {
            obj.put("authors", auths);
        }
        if (rec.getEditor() != null) {
            obj.put("editor", rec.getEditor());
        }
        if (rec.getTitle() != null) {
            obj.put("title", rec.getTitle());
        }
        if (rec.getBooktitle() != null) {
            obj.put("booktitle", rec.getBooktitle());
        }
        if (rec.getPages() != null) {
            obj.put("pages", rec.getPages());
        }
        if (rec.getYear() != null) {
            obj.put("year", rec.getYear());
        }
        if (rec.getAddress() != null) {
            obj.put("address", rec.getAddress());
        }
        if (rec.getVolume() != null) {
            obj.put("volume", rec.getVolume());
        }
        if (rec.getJournal() != null) {
            obj.put("journal", rec.getJournal());
        }
        if (rec.getNumber() != null) {
            obj.put("number", rec.getNumber());
        }
        if (rec.getMonth() != null) {
            obj.put("month", rec.getMonth());
        }
        if (rec.getUrl() != null) {
            obj.put("url", rec.getUrl());
        }
        if (rec.getEe() != null) {
            obj.put("ee", rec.getEe());
        }
        if (rec.getCdrom() != null) {
            obj.put("cdrom", rec.getCdrom());
        }
        if (rec.getCite() != null) {
            obj.put("cite", rec.getCite());
        }
        if (rec.getPublisher() != null) {
            obj.put("publisher", rec.getPublisher());
        }
        if (rec.getNote() != null) {
            obj.put("note", rec.getNote());
        }
        if (rec.getCrossref() != null) {
            obj.put("crossref", rec.getCrossref());
        }
        if (rec.getIsbn() != null) {
            obj.put("isbn", rec.getIsbn());
        }
        if (rec.getSeries() != null) {
            obj.put("series", rec.getSeries());
        }
        if (rec.getSchool() != null) {
            obj.put("school", rec.getSchool());
        }
        if (rec.getChapter() != null) {
            obj.put("chapter", rec.getChapter());
        }
        return obj.toJSONString();
    }
}
