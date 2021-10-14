package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {

    private int money;
    private String name;
    private ArrayList<Animal> animalsOwned;
    private LinkedHashMap<Food, Integer> fodderOwned;

    public Player(String name) {
        this.money = 10000;
        this.name = name;
        this.animalsOwned = new ArrayList<>();
        this.fodderOwned = new LinkedHashMap<>();
    }

    public void buyAnimal(Store store) {
        ArrayList<String> options = new ArrayList<>();
        for (String s : store.getAnimalsInStock().keySet()) {
            options.add(s + " - " + store.getPriceList().get(s).toString());
        }
        int choice = Dialog.showDialog("Animals in stock", options.toArray(new String[0]));
        String name = Dialog.readStringInput("What do you want to name this animal to: ");
        int gender = Dialog.showDialog("What gender should the animal have: ", "MALE", "FEMALE");
        if(isMoneySufficient(store.getPrice(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim()))) {
            Animal a = store.sellAnimal(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim(), name, gender);
            money -= store.getPrice(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim());
            animalsOwned.add(a);
        } else {
            System.out.println("Not enough money to buy this!");
        }
    }

    public void buyFodder(Store store) {
        ArrayList<String> options = new ArrayList<>();
        for (String s : store.getFodderInStock().keySet()) {
            options.add(s + " - " + store.getPriceList().get(s).toString());
        }
        int choice = Dialog.showDialog("Fodder in stock", options.toArray(new String[0]));
        int amount = Dialog.showDialog("How much kg do you want to buy: ");
        if (isMoneySufficient(store.getPrice(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim()) * amount)) {
            Food f = store.sellFodder(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim());
            money = money - (store.getPrice(options.get(choice).substring(0, options.get(choice).indexOf("-")).trim()) * amount);
            if (!fodderOwned.containsKey(f)) {
                fodderOwned.put(f, 0);
            }
            int currentAmount = fodderOwned.get(f);
            currentAmount += amount;
            fodderOwned.replace(f, currentAmount);
        } else {
            System.out.println("Not enough money to buy this!");
        }
    }

    public void sellAnimal(Store store) {
        String[] options = new String[animalsOwned.size()];
        for (int i = 0; i < animalsOwned.size(); i++) {
            options[i] = animalsOwned.get(i).toString();
        }
        int choice = Dialog.showDialog("Chose animal to sell:", options);
        int pay = store.buyAnimal(animalsOwned.get(choice));
        if (pay > 0) {
            money += pay;
            System.out.println("Animal sold. You earned " + pay);
        } else {
            System.out.println("Couldn't sell that.");
        }
    }

    public void attemptToMateAnAnimal() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < animalsOwned.size(); i++) {
            options.add(animalsOwned.get(i).toString());
        }
        int choice = Dialog.showDialog("Chose animal to mate:", options.toArray(new String[0]));
        Animal a = animalsOwned.get(choice);
        options.remove(choice);
        choice = Dialog.showDialog("Chose other animal to mate:", options.toArray(new String[0]));

        if (a.mate(animalsOwned.get(choice))) {
            String name = Dialog.readStringInput("What do you want to name this animal to: ");
        } else {
            System.out.println("Unsuccessful mating attempt.");
        }
        // if animal.mate(other animal) returned 1
        // create a new animal here new animal() and add it to the list
        // else print unsuccessful mating attempt
    }

    public void feedAnAnimal() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < animalsOwned.size(); i++) {
            options.add(animalsOwned.get(i).toString());
        }
        int choice = Dialog.showDialog("Chose animal to feed:", options.toArray(new String[0]));
        Animal a = animalsOwned.get(choice);
        options.clear();
        for (Food f : fodderOwned.keySet()) {
            options.add(f.getName());
        }
        choice = Dialog.showDialog("Chose food to give:", options.toArray(new String[0]));
        Food f = fodderOwned.keySet().toArray(new Food[0])[choice];
        if (a.eat(f)) {
            System.out.println("Animal is eating.");
            int amount = fodderOwned.get(f);
            if (amount > 1) {
                fodderOwned.replace(f, amount-1);
            } else {
                fodderOwned.remove(f);
            }
        } else {
            System.out.println("Animal didn't like that food.");
        }
    }

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
