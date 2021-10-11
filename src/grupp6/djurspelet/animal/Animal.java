package grupp6.djurspelet.animal;

import java.util.Random;


public abstract class Animal {

    enum Gender {
        MALE, FEMALE
    }
    Random random = new Random();

    public String name;
    public Gender gender;
    public int age;
    public int health = 100;
    public int randomNumber = (random.nextInt(3)+1)*10; // random number between 10 and 30




    public abstract void eat();
    public abstract void mate(Animal animal);
    public abstract void printDeath();



    public int getOlder() {
        health = health - randomNumber;
        if (health > 0) {
            return health;
        } else {
            //Ta bort djuret från players list!!!
            printDeath();
        }
    }

    public String getName(){
        return name;
    }

    public void setName() {
        this.name = name;
    }


}