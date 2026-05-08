package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String request = in.readLine();

            if (request.equals("stop")) {
                out.println("server stopped");
                server.stop();
            } else {
                out.println("server received the request " + request);
            }

            socket.close();

        } catch (IOException e) {

        }
    }
}