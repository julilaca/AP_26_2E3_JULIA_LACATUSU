package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;

    private boolean running = true;
    private ServerSocket serverSocket;

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }

        } catch (IOException e) {

        }
    }

    public void stop() {
        running = false;

        try {
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        new GameServer().start();
    }
}
