package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains data about cats.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Cat extends Animal{

    /**
     * Constructor of the cat objects. Takes name and gender and calls the constructor of Animal superclass.
     * @param name name for the new cat
     * @param gender gender for the new cat
     */
    public Cat(String name, int gender) {
        super(name, gender, 6, 6, new Meat());
    }

    /**
     * Creates a kitten with random gender.
     * @param randomGender gender for the child to have
     * @return a new Cat object.
     */
    @Override
    public Cat createChild(int randomGender) {
        return new Cat("", randomGender);
    }
}
