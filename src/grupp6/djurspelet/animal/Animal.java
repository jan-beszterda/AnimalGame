package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.game.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public abstract class Animal {


Scanner scanner = new Scanner(System.in);

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
    private Player owner;
    private int maxOffspringNumber;


    public Animal(String name, int gender, int maxAge, Player owner, int maxOffspringNumber, Food... acceptedFood) {
        this.name = name;
        this.gender = Gender.values()[gender];
        this.maxAge = maxAge;
        this.maxOffspringNumber = maxOffspringNumber;
        this.owner = null;
        this.acceptedFood = new ArrayList<>();
        this.acceptedFood.addAll(Arrays.asList(acceptedFood));
    }


    public abstract Animal createChild(int randomGender);


    public boolean eat(Food food) {
        for (int i = 0; i < acceptedFood.size(); i++) {
            if (food.getName().equalsIgnoreCase(acceptedFood.get(i).getName())) {
                this.increaseHealth(); // OK ???
                return true;
            }
            System.out.println("I can't eat this!"); //this can be moved before return false
        }
        return false;
    }



    public Animal mate(Animal animalToMateWith) {
        if (this.getClass() == animalToMateWith.getClass() && !this.gender.equals(animalToMateWith.gender)) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {

                int randomNrOfOffsprings = random.nextInt((this.maxOffspringNumber) + 1);
                for (int i = 0; i < randomNrOfOffsprings; i++) {
                    int randomGender = random.nextInt(2);
                    createChild(randomGender);
                }
            } else {
                System.out.println("No new animal this time.");
                return null;
            }
        }
        System.out.println("Those animals can't mate!");
        return null;
    }



    public void getOlder() {
        age += 1;
        if (age > maxAge) {
            die();
        }
    }

    //method for increasing health when animal eats health increases by 10%
    public void increaseHealth() {
        if (health <= (health / 1.1)){
        health = (health * 1.1);
        }
        else {
            health = health + (100 - health); // if health is between 91% and 100% FEL
        }

    }

    public void diminishHealth() {
        double randomNumber = (random.nextDouble() * 20) + 10;

        health = health - (int) randomNumber;
        if (health <= 0) {
            die();
        }
    }


    private void die() {

        owner.getAnimalsOwned().remove(this);
                this.owner = null;
        System.out.println("Animal is dead.");

    }


    public String toString() {

        String animalInfo = (this.name + " a " + this.age + " years old " + this.gender + " " + this.getClass().getSimpleName() + " at " + this.health + "% health.");
        return animalInfo;
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




}