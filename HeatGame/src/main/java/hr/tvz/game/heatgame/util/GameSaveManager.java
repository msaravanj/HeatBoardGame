package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.model.GameData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class GameSaveManager {

    private static final Path SAVE_FILE = Path.of("data", "game.dat");

    public static void saveGame(GameData gameData) throws IOException {

        Files.createDirectories(SAVE_FILE.getParent());

        try (var fos = new FileOutputStream(SAVE_FILE.toFile());
             var oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameData);
            System.out.println("Igra pohranjena: " + SAVE_FILE);
        }
    }

    public static Optional<GameData> loadGame() {

        if (!Files.exists(SAVE_FILE)) {
            System.out.println("Nema save datoteke.");
            return Optional.empty();
        }

        try (var fis = new FileInputStream(SAVE_FILE.toFile());
             var ois = new ObjectInputStream(fis)) {

            var state = (GameData) ois.readObject();
            System.out.println("Igra učitana: " + state);
            return Optional.of(state);

        } catch (InvalidClassException e) {
            System.err.println("Nekompatibilna verzija: " + e.getMessage());
            return Optional.empty();

        } catch (ClassNotFoundException e) {
            System.err.println("Klasa nije pronađena: " + e.getMessage());
            return Optional.empty();

        } catch (IOException e) {
            System.err.println("Greška čitanja: " + e.getMessage());
            return Optional.empty();
        }
    }


}
