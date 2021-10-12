package grupp6.djurspelet.food;


class Corn extends Food{

    private int pricePerKilo;
    private String name;

    public Corn(String name, int pricePerKilo){
        this.name = "Korn";
        pricePerKilo = 50;
    }

    public String getName() {
        return name;
    }
    public int getPricePerKilo() {
        return pricePerKilo;
    }

}