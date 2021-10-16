package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

public class Cow extends Animal{

    private String name;

    public Cow(String name, int gender){
        super(name, gender, 20, null,1, new Grass(), new Corn());
    }

    public Cow createChild(int randomGender) {
        Cow cow = new Cow("", randomGender);
        return cow;
    }

}
