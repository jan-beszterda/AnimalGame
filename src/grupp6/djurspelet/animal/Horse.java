package grupp6.djurspelet.animal;

public class Horse extends Animal{



    public Horse(String name, int gender){
        super(name, gender, 30, 1000); // added price
    }



    public void eat() {

    }


    public void mate(Animal animalToMateWith) {
        if(this.getClass() == animalToMateWith.getClass() && !this.getGender().equals(animalToMateWith.getGender())){
            //Horse horse = new Horse(); // 50% chance
            //if mating success return something for example 1/true
        }

    }

    public void printDeath(){
        System.out.println("The horse died.");
    }


}