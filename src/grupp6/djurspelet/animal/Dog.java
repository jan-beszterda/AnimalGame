package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains
 * data about dogs.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Dog extends Animal{

    public Dog(String name, int gender) {
        super(name, gender, 15, null, 6, new Meat());
    }

    /**
     * Creates a puppy with random gender.
     * @param randomGender
     * @return a new Dog object.
     */
    @Override
    public Dog createChild(int randomGender) {
        Dog dog = new Dog("", randomGender);
        return dog;
    }
}