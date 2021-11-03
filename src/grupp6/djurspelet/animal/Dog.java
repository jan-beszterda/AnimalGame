package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains data about dogs.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Dog extends Animal{

    /**
     * Constructor of the dog objects. Takes name and gender and calls the constructor of Animal superclass.
     * @param name name for the new dog
     * @param gender gender for the new dog
     */
    public Dog(String name, int gender) {
        super(name, gender, 15, 6, new Meat());
    }

    /**
     * Creates a puppy with random gender.
     * @param randomGender gender for the new dog to have
     * @return a new Dog object.
     */
    @Override
    public Dog createChild(int randomGender) {
        return new Dog("", randomGender);
    }
}