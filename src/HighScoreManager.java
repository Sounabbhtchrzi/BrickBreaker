import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String FILE_NAME = "highscores.txt";
    private List<Integer> highScores;
    private int maxScores = 5;

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(Integer.parseInt(line.trim()));
            }
            Collections.sort(highScores, Collections.reverseOrder());
        } catch (IOException | NumberFormatException e) {
            System.out.println("No high scores found or error loading scores.");
        }
    }

    // Save high scores to the file
    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int score : highScores) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addScore(int score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());

        if (highScores.size() > maxScores) {
            highScores.remove(highScores.size() - 1);
        }

        saveHighScores();
    }


    public String getHighScores() {
        StringBuilder sb = new StringBuilder("High Scores:\n");
        for (int i = 0; i < Math.min(highScores.size(), maxScores); i++) {
            sb.append((i + 1)).append(". ").append(highScores.get(i)).append("\n");
        }
        return sb.toString();
    }
}
