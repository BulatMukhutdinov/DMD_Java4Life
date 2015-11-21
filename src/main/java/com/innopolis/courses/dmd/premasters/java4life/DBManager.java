package com.innopolis.courses.dmd.premasters.java4life;

import org.json.simple.JSONObject;
import org.mapdb.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static String parseAndExecute(String line) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String args[] = line.split(" ");
        String result = "";
        List<String> fields = new LinkedList<>();
        Map<String, Record> resultTable = new HashMap<>();
        ConcurrentNavigableMap<String, Record> table;
        if (args[0].equalsIgnoreCase("count")) {
            return db.treeMap(args[1].toLowerCase()).size() + "";
        } else if (args[0].equalsIgnoreCase("join")) {
            ConcurrentNavigableMap<String, Record> table1 = db.treeMap(args[1].toLowerCase());
            ConcurrentNavigableMap<String, Record> table2 = db.treeMap(args[2].toLowerCase());
            try {
                resultTable = join(table1, table2, Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                return null;
            }
        } else if (args[0].equalsIgnoreCase("groupBy")) {
            table = DBManager.getDb().treeMap(args[1]);
            try {
                resultTable = groupBy(table, args[2], Integer.parseInt(args[3]));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("select")) {
            int offset;
            int limit;
            int i = 1, j;
            boolean isWhere = line.contains("where");
            if (isWhere) {
                args = processLongArgs(args, "where");
            }
            if (args[1].equals("*")) { // select * from article where key = ;abc cdd; 10 50 order by mdate
                table = DBManager.getDb().treeMap(args[3].toLowerCase());
                if (isWhere) {
                    offset = Integer.parseInt(args[9]);
                    limit = Integer.parseInt(args[8]);
                } else {
                    offset = Integer.parseInt(args[5]);
                    limit = Integer.parseInt(args[4]);
                }
                j = 5;
            } else { // select key mdate from article where key = abc 10 50 order by mdate

                while (!args[i].equals("from")) {
                    fields.add(args[i]);
                    i++;
                }
                table = DBManager.getDb().treeMap(args[++i].toLowerCase());
                j = i + 2;
                if (isWhere) {
                    i += 4;
                    limit = Integer.parseInt(args[++i]);
                    offset = Integer.parseInt(args[++i]);
                } else {
                    limit = Integer.parseInt(args[++i]);
                    offset = Integer.parseInt(args[++i]);
                }

            }
            Comparator<Record> comparator = null;
            if (line.contains("order")) {
                final Field field = new Record().getClass().getDeclaredField(args[i + 3]);
                field.setAccessible(true);
                comparator = new Comparator<Record>() {
                    @Override
                    public int compare(Record o1, Record o2) {
                        try {
                            if (field.get(o1) == null && field.get(o2) == null) {
                                return 0;
                            } else if (field.get(o1) == null) {
                                return -1;
                            } else if (field.get(o2) == null) {
                                return 1;
                            } else {
                                return field.get(o1).toString().compareTo(field.get(o2).toString());
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                };
            }
            Field where = null;
            if (isWhere) {
                where = new Record().getClass().getDeclaredField(args[j]);
                where.setAccessible(true);
            }
            List<Record> sortedList = new LinkedList<>();
            resultTable = new LinkedHashMap<>();
            for (Map.Entry<String, Record> entry : table.entrySet()) {
                if (offset > 0) {
                    offset--;
                    continue;
                }
                if (limit == 0) {
                    break;
                }
                if (where != null) {
                    if (where.get(entry.getValue()).toString().equals(args[j + 2])) {
                        limit--;
                        sortedList.add(entry.getValue());
                    }
                } else {
                    limit--;
                    sortedList.add(entry.getValue());
                }

            }
            if (comparator != null) {
                Collections.sort(sortedList, comparator);
            }
            for (Record r : sortedList) {
                resultTable.put(r.getKey(), r);
            }
            // isert into author values key mdate ...
        } else if (args[0].equalsIgnoreCase("insert")) { // авторы через ;
            table = db.treeMap(args[2].toLowerCase());
            Set<String> authors = new HashSet<>();
            for (String auth : args[9].split(";")) {
                authors.add(auth);
            }
            Record record = new Record(args[4], args[5], args[6], args[7], args[8], authors, args[10], args[11],
                    args[12], args[13], args[14], args[15],
                    args[16], args[17], args[18], args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28], args[29], args[30]);
            table.put(record.getKey(), record);
            db.commit();
        } else if (args[0].equalsIgnoreCase("update")) { // update article set mdate = newMdate where key = myKey
            table = db.treeMap(args[1].toLowerCase());
            String updateField = args[7];
            args = processLongArgs(args, "set");
            args = processLongArgs(args, "where");

            String value = args[5];
            Field field;
            Method method;
            field = new Record().getClass().getDeclaredField(updateField);
            field.setAccessible(true);
            method = new Record().getClass().getMethod("set" + args[3].substring(0, 1).toUpperCase() + args[3].substring(1), String.class);
            Record record = null;
            for (Map.Entry<String, Record> entry : table.entrySet()) {
                if (field.get(entry.getValue()) != null && field.get(entry.getValue()).toString().equals(args[9])) {
                    record = new Record(entry.getValue());
                    method.invoke(record, value);
                    break;
                }
            }
            if (record != null) {
                table.put(record.getKey(), record);
                db.commit();
            }
        }
        for (Record rec : resultTable.values()) {
            result += toJSON(rec, fields) + "\n";
        }
        return result;
    }

    private static String[] processLongArgs(String args[], String word) {
        int w;
        List<String> argsWithSingleArg = new LinkedList<>();
        for (w = 0; w < args.length; w++) {
            if (args[w].equals(word)) {
                argsWithSingleArg.add(args[w]);
                argsWithSingleArg.add(args[w + 1]);
                argsWithSingleArg.add(args[w + 2]);
                argsWithSingleArg.add(args[w + 3].substring(1));
                w += 3;
                if (args[w].indexOf(";", 1) != -1) {
                    argsWithSingleArg.set(argsWithSingleArg.size() - 1, args[w].substring(1, args[w].length() - 1));
                    continue;
                } else {
                    String value = "";
                    do {
                        w++;
                        value += args[w] + " ";
                    } while (!args[w].contains(";"));
                    value = value.substring(0, value.length() - 1);
                    int last = argsWithSingleArg.size() - 1;
                    String lastValue = argsWithSingleArg.get(last);
                    System.out.println(lastValue + " " + value.substring(0, value.length() - 1));
                    argsWithSingleArg.set(last, lastValue + " " + value.substring(0, value.length() - 1));
                }
            } else {
                argsWithSingleArg.add(args[w]);
            }
        }
        args = new String[argsWithSingleArg.size()];
        w = 0;
        for (String s : argsWithSingleArg) {
            args[w] = s;
            w++;
        }
        return args;
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

    public static String toJSON(Record rec, List<String> fields) {
        JSONObject obj = new JSONObject();
        JSONObject auths = new JSONObject();
        int num = 0;
        for (String a : rec.getAuthors()) {
            num++;
            auths.put(num, a);
        }

        if (fields.size() > 0 && fields.contains("mdate") || fields.size() == 0 && rec.getMdate() != null) {
            obj.put("mdate", rec.getMdate());
        }
        if (fields.size() > 0 && fields.contains("key") || fields.size() == 0 && rec.getKey() != null) {
            obj.put("key", rec.getKey());
        }
        if (fields.size() > 0 && fields.contains("publtype") || fields.size() == 0 && rec.getPubltype() != null) {
            obj.put("publtype", rec.getPubltype());
        }
        if (fields.size() > 0 && fields.contains("reviewid") || fields.size() == 0 && rec.getReviewid() != null) {
            obj.put("reviewid", rec.getReviewid());
        }
        if (fields.size() > 0 && fields.contains("rating") || fields.size() == 0 && rec.getRating() != null) {
            obj.put("rating", rec.getRating());
        }
        if (fields.size() > 0 && fields.contains("authors") || fields.size() == 0 && rec.getAuthors() != null) {
            obj.put("authors", auths);
        }
        if (fields.size() > 0 && fields.contains("editor") || fields.size() == 0 && rec.getEditor() != null) {
            obj.put("editor", rec.getEditor());
        }
        if (fields.size() > 0 && fields.contains("title") || fields.size() == 0 && rec.getTitle() != null) {
            obj.put("title", rec.getTitle());
        }
        if (fields.size() > 0 && fields.contains("booktitle") || fields.size() == 0 && rec.getBooktitle() != null) {
            obj.put("booktitle", rec.getBooktitle());
        }
        if (fields.size() > 0 && fields.contains("pages") || fields.size() == 0 && rec.getPages() != null) {
            obj.put("pages", rec.getPages());
        }
        if (fields.size() > 0 && fields.contains("year") || fields.size() == 0 && rec.getYear() != null) {
            obj.put("year", rec.getYear());
        }
        if (fields.size() > 0 && fields.contains("address") || fields.size() == 0 && rec.getAddress() != null) {
            obj.put("address", rec.getAddress());
        }
        if (fields.size() > 0 && fields.contains("volume") || fields.size() == 0 && rec.getVolume() != null) {
            obj.put("volume", rec.getVolume());
        }
        if (fields.size() > 0 && fields.contains("journal") || fields.size() == 0 && rec.getJournal() != null) {
            obj.put("journal", rec.getJournal());
        }
        if (fields.size() > 0 && fields.contains("number") || fields.size() == 0 && rec.getNumber() != null) {
            obj.put("number", rec.getNumber());
        }
        if (fields.size() > 0 && fields.contains("month") || fields.size() == 0 && rec.getMonth() != null) {
            obj.put("month", rec.getMonth());
        }
        if (fields.size() > 0 && fields.contains("url") || fields.size() == 0 && rec.getUrl() != null) {
            obj.put("url", rec.getUrl());
        }
        if (fields.size() > 0 && fields.contains("ee") || fields.size() == 0 && rec.getEe() != null) {
            obj.put("ee", rec.getEe());
        }
        if (fields.size() > 0 && fields.contains("cdrom") || fields.size() == 0 && rec.getCdrom() != null) {
            obj.put("cdrom", rec.getCdrom());
        }
        if (fields.size() > 0 && fields.contains("cite") || fields.size() == 0 && rec.getCite() != null) {
            obj.put("cite", rec.getCite());
        }
        if (fields.size() > 0 && fields.contains("publisher") || fields.size() == 0 && rec.getPublisher() != null) {
            obj.put("publisher", rec.getPublisher());
        }
        if (fields.size() > 0 && fields.contains("note") || fields.size() == 0 && rec.getNote() != null) {
            obj.put("note", rec.getNote());
        }
        if (fields.size() > 0 && fields.contains("crossref") || fields.size() == 0 && rec.getCrossref() != null) {
            obj.put("crossref", rec.getCrossref());
        }
        if (fields.size() > 0 && fields.contains("isbn") || fields.size() == 0 && rec.getIsbn() != null) {
            obj.put("isbn", rec.getIsbn());
        }
        if (fields.size() > 0 && fields.contains("series") || fields.size() == 0 && rec.getSeries() != null) {
            obj.put("series", rec.getSeries());
        }
        if (fields.size() > 0 && fields.contains("school") || fields.size() == 0 && rec.getSchool() != null) {
            obj.put("school", rec.getSchool());
        }
        if (fields.size() > 0 && fields.contains("chapter") || fields.size() == 0 && rec.getChapter() != null) {
            obj.put("chapter", rec.getChapter());
        }
        return obj.toJSONString();
    }
}
