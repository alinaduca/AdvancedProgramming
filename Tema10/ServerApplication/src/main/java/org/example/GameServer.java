package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

public class GameServer {
    private final int port;
    private ServerSocket serverSocket;
    private boolean running = false;
    private Map<String, Game> games = new HashMap<>();
    private List<ClientThread> clients = new ArrayList<>();

    public GameServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("Game server a inceput la portul " + port);

        while (running) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client conectat: " + clientSocket.getInetAddress().getHostName());
            ClientThread client = new ClientThread(clientSocket, this);
            clients.add(client);
            client.start();
        }
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        System.out.println("Serverul s-a oprit");
    }

    public void createGame(String gameId, int boardSize, int timeLimit) {
        if (games.containsKey(gameId)) {
            System.out.println("Jocul cu id-ul " + gameId + " exista deja");
            return;
        }

        Game game = new Game(boardSize);
        games.put(gameId, game);
        System.out.println("Jocul cu id-ul " + gameId + " a fost creat");
    }

    public Map<String, Game> getGames ()
    {
        return games;
    }

    public void joinGame(String gameId, String playerName, ClientThread client) {
        Game game = games.get(gameId);
        if (game == null) {
            System.out.println("Jocul cu id-ul " + gameId + " nu exista");
            return;
        }

        Player player = new Player(playerName, client);
        boolean success = game.addPlayer(player);
        if (!success) {
            System.out.println("Nu s-a reusit intarea in jocul cu id-ul " + gameId);
            return;
        }

        System.out.println("Jucatorul " + playerName + " a intrat in jocul cu id-ul " + gameId);
    }

    public String submitMove(String gameId, int row, int col, Player player) {
        Game game = games.get(gameId);
        String message = "";
        if (game == null) {
            message = "Eroare de la server";
            System.out.println("Jocul cu id-ul " + gameId + " nu exista");
            return message;
        }

        boolean timeOut = false;
        boolean success = game.makeMove(row, col, player, timeOut);
        if (!success) {
            message = "Mutare invalida";
            System.out.println("Mutare invalida primita de la jucatorul " + player.getName());
            return message;
        }
        message = "Ai efectuat o mutare in celula [" + row + "][" + col + "]";
        System.out.println("Jucatorul " + player.getName() + " a facut o mutare in jocul cu id-ul " + gameId);
        return message;
    }

    public void CloseAllClients (String message)
    {
        // Itereaza prin lista de socket-uri si trimite mesajul
        for (ClientThread client : clients) {
            try {
                Socket socket = client.getClientSocket();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Inchide toate socket-urile clientilor
        for (ClientThread client : clients) {
            try {
                Socket socket = client.getClientSocket();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getStatus(String gameId, Player player) {
        Game game = games.get(gameId);
        if (game == null) {
            System.out.println("Jocul cu id-ul " + gameId + " nu exista");
            return "Nu s-a gasit jocul";
        }

        String status = game.getStatus(player);
        return status;
    }


    public static void main(String[] args) {
        GameServer server = new GameServer(6666);
        try {
            server.start();
        } catch (IOException e) {
            System.err.println("Eroare la pornirea serverului: " + e.getMessage());
        }
    }
}