import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip clip;

    public static void playSound(String soundFile) {
        try {
            // Load sound file
            File file = new File("sounds/" + soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
    public static void playBrickHit() {
        playSound("click.wav");
    }

    public static void playGameOver() {
        playSound("gameOver.wav");
    }

    public static void playLevelComplete() {
        playSound("levelComplete.wav");
    }
    public static void playThemeSong(){
        playSound("themeSong.wav");
    }
}

