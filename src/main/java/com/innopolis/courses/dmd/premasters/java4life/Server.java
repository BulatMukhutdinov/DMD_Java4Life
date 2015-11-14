package com.innopolis.courses.dmd.premasters.java4life;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту (может быть любое число от 1025 до 65535)
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Waiting for a client...");
        while (true) {
            try {
                socket = serverSocket.accept();// заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new SocketThread(socket).start();
        }
    }
}