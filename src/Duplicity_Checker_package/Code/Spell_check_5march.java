package Duplicity_Checker_package.Code;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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

public class Spell_check_5march extends Panel_BackButton_Template {

    private JButton file_read_button , file_check_button ;
    private JTextArea text,result_textarea;
    private JScrollPane read_scrolltext1 , result_scrolltext;
    private ImageIcon file_read_img , file_check_img , save_button_img;

    char[] Alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char[] chars;
    char alphabet;
    int i,kk;
    String Given_Word;
  //  ArrayList<String> input_word;
    String FileExtension;
    String extension;
    File file,F1;
    String save_filename;
    JFileChooser savefile;
    ButtonSound sound_button = new ButtonSound();

    Spell_check_5march()  throws IOException {

        App_Icon();
        super.frame();
        super.setTitle("Spell Checker");
        super.setContainer();
        super.setPanel();
        super.BackButton();

        file_read_img = new ImageIcon (getClass().getResource("Picture//ReadFile2.png"));
        JButton file_read_button = new JButton(file_read_img);
        file_read_button.setBackground(new Color(54, 25, 189, 255));
        file_read_button.setBorder(null);
        file_read_button.setBounds(40,19,file_read_img.getIconWidth(),file_read_img.getIconHeight());
        background.add(file_read_button);

        file_check_img = new ImageIcon (getClass().getResource("Picture//Check.png"));
        JButton file_check_button = new JButton(file_check_img);
        file_check_button.setBackground(new Color(141, 75, 147, 255));
        file_check_button.setBorder(null);
        file_check_button.setBounds(482,295,file_check_img.getIconWidth(),file_check_img.getIconHeight());
        background.add(file_check_button);

        save_button_img = new ImageIcon (getClass().getResource("Picture//Save_button.png"));
        JButton save_button = new JButton(save_button_img);
        save_button.setBackground(new Color(160, 88, 167, 255));
        save_button.setBorder(null);
        save_button.setBounds(485,575,save_button_img.getIconWidth(),save_button_img.getIconHeight());
        background.add(save_button);

        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        text.setFont(new Font("Times new Roman",Font.BOLD,16));
        background.add(text);

        read_scrolltext1 = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        read_scrolltext1.setBounds(40,53,540,230);
        background.add(read_scrolltext1);

        result_textarea = new JTextArea();
        result_textarea.setLineWrap(true);
        result_textarea.setWrapStyleWord(true);
        result_textarea.setEditable(false);
        result_textarea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        result_textarea.setFont(new Font("Times new Roman",Font.BOLD,16));
        background.add(result_textarea);

        result_scrolltext = new JScrollPane(result_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        result_scrolltext.setBounds(40,335,540,230);
        background.add(result_scrolltext);

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

                    save_filename = JOptionPane.showInputDialog("Write New File Name");
                    savefile = new JFileChooser();
                    savefile.setDialogTitle("Choose Directory");
                    savefile.setSelectedFile(new File(save_filename));
                    //      BufferedWriter writer;
                    int sf = savefile.showSaveDialog(null);
                    if (sf == JFileChooser.APPROVE_OPTION) {
                        sound_button.playsound();

                        try {
                            Object[] choices = {"Docx", "Pdf", "Cancel"};
                            Object defaultChoice = choices[0];
                            int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                            if (n == JOptionPane.YES_OPTION) {
                                sound_button.playsound();
                                save_as_docx();
                            } else if (n == JOptionPane.NO_OPTION) {
                                sound_button.playsound();
                                save_as_pdf();

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

        file_read_button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent ea){
                try {

                    sound_button.playsound();
                    JFileChooser chooser = new JFileChooser ();
                    chooser.showOpenDialog(null);
                    file = chooser.getSelectedFile();
                    checking_extension();
                }
                catch (Exception e)
                {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
            }
        });

        file_check_button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent ea) {

                try {
                    sound_button.playsound();
                    if (result_textarea.getText().length() > 0) {
                        result_textarea.setText("");
                    }
                    String input_string = text.getText();
                    String[] input_sentence = input_string.split("[\\s]*[-.,!?)\"][\\s]*");
             //       input_word = new ArrayList<>();
                    for (i = 0; i < input_sentence.length; i++) {
                        String[] input_word = input_sentence[i].toLowerCase().split("\\s+");
                       /* for (String s : tmp) {
                            input_word.add(s);
                        }*/
                        //   }
                       // repeat:
                        for ( kk = 0; kk < input_word.length; kk++) {
                            char[] chars = input_word[kk].toLowerCase().toCharArray();

                            // char[] Alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                            for (int k = 0; k < chars.length; k++) {
                                if (k == 0) {
                                    for (int aa = 0; aa < Alphabet.length; aa++) {
                                        if (chars[0] == Alphabet[aa]) {
                                            alphabet = Alphabet[aa];
                                            F1 = new File("Words\\" + Alphabet[aa] + ".docx");
                                            Given_Word = input_word[kk];
                                            check_Correct_word();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    text.setEditable(true);
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

        //    Arrays.sort(Stored_Word);

            if (Collections.binarySearch(Arrays.asList(Stored_Word),Given_Word)< 0) {
//                result_textarea.append(Given_Word+" Misspell in Line no "+(i+1)+" and word no "+(kk+1)+"\n");
                result_textarea.append(Given_Word+" Misspell in Line no "+(i)+" and word no "+(kk+1)+"\n");

                for(int p=0;p<chars.length/2;p++){

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void checking_extension() throws IOException {
        FileExtension = file.getName();
        extension = "";
        int i = FileExtension.lastIndexOf('.');
        if (i >= 0) {
            extension = FileExtension.substring(i+1);
        }
        if(extension.equals("docx")){
            XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
            XWPFWordExtractor extract = new XWPFWordExtractor(docx);
            text.setText(extract.getText());
        }

        else{
            Object[] options = {"Ok"};
            int n = JOptionPane.showOptionDialog(null, "Choose docx file only", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
            if (n == JOptionPane.OK_OPTION) {
                sound_button.playsound();
            }
        }
    }

    public void save_as_docx(){
        try {
            XWPFDocument docx = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(savefile.getSelectedFile()+".docx");
            XWPFParagraph n = docx.createParagraph();
            XWPFRun run = n.createRun();
            run.setText(result_textarea.getText());
            docx.write(out);
            out.close();
            JOptionPane.showMessageDialog(null,"File saved in "+savefile.getSelectedFile()+".docx","File saved",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void save_as_pdf(){
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savefile.getSelectedFile() + ".pdf"));
            document.open();
            document.add(new Paragraph(result_textarea.getText()));
            document.close();
            writer.close();
            JOptionPane.showMessageDialog(null, "File saved in "+savefile.getSelectedFile() + ".pdf", "File saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        Spell_check_5march cp = new Spell_check_5march();
        cp.setVisible(true);
    }
}
