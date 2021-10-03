package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;

public class Basic_Frame_Duplicity extends Frame_Container_Template{

    public JTextArea comparable_file_textarea, comparing_file_textarea, result_textarea;
    public JButton file_read_button1, file_read_button2, file_check_button, back_button,save_button;
    public JScrollPane comparable_file_scrolltext, comparing_file_scrolltext, scrolltext3;
    public ImageIcon file_comparable_img, file_comparing_img, check_img, BackButton_img,save_button_img;
    public JButton button[] = new JButton[5];
    public JPanel jpanel;

    Basic_Frame_Duplicity(){
        super.frame();
        super.setContainer();
    }

   public void setPanel() {
       jpanel = new JPanel();
       jpanel.setBounds(5, 6, 623, 630);
       jpanel.setLayout(null);
       container.add(jpanel);
       jpanel.setBackground(new Color(28, 73, 102));
   }

    public void userinterface() {
        comparable_file_textarea = new JTextArea();
        comparable_file_textarea.setLineWrap(true);
        comparable_file_textarea.setWrapStyleWord(true);
        comparable_file_textarea.setEditable(false);
        comparable_file_textarea.setFont(new Font("Times new Roman", Font.BOLD, 15));
        comparable_file_textarea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        jpanel.add(comparable_file_textarea);

        comparable_file_scrolltext = new JScrollPane(comparable_file_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        comparable_file_scrolltext.setBounds(50, 52, 250, 300);
        jpanel.add(comparable_file_scrolltext);

        comparing_file_textarea = new JTextArea();
        comparing_file_textarea.setLineWrap(true);
        comparing_file_textarea.setWrapStyleWord(true);
        comparing_file_textarea.setEditable(false);
        comparing_file_textarea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        comparing_file_textarea.setFont(new Font("Times new Roman", Font.BOLD, 15));
        jpanel.add(comparing_file_textarea);

        comparing_file_scrolltext = new JScrollPane(comparing_file_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        comparing_file_scrolltext.setBounds(340, 52, 250, 300);
        jpanel.add(comparing_file_scrolltext);

        result_textarea = new JTextArea();
        result_textarea.setLineWrap(true);
        result_textarea.setWrapStyleWord(true);
        result_textarea.setEditable(false);
        result_textarea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        result_textarea.setFont(new Font("Times new Roman", Font.BOLD, 15));
        jpanel.add(result_textarea);

        scrolltext3 = new JScrollPane(result_textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrolltext3.setBounds(50, 394, 540, 200);
        jpanel.add(scrolltext3);
    }

    public JButton[] buttons() {
        file_comparable_img = new ImageIcon(getClass().getResource("Picture//comparable1.png"));
        file_read_button1 = new JButton(file_comparable_img);
        file_read_button1.setBackground(new Color(28, 73, 102));
        file_read_button1.setBorder(null);
        file_read_button1.setBounds(50, 15, file_comparable_img.getIconWidth(), file_comparable_img.getIconHeight());
        jpanel.add(file_read_button1);

        file_comparing_img = new ImageIcon(getClass().getResource("Picture//compare.png"));
        file_read_button2 = new JButton(file_comparing_img);
        file_read_button2.setBackground(new Color(28, 73, 102));
        file_read_button2.setBorder(null);
        file_read_button2.setBounds(340, 15, file_comparing_img.getIconWidth(), file_comparing_img.getIconHeight());
        jpanel.add(file_read_button2);

        check_img = new ImageIcon(getClass().getResource("Picture//Check.png"));
        file_check_button = new JButton(check_img);
        file_check_button.setBackground(new Color(28, 73, 102));
        file_check_button.setBorder(null);
        file_check_button.setBounds(488, 359, check_img.getIconWidth(), check_img.getIconHeight());
        jpanel.add(file_check_button);

        BackButton_img = new ImageIcon(getClass().getResource("Picture//BackButton.png"));
        back_button = new JButton(BackButton_img);
        back_button.setBackground(new Color(28, 73, 102));
        back_button.setBorder(null);
        back_button.setBounds(550, 8, BackButton_img.getIconWidth(), BackButton_img.getIconHeight());
        jpanel.add(back_button);

        save_button_img = new ImageIcon (getClass().getResource("Picture//Save_button.png"));
        save_button = new JButton(save_button_img);
        save_button.setBackground(new Color(28, 73, 102));
        save_button.setBorder(null);
        save_button.setBounds(493,598,save_button_img.getIconWidth(),save_button_img.getIconHeight());
        jpanel.add(save_button);

        button[0]=file_read_button1;
        button[1]=file_read_button2;
        button[2]=file_check_button;
        button[3]=back_button;
        button[4]=save_button;

        return new JButton[] {file_read_button1,file_read_button2,file_check_button, back_button,save_button};
    }
}