package grupp6.djurspelet.game;


import grupp6.djurspelet.animal.Animal;
import grupp6.djurspelet.animal.Horse;
import grupp6.djurspelet.food.Food;

import java.awt.image.PixelGrabber;
import java.util.ArrayList;

public class Store {

    private ArrayList<Animal> animalsInStock;
    private ArrayList<Food> fodderInStock;

    public Store() {
        this.animalsInStock = new ArrayList<>();
        this.fodderInStock = new ArrayList<>();
    }

    public Animal sellAnimal(int choice, String name, int gender) {
        Animal a = animalsInStock.get(choice);
        String type = a.getName();
        switch (type) {
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

    public ArrayList<Animal> getAnimalsInStock() {
        return animalsInStock;
    }

    public ArrayList<Food> getFodderInStock() {
        return fodderInStock;
    }

    public String[] getProductNames(String type) {
        String[] productNames = null;
        if (type.equalsIgnoreCase("animal")) {
            productNames = new String[animalsInStock.size()];
            int i = 0;
            for (Animal a : animalsInStock) {
                productNames[i] = a.getName();
            }
        } else if (type.equalsIgnoreCase("fodder")) {
            productNames = new String[fodderInStock.size()];
            int i = 0;
            for (Food f : fodderInStock) {
                productNames[i] = f.getName();
            }
        }
        return productNames;
    }
}
