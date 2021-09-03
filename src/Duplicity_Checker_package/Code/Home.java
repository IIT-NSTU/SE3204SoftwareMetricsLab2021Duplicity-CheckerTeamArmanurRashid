package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Home extends JFrame {
    public static int volume_icon_variable =1;
    int value_of_text_file;
    private Container container;
    private ImageIcon duplicity_img, capitalization_img, spell_img, aboutus_img, guideline_img, volumeON_img,volumeOFF_img;

    ButtonSound sound_button = new ButtonSound();

    public Home() throws IOException {

        App_Icon();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(350, 25, 650, 680);
        this.setTitle("Duplicity Checker");
        this.setResizable(false);

        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(255, 255, 255, 255));

        JLabel image = new JLabel(new ImageIcon(getClass().getResource("Picture//home_img.jpg")));
        image.setBounds(135, 0, 400, 400);
        container.add(image);

        volumeON_img = new ImageIcon(getClass().getResource("Picture//off1.png"));
        JButton volume_on_button = new JButton(volumeON_img);
        volume_on_button.setBackground(new Color(255, 255, 255, 255));
        volume_on_button.setBorder(null);
        volume_on_button.setBounds(565, 20, volumeON_img.getIconWidth(), volumeON_img.getIconHeight());
        container.add(volume_on_button);

        volumeOFF_img = new ImageIcon(getClass().getResource("Picture//on1.png"));
        JButton volume_off_button = new JButton(volumeOFF_img);
        volume_off_button.setBackground(new Color(255, 255, 255, 255));
        volume_off_button.setBorder(null);
        volume_off_button.setBounds(585, 28, volumeOFF_img.getIconWidth(), volumeOFF_img.getIconHeight());

        Font f = new Font("Calibri", Font.BOLD, 20);
        duplicity_img = new ImageIcon(getClass().getResource("Picture//Duplicity_image.png"));
        JButton duplicity_button = new JButton(duplicity_img);
        duplicity_button.setBackground(new Color(255, 255, 255, 255));
        duplicity_button.setBorder(null);
        duplicity_button.setBounds(200, 400, duplicity_img.getIconWidth(), duplicity_img.getIconHeight());
        container.add(duplicity_button);

        capitalization_img = new ImageIcon(getClass().getResource("Picture//Capitalization_image.png"));
        JButton capitalization_button = new JButton(capitalization_img);
        capitalization_button.setBackground(new Color(255, 255, 255, 255));
        capitalization_button.setBorder(null);
        capitalization_button.setBounds(55, 465, capitalization_img.getIconWidth(), capitalization_img.getIconHeight());
        container.add(capitalization_button);

        spell_img = new ImageIcon(getClass().getResource("Picture//spell_check_image.png"));
        JButton spell_button = new JButton(spell_img);
        spell_button.setBackground(new Color(255, 255, 255, 255));
        spell_button.setBorder(null);
        spell_button.setBounds(350, 465, spell_img.getIconWidth(), spell_img.getIconHeight());
        container.add(spell_button);

        aboutus_img = new ImageIcon(getClass().getResource("Picture//aboutus_image.png"));
        JButton aboutus_button = new JButton(aboutus_img);
        aboutus_button.setBackground(new Color(255, 255, 255, 255));
        aboutus_button.setBorder(null);
        aboutus_button.setBounds(55, 530, aboutus_img.getIconWidth(), aboutus_img.getIconHeight());
        container.add(aboutus_button);

        /*guideline_img = new ImageIcon(getClass().getResource("Picture//Guideline_image.png"));
        JButton guide_button = new JButton(guideline_img);
        guide_button.setBackground(new Color(255, 255, 255, 255));
        guide_button.setBorder(null);
        guide_button.setBounds(350, 465, guideline_img.getIconWidth(), guideline_img.getIconHeight());
        container.add(guide_button);*/

        guideline_img = new ImageIcon(getClass().getResource("Picture//Guideline_image.png"));
        JButton guide_button = new JButton(guideline_img);
        guide_button.setBackground(new Color(255, 255, 255, 255));
        guide_button.setBorder(null);
        guide_button.setBounds(350, 530, guideline_img.getIconWidth(), guideline_img.getIconHeight());
        container.add(guide_button);

    try {
        Scanner scanner = new Scanner(new File("value3.txt"));
        while (scanner.hasNextInt()) {
            value_of_text_file = scanner.nextInt();
            if(value_of_text_file==0){
                volume_on_button.setIcon(volumeOFF_img);
                volume_icon_variable =0;
                sound_button.OFFvariable = 0 ;
                sound_button.ONvariable=1;
            }
            else{
                volume_on_button.setIcon(volumeON_img);
            }
        }
    } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
    }

        duplicity_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {

                sound_button.playsound();
                try {
                    Object[] choices = {"Choose Randomly", "Select Folder", "Cancel"};
                    Object defaultChoice = choices[0];
                    int n = JOptionPane.showOptionDialog(null, "How do you want to check ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                    if (n == JOptionPane.YES_OPTION) {
                        sound_button.playsound();
                        Multiple_with_CTRL_A LF = new Multiple_with_CTRL_A();
                        LF.setVisible(true);
                        dispose();
                    } else if (n == JOptionPane.NO_OPTION) {
                        sound_button.playsound();
                        Multiple_by_selecting_folder_sorting LF = new Multiple_by_selecting_folder_sorting();
                        LF.setVisible(true);
                        dispose();
                    } else {
                        sound_button.playsound();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        capitalization_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {

                sound_button.playsound();
                Capitalization LF = null;
                try {
                    LF = new Capitalization();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                LF.setVisible(true);
                dispose();
            }
        });

        spell_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ea) {

                sound_button.playsound();

                Spell_Check LF = null;
                try {
                    LF = new Spell_Check();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LF.setVisible(true);
                dispose();
            }
        });

        aboutus_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();
                Aboutus aboutus = new Aboutus();
                aboutus.setVisible(true);
                dispose();
            }
        });
        guide_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();
                User_GuideLines guideLines = new User_GuideLines();
                guideLines.setVisible(true);
                dispose();
            }
        });

        volume_on_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();
                if(volume_icon_variable==0){
                    volume_on_button.setIcon(volumeON_img);
                    sound_button.ONvariable=0;
                    sound_button.OFFvariable = 1;
                    volume_icon_variable=1;
                }
                else {
                    volume_on_button.setIcon(volumeOFF_img);
                    sound_button.OFFvariable = 0;
                    sound_button.ONvariable = 1;
                    volume_icon_variable=0;
                }

                try {
                    FileOutputStream fout = new FileOutputStream("value3.txt");
                    PrintStream pout = new PrintStream(fout);
                    int kk = volume_icon_variable;
                    pout.println(kk);
                    pout.close();
                    fout.close();

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        Home hm = new Home();
        hm.setVisible(true);
    }
}