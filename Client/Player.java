package Client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Player {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1. Player gives their Ip.
        System.out.print("Give your Ip adress: ");
        String ipAdress = scanner.nextLine();

        //2. Player gives the port they want to connect on.
        System.out.print("Give a port on which you want to play: ");
        int port = Integer.parseInt(scanner.nextLine());

        startGame(ipAdress, port, scanner);
    }

    public static void startGame(String ipAdress, int port, Scanner scanner){
        
        try {
            Socket clientSocket = new Socket(ipAdress, port);
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
