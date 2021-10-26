package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player implements Serializable {

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
        String[] options = store.getAnimalStock().keySet().toArray(new String[0]);
        for (int i = 0; i < store.getAnimalStock().keySet().size(); i++) {
            String s = options[i] + " - price: " + store.getAnimalStock().get(options[i]).toString();
            options[i] = s;
        }
        int choice = Dialog.showDialog("Animals in stock", options);
        if(isMoneySufficient(store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()))) {
            int gender = Dialog.showDialog("What gender should the animal have: ", "MALE", "FEMALE");
            Animal a = store.sellAnimal(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim(), "", gender-1);
            int price = store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim());
            money -= price;
            String name = Dialog.readStringInput("What do you want to name this animal to?");
            a.setName(name);
            a.setOwner(this);
            animalsOwned.add(a);
            System.out.println("You bought a " + a.getClass().getSimpleName() + " and paid " + price + ".");
        } else {
            System.out.println("Not enough money to buy this!");
        }
    }

    public void buyFodder(Store store) {
        String[] options = store.getFodderStock().keySet().toArray(new String[0]);
        for (int i = 0; i < store.getFodderStock().keySet().size(); i++) {
            String s = options[i] + " - price: " + store.getFodderStock().get(options[i]).toString() + "/kg";
            options[i] = s;
        }
        int choice = Dialog.showDialog("Fodder in stock", options);
        int amount = Dialog.showDialog("How much kg do you want to buy?");
        if (isMoneySufficient(store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()) * amount)) {
            Food f = store.sellFodder(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim());
            int price = (store.getPrice(options[choice-1].substring(0, options[choice-1].indexOf("-")).trim()) * amount);
            money = money - price;
            if (!fodderOwned.containsKey(f)) {
                fodderOwned.put(f, 0);
            }
            int currentAmount = fodderOwned.get(f);
            currentAmount += amount;
            fodderOwned.replace(f, currentAmount);
            System.out.println("You bought " + amount + " kg of " + f.getName() + " and paid " + price + ".");
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
        Animal a = animalsOwned.get(choice-1);
        int pay = store.buyAnimal(a);
        if (pay > 0) {
            money += pay;
            System.out.println("You sold a " + a.getClass().getSimpleName() + " (" + a.getName() + "). You earned " + pay + ".");
            a.setOwner(null);
            animalsOwned.remove(a);
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

    public void mateAnimals() {
        ArrayList<String> options = new ArrayList<>();
        for (Animal a : animalsOwned) {
            options.add(a.toString());
        }
        int choice = Dialog.showDialog("Chose animal to mate:", options.toArray(new String[0]));
        Animal a = animalsOwned.get(choice-1);
        choice = Dialog.showDialog("Chose other animal to mate:", options.toArray(new String[0]));
        Animal[] children = a.mate(animalsOwned.get(choice-1));
        if (children != null && children.length != 0) {
            String s = "Success! " + name + " you got " + children.length + " new " + a.getClass().getSimpleName();
            if (children.length > 1) {
                s += "s!";
            }
            System.out.println(s);
            for (Animal child : children) {
                String name = Dialog.readStringInput("What do you want to name this " + child.getGender() +
                        " " + child.getClass().getSimpleName() + " to?");
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
        int choice = Dialog.showDialog("Chose the animal to feed:", options);
        Animal a = animalsOwned.get(choice-1);
        options = new String[fodderOwned.keySet().size()];
        int i = 0;
        for (Food f : fodderOwned.keySet()) {
            options[i] = f.getName();
            i++;
        }
        choice = Dialog.showDialog("Chose the food to give:", options);
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
