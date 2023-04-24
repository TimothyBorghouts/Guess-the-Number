package Client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Player2 {

    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 1112;

        startGame(serverName, port);
    }

    public static void startGame(String serverName, int port){
        Scanner scanner = new Scanner(System.in);
        try {
            Socket clientSocket = new Socket(serverName, port);
            System.out.println("Connected to the GameServer on port " + port + ".");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while(true){
                String messageFromServer = in.readLine().toString();
                if(messageFromServer.equals("start")){
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                    String givenNumber = scanner.nextLine();
                    out.println(givenNumber);
                } else {
                    System.out.println("Server: " + messageFromServer);
                }
            }

        } catch(Exception exception){
            exception.printStackTrace();
        }
        
    }
    
}
