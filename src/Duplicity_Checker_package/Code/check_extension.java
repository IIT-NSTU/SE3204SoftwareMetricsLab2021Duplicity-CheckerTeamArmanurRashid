package Duplicity_Checker_package.Code;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class check_extension {
    public void checking_extension(File file,ButtonSound sound_button,JTextArea text) throws IOException {
        String FileExtension = file.getName();
        String extension = "";
        int i = FileExtension.lastIndexOf('.');
        if (i >= 0) {
            extension = FileExtension.substring(i + 1);
        }
        if (extension.equals("docx")) {
            XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
            XWPFWordExtractor extract = new XWPFWordExtractor(docx);
            text.setText(extract.getText());
        } else {
            Object[] options = {"Ok"};
            int n = JOptionPane.showOptionDialog(null, "Choose docx file only", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
            if (n == JOptionPane.OK_OPTION) {
                sound_button.playsound();
            }
        }
    }
}