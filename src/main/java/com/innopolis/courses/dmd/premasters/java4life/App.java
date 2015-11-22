package com.innopolis.courses.dmd.premasters.java4life;

import java.lang.reflect.InvocationTargetException;


public class App {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // GROUP BY
        /*ConcurrentNavigableMap<String, Record> articles = DBManager.getDb().treeMap("article");
        long time = System.currentTimeMillis();
        LinkedHashMap<String, Record> map = DBManager.groupBy(articles, "mdate", 10);
        System.out.println("Group by " + (System.currentTimeMillis() - time));
        // SELECT
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("select * from article where volume = ;5; 100 50");
        System.out.println("Select " + (System.currentTimeMillis() - time));
        // PROJECTION
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("select key mdate rating from article 100 50");
        System.out.println("Projection " + (System.currentTimeMillis() - time));
        // INSERT
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("insert into article values " +
                "test test test test test test test test test test test test test test test test test test test test test test test test test test test");
        System.out.println("Insertion " + (System.currentTimeMillis() - time));
        // UPDATE
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("update article set month = ;1; where key = ;conf/www/BeszteriV07;");
        System.out.println("Update " + (System.currentTimeMillis() - time));
        // SORTED SELECT
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("select * from article where volume = ;5; 100 50 order by volume");
        System.out.println("Sort " + (System.currentTimeMillis() - time));
        // DELETE
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("delete from article where volume = ;5;");
        System.out.println("Delete " + (System.currentTimeMillis() - time));
        // JOIN
        time = System.currentTimeMillis();
        DBManager.parseAndExecute("join article book 100 50 volume");
        System.out.println("Join " + (System.currentTimeMillis() - time));
        */
        //XMLParser xmlParser = new XMLParser();
        //xmlParser.STAXParse("src/main/resources/dblp.xml");
        Server server = new Server();
        server.start(6666);
    }


}
