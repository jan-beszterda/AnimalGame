package group6.animalgame.fodder;

import java.io.Serializable;

/**
 * This is abstract class there we give name to different types of food.
 *
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public abstract class Food implements Serializable {

    private String name;

    /**
     * Constructor of food class.
     *
     * @param name name of the Food
     */
    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * This method compares two food types.
     *
     * @param food food object to compare to this food object
     * @return true if same food object or same type of food.
     */
    @Override
    public boolean equals(Object food) {
        if (this == food) {
            return true;
        } else if (this.getClass() == food.getClass()) {
            return true;
        } else {
            return false;
        }
    }
}