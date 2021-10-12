package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public abstract class Animal {

    enum Gender {
        MALE, FEMALE
    }
    private ArrayList<Food> acceptedFood;
    private Random random = new Random();
    private String name;
    private Gender gender;
    private int age = 0;
    private int maxAge; // varje djurtyp har separat max ålder till exempel 30
    private int health = 100;
    private int price;


    public Animal(String name, int gender, int maxAge, int price, Food... acceptedFood) { // change gender to be taken as int and then selected from enum
        this.name = name;
        this.gender = Gender.values()[gender];
        this.maxAge = maxAge;
        this.price = price;
        this.acceptedFood.addAll(Arrays.asList(acceptedFood));
    }


    public abstract void printDeath();


    public boolean eat(Food food) {
        if (acceptedFood.contains(food.getName())) {
            return true;
        }
            else {
                System.out.println("I can't eat this!");
                return false;
            }

        }

    public boolean mate(Animal animalToMateWith) {
        if (this.getClass() == animalToMateWith.getClass() && !this.getGender().equals(animalToMateWith.getGender())) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {
                //Horse horse = new Horse(); // 50% chance
                return true;
            }
            else {
                return false;
            }
        }
        else {
            System.out.println("Those animals can not mate!");
        }
        return false;
    }


    //method for increasing health when animal eats health increases by 10%

    public void getOlder() {
        age += 1;
        if (age > maxAge) {
            die();
        }
    }

    public void diminishHealth() {
        int randomNumber = (random.nextInt(3)+1)*10; // random number between 10 and 30
        health = health - randomNumber;
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        // See later how to do this
    }

    public String getName(){
        return name;
    }


    public Gender getGender() {
        return this.gender;
    }

    public int getPrice(){
        return price;
    }

}}