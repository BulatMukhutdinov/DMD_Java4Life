package com.innopolis.courses.dmd.premasters.java4life;

import java.io.*;
import java.net.*;
import java.nio.channels.ClosedChannelException;
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
                System.out.println("Message from client: \"" + line + "\"");
                String res = DBManager.parseAndExecute(line);
                outputStream.write(res.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (ClosedChannelException x) {
            System.out.println("Client " + getName() + " closed");
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