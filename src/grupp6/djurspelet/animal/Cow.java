package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

/**
 * This class is subclass of the animal class and contains
 * data about cows.
 * @grupp6
 */
public class Cow extends Animal{

    public Cow(String name, int gender){
        super(name, gender, 20, null,1, new Grass(), new Corn());
    }

    /**
     * Creates a calf with random gender.
     * @param randomGender
     * @return a new Cow object.
     */
    @Override
    public Cow createChild(int randomGender) {
        Cow cow = new Cow("", randomGender);
        return cow;
    }

}
