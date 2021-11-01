package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.game.Player;
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
    private ArrayList<Food> acceptedFood;
    private Random random = new Random();
    private String name;
    private Gender gender;
    private int age = 0;
    private int maxAge;
    private int health = 100;
    private int previousHealth;
    private Player owner;
    private int maxOffspringNumber;
    private boolean alive = true;

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
        this.maxAge = maxAge;
        this.maxOffspringNumber = maxOffspringNumber;
        this.owner = null;
        this.acceptedFood = new ArrayList<>();
        this.acceptedFood.addAll(Arrays.asList(acceptedFood));
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
                System.out.println("-".repeat(20));
                System.out.println(this.getName() + " and " + animalToMateWith.getName() + " did not want to mate today.");
                return null;
            }
        }
        System.out.println("-".repeat(20));
        System.out.println("These animals cannot mate!");
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

    /**
     * Checks if the animal is alive.
     * @return value of the alive boolean field
     */
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

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public int getPreviousHealth() {
        return previousHealth;
    }
}