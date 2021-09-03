package Duplicity_Checker_package.Code;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Spell_Check extends Panel_BackButton_Template{

    private ImageIcon file_read_img,save_button_img;
    private JTextArea text;
    private JScrollPane scrolltext ;
    private JButton save_button,file_read_button;

    ButtonSound sound_button = new ButtonSound();

    String FileExtension;
    String extension;
    File file;
    String save_filename;
    JFileChooser savefile;
    String lowercase_text;
    Spell_Check() throws IOException {

        App_Icon();
        super.frame();
        super.setContainer();
        super.setPanel();
        super.BackButton();
        button();
        design();
    }

    public void button() {
        file_read_img = new ImageIcon(getClass().getResource("Picture//ReadFile2.png"));
        file_read_button = new JButton(file_read_img);
        file_read_button.setBackground(new Color(54, 25, 189, 255));
        file_read_button.setBorder(null);
        file_read_button.setBounds(45, 20, file_read_img.getIconWidth(), file_read_img.getIconHeight());
        background.add(file_read_button);

        save_button_img = new ImageIcon(getClass().getResource("Picture//Save_button.png"));
        save_button = new JButton(save_button_img);
        save_button.setBackground(new Color(160, 88, 167, 255));
        save_button.setBorder(null);
        save_button.setBounds(487, 520, save_button_img.getIconWidth(), save_button_img.getIconHeight());
        background.add(save_button);
    }

    public void design(){
        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        background.add(text);

        scrolltext = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrolltext.setBounds(40,60,540,450);
        background.add(scrolltext);

        SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        SpellChecker.registerDictionaries(null, null);
        SpellChecker.register(text);
    //    SpellChecker.register(lowercase_text);

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
                if (text.getText().length() > 0) {
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

                sound_button.playsound();

                JFileChooser chooser = new JFileChooser();
                int returnValue=chooser.showOpenDialog(null);
                file = chooser.getSelectedFile();
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    try {
                        checking_extension();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else{
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {

                        sound_button.playsound();
                    }
                }
                SpellChecker.register(text);
            }
        });
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
     //    text.setText(extract.getText().toLowerCase());
         text.setText(extract.getText());
           // lowercase_text = extract.getText().toLowerCase();
        }

        else{
         Object[] options = {"Ok"};
         int n = JOptionPane.showOptionDialog(null, "Choose docx or pdf file only", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
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
              run.setText(text.getText());
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
            document.add(new Paragraph(text.getText()));
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
        Spell_Check sp = new Spell_Check();
        sp.setVisible(true);
    }
}