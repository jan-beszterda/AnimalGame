package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.food.Food;

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

    }

    public void buyFodder(Store store) {

    }

    public void sellAnimal() {

    }

    public void attemptToMateAnAnimal() {

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
