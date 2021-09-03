package Duplicity_Checker_package.Code;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Spell_checker_18feb extends JFrame {

    private Container container;
    private JButton file_read_button, file_check_button, save_button;
    public JButton back_button;
    private JTextArea text,text1, result_textarea;
    private JScrollPane read_scrolltext1, result_scrolltext;
    private ImageIcon file_read_img, file_check_img, back_button_img;

    char alphabet;
    String Given_Word;
    ArrayList<String> input_word;
    String save_filename;
    int j;
    JFileChooser savefile;
    ButtonSound bs = new ButtonSound();
    Back_Button B = new Back_Button();
    File file;
    File F1;
    ButtonSound sound_button = new ButtonSound();
    Spell_checker_18feb() throws IOException {
        App_Icon();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(350, 60, 650, 650);
        this.setTitle("SPELL_FROM_WORD_FILE");
        this.setResizable(false);
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(180, 210, 195, 255));

        file_read_img = new ImageIcon(getClass().getResource("Picture//ReadFile2.png"));
        JButton file_read_button = new JButton(file_read_img);
        file_read_button.setBackground(new Color(180, 210, 195, 255));
        file_read_button.setBorder(null);
        file_read_button.setBounds(50, 19, file_read_img.getIconWidth(), file_read_img.getIconHeight());
        container.add(file_read_button);

        file_check_img = new ImageIcon(getClass().getResource("Picture//Check.png"));
        JButton file_check_button = new JButton(file_check_img);
        file_check_button.setBackground(new Color(180, 210, 195, 255));
        file_check_button.setBorder(null);
        file_check_button.setBounds(487, 295, file_check_img.getIconWidth(), file_check_img.getIconHeight());
        container.add(file_check_button);

        back_button_img = new ImageIcon(getClass().getResource("Picture//BackButton.png"));
        back_button = new JButton(back_button_img);
        back_button.setBackground(new Color(180, 210, 195, 255));
        back_button.setBorder(null);
        back_button.setBounds(545, 10, back_button_img.getIconWidth(), back_button_img.getIconHeight());
        container.add(back_button);

        save_button = new JButton("save");
        save_button.setBackground(new Color(26, 49, 37, 255));
        save_button.setBorder(null);
        save_button.setBounds(387, 295, file_check_img.getIconWidth(), file_check_img.getIconHeight());
        container.add(save_button);

        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFont(new Font("Times new Roman", Font.BOLD, 16));
        container.add(text);

        read_scrolltext1 = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        read_scrolltext1.setBounds(50, 53, 535, 230);
        container.add(read_scrolltext1);

        result_textarea = new JTextArea();
        result_textarea.setLineWrap(true);
        result_textarea.setWrapStyleWord(true);
        result_textarea.setEditable(false);
        result_textarea.setFont(new Font("Times new Roman", Font.BOLD, 16));
        container.add(result_textarea);

        result_scrolltext = new JScrollPane(result_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        result_scrolltext.setBounds(50, 335, 535, 230);
        container.add(result_scrolltext);


        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                save_filename = JOptionPane.showInputDialog("Write New File Name");
                savefile = new JFileChooser();
                savefile.setDialogTitle("Choose Directory");
                savefile.setSelectedFile(new File(save_filename));
                //      BufferedWriter writer;
                int sf = savefile.showSaveDialog(null);
                if (sf == JFileChooser.APPROVE_OPTION) {

                    try {
                        Object[] choices = {"Docx", "Pdf", "Cancel"};
                        Object defaultChoice = choices[0];
                        int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                        if (n == JOptionPane.YES_OPTION) {
                            //bs.playsound();
                            //   B.Sound_Button();
                            save_as_docx();
                        } else if (n == JOptionPane.NO_OPTION) {
                            //bs.playsound();
                            //    B.Sound_Button();


                        } else {
                            //bs.playsound();
                            //    B.Sound_Button();
                        }
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null, ee);
                    }

                }


            }
        });

        back_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {
                //  bs.playsound();
                //  B.Sound_Button();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });

        file_read_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {
                try {

                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    file = chooser.getSelectedFile();
                    XWPFDocument Input_Docx = new XWPFDocument(new FileInputStream(file));
                    XWPFWordExtractor extract = new XWPFWordExtractor(Input_Docx);
                    text.setText(extract.getText());

                } catch (Exception e) {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        //bs.playsound();
                        //  B.Sound_Button();
                    }
                }
            }
        });

        file_check_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {

                try {
                    String input_string = text.getText();
                    String[] input_sentence = input_string.split("[\\s]*[-.,!?)\"][\\s]*");
                    input_word = new ArrayList<>();
                    for (int i = 0; i < input_sentence.length; i++) {
                        String[] tmp = input_sentence[i].toLowerCase().split("\\s+");
                        for (String s : tmp) {
                            input_word.add(s);
                        }
                    }

                    for (int kk = 0; kk < input_word.size(); kk++) {
                        char[] chars = input_word.get(kk).toLowerCase().toCharArray();

                        char[] Alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                        for (int k = 0; k < chars.length; k++) {
                            if (k == 0) {
                                for (int aa=0 ; aa <Alphabet.length;aa++){
                                    if (chars[0] == Alphabet[aa]) {
                                        alphabet=Alphabet[aa];
                                        F1 = new File("Words\\"+Alphabet[aa]+".docx");
                                        Given_Word= input_word.get(kk);
                                        check_Correct_word();
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception e){
                }
            }
        });
    }

    public void check_Correct_word(){
        try{
            XWPFDocument Stored_Docx = new XWPFDocument(new FileInputStream(F1));
            XWPFWordExtractor Stored_Text = new XWPFWordExtractor(Stored_Docx);
            String Stored_String = Stored_Text.getText();
            String[] Stored_Sentence = Stored_String.split("[\\s]*[.][\\s]*");
            String[] Stored_Word = null;
            for (int yy = 0; yy < Stored_Sentence.length; yy++) {
                Stored_Word = Stored_Sentence[yy].toLowerCase().split("\\s+");
            }

            Arrays.sort(Stored_Word);

            if (Collections.binarySearch(Arrays.asList(Stored_Word),Given_Word)< 0) {
                result_textarea.append(Given_Word+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save_as_docx() {

        try {
            XWPFDocument docx = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(savefile.getSelectedFile() + ".docx");
            XWPFParagraph n = docx.createParagraph();
            XWPFRun run = n.createRun();
            run.setText(result_textarea.getText());
            docx.write(out);
            out.close();

            JOptionPane.showMessageDialog(null, "File saved in " + savefile.getSelectedFile() + ".docx", "File saved", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }
    public static void main(String[] args) throws IOException {
        Spell_checker_18feb LF = new Spell_checker_18feb();
        LF.setVisible(true);
    }
}
