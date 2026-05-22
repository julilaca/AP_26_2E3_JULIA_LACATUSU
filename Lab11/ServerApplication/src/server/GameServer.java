package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GameServer {
    public static final int PORT = 8100;

    private boolean running = true;
    private ServerSocket serverSocket;
    private EntityManagerFactory emf;

    public void start() {
        try {
            emf = Persistence.createEntityManagerFactory("GamePU");
            serverSocket = new ServerSocket(PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void main(String[] args) {
        new GameServer().start();
    }
}