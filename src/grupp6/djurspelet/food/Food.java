package grupp6.djurspelet.food;

import java.io.Serializable;

public abstract class Food implements Serializable {

    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object food){
        if (this == food) {
            return true;
        } else if (this.getClass() == food.getClass()) {
            return true;
        } else {
            return false;
        }
    }
}