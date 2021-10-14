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


    public Animal(String name, int gender, int maxAge, Player owner, Food... acceptedFood) {
        this.name = name;
        this.gender = Gender.values()[gender];
        this.maxAge = maxAge;
        this.owner = null;
        this.acceptedFood.addAll(Arrays.asList(acceptedFood));
    }




    public boolean eat(Food food) {
        if (acceptedFood.contains(food.getName())) {    //this will need to be a for loop that goes through each Food object in acceptedFood ArrayList
                                                        //and then compares the name of the Food object in the current iteration
                                                        //with the name of the Food object in the method argument
                                                        //if there's a match health should increase and method returns true
                                                        //otherwise method returns false
            return true;
        } else {
                System.out.println("I can't eat this!");
                return false;
        }
    }



    public Animal mate(Animal animalToMateWith) {
        if (this.getClass() == animalToMateWith.getClass() && !this.gender.equals(animalToMateWith.gender)) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {
                int randomGender = random.nextInt(2);
                createChild(randomGender);
            }
            else {
                return null;
            }
        }
        else {
            System.out.println("Those animals can not mate!");
            return null;

        }
    }

    public abstract Animal createChild(int randomGender);

    //To make mate() method work we might need an abstract method for creating the child -> private abstract Animal createChild();
    //this method would be called if in mate() if mating succeeded it would need to be overridden in each subclass of animal







    //method for increasing health when animal eats health increases by 10%

    public void getOlder() {
        age += 1;
        if (age > maxAge) {
            die();
        }
    }

    public void diminishHealth() {
        double randomNumber = (random.nextDouble()*20)+10;

        health = health - (int)randomNumber;
        if (health <= 0) {
            die();
        }
    }





    private void die() {
        //we'll see later how to do this
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

    public String toString() {

        return null;
    }
//toString() method will be needed to display full information about the animal to the player
    //returned string should include name, class name, age, gender and health


}