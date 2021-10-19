package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

import java.io.Serializable;

public class Horse extends Animal implements Serializable {



        public Horse(String name, int gender) {
            super(name, gender, 30, null,1, new Grass(), new Corn());

        }
    public Horse createChild(int randomGender) {
        Horse horse = new Horse("", randomGender);
        return horse;
    }
    }



