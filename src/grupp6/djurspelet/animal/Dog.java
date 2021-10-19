package grupp6.djurspelet.animal;
import grupp6.djurspelet.food.Meat;

import java.io.Serializable;


public class Dog extends Animal implements Serializable {

    private String name;



    public Dog(String name, int gender) {
        super(name, gender, 15, null, 6,  new Meat());

    }

    @Override
   public Dog createChild(int randomGender) {
         Dog dog = new Dog("", randomGender);
        return dog;
    }
}
    //private Horse createChild(int randomNumber)
// { return new Horse("", randomNumber, 30, null, new Grass(), new Corn()) }