package grupp6.djurspelet.game;

import grupp6.djurspelet.animal.*;
import grupp6.djurspelet.food.*;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class Store implements Serializable {

    private LinkedHashMap<String, Integer> animalStock;
    private LinkedHashMap<String, Integer> fodderStock;

    public Store() {
        this.animalStock = new LinkedHashMap<>();
        this.fodderStock = new LinkedHashMap<>();
        initialiseStore();
    }

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

    public Animal sellAnimal(String choice, String name, int gender) {
        switch (choice) {
            case "Horse":
                return new Horse(name, gender);
            case "Cow":
                return new Cow(name, gender);
            case "Cat":
                return new Cat(name, gender);
            case "Dog":
                return new Dog(name, gender);
            case "Pig":
                return new Pig(name, gender);
            default:
                return null;
        }
    }
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

    public int getCheapestAnimal() {
        int price = 0;
        for (String s : animalStock.keySet()) {
            if (price > animalStock.get(s)) {
                price = animalStock.get(s);
            }
        }
        return price;
    }

    public int getCheapestFodder() {
        int price = 0;
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
