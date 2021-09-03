package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aboutus extends Panel_BackButton_Template {

    ButtonSound sound_button = new ButtonSound();

    Aboutus()  {
        super.frame();
        super.setContainer();
        super.setPanel();
        super.BackButton();
        design();
        }

    public void design(){

        JTextArea Developers = new JTextArea();
        Developers.setEditable(false);
        Developers.setFont(new Font("Times new Roman", Font.CENTER_BASELINE, 30));
        Developers.setBounds(45, 58, 535, 40);
        Developers.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.background.add(Developers);
        Developers.append("                        Developers");

        JTextArea Developers_Details = new JTextArea();
        Developers_Details.setEditable(false);
        Developers_Details.setFont(new Font("Times new Roman", Font.BOLD, 17));
        Developers_Details.setBounds(45, 100, 535, 300);
        Developers_Details.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.background.add(Developers_Details);
        Developers_Details.append("\n" + "    Name  : Md. Armanur Rashid" + "\n" + "    Email : armanurrashid105086@gmail.com" + "\n\n");
        Developers_Details.append("    Name  : Sourav Debnath" + "\n" + "    Email : souravdebnath97@gmail.com" + "\n\n");
        Developers_Details.append("    Name  : Sourav Barman" + "\n" + "    Email : sourav.iit.nstu@gmail.com" + "\n\n");
        Developers_Details.append("    Students of -" + "\n" + "    IIT 2nd Batch" + "\n"+"    Noakhali Science and Technology University"+"\n");

        JTextArea Supervisor = new JTextArea();
        Supervisor.setEditable(false);
        Supervisor.setFont(new Font("Times new Roman", Font.CENTER_BASELINE, 30));
        Supervisor.setBounds(45, 402, 535, 40);
        Supervisor.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.background.add(Supervisor);
        Supervisor.append("                         Supervisor");

        JTextArea Supervisor_details = new JTextArea();
        Supervisor_details.setEditable(false);
        Supervisor_details.setFont(new Font("Times new Roman", Font.BOLD, 17));
        Supervisor_details.setBounds(45, 444, 535, 130);
        Supervisor_details.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        super.background.add(Supervisor_details);
        Supervisor_details.append("\n" + "    Name : Falguni Roy" + "\n" + "    Assistant Professor, IIT" + "\n" + "    Noakhali Science and Technology University" + "\n" + "    Email : falguniroy.iit@gmail.com" + "\n\n");

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