# Guess-the-Number
This is a simple two-player game built with Java on a TCP connection. The game generates a random number between 1 and 20, and each player takes turns guessing the number. The player who guesses the number correctly wins the game.

## Installing
1. Clone the repository to your local machine using the following command:

* git clone https://github.com/yourusername/guess-the-number.git

2. Open the project in a Java IDE.
3. Run the GameServer.java file.
4. Run both the Player1.java and Player2.java file.

* You will need to install and run player 1, and player 2 to two different computers on the same network for the game to work

## How to Play
1. Start the GameServer by running the code.
2. Player 1 and Player 2 connect to the server by running their code.
3. The server generates a random number between 1 and 20, which will be the target to guess.
4. Player 1 and Player 2 will send their guesses to the server by typing their guess and pressing the Enter key.
5. The server will compare the guesses of both players to the target number and send a message indicating if a player won or not.
6. Players take turns guessing the number until one of them guesses correctly.
7. The player who guesses the number correctly wins the game.
8. The game ends and the GameServer closes.

## Tech Stack
<img src="https://skillicons.dev/icons?i=java" />

## Authors
* Timothy Borghouts

## License
This project is licensed under the MIT License.
