package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Capitalization extends Panel_BackButton_Template {

    private JButton file_read_button, file_check_button;
    private JTextArea text, result_textarea;
    private JScrollPane read_scrolltext1, result_scrolltext;
    private ImageIcon file_read_img, file_check_img, save_button_img;

    String FileExtension;
    String extension;
    File file;
    String save_filename;
    JFileChooser savefile;
    ButtonSound sound_button = new ButtonSound();

    Capitalization() throws IOException {

        App_Icon();
        super.frame();
        super.setContainer();
        super.setTitle("Capitalization");
        super.setPanel();
        super.BackButton();

        file_read_img = new ImageIcon(getClass().getResource("Picture//ReadFile2.png"));
        JButton file_read_button = new JButton(file_read_img);
        file_read_button.setBackground(new Color(28, 73, 102));
        file_read_button.setBorder(null);
        file_read_button.setBounds(40, 19, file_read_img.getIconWidth(), file_read_img.getIconHeight());
        jpanel.add(file_read_button);

        file_check_img = new ImageIcon(getClass().getResource("Picture//Check.png"));
        JButton file_check_button = new JButton(file_check_img);
        file_check_button.setBackground(new Color(28, 73, 102));
        file_check_button.setBorder(null);
        file_check_button.setBounds(482, 295, file_check_img.getIconWidth(), file_check_img.getIconHeight());
        jpanel.add(file_check_button);

        save_button_img = new ImageIcon(getClass().getResource("Picture//Save_button.png"));
        JButton save_button = new JButton(save_button_img);
        save_button.setBackground(new Color(28, 73, 102));
        save_button.setBorder(null);
        save_button.setBounds(485, 575, save_button_img.getIconWidth(), save_button_img.getIconHeight());
        jpanel.add(save_button);

        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 255)));
        text.setFont(new Font("Times new Roman", Font.BOLD, 16));
        jpanel.add(text);

        read_scrolltext1 = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        read_scrolltext1.setBounds(40, 53, 540, 230);
        jpanel.add(read_scrolltext1);

        result_textarea = new JTextArea();
        result_textarea.setLineWrap(true);
        result_textarea.setWrapStyleWord(true);
        result_textarea.setEditable(false);
        result_textarea.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 255)));
        result_textarea.setFont(new Font("Times new Roman", Font.BOLD, 16));
        jpanel.add(result_textarea);

        result_scrolltext = new JScrollPane(result_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        result_scrolltext.setBounds(40, 335, 540, 230);
        jpanel.add(result_scrolltext);

        back_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {
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
                            if (n == JOptionPane.YES_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_docx(savefile.getSelectedFile(),result_textarea.getText());
                            } else if (n == JOptionPane.NO_OPTION) {
                                sound_button.playsound();
                                save_file save_file = new save_file();
                                save_file.save_as_pdf(savefile.getSelectedFile(),result_textarea.getText());

                            } else {
                                sound_button.playsound();
                            }
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, ee);
                        }
                    } else {
                        sound_button.playsound();
                    }
                } else {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "There is no text to save", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
            }
        });

        file_read_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {
                file_read file_read = new file_read();
                file_read.fileRead(sound_button,file,text);
            }
        });

        file_check_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {

                sound_button.playsound();
                String str = text.getText() + " ";

                // Check whether the file is empty or not
                if (str.length() == 0) {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "Nothing to Check", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }

                String s = capitalize(str);

                // Showing the capitalized string in text area
                result_textarea.setText(s);

            }
        });
    }

    private String capitalize(String str) {
        if (str != null && str.length() != 0) {
            return getCapitalizeString(str);
        } else {
            return str;
        }
    }

    private String getCapitalizeString(String str) {
        // At first we will make the string as lower case
        char[] new_chars = str.replaceAll("[,.!?;:]", "$0").toLowerCase().toCharArray();

        //Passing the lower case string to a function for proper capitalization
        return new String(handleCharsArray(new_chars));
    }

    private char[] handleCharsArray(char[] chars) {

        //Making first character as uppercase
        chars[0] = Character.toUpperCase(chars[0]);

        //Iterating through until the second last character
        for (int i = 0; i < chars.length - 1; i++) {

            //Check if the character is (!) or (?) or (.) or (:) because after these characters any character must be in uppercase
            if ((chars[i] == '!') || (chars[i] == '?') || (chars[i] == '.') || (chars[i] == ':')) {

                //After 4 new lines of these punctuations the next letter will be in uppercase
                if ((i < chars.length - 5) && (chars[i + 1] == '\n') && (chars[i + 2] == '\n') && (chars[i + 3] == '\n') && (chars[i + 4] == '\n') && (chars[i + 5] != ' ' && chars[i + 5] != '\n')) {
                    i += 5;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 3 new lines of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 4) && (chars[i + 1] == '\n') && (chars[i + 2] == '\n') && (chars[i + 3] == '\n') && (chars[i + 4] != ' ' && chars[i + 4] != '\n')) {
                    i += 4;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 2 new lines of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 3) && (chars[i + 1] == '\n') && (chars[i + 2] == '\n') && (chars[i + 3] != ' ' && chars[i + 3] != '\n')) {
                    i += 3;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 4 spaces of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 5) && (chars[i + 1] == ' ') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] == ' ') && (chars[i + 5] != '\n' && chars[i + 5] != ' ')) {
                    i += 5;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 3 spaces of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 4) && (chars[i + 1] == ' ') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] != '\n' && chars[i + 4] != ' ')) {
                    i += 4;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 2 spaces of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 3) && (chars[i + 1] == ' ') && (chars[i + 2] == ' ') && (chars[i + 3] != '\n' && chars[i + 3] != ' ')) {
                    i += 3;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After 1 space of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 2) && (chars[i + 1] == ' ') && (chars[i + 2] != '\n' && chars[i + 2] != ' ')) {
                    i += 2;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //After a new line of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 2) && (chars[i + 1] == '\n') && (chars[i + 2] != ' ')) {
                    i += 2;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //A single space after a new line of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 3) && (chars[i + 1] == '\n') && (chars[i + 2] == ' ') && (chars[i + 3] != ' ')) {
                    i += 3;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //two spaces after a new line of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 4) && (chars[i + 1] == '\n') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] != ' ')) {
                    i += 4;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //three spaces after a new line of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 5) && (chars[i + 1] == '\n') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] == ' ') && (chars[i + 5] != ' ')) {
                    i += 5;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //four spaces after a new line of these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 6) && (chars[i + 1] == '\n') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] == ' ') && (chars[i + 5] == ' ') && (chars[i + 6] != ' ')) {
                    i += 6;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //A space and a new line after these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 3) && (chars[i + 1] == ' ') && (chars[i + 2] == '\n') && (chars[i + 3] != ' ')) {
                    i += 3;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

//              //Two spaces and a new line after these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 4) && (chars[i + 1] == ' ') && (chars[i + 2] == ' ') && (chars[i + 3] == '\n') && (chars[i + 4] != ' ')) {
                    i += 4;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //Three spaces and a new line after these punctuations the next letter will be in uppercase
                else if ((i < chars.length - 5) && (chars[i + 1] == ' ') && (chars[i + 2] == ' ') && (chars[i + 3] == ' ') && (chars[i + 4] == '\n') && (chars[i + 5] != ' ')) {
                    i += 5;
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                //after these punctuations the next letter will be in uppercase
                else {
                    chars[i + 1] = Character.toUpperCase(chars[i + 1]);
                }
            }

            // A single character 'i' will be always in upper case
            else if ((chars[i] == ' ') && (chars[i + 1] == 'i') && (chars[i + 2] == ' ')) {
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);
            }

            // After "mr.","dr.","mrs." the next character will be always in upper case
            else if (((chars[i] == 'm') && (chars[i + 1] == 'r') && (chars[i + 2] == '.')) || ((chars[i] == 'd') && (chars[i + 1] == 'r') && (chars[i + 2] == '.')) || ((chars[i] == 'm') && (chars[i + 1] == 'r') && (chars[i + 2] == 's') && (chars[i + 3] == '.'))) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return chars;
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        Capitalization cp = new Capitalization();
        cp.setVisible(true);
    }
}