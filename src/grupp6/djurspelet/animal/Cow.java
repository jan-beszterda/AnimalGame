package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

/**
 * This class is subclass of the animal class and contains data about cows.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Cow extends Animal{

    /**
     * Constructor of the cow objects. Takes name and gender and calls the constructor of Animal superclass.
     * @param name name for the new cow
     * @param gender gender for the new cow
     */
    public Cow(String name, int gender){
        super(name, gender, 20,1, new Grass(), new Corn());
    }

    /**
     * Creates a calf with random gender.
     * @param randomGender gender for the new cow
     * @return a new Cow object.
     */
    @Override
    public Cow createChild(int randomGender) {
        Cow cow = new Cow("", randomGender);
        return cow;
    }

}
