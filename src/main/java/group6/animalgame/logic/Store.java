package group6.animalgame.logic;

import group6.animalgame.animals.*;
import group6.animalgame.fodder.*;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Store class is responsible for selling animals and fodder to players in the game as well as buying animals from the
 * players.
 *
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Store implements Serializable {

    private LinkedHashMap<String, Integer> animalStock;
    private LinkedHashMap<String, Integer> fodderStock;

    /**
     * Constructor of the Store object, initialises maps holding animals/fodder and their prices.
     */
    public Store() {
        this.animalStock = new LinkedHashMap<>();
        this.fodderStock = new LinkedHashMap<>();
        initialiseStore();
    }

    /**
     * Method fills store maps with animals/fodder and their prices.
     */
    private void initialiseStore() {
        this.animalStock.put("Horse", 1000);
        this.animalStock.put("Cow", 500);
        this.animalStock.put("Dog", 300);
        this.animalStock.put("Cat", 200);
        this.animalStock.put("Pig", 500);

        this.fodderStock.put("Meat", 100);
        this.fodderStock.put("Grass", 30);
        this.fodderStock.put("Corn", 50);
    }

    /**
     * Method to return a price of the product by its name.
     *
     * @param productName name of the product to check for price
     * @return product's price
     */
    public int getPrice(String productName) {
        int price = -1;
        for (String s : animalStock.keySet()) {
            if (s.equalsIgnoreCase(productName)) {
                price = animalStock.get(s);
            }
        }
        for (String s : fodderStock.keySet()) {
            if (s.equalsIgnoreCase(productName)) {
                price = fodderStock.get(s);
            }
        }
        return price;
    }

    /**
     * Method responsible for selling an animal to the player.
     *
     * @param choice animal type chosen by player
     * @param gender gender of the animal purchased by the player
     * @return the animal bought by the player
     */
    public Animal sellAnimal(String choice, int gender) {
        switch (choice) {
            case "Horse":
                return new Horse("", gender);
            case "Cow":
                return new Cow("", gender);
            case "Cat":
                return new Cat("", gender);
            case "Dog":
                return new Dog("", gender);
            case "Pig":
                return new Pig("", gender);
            default:
                return null;
        }
    }

    /**
     * Method responsible for selling fodder to the player.
     *
     * @param choice fodder type chosen by the player
     * @return fodder bought by the player
     */
    public Food sellFodder(String choice) {
        switch (choice) {
            case "Meat":
                return new Meat();
            case "Grass":
                return new Grass();
            case "Corn":
                return new Corn();
            default:
                return null;
        }
    }

    /**
     * Method responsible for buying an animal from the player.
     *
     * @param animal animal that the player wants to sell
     * @return money amount the store buys the animal for
     */
    public int buyAnimal(Animal animal) {
        String productName = animal.getClass().getSimpleName();
        int pay = 0;
        for (String s : animalStock.keySet()) {
            if (s.equalsIgnoreCase(productName)) {
                pay = (int) (getPrice(productName) * (animal.getHealth() / 100.0));
                break;
            }
        }
        return pay;
    }

    /**
     * Method gives the price of the cheapest animal in stock.
     *
     * @return the price of the cheapest animal
     */
    public int getCheapestAnimal() {
        int price = Integer.MAX_VALUE;
        for (String s : animalStock.keySet()) {
            if (price > animalStock.get(s)) {
                price = animalStock.get(s);
            }
        }
        return price;
    }

    /**
     * Method gives the price of the cheapest fodder in stock.
     *
     * @return the price of the cheapest fodder
     */
    public int getCheapestFodder() {
        int price = Integer.MAX_VALUE;
        for (String s : fodderStock.keySet()) {
            if (price > fodderStock.get(s)) {
                price = fodderStock.get(s);
            }
        }
        return price;
    }

    public LinkedHashMap<String, Integer> getFodderStock() {
        return fodderStock;
    }

    public LinkedHashMap<String, Integer> getAnimalStock() {
        return animalStock;
    }
}
