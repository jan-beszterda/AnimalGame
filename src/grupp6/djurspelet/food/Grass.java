package grupp6.djurspelet.food;


class Grass extends Food{

    private int pricePerKilo;
    private String name;

    public Grass (String name, int pricePerKilo){
        this.name = "Grass";
        pricePerKilo = 35;
    }

    public String getName() {
        return name;
    }
    public int getPricePerKilo() {
        return pricePerKilo;
    }


}
