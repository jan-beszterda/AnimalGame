package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

/**
 * This class extends animal class and contains
 * data about dogs.
 * @grupp6
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