package grupp6.djurspelet.animal;

class Horse extends Animal{



    public Horse(String name, Gender gender){
        super(name, gender, 30);
    }



    public void eat() {

    }


    public void mate(Animal animalToMateWith) {
        if(this.getClass() == animalToMateWith.getClass() && !this.getGender().equals(animalToMateWith.getGender())){
            //Horse horse = new Horse(); // 50% chance

        }

    }

    public void printDeath(){
        System.out.println("The horse died.");
    }


}