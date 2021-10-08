package djurspelet;

abstract class Food {


    enum TypeOfFood{
        MEAT, GRASS, KORN
    }

    public abstract int price(int weight);
    public abstract String foodType();



    public class Meat extends Food{
        int pricePerKiloMeet = 100;

        public int price(int weight) {
            int price = pricePerKiloMeet * weight;
            return price;
        }

        public String foodType() {
            String food = TypeOfFood.MEAT.toString();
            return food;
        }

    }
    public class Grass extends Food{
        int pricePerKiloGrass = 30;

        public int price(int weight) {
            int price = pricePerKiloGrass * weight;
            return price;
        }

        public String foodType() {
            String food = TypeOfFood.GRASS.toString();
            return food;
        }

    }
    public class Korn extends Food{
        int pricePerKiloKorn = 50;

        public int price(int weight) {
            int price = pricePerKiloKorn * weight;
            return price;
        }

        public String foodType() {
            String food = TypeOfFood.KORN.toString();
            return food;
        }
    }

}
