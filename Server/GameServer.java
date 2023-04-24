package Server;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class GameServer {

    public static void main(String[] args) {
        int portFirstPlayer = 1111;
        int portSecondPlayer = 1112;

        startServer(portFirstPlayer, portSecondPlayer);
    }

    public static void startServer(int portFirstPlayer, int portSecondPlayer){

        try{
            //1. Starting the two serverSockets on two different ports given.
            ServerSocket serverSocket1 = new ServerSocket(portFirstPlayer);
            ServerSocket serverSocket2 = new ServerSocket(portSecondPlayer);
            System.out.println("\nStarted GameServer on ports " + portFirstPlayer + " and " + portSecondPlayer + "." +
            "\nLet two players connect to the ports " + portFirstPlayer + " and " + portSecondPlayer + " to start playing.");
            
            //2.1 Waiting for the first player to connect and then creating a input(BufferedReader) and a output(PrintWriter).
            Socket clientSocket1 = serverSocket1.accept();
            System.out.println("Player 1 connected on port " + portFirstPlayer + ".");
            BufferedReader player1Input = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

            //2.2 Waiting for the second player to connect and then creating a input(BufferedReader) and a output(PrintWriter).
            Socket clientSocket2 = serverSocket2.accept();
            System.out.println("Player 2 connected on port " + portSecondPlayer + ".");
            BufferedReader player2Input = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

            //3. Check if both sockets are correclty connected
            if(clientSocket1.isConnected() && clientSocket2.isConnected()){
                //4. Both Sockets have been accepted and both players are connected to the GameServer.
                System.out.println("\nBoth players connected to the server. Let's start the game!\n");
            }

            //5. Save both sockets in an ArrayList.
            ArrayList<Socket> sockets = new ArrayList<>();
            sockets.add(clientSocket1);
            sockets.add(clientSocket2);
            
            //6. Generated a random number from 1 to 20.
            int randomNumber = generateRandomNumber();

            //7. Sending message to players with information
            sendToAllPlayers("Welcome to Gues the Number.\nThe player who gets it first wins the game!\nI selected a number from 1 to 20", sockets);

            //8. Starting a loop in which the players will guess the number.
            while(true){
                sendToAllPlayers("Give a number:", sockets);
                sendToAllPlayers("start", sockets);
                System.out.println("Waiting for number input from both players.");
                String givenInputPlayer1 = player1Input.readLine();
                String givenInputPlayer2 = player2Input.readLine();
                int giveNumberPlayer1 = Integer.parseInt(givenInputPlayer1);
                int giveNumberPlayer2 = Integer.parseInt(givenInputPlayer2);
                System.out.println("Player 1: " + giveNumberPlayer1 + "\nPlayer 2: " + giveNumberPlayer2 +"\n");

                if (randomNumber == giveNumberPlayer1){
                    System.out.println("Player 1 has won the game by guessing number " + giveNumberPlayer1 + "!");
                    sendToAllPlayers("Player 1 has won the game by guessing number " + giveNumberPlayer1 + "!", sockets);
                    break;
                }else if (randomNumber == giveNumberPlayer2){
                    System.out.println("Player 2 has won the game by guessing number " + giveNumberPlayer2 + "!");
                    sendToAllPlayers("Player 2 has won the game by guessing number " + giveNumberPlayer2 + "!", sockets);
                    break;
                }else {
                    sendToAllPlayers("Player 1 guessed " + giveNumberPlayer1 + " but it's wrong.", sockets);
                    sendToAllPlayers("Player 2 guessed " + giveNumberPlayer2 + " but it's wrong.", sockets);
                }
            }

            System.out.println("Closing the server.");
            sendToAllPlayers("Thank you for playing!\n\nGameServer is closing", sockets);

            clientSocket1.close();
            clientSocket2.close();

        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public static void sendToAllPlayers(String message, ArrayList<Socket> clients) {
        for (Socket client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int generateRandomNumber() {
        return (int) (Math.random() * 20) + 1;
    }
    

}
