package grupp6.djurspelet.game;

import com.sun.jdi.VMOutOfMemoryException;
import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.utilities.Dialog;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private int money;
    private String name;
    private ArrayList<Animal> animalsOwned;
    private HashMap<Food, Integer> fodderOwned;

    public Player(String name) {
        this.money = 10000;
        this.name = name;
        this.animalsOwned = new ArrayList<>();
        this.fodderOwned = new HashMap<>();
    }

    public void buyAnimal(Store store) {
        int choice = Dialog.showDialog("Animals in stock", store.getProductNames("animal"));
        String name = Dialog.readStringInput("What do you want to name this animal to: ");
        int gender = Dialog.showDialog("What gender should the animal have: ", "MALE", "FEMALE");
        Animal a = store.sellAnimal(choice, name, gender);
        //money -= a.getPrice();
        animalsOwned.add(a);
    }

    public void buyFodder(Store store) {
        Dialog.showDialog("Fodder in stock", store.getProductNames("fodder"));
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

    public HashMap<Food, Integer> getFodderOwned() {
        return fodderOwned;
    }
}
