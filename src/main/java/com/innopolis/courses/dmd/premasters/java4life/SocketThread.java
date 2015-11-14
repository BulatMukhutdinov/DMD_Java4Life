package com.innopolis.courses.dmd.premasters.java4life;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentNavigableMap;

public class SocketThread extends Thread {
    protected Socket socket;

    public SocketThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
            System.out.println("Got a client " + getName());
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(inputStream);
            String line;
            while (true) {
                line = in.readLine(); // ожидаем пока клиент пришлет строку текста.
                if (line == null) {
                    continue;
                }
                //charArray.add(line);
                System.out.println("Message from client: \"" + line + "\"");
                String res = DBManager.parseAndExecute(line);
                /*ConcurrentNavigableMap<String, Record> articles = DBManager.getDb().treeMap("article");
                //LinkedHashMap<String, Record> map = DBManager.groupBy(articles, "mdate", 100);
                String res = "";
                int i = 0;
                for (Record rec : articles.values()) {
                    if (i > 10000) {
                        break;
                    }
                    res += rec.toString();
                    i++;
                }*/
                outputStream.write(res.getBytes());
                outputStream.flush();
            }
        } catch (SocketException x) {
            System.out.println("Client " + getName() + " closed");
            System.out.println();
        } catch (EOFException x) {
            System.out.println("Waiting for the next record...");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}