package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.io.File;

public class file_read {
    public void fileRead(ButtonSound sound_button, File file,JTextArea text) {
        try {
            sound_button.playsound();
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            file = chooser.getSelectedFile();
            check_extension check_extension = new check_extension();
            check_extension.checking_extension(file, sound_button, text);
        } catch (Exception e) {
            Object[] options = {"Ok"};
            int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
            if (n == JOptionPane.OK_OPTION) {
                sound_button.playsound();
            }
        }
    }
}