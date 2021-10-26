package grupp6.djurspelet.utilities;

import grupp6.djurspelet.game.Game;

import java.io.*;

public class FileUtilities implements Serializable {

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

    public static Game loadGameFromFile(String fileName) {
        ObjectInputStream objectinputstream = null;
        Game game = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName + ".ser");
            objectinputstream = new ObjectInputStream(streamIn);
            game = (Game) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            System.out.println("Something went wrong when loading the game from file!");
        }
        return game;
    }
}
