package grupp6.djurspelet.animal;
import java.util.Random;


public class Horse extends Animal{

    private Random random = new Random(); //we won't need another random here


    public Horse(String name, int gender){
        super(name, gender, 30, 1000); //I moved price to the Store class
                                                    // it won't be needed here after the variable is removed in Animal class
    }






    public void printDeath(){ //printing of the death message will probably be done in the Game class
        System.out.println("The horse died.");
    }

    private void addAcceptedFood(){ //this should be passed through constructor as comma separated Food objects at the end of super() call
        super.add("Grass");
        super.add("Korn");
    }

    public void getFoodToEat() { //this getter method probably won't be needed but if yes then it should be in animal class
        return ;
    }


}