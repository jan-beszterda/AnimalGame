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


    @Override
    public boolean equals(Object food) {
        if (food != null) {
            if (this == food) {
                return true;
            } else if (this.getClass() == food.getClass()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}