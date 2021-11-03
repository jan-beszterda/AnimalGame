package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains data about horses.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Horse extends Animal{

    /**
     * Constructor of the horse objects. Takes name and gender and calls the constructor of Animal superclass.
     * @param name name for the new horse
     * @param gender gender for the new horse
     */
    public Horse(String name, int gender) {
        super(name, gender, 30, 1, new Grass(), new Corn());
    }

    /**
     * Creates a foal with random gender.
     * @param randomGender gender for the new horse to have
     * @return a new Horse object.
     */
    @Override
    public Horse createChild(int randomGender) {
        return new Horse("", randomGender);
    }
}



