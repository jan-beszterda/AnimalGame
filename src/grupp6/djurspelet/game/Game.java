package grupp6.djurspelet.game;

import java.util.ArrayList;

public class Game {

    ArrayList<Player> playersList = new ArrayList<>(4);
    Player currentPlayer = new Player("");
    int maxNumberOfRounds;
    int currentRoundNumber;

    public Game (){
    }

    public void nextPlayerRound() {
        int i = playersList.indexOf(currentPlayer);
        if (i < playersList.size() - 1) {
            currentPlayer = playersList.get(i + 1);
        } else {
            currentPlayer = playersList.get(0);
            currentRoundNumber ++;
        }
    }


    /*


    We will need method to start the game
    We will need method to save game
    We will need method to load game
    We will need method to quit game
    We will need method to finalise the game
    */
}
