package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.*;

public class Cat extends Animal{

    public Cat(String name, int gender) {
        super(name, gender, 12, null, 12, new Meat());
    }

    public Cat createChild(int randomGender) {
        Cat cat = new Cat ("", randomGender);
        return cat;
    }
}
