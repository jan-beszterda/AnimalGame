package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

/**
 * This class is subclass of the animal class and contains
 * data about pigs.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Pig extends Animal{

    public Pig(String name, int gender) {
        super(name, gender, 20, null, 10, new Corn());
    }

    /**
     * Creates a piglet with random gender.
     * @param randomGender
     * @return a new Pig object.
     */
    @Override
    public Pig createChild(int randomGender) {
        Pig pig = new Pig("", randomGender);
        return pig;
    }
}

