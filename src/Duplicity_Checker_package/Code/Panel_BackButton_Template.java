package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;

public class Panel_BackButton_Template extends Frame_Container_Template{
    public JPanel jpanel;
    public JLabel background;
    public ImageIcon BackButton_img;
    public JButton back_button;

    Panel_BackButton_Template(){
        super.frame();
        super.setContainer();
    }

    public void setPanel(){
        jpanel = new JPanel();
        jpanel.setBounds(5,6,623,630);
        jpanel.setLayout(null);
        super.container.add(jpanel);
        jpanel.setBackground(new Color(28, 73, 102));
    }

public JButton BackButton(){
    BackButton_img = new ImageIcon(getClass().getResource("Picture//BackButton.png"));
    back_button = new JButton(BackButton_img);
    back_button.setBackground(new Color(28, 73, 102));
    back_button.setBorder(null);
    back_button.setBounds(545, 10, BackButton_img.getIconWidth(), BackButton_img.getIconHeight());
    jpanel.add(back_button);
    return back_button;
}
}