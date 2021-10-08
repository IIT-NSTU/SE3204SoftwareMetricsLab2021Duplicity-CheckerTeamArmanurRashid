package Duplicity_Checker_package.Code;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Duplicity_Random_File extends Basic_Frame_Duplicity {

    String[] array1          = new String[100];
    String[] array2          = new String[100];
    String[] FILENAME1       = new String[100];
    String[] FILENAME2       = new String[100];
    String[] FILENAME3       = new String[100];
    String[] FILENAME4       = new String[100];
    File  [] selected_files1 = new File  [100];
    File  [] selected_files2 = new File  [100];
    float [] result          = new float [100];
    float [] matching_line_number = new float[100];

    JFileChooser savefile;
    String save_filename;
    char per = '%' ;
    float matching_word_count = 0  , line = 0 ,temp ;
    int single_counter = 0 , multiple_counter = 0 ;
    int file1_counter = 0 , file2_counter=0 ,file_number_check1=0,file_number_check2=0, result_counter =0 ;
    String string_temp1,string_temp2 , File1Extension , File2Extension , extension1,extension2;
    ButtonSound sound_button = new ButtonSound();

    Duplicity_Random_File() throws IOException {
        App_Icon();
        super.frame();
        super.setPanel();
        super.setTitle("Duplicity Checker");
        super.userinterface();
        super.buttons();
        Logical_part();
    }

    public void Logical_part(){
        back_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });

        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();

                if (result_textarea.getText().length() > 0) {
                    //write file name and choose directory
                    save_filename = JOptionPane.showInputDialog("Write New File Name");
                    savefile = new JFileChooser();
                    savefile.setDialogTitle("Choose Directory");
                    savefile.setSelectedFile(new File(save_filename));
                    int sf = savefile.showSaveDialog(null);
                    if (sf == JFileChooser.APPROVE_OPTION) {
                        sound_button.playsound();
                            // choose the file format
                        try {
                            Object[] choices = {"Docx", "Pdf", "Cancel"};
                            Object defaultChoice = choices[0];
                            int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                            // save as docx
                            if (n == JOptionPane.YES_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_docx(savefile.getSelectedFile(),result_textarea.getText());
                            }
                            // save as pdf
                            else if (n == JOptionPane.NO_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_pdf(savefile.getSelectedFile(),result_textarea.getText());
                            }
                            // cancel
                            else {
                                sound_button.playsound();
                            }
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, ee);
                        }
                    } else {
                        sound_button.playsound();
                    }
                }
                // If there is no text in JTextArea. Warning Message .
                else{
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "There is no text to save", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
            }
        });

        file_read_button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                file_number_check1=0;
                sound_button.playsound();
                try{
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(true);
                    int option = chooser.showOpenDialog(null);
                    if(option == JFileChooser.APPROVE_OPTION) {

                        if (comparable_file_textarea.getText().length() > 0) {
                            comparable_file_textarea.setText("");
                        }

                        selected_files1 = chooser.getSelectedFiles();

                        for (int i = 0; i < selected_files1.length; i++) {

                            FILENAME1[i] = selected_files1[i].getName();
                            File1Extension = selected_files1[i].getName();
                            extension1 = "";
                            // Extension check. Is it pdf or docx
                            int index_position = File1Extension.lastIndexOf('.');
                            if (index_position >= 0) {
                                extension1 = File1Extension.substring(index_position + 1);
                            }

                            // Reading docx file
                            if (extension1.matches("docx")) {
                                XWPFDocument docx = new XWPFDocument(new FileInputStream(selected_files1[i]));
                                XWPFWordExtractor extract = new XWPFWordExtractor(docx);
                                if (selected_files1.length != 1) {
                                    comparable_file_textarea.append("File Name : " + FILENAME1[i] + "\n");
                                }
                                comparable_file_textarea.append(extract.getText() + "\n\n");
                                array1[i] = extract.getText();
                                file1_counter++;
                                file_number_check1++;
                            }
                            //Reading pdf file
                            else if (extension1.matches("pdf")) {
                                PDDocument document = PDDocument.load(selected_files1[i]);
                                PDFTextStripper pdfStripper = new PDFTextStripper();
                                if (selected_files1.length != 1) {
                                    comparable_file_textarea.append("File Name : " + FILENAME1[i] + "\n");
                                }
                                comparable_file_textarea.append(pdfStripper.getText(document) + "\n\n");
                                array1[i] = pdfStripper.getText(document);
                                file1_counter++;
                                file_number_check1++;
                            }
                        }
                    }
                    else{
                        Object[] options = {"Ok"};
                        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                        if (n == JOptionPane.OK_OPTION) {
                            sound_button.playsound();
                        }
                    }
                }
                catch (Exception e) {
                    ok_button_sound();
                }
                file1_counter=0;
            }
        });

        file_read_button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                file_number_check2=0;

                sound_button.playsound();
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(true);
                    int option = chooser.showOpenDialog(null);
                    if(option == JFileChooser.APPROVE_OPTION) {
                        if(comparing_file_textarea.getText().length()>0){
                            comparing_file_textarea.setText("");
                        }
                        selected_files2 = chooser.getSelectedFiles();

                        for(int i = 0 ; i< selected_files2.length ;i++){

                            FILENAME2[i] = selected_files2[i].getName();
                            File2Extension = selected_files2[i].getName();
                            //extension Check
                            extension2 = "";
                            int index_position = File2Extension.lastIndexOf('.');
                            if (index_position >= 0) {
                                extension2 = File2Extension.substring(index_position+1);
                            }

                            // Reading docx file
                            if(extension2.matches("docx")){
                                XWPFDocument docx = new XWPFDocument(new FileInputStream(selected_files2[i]));
                                XWPFWordExtractor extract = new XWPFWordExtractor(docx);
                                if(selected_files2.length!=1){
                                    comparing_file_textarea.append("File Name : "+FILENAME2[i]+"\n");
                                }
                                comparing_file_textarea.append(extract.getText()+"\n\n");
                                array2[i]=extract.getText();
                                file2_counter++;
                                file_number_check2++;

                            }

                            //Reading pdf file
                            if(extension2.matches("pdf")){
                                PDDocument document = PDDocument.load(selected_files2[i]);
                                PDFTextStripper pdfStripper = new PDFTextStripper();
                                String extract = pdfStripper.getText(document);
                                if(selected_files2.length!=1){
                                    comparing_file_textarea.append("File Name : "+FILENAME2[i]+"\n");
                                }
                                comparing_file_textarea.append(extract+"\n\n");
                                array2[i]=extract;
                                file2_counter++;
                                file_number_check2++;
                            }
                        }

                    }
                    else{
                        Object[] options = {"Ok"};
                        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                        if (n == JOptionPane.OK_OPTION) {
                            sound_button.playsound();
                        }
                    }
                }
                catch (Exception e)
                {
                    ok_button_sound();
                }
                file2_counter=0;
            }
        });

        file_check_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {

                sound_button.playsound();
                if(result_textarea.getText().length()>0){
                    result_textarea.setText("");
                }

                //Warning message if there is no text to check
                if (comparable_file_textarea.getText().length() == 0 || comparing_file_textarea.getText().length() == 0) {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "Nothing to Check", "Message", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                    result_textarea.setText("");
                }
                //if Comparison occurred between two files
                if (file_number_check1 == 1 && file_number_check2==1) {
                    single_file();
                }
                //if file number is greater than one
                else if(file_number_check1>1 || file_number_check2>1){
                    multiple_files();
                }
            }
        });
    }

    //If comparison occurred between two files
    public void single_file(){
        if(result_textarea.getText().length()>0){
            result_textarea.setText("");
        }
        try {
            // Split the whole comparable_file_textarea's text into sentence.
            String string1 = comparable_file_textarea.getText();
            String[] str1 = string1.split("[\\s]*[.?!][\\s]*");

            // Split the whole comparing_file_textarea's text into sentence.
            String string2 = comparing_file_textarea.getText();
            String[] str2 = string2.split("[\\s]*[.?!][\\s]*");

            repeat_from_first:
            for (int i = 0; i < str1.length; i++) {
                //Split the comparable sentence into word
                String[] word1 = str1[i].split("\\s+");

                for (int j = 0; j < str2.length; j++) {

                    //Split the comparing sentence into word
                    String[] word2 = str2[j].split("\\s+");
                    repeat:
                    for (int x = 0; x < word1.length; x++) {

                        for (int l = 0; l < word2.length; l++) {
                            // Don't check these words
                            if(word1[x].matches("am|is|are|was|were|have|has|had|a|an|the")){
                                single_counter++;
                                continue repeat;
                            }
                            else if (word1[x].equals(word2[l])) {
                                matching_word_count++;
                                continue repeat;
                            }
                        }
                    }

                    // Checking if the line will count as matching line
                    float length = (word2.length - single_counter);
                    float ans = ((matching_word_count / length) * 100);
                    if (ans > 60) {
                        line++;
                        if (string1.length() == 0 || string2.length() == 0) {
                            result_textarea.setText("");
                        }
                        //highlighting the matching line.
                        else {
                            result_textarea.append("Matching lines : " + str2[j]);
                            result_textarea.append("\n");
                        }
                    }
                    matching_word_count = 0;
                    single_counter=0;
                    if(ans>60){
                        continue repeat_from_first;
                    }
                }
            }

            if (string1.length() == 0 || string2.length() == 0) {
                result_textarea.setText("");
            }

            // appending matching line in result_textarea
            else {
                result_textarea.append("\n"+"Matching Line : " + line+"\n\n");
                float length = str2.length;
                float result = ((line / length) * 100);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                result_textarea.append("Percentage : " + df.format(result) + "%"+"\n\n\n");
            }
            line = 0;
        }
        catch (Exception e) { }
    }

    public void multiple_files() {

        //Loop for n number of comparable file
        for(int u= 0;u<file_number_check1;u++) {
            String s = array1[u];
            // Split the whole comparable_file_textarea's text into sentence.
            String[] str = s.split("[\\s]*[.][\\s]*");

            //Loop for n number of comparing file
            for (int r = 0; r < file_number_check2; r++) {
                String s1 = array2[r];
                // Split the whole comparing_file_textarea's text into sentence.
                String[] str1 = s1.split("[\\s]*[.][\\s]*");

                repeat_from_first:
                for (int i = 0; i < str.length; i++) {
                    //Split the comparable sentence into word
                    String[] word1 = str[i].split("\\s+");

                    for (int j = 0; j < str1.length; j++) {
                        //Split the comparing sentence into word
                        String[] word2 = str1[j].split("\\s+");

                        repeat:
                        for (int k = 0; k < word1.length; k++) {
                            // Don't check these words
                            if (word1[k].matches("am|is|are|was|were|have|has|had|a|an|the")) {
                                continue repeat;
                            }
                            for (int l = 0; l < word2.length; l++) {

                                if (word1[k].equals(word2[l])) {
                                    matching_word_count++;
                                    continue repeat;
                                }
                            }
                        }
                        // Checking if the line will count as matching line
                        float lengthh = (word2.length);
                        float ans = ((matching_word_count / lengthh) * 100);
                        if (ans > 60) {
                            line++;
                        }
                        matching_word_count = 0;
                        if(ans>60){
                            continue repeat_from_first;
                        }
                    }
                }
                matching_line_number[result_counter] = line;
                float length = str1.length;
                result[result_counter] = ((line / length) * 100);
                line = 0;
                FILENAME3[result_counter]=selected_files1[u].getName();
                FILENAME4[result_counter]=selected_files2[r].getName();
                result_counter++;
            }
        }
        //sorting the result with file names
        Sorting_result SR = new Sorting_result();
        try {
            SR.sort(FILENAME3,FILENAME4,matching_line_number,result,result_counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        display_percentage_after_sorting();
    }

    //Display result
    public void display_percentage_after_sorting(){
        for(int aa = 0 ; aa<result_counter;aa++) {
            if (FILENAME3[aa].equals(FILENAME4[aa])) { }
            else{
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                result_textarea.append(FILENAME3[aa] + "  matched  " + matching_line_number[aa] + " line and  " + df.format(result[aa]) + per + "  with  " + FILENAME4[aa] + "\n\n");
            }
        }
        result_counter=0;
    }

    public void ok_button_sound(){
        Object[] options = {"Ok"};
        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Message", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
        if (n == JOptionPane.OK_OPTION) {
            sound_button.playsound();
        }
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        Duplicity_Random_File ma = new Duplicity_Random_File();
        ma.setVisible(true);
    }
}