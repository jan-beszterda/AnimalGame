package grupp6.djurspelet.game;


import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;
import grupp6.djurspelet.utilities.FileUtilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Game {

        private ArrayList<Player> playersList = new ArrayList<>(4);
        private Player currentPlayer = null;
        private int maxNumberOfRounds;
        private int currentRoundNumber;
        private Store store = new Store();

        public Game () {
            startGame();
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

        public void startGame(){
            int answer = Dialog.showDialog("** ANIMAL GAME **", "1. Start New Game", "2. Load Game", "3. Quit Game");
       switch (answer){

           case 1:

               break;
           case 2:

               break;
           case 3:
               System.exit(0);
               break;
       }

        }
    /*
    We will need a variable to hold all players in the game, example ArrayList
    We will need a variable to hold current player that makes choices
    We will need a variable to hold the total number of rounds players will play
    We will need a variable to hold the number of current round

    We will need method to move the round to next player

    We will need method to start the game
    We will need method to quit game
    We will need method to finalise the game
    */

    private void saveGame(){
        String inp = Dialog.readStringInput("What should the game be saved as?");
        FileUtilities.saveGameToFile(inp, this);
        System.out.println("Game has been saved!");
    }

    //TODO om fler variabler läggs till så lägg till dem i här
    private void loadGame(){
        String inp = Dialog.readStringInput("What is th name of the save file?");
        Game game = FileUtilities.loadGameFromFile(inp);
        this.playersList = game.playersList;
        this.currentPlayer = game.currentPlayer;
        this.currentRoundNumber = game.currentRoundNumber;
        this.store = game.store;
        System.out.println("Game file " + inp + " has been loaded!");
    }

    private void quitGame(){
        if (this.playersList != null){
            int answer = Dialog.showDialog("Do you really want to quit? Your progress will be lost.",
                    "Yes", "No");
            switch (answer){
                case 1 -> System.exit(0);
                case 2 -> {
                    saveGame();
                    System.out.println("Quitting game...");
                    System.exit(0);
                }
            }
        }
        System.out.println("Quitting game...");
        System.exit(0);
    }

    private void updatePlayersAnimals() {
        for (Animal a : currentPlayer.getAnimalsOwned()) {
            a.getOlder();
            if (a.isAlive()) {
                a.diminishHealth();
            }
        }
    }

    private boolean checkIfPlayerLost() {
        if (currentPlayer.getMoney() <= 0 && currentPlayer.getAnimalsOwned().size() <= 0) {
            return true;
        }
        return false;
    }

    private void removePlayer() {
        System.out.println(currentPlayer.getName() + ", you have no money and no animals...");
        System.out.println("You lost the game!");
        playersList.remove(currentPlayer);
    }

    private void showPlayerStatus() {
        System.out.println("-".repeat(50));
        System.out.println("Your animals:");
        for (Animal a : currentPlayer.getAnimalsOwned()) {
            System.out.println(a.toString());
        }
        System.out.println("-".repeat(50));
        System.out.println("Your fodder:");
        Set<Map.Entry<Food, Integer>> entries = currentPlayer.getFodderOwned().entrySet();
        for (Map.Entry<Food, Integer> e : entries
        ) {
            System.out.println(e.getKey().getClass().getSimpleName() + " - " + e.getValue().toString() + " kg.");
        }
        System.out.println("-".repeat(50));
        System.out.println("Your money: " + currentPlayer.getMoney());
        System.out.println("-".repeat(50));
    }

    private void startNewGame(){
        int numberOfPlayers = Dialog.showDialog("Number of players in the game?");
        for (int i = 0; i < numberOfPlayers; i++){
            String name = Dialog.readStringInput("What is your name player " + (i+1) + " ?");
            Player player = new Player(name);
            playersList.add(player);
        }
        maxNumberOfRounds = Dialog.showDialog("Input number of rounds you want to play (MAX 30): ");
        while (maxNumberOfRounds <= 0 || maxNumberOfRounds > 30){
            maxNumberOfRounds = Dialog.showDialog("Input number of rounds you want to play (MAX 30): ");
        }
        currentPlayer = playersList.get(0);
        currentRoundNumber = 1;
        // play Round();

    }
}




