package src.main.java.util;

import java.io.*;
import java.util.List;

public class FileUtil {
    public static void saveToFile(String filename, Object obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream("src/main/resources/" + filename))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream("src/main/resources/" + filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}