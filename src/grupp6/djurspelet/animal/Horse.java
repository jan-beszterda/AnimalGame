package grupp6.djurspelet.animal;


import java.lang.reflect.Array;
import java.util.Random;


public class Horse extends Animal{

    private Random random = new Random();


    public Horse(String name, int gender){
        super(name, gender, 30, 1000, acceptedFood); // added price
    }




    public boolean mate(Animal animalToMateWith) {
        if (this.getClass() == animalToMateWith.getClass() && !this.getGender().equals(animalToMateWith.getGender())) {
            int randomNumber = random.nextInt(2);
            if (randomNumber == 1) {
                //Horse horse = new Horse(); // 50% chance
                return true;
            }
            else {
                return false;
            }
        }
        else {
            System.out.println("Those animals can not mate!");
        }
        return false;
    }

    public void printDeath(){
        System.out.println("The horse died.");
    }

    private void addAcceptedFood(){
        super.add("Grass");
        super.add("Korn");
    }

    public String Array[] getFoodToEat() {
        return super.;
    }


}