package group6.animalgame.animals;

import group6.animalgame.fodder.Food;
import group6.animalgame.logic.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * This is the abstract class Animal where we store info about animals in the game and types of food that different
 * animals can eat.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public abstract class Animal implements Serializable {

    enum Gender {
        MALE, FEMALE
    }

    private Random random = new Random();
    private String name;
    private Gender gender;
    private int age;
    private int maxAge;
    private int health;
    private int previousHealth;
    private int maxOffspringNumber;
    private boolean alive;
    private ArrayList<Food> acceptedFood;
    private Player owner;

    /**
     * Constructor of the animal object.
     * @param name name the animal should have
     * @param gender gender the animal should have
     * @param maxAge maximum age of the animal
     * @param maxOffspringNumber maximum number of offspring the animal can have
     * @param acceptedFood array of fodder accepted by the animal
     */
    public Animal(String name, int gender, int maxAge, int maxOffspringNumber, Food... acceptedFood) {
        this.name = name;
        this.gender = Gender.values()[gender];
        this.age = 0;
        this.maxAge = maxAge;
        this.health = 100;
        this.maxOffspringNumber = maxOffspringNumber;
        this.alive = true;
        this.acceptedFood = new ArrayList<>();
        this.acceptedFood.addAll(Arrays.asList(acceptedFood));
        this.owner = null;
    }

    /**
     * Abstract method for creating offspring.
     * @param randomGender gender of the child animal
     * @return new animal of the same type as animal calling this method
     */
    public abstract Animal createChild(int randomGender);

    /**
     * Takes in food object, checks if animal can eat the particular food. If the food is accepted, the method
     * increases the animal's health.
     * @param food food given to the animal to eat
     * @return true if the food is accepted by the animal.
     */
    public boolean eat(Food food, int amountOfFood) {
        for (Food f : acceptedFood) {
            if (f.equals(food)) {
                this.increaseHealth(amountOfFood);
                return true;
            }
        }
        return false;
    }

    /**
     * This method takes a second animal as a parameter and checks if the second animal is of same kind
     * but different gender as the first animal. If this is true, method creates random number of
     * offsprings with random gender.
     * @param animalToMateWith animal this animal should mate with
     * @return an array of animal offsprings.
     */
    public Animal[] mate(Animal animalToMateWith) {
        if (this.getClass() == animalToMateWith.getClass() && !this.gender.equals(animalToMateWith.gender)) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {
                int randomNrOfOffsprings = random.nextInt((this.maxOffspringNumber) + 1);
                Animal[] offspring = new Animal[randomNrOfOffsprings];
                for (int i = 0; i < randomNrOfOffsprings; i++) {
                    int randomGender = random.nextInt(2);
                    offspring[i] = createChild(randomGender);
                }
                return offspring;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Adds one year to animal's age. If the animal is older than it's max age it dies.
     */
    public void getOlder() {
        age += 1;
        if (age > maxAge) {
            die();
        }
    }

    /**
     * Increases the animal's health by 10.
     */
    public void increaseHealth(int amountOfFood) {
        health = health + 10 * amountOfFood;
        if (health > 100) {
            health = 100;
        }
    }

    /**
     * Decreases the animal's health by random value between 10 and 30. If the health is 0 animal dies.
     */
    public void diminishHealth() {
        double randomNumber = (random.nextDouble() * 20) + 10;
        previousHealth = health;
        health = health - (int) randomNumber;
        if (health <= 0) {
            die();
        }
    }

    /**
     * Sets boolean variable alive to false.
     */
    private void die() {
        alive = false;
    }

    /**
     * This method collects info about an animal and stores it into a string.
     * @return a string containing info about the animal.
     */
    @Override
    public String toString() {
        String animalInfo = (this.name + " a " + this.age + " years old " + this.gender + " " + this.getClass().getSimpleName() + " at " + this.health + "% health");
        return animalInfo;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Gender getGender() {
        return this.gender;
    }

    public int getHealth() {
        return health;
    }

    public int getPreviousHealth() {
        return previousHealth;
    }
}