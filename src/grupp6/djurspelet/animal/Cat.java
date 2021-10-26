package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

/**
 * This class extends animal class and contains
 * data about cats.
 * @grupp6
 */
public class Cat extends Animal{

    public Cat(String name, int gender) {
        super(name, gender, 12, null, 12, new Meat());
    }

    /**
     * Creates a kitten with random gender.
     * @param randomGender
     * @return a new Cat object.
     */
    @Override
    public Cat createChild(int randomGender) {
        Cat cat = new Cat("", randomGender);
        return cat;
    }
}
