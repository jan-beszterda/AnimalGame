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

    public Game() {
        startGame();
    }

    public void nextPlayerRound() {
        currentPlayerIndex++;
        if (currentPlayerIndex > playersList.size() - 1) {
            currentPlayerIndex = 0;
            currentRoundNumber++;
        }
        currentPlayer = playersList.get(currentPlayerIndex);
        updatePlayersAnimals();
        if (checkIfPlayerLost()) {
            removePlayer();
            nextPlayerRound();
        } else {
            System.out.println("-".repeat(50));
            System.out.println(currentPlayer.getName() + " - your turn begins!");
            showPlayerStatus();
            playPlayerRound();
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
                System.exit(0);
                break;
        }
    }

    private void playPlayerRound() {
        String[] options = {"Buy animal", "Buy fodder", "Feed an animal", "Attempt to mate two animals", "Sell animal", "Save game and quit"};
        int answer = Dialog.showDialog("Make your choice:", options);
        switch (answer) {
            case 1:
                do {
                    currentPlayer.buyAnimal(store);
                    if (currentPlayer.getMoney() < store.getCheapestAnimal()) {
                        answer = 1;
                    } else {
                        answer = Dialog.showDialog("Do you want to buy another animal:", "Yes", "No");
                    }
                } while (answer - 1 == 0);
                break;
            case 2:
                do {
                    currentPlayer.buyFodder(store);
                    if (currentPlayer.getMoney() < store.getCheapestFodder()) {
                        answer = 1;
                    } else {
                        answer = Dialog.showDialog("Do you want to buy more fodder:", "Yes", "No");
                    }
                } while (answer - 1 == 0);
                break;
            case 3:
                if (!currentPlayer.getFodderOwned().isEmpty() && !currentPlayer.getAnimalsOwned().isEmpty()) {
                    currentPlayer.feedAnAnimal();
                } else {
                    System.out.println("You have no animals to feed or no fodder to give!");
                }
                break;
            case 4:
                if (currentPlayer.getAnimalsOwned().size() > 2) {
                    currentPlayer.attemptToMateAnAnimal();
                } else {
                    System.out.println("You need at least two animals to attempt to mate them!");
                }
                break;
            case 5:
                if (!currentPlayer.getAnimalsOwned().isEmpty()) {
                    currentPlayer.sellAnimal(store);
                } else {
                    System.out.println("You need no animals to sell!");
                }
                break;
            case 6:
                quitGame();
                break;

        }
        nextPlayerRound();
    }

    private void saveGame() {
        String inp = Dialog.readStringInput("What should the game be saved as?");
        FileUtilities.saveGameToFile(inp, this);
        System.out.println("Game has been saved!");
    }

    private void loadGame() {
        String inp = Dialog.readStringInput("What is the name of the save file?");
        Game game = FileUtilities.loadGameFromFile(inp);
        this.playersList = game.playersList;
        this.currentPlayer = game.currentPlayer;
        this.currentRoundNumber = game.currentRoundNumber;
        this.store = game.store;
        this.currentPlayerIndex = game.currentPlayerIndex;
        System.out.println("Game file " + inp + " has been loaded!");
        playPlayerRound();
    }

    private void quitGame() {
        if (this.playersList != null) {
            int answer = Dialog.showDialog("Do you really want to quit? Your progress will be lost.",
                    "Yes", "No");
            switch (answer) {
                case 1:
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
        int numberOfPlayers = Dialog.showDialog("Number of players in the game?");
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = Dialog.readStringInput("What is your name player " + (i + 1) + " ?");
            Player player = new Player(name);
            playersList.add(player);
        }
        maxNumberOfRounds = Dialog.showDialog("Input number of rounds you want to play (MAX 30): ");
        while (maxNumberOfRounds <= 0 || maxNumberOfRounds > 30) {
            maxNumberOfRounds = Dialog.showDialog("Input number of rounds you want to play (MAX 30): ");
        }
        currentPlayer = playersList.get(0);
        currentRoundNumber = 1;
        playPlayerRound();
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



