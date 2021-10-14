package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

public class Cow extends Animal{

    public Cow(String name, int gender){
        super(name, gender, 20, null, new Grass());
    }

    @Override
    public void printDeath() {

    }
}
