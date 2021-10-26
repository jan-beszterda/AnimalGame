package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

/**
 * This class extends animal class and contains
 * data about horses.
 * @grupp6
 */
public class Horse extends Animal{

    public Horse(String name, int gender) {
        super(name, gender, 30, null, 1, new Grass(), new Corn());
    }

    /**
     * Creates a foal with random gender.
     * @param randomGender
     * @return a new Horse object.
     */
    @Override
    public Horse createChild(int randomGender) {
        Horse horse = new Horse("", randomGender);
        return horse;
    }
}



