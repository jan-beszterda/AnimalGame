package grupp6.djurspelet.animal;

import grupp6.djurspelet.food.Meat;

public class Dog extends Animal {



    private String name;



    public Dog(String name, int gender) {
        super(name, gender, 15, null, new Meat());

    }
}
