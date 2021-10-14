package grupp6.djurspelet.food;


class Corn extends Food{

    private int pricePerKilo; //price will be in the Store class
    private String name;

    public Corn(String name, int pricePerKilo){ //since price is in the Store class only one parameter (String name) is needed
        this.name = "Korn";
        pricePerKilo = 50; //with price in the Store class this line is no longer needed
    }

    public String getName() { //this is inherited from Food class so no need to write it again here
        return name;
    }

    public int getPricePerKilo() { //with price in the Store class this method is no longer needed
        return pricePerKilo;
    }

}