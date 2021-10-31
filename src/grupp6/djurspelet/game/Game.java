package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;
import grupp6.djurspelet.utilities.FileUtilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Game implements Serializable {

    private ArrayList<Player> playersList = new ArrayList<>(4);
    private Player currentPlayer = null;
    private int maxNumberOfRounds;
    private int currentRoundNumber;
    private Store store = new Store();
    private int currentPlayerIndex = 0;
    private String currentSaveFileName = null;

    public Game() {
        startGame();
    }

    public void moveTurn() {
        currentPlayerIndex++;
        if (currentPlayerIndex > playersList.size() - 1) {
            currentPlayerIndex = 0;
            currentRoundNumber++;
            if (currentRoundNumber > maxNumberOfRounds) {
                finalizeGame();
                System.exit(0);
            }
            System.out.println("-".repeat(50));
            System.out.println("ROUND " + currentRoundNumber + " BEGINS!");
            System.out.println("-".repeat(50));
        }
        currentPlayer = playersList.get(currentPlayerIndex);
        updatePlayersAnimals();
        if (checkIfPlayerLost()) {
            removePlayer();
            moveTurn();
        } else {
            System.out.println("-".repeat(50));
            System.out.println(currentPlayer.getName() + " - your turn begins!");
            showPlayerStatus();
            playTurn();
        }
    }

    public void startGame() {
        int answer = Dialog.showDialog("** ANIMAL GAME **", "Start New Game", "Load Game", "Quit Game");
        switch (answer) {
            case 1:
                startNewGame();
                break;
            case 2:
                loadGame();
                break;
            case 3:
                quitGame();
                break;
        }
    }

    private void playTurn() {
        String[] options = {"Buy an animal", "Buy fodder", "Feed an animal", "Mate two animals", "Sell an animal", "Quit game"};
        int answer = Dialog.showDialog("Make your choice:", options);
        switch (answer) {
            case 1:
                if (currentPlayer.getMoney() >= store.getCheapestAnimal()) {
                    while (currentPlayer.getMoney() >= store.getCheapestAnimal()) {
                        currentPlayer.buyAnimal(store);
                        if (currentPlayer.getMoney() >= store.getCheapestAnimal()) {
                            answer = Dialog.showDialog("Do you want to buy another animal?", "Yes", "No");
                            if (answer == 2) {
                                break;
                            }
                        } else {
                            System.out.println("You don't have enough money to buy another animal.");
                            break;
                        }
                    }
                } else {
                    System.out.println("You don't have enough money to buy an animal.");
                    playTurn();
                }
                break;
            case 2:
                if (currentPlayer.getMoney() >= store.getCheapestFodder()) {
                    while (currentPlayer.getMoney() >= store.getCheapestFodder()) {
                        currentPlayer.buyFodder(store);
                        if (currentPlayer.getMoney() >= store.getCheapestFodder()) {
                            answer = Dialog.showDialog("Do you want to buy more fodder?", "Yes", "No");
                            if (answer == 2) {
                                break;
                            }
                        } else {
                            System.out.println("You don't have enough money to buy more fodder.");
                            break;
                        }
                    }
                } else {
                    System.out.println("You don't have enough money to buy fodder.");
                    playTurn();
                }
                break;
            case 3:
                if (!currentPlayer.getFodderOwned().isEmpty() && !currentPlayer.getAnimalsOwned().isEmpty()) {
                    currentPlayer.feedAnAnimal();
                } else {
                    System.out.println("You have no animals to feed or no fodder to give!");
                    playTurn();
                }
                break;
            case 4:
                if (currentPlayer.getAnimalsOwned().size() > 1) {
                    currentPlayer.mateAnimals();
                } else {
                    System.out.println("You need at least two animals to mate!");
                    playTurn();
                }
                break;
            case 5:
                if (!currentPlayer.getAnimalsOwned().isEmpty()) {
                    while (!currentPlayer.getAnimalsOwned().isEmpty()) {
                        currentPlayer.sellAnimal(store);
                        if (!currentPlayer.getAnimalsOwned().isEmpty()) {
                            answer = Dialog.showDialog("Do you want to sell another animal?", "Yes", "No");
                            if (answer == 2) {
                                break;
                            }
                        } else {
                            System.out.println("You don't have any more animals to sell.");
                            break;
                        }
                    }
                } else {
                    System.out.println("You have no animals to sell!");
                    playTurn();
                }
                break;
            case 6:
                quitGame();
                break;

        }
        moveTurn();
    }

    private void saveGame() {
        if (currentSaveFileName != null) {
            int answer = Dialog.showDialog("Do you want to overwrite your previous save?",
                    "No", "Yes");
            switch (answer) {
                case 1:
                    String inp = Dialog.readStringInput("What should the game be saved as?");
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
            String inp = Dialog.readStringInput("What should the game be saved as?");
            FileUtilities.saveGameToFile(inp, this);
            System.out.println("Game has been saved!");
            System.out.println("Quitting game...");
            System.exit(0);
        }
    }

    private void loadGame() {
        String inp = Dialog.readStringInput("What is the name of the save file?");
        try {
            Game game = FileUtilities.loadGameFromFile(inp);
            currentSaveFileName = inp;
            this.playersList = game.playersList;
            this.currentPlayer = game.currentPlayer;
            this.currentRoundNumber = game.currentRoundNumber;
            this.maxNumberOfRounds = game.maxNumberOfRounds;
            this.store = game.store;
            this.currentPlayerIndex = game.currentPlayerIndex;
            System.out.println("Game file " + inp + " has been loaded!");
            System.out.println(currentPlayer.getName() + " - your turn begins!");
            showPlayerStatus();
            playTurn();
        } catch (Exception e) {
            System.out.println("Wrong file name!");
            startGame();
        }

    }
    private void quitGame() {
        if (!this.playersList.isEmpty()) {
            int answer = Dialog.showDialog("Do you want to save your game before you quit?",
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
            for (Animal a : toRemove) {
                currentPlayer.getAnimalsOwned().remove(a);
                a.setOwner(null);
                System.out.println("-".repeat(50));
                System.out.println(currentPlayer.getName() + "! Your " + a.getClass().getSimpleName() + " " + a.getName() + " died.");
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
        for (Map.Entry<Food, Integer> e : entries) {
            System.out.println(e.getKey().getClass().getSimpleName() + " - " + e.getValue().toString() + " kg.");
        }
        System.out.println("-".repeat(50));
        System.out.println("Your money: " + currentPlayer.getMoney());
        System.out.println("-".repeat(50));
    }

    private void startNewGame() {
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            numberOfPlayers = Dialog.showDialog("How many players will play the game (2-4)?");
            if (numberOfPlayers < 2) {
                System.out.println("You need at least 2 players to play the game!");
            } else if (numberOfPlayers > 4) {
                System.out.println("Maximum 4 players can play the game!");
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = Dialog.readStringInput("Player " + (i + 1) + ", what is your name?");
            Player player = new Player(name);
            playersList.add(player);
        }
        while (maxNumberOfRounds < 5 || maxNumberOfRounds > 30) {
            maxNumberOfRounds = Dialog.showDialog("How many rounds do you want to play (5-30)?");
            if (maxNumberOfRounds < 5) {
                System.out.println("Minimum number of rounds is 5!");
            } else if (maxNumberOfRounds > 30) {
                System.out.println("Maximum number of rounds is 30!");
            }
        }
        currentPlayer = playersList.get(0);
        currentRoundNumber = 1;
        System.out.println("-".repeat(50));
        System.out.println("ROUND " + currentRoundNumber + " BEGINS!");
        System.out.println("-".repeat(50));
        System.out.println(currentPlayer.getName() + " - your turn begins!");
        showPlayerStatus();
        playTurn();
    }

    private void finalizeGame() {
        ArrayList<Player> richestPlayers = new ArrayList<>();
        richestPlayers.add(playersList.get(0));
         for (Player player : playersList) {
            player.sellAllAnimals(player.getAnimalsOwned(), store);

            for (Player currentRichestPlayer : richestPlayers) {
                if (player.getMoney() > currentRichestPlayer.getMoney() && !player.equals(currentRichestPlayer)) {
                    richestPlayers.remove(currentRichestPlayer);
                    richestPlayers.add(player);
                }
            }
        }
        if (richestPlayers.size() > 1) {
            System.out.println("Game ended in a draw!");
            System.out.println("The winners are: ");
            for (Player winner : richestPlayers) {
                System.out.println(winner.getName() + " money: " + winner.getMoney());
            }
        } else {
            System.out.println("The player who won is: " + richestPlayers.get(0).getName()
                    + " with a total money amount of " + richestPlayers.get(0).getMoney());
        }
    }
}



