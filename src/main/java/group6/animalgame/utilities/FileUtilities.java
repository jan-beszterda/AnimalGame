package group6.animalgame.utilities;

import group6.animalgame.logic.Game;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class takes care of saving and loading the game.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class FileUtilities{

    /**
     * Method takes the name of the save file and saves the game in it.
     * @param fileName name of the file to save to
     * @param game game to save
     */
    public static void saveGameToFile(String fileName, Game game) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileName + ".ser", false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Something went wrong when saving the game to file!");
            e.printStackTrace();
        }
    }

    /**
     * This method loads end returns game from the save file.
     * @param fileName name of the file to load from
     * @return saved game loaded from file
     */
    public static Game loadGameFromFile(String fileName) {
        ObjectInputStream objectinputstream = null;
        Game game = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName + ".ser");
            objectinputstream = new ObjectInputStream(streamIn);
            game = (Game) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to load the game from file!");
        }
        return game;
    }
}
