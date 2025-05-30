import javax.sound.sampled.*;
import java.io.File;

public class MusicaUtils {
    private static Clip clip;

    public static void tocarMusica(String caminho) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminho));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    public static void pararMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
