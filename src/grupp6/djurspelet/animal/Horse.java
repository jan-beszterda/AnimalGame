package grupp6.djurspelet.animal;
import java.util.Random;


public class Horse extends Animal{

    private Random random = new Random();


    public Horse(String name, int gender){
        super(name, gender, 30, 1000); // added price
    }






    public void printDeath(){
        System.out.println("The horse died.");
    }

    private void addAcceptedFood(){
        super.add("Grass");
        super.add("Korn");
    }

    public void getFoodToEat() {
        return ;
    }


}}