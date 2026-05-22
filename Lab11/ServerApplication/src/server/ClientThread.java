package server;

import server.entity.Player;
import server.repository.PlayerRepository;
import javax.persistence.EntityManager;
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
        EntityManager em = server.getEntityManagerFactory().createEntityManager();
        PlayerRepository playerRepo = new PlayerRepository(em);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String request = in.readLine();
            if (request == null) return;

            if (request.equals("stop")) {
                out.println("server stopped");
                server.stop();
            } else if (request.startsWith("create ")) {
                Player newPlayer = new Player(request.substring(7));
                playerRepo.save(newPlayer);
                out.println("created player id: " + newPlayer.getId());
            } else if (request.startsWith("find ")) {
                Player found = playerRepo.findByName(request.substring(5));
                out.println(found != null ? "found player id: " + found.getId() : "not found");
            } else {
                out.println("server received the request " + request);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            em.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}