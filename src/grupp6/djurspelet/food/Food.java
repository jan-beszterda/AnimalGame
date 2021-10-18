package grupp6.djurspelet.food;

public abstract class Food {

    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object food){
        return this.getClass() == food.getClass() && this == food;
    }
}