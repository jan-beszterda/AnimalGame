package grupp6.djurspelet.animal;

import java.util.Random;


public abstract class Animal {

    enum Gender {
        MALE, FEMALE
    }
    private Random random = new Random();
    private String name;
    private Gender gender;
    private int age; // varje djur börjar på ålder 0
    private int maxAge; // varje djurtyp har separat max ålder till exempel 30
    private int health = 100;
    private int price; // added price because I couldn't make the store work otherwise, this will require a simple getter


    public Animal(String name, int gender, int maxAge, int price) { // change gender to be taken as int and then selected from enum
        this.name = name;
        this.gender = Gender.values()[gender];
        this.maxAge = maxAge;
        this.price = price;
    }

    public abstract void eat();
    public abstract void mate(Animal animal); // check if it's possible to develop this method here, think how new animal is created
    public abstract void printDeath();

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

}