package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

public class Pig extends Animal implements Serializable {

    public Pig(String name, int gender) {
        super(name, gender, 20, null, 10, new Corn());
    }

    @Override
    public Pig createChild(int randomGender) {
        Pig pig = new Pig("", randomGender);
        return pig;
    }
}

