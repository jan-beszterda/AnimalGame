package grupp6.djurspelet.game;

import grupp6.djurspelet.utilities.Dialog;
import grupp6.djurspelet.utilities.FileUtilities;

public class Game {

    /*
    We will need a variable to hold all players in the game, example ArrayList
    We will need a variable to hold current player that makes choices
    We will need a variable to hold the total number of rounds players will play
    We will need a variable to hold the number of current round

    We will need method to move the round to next player
    We will need method to start the game
    We will need method to save game
    We will need method to load game
    We will need method to quit game
    We will need method to finalise the game
    */

    private void saveGame(){
        String inp = Dialog.readStringInput("What should the game be saved as?");
        FileUtilities.saveGameToFile(inp, this);
        System.out.println("Game has been saved!");
    }
}
