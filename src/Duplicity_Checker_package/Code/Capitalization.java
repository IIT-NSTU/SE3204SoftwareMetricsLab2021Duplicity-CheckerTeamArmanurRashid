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

public class Capitalization extends Panel_BackButton_Template {

    private JButton file_read_button , file_check_button ;
    private JTextArea text,result_textarea;
    private JScrollPane read_scrolltext1 , result_scrolltext;
    private ImageIcon file_read_img , file_check_img , save_button_img;

    String FileExtension;
    String extension;
    File file;
    String save_filename;
    JFileChooser savefile;
    ButtonSound sound_button = new ButtonSound();

    Capitalization()  throws IOException {

        App_Icon();
        super.frame();
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

                sound_button.playsound();
                String str = text.getText()+" ";

                if (str.length() == 0) {
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "Nothing to Check", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
                capitalize(str);
                String s = capitalize(str);
                result_textarea.setText(s);
                int ss = result_textarea.getText().toCharArray().length;
                System.out.println("final : "+ss);
            }
        });
    }

    private String capitalize(String str) {
        if (str != null && str.length() != 0) {
            return getCapitalizeString(str);
        }
        else {
            return str;
        }
    }

    private String getCapitalizeString(String str) {

        char[] new_chars = str.replaceAll("[,.!?;:]", "$0").toCharArray();
//      for(int i = 0, j = 0 ; i<new_chars.length-1;i++){
//           if(!((new_chars[i]==' ') && ( new_chars[i+1]==' ')))
//           {
//               new_chars[j] = new_chars[i];
//            j++;
//           }
//           else if(i==new_chars.length-1){
//               new_chars[j]=new_chars[i];
//           }
//       }
        return new String(handleCharsArray(new_chars));
    }

    private char[] handleCharsArray(char[] chars) {
        System.out.println("chars  "+chars.length);
        chars[0] = Character.toUpperCase(chars[0]);

        for (int i = 0; i < chars.length; i++) {
            if(i==chars.length-1){
                chars[i] = Character.toUpperCase(chars[i]);
            }
            else {
                for(i=0;i<chars.length-1;i++){
                    if ((chars[i] == '!') || (chars[i] == '?') || (chars[i] == '.')|| (chars[i] == ':'))
                    {
                        if ((i < chars.length - 5) &&(chars[i + 1] == ' ')&&(chars[i+2]==' ')&&(chars[i+3]==' ')&&(chars[i+4]==' ')&&(chars[i+5]!='\n'&&chars[i+5]!=' '))
                        {
                            i+= 5;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }
                        else if ((i < chars.length - 4) &&(chars[i + 1] == ' ')&&(chars[i+2]==' ')&&(chars[i+3]==' ')&&(chars[i+4]!='\n'&&chars[i+4]!=' '))
                        {
                            i+= 4;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }

                        else if ((i < chars.length - 3) &&(chars[i + 1] == ' ')&&(chars[i+2]==' ')&&(chars[i+3]!='\n'&&chars[i+3]!=' '))
                        {
                            i+= 3;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }
                        else if ((i < chars.length - 2) &&(chars[i + 1] == ' ')&&(chars[i+2]!='\n' && chars[i+2]!=' '))
                        {
                            i+= 2;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }

                        else if((i<chars.length-2)&&(chars[i+1]=='\n')&&(chars[i+2]!=' ')){
                            i+=2;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }

                        else if((i<chars.length-3)&&(chars[i+1]=='\n')&&(chars[i+2]==' ') &&(chars[i+3]!=' ')){
                            i+=3;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }
                        else if((i<chars.length-4)&&(chars[i+1]=='\n')&&(chars[i+2]==' ') &&(chars[i+3]==' ') && (chars[i+4]!=' ')){
                            i+=4;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }

                        else if ((i < chars.length - 5) && (chars[i+1] == '\n') && (chars[i+2]==' ')&& (chars[i+3]==' ')&& (chars[i+4]==' ') && (chars[i+5]!=' ')){
                            i+=5;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }
                        else if ((i < chars.length - 6) && (chars[i+1] == '\n') && (chars[i+2]==' ')&& (chars[i+3]==' ')&& (chars[i+4]==' ') && (chars[i+5]==' ') && (chars[i+6]!=' ')){
                            i+=6;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }

                        else if ((i < chars.length - 2) && (chars[i+1] == ' ') && (chars[i+2]=='\n')){
                            i+=3;
                            chars[i] = Character.toUpperCase(chars[i]);
                        }



                        else {
                            chars[i + 1] = Character.toUpperCase(chars[i + 1]);
                        }
                    }

                    else if ((chars[i] == ' ') && (chars[i + 1] == 'i') && (chars[i + 2] == ' '))
                    {
                        chars[i + 1] = Character.toUpperCase(chars[i + 1]);
                    }
                    else if (((chars[i] == 'm') && (chars[i + 1] == 'r') && (chars[i + 2] == '.')) || ((chars[i] == 'd') && (chars[i + 1] == 'r') && (chars[i + 2] == '.')) ||  ((chars[i] == 'm') && (chars[i + 1] == 'r') && (chars[i + 2] == 's')&& (chars[i + 3] == '.')) )
                    {
                        chars[i] = Character.toUpperCase(chars[i]);
                    }
                }
            }

        }
        return chars;
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
        Capitalization cp = new Capitalization();
        cp.setVisible(true);
    }
}