package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class setting_update extends Panel_BackButton_Template {
    private ImageIcon volumeON_img,volumeOFF_img;
    ButtonSound sound_button = new ButtonSound();
    public static int value=1;

    setting_update() throws IOException {
        App_Icon();
        super.frame();
        super.setContainer();
        super.setPanel();
        super.BackButton();

        volumeON_img = new ImageIcon(getClass().getResource("Picture//soundon.png"));
        JButton volume_on_button = new JButton(volumeON_img);
        volume_on_button.setBackground(new Color(255, 255, 255, 255));
        volume_on_button.setBorder(null);
        volume_on_button.setBounds(220, 220, volumeON_img.getIconWidth(), volumeON_img.getIconHeight());
        background.add(volume_on_button);

  //      volumeOFF_img = new ImageIcon(getClass().getResource("Picture//soundoff.png"));
    //    JButton volume_off_button = new JButton(volumeOFF_img);
      //  volume_off_button.setBackground(new Color(255, 255, 255, 255));
        //volume_off_button.setBorder(null);
        //volume_off_button.setBounds(220, 280, volumeOFF_img.getIconWidth(), volumeOFF_img.getIconHeight());
       //background.add(volume_off_button);

        back_button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });


        volume_on_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();
                if(value==1){
                    volumeON_img = new ImageIcon(getClass().getResource("Picture//soundon.png"));
                    JButton volume_on_button = new JButton(volumeON_img);
                    volume_on_button.setBackground(new Color(255, 255, 255, 255));
                    volume_on_button.setBorder(null);
                    volume_on_button.setBounds(220, 220, volumeON_img.getIconWidth(), volumeON_img.getIconHeight());
                    background.add(volume_on_button);

                 //   volume_on_button.setIcon(volumeON_img);
                    value=0;
                }
                else if(value==0){
                    volumeOFF_img = new ImageIcon(getClass().getResource("Picture//soundoff.png"));
                    JButton volume_off_button = new JButton(volumeOFF_img);
                    volume_off_button.setBackground(new Color(255, 255, 255, 255));
                    volume_off_button.setBorder(null);
                    volume_off_button.setBounds(220, 220, volumeOFF_img.getIconWidth(), volumeOFF_img.getIconHeight());
                    background.add(volume_off_button);
                    value=1;
                }
            //    sound_button.ONvariable=0;
              //  sound_button.OFFvariable = 1;
            }
        });
      /*  volume_off_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();
                sound_button.OFFvariable = 0;
                sound_button.ONvariable = 1;
            }
        });

       */

    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public static void main(String[] args) throws IOException {
        setting_update st = new setting_update();
        st.setVisible(true);
    }
}
