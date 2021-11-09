package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains data about pigs.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Pig extends Animal{

    /**
     * Constructor of the pig objects. Takes name and gender and calls the constructor of Animal superclass.
     * @param name name for the new pig
     * @param gender gender for the new pig
     */
    public Pig(String name, int gender) {
        super(name, gender, 10, 5, new Corn());
    }

    /**
     * Creates a piglet with random gender.
     * @param randomGender gender for the child to have
     * @return a new Pig object.
     */
    @Override
    public Pig createChild(int randomGender) {
        return new Pig("", randomGender);
    }
}

