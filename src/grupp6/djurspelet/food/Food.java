package grupp6.djurspelet.food;

public abstract class Food {

    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}