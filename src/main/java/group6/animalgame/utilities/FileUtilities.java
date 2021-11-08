package group6.animalgame.utilities;

import group6.animalgame.logic.Game;

import java.io.*;

/**
 * This class takes care of saving and loading the game.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class FileUtilities{

    /**
     * Method takes the name of the save file and saves the game in it.
     * @param game game to save
     */
    public static void saveGameToFile(File file, Game game) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(file, false);
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
     * @return saved game loaded from file
     */
    public static Game loadGameFromFile(File file) {
        ObjectInputStream objectinputstream = null;
        Game game = null;
        try {
            FileInputStream streamIn = new FileInputStream(file);
            objectinputstream = new ObjectInputStream(streamIn);
            game = (Game) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to load the game from file!");
        }
        return game;
    }
}
