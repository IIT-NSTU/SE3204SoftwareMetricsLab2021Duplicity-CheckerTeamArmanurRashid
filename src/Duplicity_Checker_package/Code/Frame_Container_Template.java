package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;

public class Frame_Container_Template extends JFrame {
    public Container container;

    public void frame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(350, 25, 650, 680);
        this.setTitle("Duplicity Checker");
        this.setResizable(false);
    }
    public void setContainer(){
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(0, 0, 0, 255));
    }
}
