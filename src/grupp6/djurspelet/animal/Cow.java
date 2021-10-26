package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;
import java.io.Serializable;

public class Cow extends Animal implements Serializable {

    public Cow(String name, int gender){
        super(name, gender, 20, null,1, new Grass(), new Corn());
    }

    @Override
    public Cow createChild(int randomGender) {
        Cow cow = new Cow("", randomGender);
        return cow;
    }

}
