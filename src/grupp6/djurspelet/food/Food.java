package grupp6.djurspelet.food;

import java.io.Serializable;

/**
 * This is abstract class there we give name to different types of food.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public abstract class Food implements Serializable {

    private String name;

    /**
     * Constructor of food class.
     * @param name
     */
    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * This method compares two food types.
     * @param food
     * @return true if same type of food.
     */
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