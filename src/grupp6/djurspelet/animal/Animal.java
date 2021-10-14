package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.Food;
import grupp6.djurspelet.game.Player;

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

    public Animal mate(Animal animalToMateWith) { //I think we need to make this method return Animal instead of boolean
        if (this.getClass() == animalToMateWith.getClass() && !this.gender.equals(animalToMateWith.gender)) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {

                return Animal;
            }
            else {
                return null;
            }
        }
        else {
            System.out.println("Those animals can not mate!");
            return null;
        }

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
        int randomNumber = (random.nextInt(3)+1)*10;  //this actually gives 10, 20 or 30, nothing in between
                                                            //it will probably need to be nextDouble() plus some mathematical operation
        health = health - randomNumber;
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


    //to solve some issues and to make some operations simpler we might need to add public void setName(String name) method

    //a setter method to set owner will be needed -> public void setOwner(Player owner)

    //a getter method for the current age and health will be needed

    public Gender getGender() {
        return this.gender;
    }

    public int getPrice(){ //with the removal of price variable this getter method is no longer needed
        return price;
    }

    //toString() method will be needed to display full information about the animal to the player
    //returned string should include name, class name, age, gender and health
}