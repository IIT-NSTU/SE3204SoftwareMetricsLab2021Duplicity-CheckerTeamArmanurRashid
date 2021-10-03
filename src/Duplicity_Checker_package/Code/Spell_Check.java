package Duplicity_Checker_package.Code;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Spell_Check extends Panel_BackButton_Template {

    private JButton file_read_button , file_check_button;
    private JTextArea text;
    private JScrollPane read_scrolltext1 ;
    private ImageIcon file_read_img , file_check_img , save_button_img;

    char[] Alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char[] chars;
    char alphabet;
    int i,kk;
    int error_counter=0;
    String Given_Word,FileExtension,extension;
    File file,F1;
    String save_filename;
    JFileChooser savefile;
    ButtonSound sound_button = new ButtonSound();

    Spell_Check()  throws IOException {

        App_Icon();
        super.frame();
        super.setTitle("Spell Checker");
        super.setContainer();
        super.setPanel();
        super.BackButton();

        file_read_img = new ImageIcon (getClass().getResource("Picture//ReadFile2.png"));
        JButton file_read_button = new JButton(file_read_img);
        file_read_button.setBackground(new Color(28, 73, 102));
        file_read_button.setBorder(null);
        file_read_button.setBounds(40,19,file_read_img.getIconWidth(),file_read_img.getIconHeight());
        jpanel.add(file_read_button);

        file_check_img = new ImageIcon (getClass().getResource("Picture//Check.png"));
        JButton file_check_button = new JButton(file_check_img);
        file_check_button.setBackground(new Color(28, 73, 102));
        file_check_button.setBorder(null);
        file_check_button.setBounds(482,575,file_check_img.getIconWidth(),file_check_img.getIconHeight());
        jpanel.add(file_check_button);

        save_button_img = new ImageIcon (getClass().getResource("Picture//Save_button.png"));
        JButton save_button = new JButton(save_button_img);
        save_button.setBackground(new Color(28, 73, 102));
        save_button.setBorder(null);
        save_button.setBounds(40,575,save_button_img.getIconWidth(),save_button_img.getIconHeight());
        save_button.setEnabled(false);
        jpanel.add(save_button);

        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        text.setFont(new Font("Times new Roman",Font.BOLD,16));
        jpanel.add(text);

        read_scrolltext1 = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        read_scrolltext1.setBounds(40,60,540,500);
        jpanel.add(read_scrolltext1);

        //Back button
        back_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });

        //save button
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();

                //Checking whether the text is not empty or not
                if (text.getText().length() > 0) {

                    save_filename = JOptionPane.showInputDialog("Write New File Name");
                    savefile = new JFileChooser();
                    savefile.setDialogTitle("Choose Directory");
                    savefile.setSelectedFile(new File(save_filename));

                    int sf = savefile.showSaveDialog(null);
                    if (sf == JFileChooser.APPROVE_OPTION) {
                        sound_button.playsound();

                        try {
                            Object[] choices = {"Docx", "Pdf", "Cancel"};
                            Object defaultChoice = choices[0];
                            int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);

                            //Save file as docx
                            if (n == JOptionPane.YES_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_docx(savefile.getSelectedFile(),text.getText());
                            }

                            //Save file as pdf
                            else if (n == JOptionPane.NO_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_pdf(savefile.getSelectedFile(),text.getText());

                            } else {
                                sound_button.playsound();
                            }
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, ee);
                        }
                    } else {
                        sound_button.playsound();
                    }
                }
                else{
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "There is no text to save", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
            }
        });

        //File read
        file_read_button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent ea){
                file_read file_read = new file_read();
                file_read.fileRead(sound_button,file,text);
            }
        });

        //File check
        file_check_button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent ea) {

                try {
                    sound_button.playsound();
                    error_counter=0;
                    Highlighter hilite = text.getHighlighter();
                    hilite.removeAllHighlights();

                    String input_string = text.getText();
                    //split the text into sentences
                    String[] input_sentence = input_string.split("[\\s]*[-.,!?:)\"][\\s]*");

                    for (i = 0; i < input_sentence.length; i++) {
                        //Split the sentences into words
                        String[] input_word = input_sentence[i].toLowerCase().split("\\s+");

                        for ( kk = 0; kk < input_word.length; kk++) {
                            //split the words into char
                            char[] chars = input_word[kk].toLowerCase().toCharArray();

                            for (int k = 0; k < chars.length; k++) {
                                //Looking for first char of the word
                                if (k == 0) {
                                    for (int aa = 0; aa < Alphabet.length; aa++) {
                                        if (chars[0] == Alphabet[aa]) {
                                            alphabet = Alphabet[aa];
                                            //path of stored_word files
                                            F1 = new File("Words\\" + Alphabet[aa] + ".docx");
                                            Given_Word = input_word[kk];
                                            check_Correct_word(save_button);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //if any error remaining in Jtextarea disable save button
                    if(error_counter>0){
                        save_button.setEnabled(false);
                    }
                    else if(error_counter==0) {save_button.setEnabled(true);}
                    text.setEditable(true);
                }
                catch (Exception e){
                }
            }
        });
    }

    public void check_Correct_word(JButton save_button){
        try{
            XWPFDocument Stored_Docx = new XWPFDocument(new FileInputStream(F1));
            XWPFWordExtractor Stored_Text = new XWPFWordExtractor(Stored_Docx);
            String Stored_String = Stored_Text.getText();
            String[] Stored_Sentence = Stored_String.split("[\\s]*[.][\\s]*");
            String[] Stored_Word = null;

            //Split the stored sentence into words
            highlight_text highlight_text = new highlight_text();
            for (int yy = 0; yy < Stored_Sentence.length; yy++) {
                Stored_Word = Stored_Sentence[yy].toLowerCase().split("\\s+");
            }

            //Highlighting wrong word
            if (Collections.binarySearch(Arrays.asList(Stored_Word),Given_Word)< 0) {
                error_counter++;
                highlight_text.highlight(text,Given_Word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        Spell_Check cp = new Spell_Check();
        cp.setVisible(true);
    }
}