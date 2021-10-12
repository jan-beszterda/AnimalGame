package grupp6.djurspelet.game;


import grupp6.djurspelet.animal.*;
import grupp6.djurspelet.food.*;

import java.util.LinkedHashMap;

public class Store {

    private LinkedHashMap<String, Animal> animalsInStock;
    private LinkedHashMap<String, Food> fodderInStock;
    private LinkedHashMap<String, Integer> priceList;

    public Store() {
        this.animalsInStock = new LinkedHashMap<>();
        this.fodderInStock = new LinkedHashMap<>();
        this.priceList = new LinkedHashMap<>();
        initialiseStore();
    }

    private void initialiseStore() {
        this.animalsInStock.put("Horse", new Horse("", 0));
        this.animalsInStock.put("Cow", new Cow("", 0));
        this.animalsInStock.put("Dog", new Dog("", 0));
        this.animalsInStock.put("Cat", new Cat("", 0));
        this.animalsInStock.put("Pig", new Pig("", 0));

        this.fodderInStock.put("Meat", new Meat());
        this.fodderInStock.put("Grass", new Grass());
        this.fodderInStock.put("Corn", new Corn());

        this.priceList.put("Horse", 1000);
        this.priceList.put("Cow", 500);
        this.priceList.put("Dog", 300);
        this.priceList.put("Cat", 200);
        this.priceList.put("Pig", 500);
        this.priceList.put("Meat", 100);
        this.priceList.put("Grass", 30);
        this.priceList.put("Corn", 50);
    }

    public int getPrice(String productName) {
        int price = 0;
        for (String s : priceList.keySet()) {
            if (s.equalsIgnoreCase(productName)) {
                price = priceList.get(s);
            }
        }
        return price;
    }

    public Animal sellAnimal(String choice, String name, int gender) {
        switch (choice) {
            case "Horse":
                return new Horse(name, gender);
                break;
            case "Cow":
                return new Cow(name, gender);
                break;
            case "Cat":
                return new Cat(name, gender);
                break;
            case "Dog":
                return new Dog(name, gender);
                break;
            case "Pig":
                return new Pig(name, gender);
                break;
            default:
                return null;
                break;
        }
    }
    public Food sellFodder(String choice) {
        switch (choice) {
            case "Meat":
                return new Meat();
                break;
            case "Grass":
                return new Grass();
                break;
            case "Corn":
                return new Corn();
                break;
            default:
                return null;
                break;
        }
    }


    public LinkedHashMap<String, Animal> getAnimalsInStock() {
        return animalsInStock;
    }

    public LinkedHashMap<String, Food> getFodderInStock() {
        return fodderInStock;
    }

    public LinkedHashMap<String, Integer> getPriceList() {
        return priceList;
    }
}
