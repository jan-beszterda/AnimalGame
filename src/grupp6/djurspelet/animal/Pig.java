package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

public class Pig extends Animal{


        public Pig(String name, int gender) {
            super(name, gender, 20, null, new Corn());

        }

    public Pig createChild(int randomGender) {
        Pig pig = new Pig("", randomGender);
        return pig;
    }
    }

