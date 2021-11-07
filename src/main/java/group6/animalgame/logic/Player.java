package group6.animalgame.logic;

import group6.animalgame.animals.Animal;
import group6.animalgame.fodder.Food;
import group6.animalgame.utilities.Dialogs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Player class allows player to buy, sell and take care of animals and food they own.
 *
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */

public class Player implements Serializable {

    private int money;
    private String name;
    private ArrayList<Animal> animalsOwned;
    private LinkedHashMap<Food, Integer> fodderOwned;

    /**
     * Constructor of the player class. Initializes players money, saves players animals and food.
     *
     * @param name name of the player
     */
    public Player(String name) {
        this.money = 10000;
        this.name = name;
        this.animalsOwned = new ArrayList<>();
        this.fodderOwned = new LinkedHashMap<>();
    }


    public boolean buyAnimal(String animalType, Store store) {
        if (isMoneySufficient(store.getPrice(animalType))) {
            int gender = Dialogs.showChooseGenderDialog(animalType);
            Animal a = store.sellAnimal(animalType, gender);
            int price = store.getPrice(animalType);
            money -= price;
            String name = Dialogs.showInputTextDialog("Please enter animal's name.", "Animal's name:");
            a.setName(name);
            a.setOwner(this);
            animalsOwned.add(a);
            return true;
        } else {
            return false;
        }
    }

    public boolean buyFodder(String fodderType, int amountToBuy, Store store) {
        if (isMoneySufficient(store.getPrice(fodderType) * amountToBuy)) {
            Food f = store.sellFodder(fodderType);
            int price = (store.getPrice(fodderType) * amountToBuy);
            money = money - price;
            boolean replaced = false;
            for (Food food : fodderOwned.keySet()) {
                if (food.getName().equals(f.getName())) {
                    int currentAmount = fodderOwned.get(food);
                    currentAmount += amountToBuy;
                    fodderOwned.replace(food, currentAmount);
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                fodderOwned.put(f, amountToBuy);
            }
            return true;
        } else {
            return false;
        }
    }

    public int sellAnimal(Animal a, Store store) {
        int pay = store.buyAnimal(a);
        if (pay > 0) {
            money += pay;
            a.setOwner(null);
            animalsOwned.remove(a);
            return pay;
        } else {
            return -1;
        }
    }

    /**
     * This method sells players animals to the store.
     *
     * @param store store that buys an animal from player
     */
    public void sellAnimal(Store store) {
        String[] options = new String[animalsOwned.size()];
        for (int i = 0; i < animalsOwned.size(); i++) {
            options[i] = animalsOwned.get(i).toString();
        }
        int choice = Dialogs.showDialog("Chose animal to sell:", options);
        Animal a = animalsOwned.get(choice - 1);
        int pay = store.buyAnimal(a);
        if (pay > 0) {
            money += pay;
            System.out.println("-".repeat(20));
            System.out.println("You sold a " + a.getClass().getSimpleName() + " (" + a.getName() + "). You earned " + pay + ".");
            a.setOwner(null);
            animalsOwned.remove(a);
        } else {
            System.out.println("Couldn't sell that.");
        }
    }

    /**
     * The method that sells all animals the player owns.
     *
     * @param store store that buys the animals
     */
    public void sellAllAnimals(Store store) {
        int pay;
        for (Animal animal : animalsOwned) {
            pay = store.buyAnimal(animal);
            money += pay;
        }
    }

    public void mateAnimals(Animal a1, Animal a2) {
        Animal[] children = a1.mate(a2);
        if (children != null && children.length != 0) {
            String headerText = "Success!";
            String contentText = name + ", you got " + children.length + " new " + a1.getClass().getSimpleName();
            if (children.length > 1) {
                contentText += "s!";
            }
            Dialogs.showAlert(headerText, contentText);
            for (Animal child : children) {
                String name = Dialogs.showInputTextDialog("Please enter this " + child.getClass().getSimpleName()
                        + "'s (" + child.getGender() + ") name.", "Enter name:");
                child.setName(name);
                child.setOwner(this);
                animalsOwned.add(child);
            }
        } else {
            String headerText = "Bad news!";
            String contentText = a1.getName() + " did not want to mate with " + a2.getName() + " this time.";
            Dialogs.showAlert(headerText, contentText);
        }
    }

    /**
     * This method allows player to choose two animals to mate and stores animals offspring.
     */
    public void mateAnimals() {
        ArrayList<String> options = new ArrayList<>();
        for (Animal a : animalsOwned) {
            options.add(a.toString());
        }
        int choice = Dialogs.showDialog("Chose animal to mate:", options.toArray(new String[0]));
        Animal a = animalsOwned.get(choice - 1);
        choice = Dialogs.showDialog("Chose other animal to mate:", options.toArray(new String[0]));
        Animal[] children = a.mate(animalsOwned.get(choice - 1));
        if (children != null && children.length != 0) {
            String s = "Success! " + name + " you got " + children.length + " new " + a.getClass().getSimpleName();
            if (children.length > 1) {
                s += "s!";
            }
            System.out.println(s);
            for (Animal child : children) {
                System.out.println("-".repeat(20));
                String name = Dialogs.readStringInput("What do you want to name this " + child.getGender() +
                        " " + child.getClass().getSimpleName() + " to?");
                child.setName(name);
                child.setOwner(this);
                animalsOwned.add(child);
            }
        }
    }

    public boolean feedAnAnimal(Animal a, Food f, int amountToFeed) {
        int pHealth = a.getHealth();
        if (a.eat(f, amountToFeed)) {
            if (fodderOwned.get(f) - amountToFeed > 0) {
                fodderOwned.replace(f, fodderOwned.get(f) - amountToFeed);
            } else {
                fodderOwned.remove(f);
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * feedAnimal method is responsible for giving chosen food to chosen animal.
     */
    public void feedAnAnimal() {
        String[] options = new String[animalsOwned.size()];
        int choice;
        for (int i = 0; i < animalsOwned.size(); i++) {
            options[i] = animalsOwned.get(i).toString();
        }
        choice = Dialogs.showDialog("Chose the animal to feed:", options);
        Animal a = animalsOwned.get(choice - 1);
        options = new String[fodderOwned.keySet().size()];
        int i = 0;
        for (Food f : fodderOwned.keySet()) {
            options[i] = f.getName() + " - " + fodderOwned.get(f) + " kg";
            i++;
        }
        choice = Dialogs.showDialog("Chose the food to give:", options);
        Food f = fodderOwned.keySet().toArray(new Food[0])[choice - 1];
        System.out.println("How much food would you like to feed it?");
        choice = Dialogs.readIntInput(1, fodderOwned.get(f));
        int pHealth = a.getHealth();
        if (a.eat(f, choice)) {
            System.out.println("-".repeat(20));
            System.out.println(a.getName() + " is eating. Health increased by " + (a.getHealth() - pHealth) + "%. " +
                    "Current health: " + a.getHealth() + "%.");
            if (fodderOwned.get(f) - choice > 0) {
                fodderOwned.replace(f, fodderOwned.get(f) - choice);
            } else {
                fodderOwned.remove(f);
            }
        } else {
            System.out.println("-".repeat(20));
            System.out.println(a.getName() + " didn't like that food. Health not increased.");
        }
    }

    /**
     * This method checks if player has enough money.
     *
     * @param amountToPay price of products to be bought
     * @return true or false depending on if player has enough money.
     */
    private boolean isMoneySufficient(int amountToPay) {
        if (amountToPay > money) {
            return false;
        } else {
            return true;
        }
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Animal> getAnimalsOwned() {
        return animalsOwned;
    }

    public LinkedHashMap<Food, Integer> getFodderOwned() {
        return fodderOwned;
    }
}
