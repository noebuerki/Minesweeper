package ch.bbcag.minesweeper;

import ch.bbcag.minesweeper.enums.Profile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    private static final File highScoreFile = new File("highscores.txt");

    public static void createFile(){

        HashMap<Enum<Profile>, Integer> highScoreMap = new HashMap<>();

        highScoreMap.put(Profile.ONE, 0);
        highScoreMap.put(Profile.TWO, 0);
        highScoreMap.put(Profile.THREE, 0);

        writeHighscore(highScoreMap);
    }

    public static int getHighscore(Profile profile) {

        if(!Files.exists(Path.of("highscores.txt"))){
            createFile();
        }
        HashMap<Enum<Profile>, Integer> highScoreMap = readHighscore();
        return  highScoreMap.get(profile);
    }

    public static void setHighscore(Profile profile, int score){

        if(!Files.exists(Path.of("highscores.txt"))){
            createFile();
        }
        HashMap<Enum<Profile>, Integer> highScoreMap = readHighscore();
        highScoreMap.replace(profile, score);
        writeHighscore(highScoreMap);
    }

    public static HashMap<Enum<Profile>, Integer> readHighscore() {

        HashMap<Enum<Profile>, Integer> highScoreMap = new HashMap<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(highScoreFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(":");
                highScoreMap.put(Profile.valueOf(temp[0]), Integer.parseInt(temp[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (NullPointerException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return highScoreMap;
    }

    private static void writeHighscore(HashMap<Enum<Profile>, Integer> highScoreMap){

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(highScoreFile));
            for (Map.Entry<Enum<Profile>, Integer> entry : highScoreMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
