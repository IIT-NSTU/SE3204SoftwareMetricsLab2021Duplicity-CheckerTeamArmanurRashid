package Duplicity_Checker_package.Code;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class ButtonSound extends JFrame {
public static int OFFvariable =0;
public static int ONvariable =0;
        URL sound = getClass().getResource("button-16.wav");
    public void playsound() {
        if(OFFvariable == 0 && ONvariable==1){}
        else {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sound));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception e) {
            }
        }
    }
}