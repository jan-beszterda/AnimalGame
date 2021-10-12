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
        String[] options = store.getAnimalsInStock().keySet().toArray(new String[store.getAnimalsInStock().keySet().size()]);
        int choice = Dialog.showDialog("Animals in stock", options);
        String name = Dialog.readStringInput("What do you want to name this animal to: ");
        int gender = Dialog.showDialog("What gender should the animal have: ", "MALE", "FEMALE");
        Animal a = store.sellAnimal(options[choice], name, gender);
        money -= store.getPrice(options[choice]);
        animalsOwned.add(a);
    }

    public void buyFodder(Store store) {
        String[] options = store.getFodderInStock().keySet().toArray(new String[store.getFodderInStock().keySet().size()]);
        int choice = Dialog.showDialog("Fodder in stock", options);
        int amount = Dialog.showDialog("How much kg do you want to buy: ");
        Food f = store.sellFodder(options[choice]);
        money = money - (store.getPrice(options[choice]) * amount);
        fodderOwned.put(f, amount);
    }

    public void sellAnimal() {

    }

    public void attemptToMateAnAnimal() {
        // if animal.mate(other animal) returned 1
        // create a new animal here new animal() and add it to the list
        // else print unsuccessful mating attempt
    }

    public void feedAnAnimal() {

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
