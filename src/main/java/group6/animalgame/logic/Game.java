package group6.animalgame.logic;

import group6.animalgame.Main;
import group6.animalgame.animals.Animal;
import group6.animalgame.fodder.Food;
import group6.animalgame.utilities.Dialogs;
import group6.animalgame.utilities.FileUtilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Game class is responsible for the game logic and storing current game data about players and rounds.
 *
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Game implements Serializable {

    private ArrayList<Player> playersList = new ArrayList<>(4);
    private Player currentPlayer = null;
    private int maxNumberOfRounds;
    private int currentRoundNumber;
    private Store store = new Store();
    private int currentPlayerIndex = 0;
    private String currentSaveFileName = null;

    private Main main;

    /**
     * Constructor of the Game object. Starts the game.
     */
    public Game(Main main) {
        this.main = main;
        startGame();
    }

    /*public Game(Main main) throws IOException {
        this.main = main;
        startGame();
        main.setScene("gameScene");
    }
*/
    private void initialisePlayers() {
        int numberOfPlayers = Dialogs.showInputNumberDialog("Input number of players that will play the game!",
                "Number of players:", 2, 4, 2, 1);
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = Dialogs.showInputTextDialog("Please enter player's name.", "Player's name:");
            playersList.add(new Player(name));
        }
    }

    private void initialiseRounds() {
        this.maxNumberOfRounds = Dialogs.showInputNumberDialog("Input number of rounds to play in the game!",
                "Number of rounds:", 5, 30, 5, 1);
    }



    /**
     * Initial start method, showing the main options after game is started and moving the game forward depending on the
     * option chosen.
     */
    private void startGame() {
        initialisePlayers();
        currentPlayer = playersList.get(0);
        initialiseRounds();
        currentRoundNumber = 1;
    }

    public boolean allowFeedAnimal() {
        if (!currentPlayer.getFodderOwned().isEmpty() && !currentPlayer.getAnimalsOwned().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean allowMateAnimals() {
        if (currentPlayer.getAnimalsOwned().size() > 1) {
            boolean success = false;
            ArrayList<String> types = new ArrayList<>();
            for (int i = 0; i < currentPlayer.getAnimalsOwned().size(); i++) {
                types.add(currentPlayer.getAnimalsOwned().get(i).getClass().getSimpleName());
            }
            for (String t : types) {
                int firstIndex = types.indexOf(t);
                int lastIndex = types.lastIndexOf(t);
                if(firstIndex != lastIndex) {
                    Animal first = currentPlayer.getAnimalsOwned().get(firstIndex);
                    Animal last = currentPlayer.getAnimalsOwned().get(lastIndex);
                    if (first.getGender() != last.getGender()) {
                        success = true;
                    }
                }
            }
            return success;
        } else {
            return false;
        }
    }

    /**
     * Method responsible for processing player's choice to sell an animal.
     */
    public boolean allowAnimalSale() {
        if (!currentPlayer.getAnimalsOwned().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method responsible for processing player's choice to purchase fodder.
     */
    public boolean allowFodderPurchase() {
        if (currentPlayer.getMoney() >= store.getCheapestFodder()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method responsible for processing player's choice to purchase an animal.
     */
    public boolean allowAnimalPurchase() {
        if (currentPlayer.getMoney() >= store.getCheapestAnimal()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method responsible for forwarding the turn to next player.
     */
    public void moveTurn() throws IOException {
        currentPlayerIndex++;
        if (currentPlayerIndex > playersList.size() - 1) {
            currentPlayerIndex = 0;
            currentRoundNumber++;
            if (currentRoundNumber > maxNumberOfRounds) {
                main.setScene("endGameScene");
                return;
            }
        }
        currentPlayer = playersList.get(currentPlayerIndex);
        updatePlayersAnimals();
        if (checkIfPlayerLost()) {
            removePlayer();
            moveTurn();
        }
        main.setScene("gameScene");
    }

    /**
     * Method responsible for updating players animals between their turns.
     */
    private void updatePlayersAnimals() {
        ArrayList<Animal> toRemove = new ArrayList<>();
        for (Animal a : currentPlayer.getAnimalsOwned()) {
            a.getOlder();
            if (a.isAlive()) {
                a.diminishHealth();
            }
            if (!a.isAlive()) {
                toRemove.add(a);
            }
        }
        if (!toRemove.isEmpty()) {
            StringBuilder info  = new StringBuilder();
            for (Animal a : toRemove) {
                currentPlayer.getAnimalsOwned().remove(a);
                a.setOwner(null);
                info.append(a.getClass().getSimpleName()).append(" (").append(a.getName()).append(") died.\n");
            }
            Dialogs.showAlert("Bad news " + currentPlayer.getName() + "!", info.toString());
        }
    }

    /**
     * Method responsible for checking if player should be eliminated from the game.
     */
    private boolean checkIfPlayerLost() {
        if (currentPlayer.getMoney() <= 0 && currentPlayer.getAnimalsOwned().size() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Method responsible for removing eliminated player from the game.
     */
    private void removePlayer() {
        String info = currentPlayer.getName() + ", you have no money and no animals. You lost the game!";
        Dialogs.showAlert("Bad news " + currentPlayer.getName() + "!", info);
        playersList.remove(currentPlayer);
    }

    /**
     * Method responsible for displaying status to the player when their turn begins.
     */
    public String showPlayerStatus() {
        StringBuilder text = new StringBuilder();
        text.append("-".repeat(80));
        text.append("\n");
        text.append("ROUND").append(currentRoundNumber);
        text.append("\n");
        text.append("-".repeat(80));
        text.append("\n");
        text.append(currentPlayer.getName()).append(" - it's your turn now!");
        text.append("\n");
        text.append("-".repeat(80));
        text.append("\n");
        text.append("Your animals:");
        text.append("\n");
        for (Animal a : currentPlayer.getAnimalsOwned()) {
            String info = a.toString();
            if (a.getPreviousHealth() - a.getHealth() > 0) {
                info += " (-" + (a.getPreviousHealth() - a.getHealth()) + "% from previous turn).";
            } else {
                info += ".";
            }
            text.append(info);
            text.append("\n");
        }
        text.append("\n");
        text.append("-".repeat(80));
        text.append("\n");
        text.append("Your fodder:");
        text.append("\n");
        Set<Map.Entry<Food, Integer>> entries = currentPlayer.getFodderOwned().entrySet();
        for (Map.Entry<Food, Integer> e : entries) {
            text.append(e.getKey().getClass().getSimpleName()).append(" - ").append(e.getValue().toString()).append(" kg.");
            text.append("\n");
        }
        text.append("-".repeat(80));
        text.append("\n");
        text.append("Your money: ").append(currentPlayer.getMoney());
        text.append("\n");
        text.append("-".repeat(80));
        text.append("\n");
        return text.toString();
    }

    /**
     * Method responsible for saving current game.
     */
    private void saveGame() {
        if (currentSaveFileName != null) {
            int answer = Dialogs.showDialog("Do you want to overwrite your previous save?",
                    "No", "Yes");
            switch (answer) {
                case 1:
                    String inp = Dialogs.readStringInput("What should the game be saved as?");
                    FileUtilities.saveGameToFile(inp, this);
                    System.out.println("Game has been saved!");
                    System.out.println("Quitting game...");
                    System.exit(0);
                case 2:
                    FileUtilities.saveGameToFile(currentSaveFileName, this);
                    System.out.println("Game has been saved!");
                    System.out.println("Quitting game...");
                    System.exit(0);
            }
        } else {
            String inp = Dialogs.readStringInput("What should the game be saved as?");
            FileUtilities.saveGameToFile(inp, this);
            System.out.println("Game has been saved!");
            System.out.println("Quitting game...");
            System.exit(0);
        }
    }

    /**
     * Method responsible for loading previously saved game.
     */
    private void loadGame() {
        System.out.println("-".repeat(20));
        String inp = Dialogs.readStringInput("What is the name of the save file?");
        Game game = FileUtilities.loadGameFromFile(inp);
        if (game != null) {
            currentSaveFileName = inp;
            this.playersList = game.playersList;
            this.currentPlayer = game.currentPlayer;
            this.currentRoundNumber = game.currentRoundNumber;
            this.maxNumberOfRounds = game.maxNumberOfRounds;
            this.store = game.store;
            this.currentPlayerIndex = game.currentPlayerIndex;
            System.out.println("-".repeat(20));
            System.out.println("Game file " + inp + " has been loaded!");
            System.out.println("-".repeat(20));
            Dialogs.clear();
            System.out.println(currentPlayer.getName() + " - your turn begins!");
            showPlayerStatus();
            //playTurn();
        } else {
            startGame();
        }
    }


    /**
     * Method responsible for quitting current game.
     */
    private void quitGame() {
        if (!this.playersList.isEmpty()) {
            int answer = Dialogs.showDialog("Do you want to save your game before you quit?",
                    "No (Warning! Your progress will be lost.)", "Yes");
            switch (answer) {
                case 1:
                    System.out.println("Quitting game...");
                    System.exit(0);
                case 2:
                    saveGame();
                    System.out.println("Quitting game...");
                    System.exit(0);
            }
        }
        System.out.println("Quitting game...");
        System.exit(0);
    }

    /**
     * Method responsible for finalising game after all rounds were played.
     */
    public ArrayList<Player> finalizeGame() {
        ArrayList<Player> sortedPlayers = new ArrayList<>();
        for (Player player : playersList) {
            player.sellAllAnimals(store);
            sortedPlayers.add(player);
        }
        sortedPlayers.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getMoney(), p1.getMoney());
            }
        });
        return sortedPlayers;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Store getStore() {
        return store;
    }
}



