package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient
{
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void start()
    {
        try
        {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Te-ai conectat la serverul " + host + ":" + port);
            System.out.println("'exit' - parasire joc, 'stop' - stop server");
            System.out.println("'create' id_game size_board - creare joc, 'join' id_game nume_jucator - intrare in joc");
            System.out.println("'submit' linie coloana - executare mutare, 'status' - status joc");

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                String input = keyboard.readLine();
                if (input == null || input.equalsIgnoreCase("exit"))
                {
                    break;
                }
                out.println(input);
                String response = in.readLine();
                String[] components = response.split("@");
                for (int i=0; i<components.length; i++)
                {
                    System.out.println(components[i]);
                }
            }
            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("A castigat celalalt jocator");
            System.err.println("Eroare la conectarea cu serverul: " + e.getMessage());

        }
    }

    public static void main(String[] args)
    {
        GameClient gameClient = new GameClient("localhost", 6666);
        gameClient.start();
    }
}


//system get nanoseconds