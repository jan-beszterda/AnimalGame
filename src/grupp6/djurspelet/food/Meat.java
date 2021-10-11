package grupp6.djurspelet.food;

class Meat extends Food {

    private int pricePerKilo;
    private String name;

    public Meat(String name, int pricePerKilo){
        this.name = "Meat";
        pricePerKilo = 100;
    }

    public String getName() {
        return name;
    }
    public int getPricePerKilo() {
        return pricePerKilo;
    }



}
