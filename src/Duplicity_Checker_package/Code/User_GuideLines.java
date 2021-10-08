package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_GuideLines extends Panel_BackButton_Template {

    ButtonSound sound_button = new ButtonSound();

    User_GuideLines() {
        super.frame();
        super.setContainer();
        super.setPanel();
        super.BackButton();
        design();
    }

    public void design(){

        JTextArea Heading_area = new JTextArea();
        Heading_area.setEditable(false);
        Heading_area.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,30));
        Heading_area.setBounds(50,55,535,40);
        Heading_area.setBackground(Color.WHITE);
        Heading_area.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.jpanel.add(Heading_area);
        Heading_area.append("                    User Guidelines");

        JTextArea guidelines_area = new JTextArea();
        guidelines_area.setEditable(false);
        guidelines_area.setFont(new Font("Calibari",Font.BOLD,16));
        guidelines_area.setBounds(50,98,535,500);
        guidelines_area.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.jpanel.add(guidelines_area);
        guidelines_area.append("\n"+"    **  You can select only docx or pdf files in 'Duplicity Check' option."+"\n"+"         In other options you can read only Docx files."+"\n\n");
        guidelines_area.append("    **  This application is only applicable for English language."+"\n\n");
        guidelines_area.append("    **  In Duplicity check User Interface, left textarea is for comparable"+"\n"+"         files and right textarea is for comparing files. Which is/are "+"\n"+
                    "         compared with leftside textarea's text."+"\n\n");
        guidelines_area.append("    **  If you want to select files freewill then click 'Choose Randomly' "+"\n"+"         option in 'Duplicity Check' Button."+"\n\n");
        guidelines_area.append("    **  If you want to select a folder then click 'Select Folder' option in "+"\n"+"         'Duplicity Check' Button."+"\n\n");
        guidelines_area.append("    **  In 'Select folder' option, When you are selecting a folder from"+"\n"+"         your PCs Directory you have to be alert that, this directory"+"\n"+
                    "         contain only one folder. You have to be careful about this."+"\n\n");
        guidelines_area.append("    **  In 'Choose Randomly' option, you can choose one or multiple  "+"\n"+"         file by holding out ctrl button. If you want to select all files then"+"\n"+
                    "         you have to press ctrl + a ."+"\n\n");

    back_button.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });
    }
}