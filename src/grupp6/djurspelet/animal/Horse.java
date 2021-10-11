package grupp6.djurspelet.animal;

class Horse extends Animal{



    public Horse(String name, Gender gender, int health, int age ){
        this.name = name;
        this.gender = gender;
        this.health = super.health;
        this.age = 30;
    }



    public void eat() {

    }


    public void mate(Animal animalToMateWith) {
        if(this.getClass() == animalToMateWith.getClass() && !this.gender.equals(animalToMateWith.gender)){
            Horse horse = new Horse(); // 50% chance
            Horse.super.setName();
        }

    }

    public void printDeath(){
        System.out.println("The horse died.");
    }


}