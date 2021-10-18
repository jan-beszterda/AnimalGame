package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
        String[] options = store.getAnimalsInStock().keySet().toArray(new String[0]);
        for (int i = 0; i < store.getAnimalsInStock().keySet().size(); i++) {
            String s = options[i] + " - price: " + store.getPriceList().get(options[i]).toString();
            options[i] = s;
        }
        int choice = Dialog.showDialog("Animals in stock", options);
        String name = Dialog.readStringInput("What do you want to name this animal to: ");
        int gender = Dialog.showDialog("What gender should the animal have: ", "MALE", "FEMALE");
        if(isMoneySufficient(store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()))) {
            Animal a = store.sellAnimal(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim(), name, gender-1);
            money -= store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim());
            animalsOwned.add(a);
        } else {
            System.out.println("Not enough money to buy this!");
        }
    }

    public void buyFodder(Store store) {
        String[] options = store.getFodderInStock().keySet().toArray(new String[0]);
        for (int i = 0; i < store.getFodderInStock().keySet().size(); i++) {
            String s = options[i] + " - price: " + store.getPriceList().get(options[i]).toString() + " per kg";
            options[i] = s;
        }
        int choice = Dialog.showDialog("Fodder in stock", options);
        int amount = Dialog.showDialog("How much kg do you want to buy: ");
        if (isMoneySufficient(store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()) * amount)) {
            Food f = store.sellFodder(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim());
            money = money - (store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()) * amount);
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

    public void sellAllAnimals(ArrayList<Animal> animals, Store store){
        int pay;
        for (Animal animal : animals) {
            pay = store.buyAnimal(animal);
            money += pay;
        }
    }

    public void attemptToMateAnAnimal() {
        String[] options = new String[animalsOwned.size()];
        for (int i = 0; i < animalsOwned.size(); i++) {
            options[i] = animalsOwned.get(i).toString();
        }
        int choice = Dialog.showDialog("Chose animal to mate:", options);
        Animal a = animalsOwned.get(choice);
        options = new String[animalsOwned.size()-1];
        for (int i = 0; i < animalsOwned.size(); i++) {
            if (i == choice) {
                continue;
            }
            options[i] = animalsOwned.get(i).toString();
        }
        choice = Dialog.showDialog("Chose other animal to mate:", options);
        Animal[] children = a.mate(animalsOwned.get(choice));
        if (children != null || children.length != 0) {
            for (Animal child : children) {
                String name = Dialog.readStringInput("What do you want to name this animal to: ");
                child.setName(name);
                child.setOwner(this);
                animalsOwned.add(child);
            }
        } else {
            System.out.println("Unsuccessful mating attempt.");
        }
    }

    public void feedAnAnimal() {
        String[] options = new String[animalsOwned.size()];
        for (int i = 0; i < animalsOwned.size(); i++) {
            options[i] = animalsOwned.get(i).toString();
        }
        int choice = Dialog.showDialog("Chose animal to feed:", options);
        Animal a = animalsOwned.get(choice-1);
        options = new String[fodderOwned.keySet().size()];
        int i = 0;
        for (Food f : fodderOwned.keySet()) {
            options[i] = f.getName();
            i++;
        }
        choice = Dialog.showDialog("Chose food to give:", options);
        Food f = fodderOwned.keySet().toArray(new Food[0])[choice-1];
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
