package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static final int PORT = 8100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            try {
                Socket socket = new Socket("127.0.0.1", PORT);

                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                out.println(command);

                String response = in.readLine();
                System.out.println(response);

                socket.close();

            } catch (IOException e) {

            }
        }
    }
}
