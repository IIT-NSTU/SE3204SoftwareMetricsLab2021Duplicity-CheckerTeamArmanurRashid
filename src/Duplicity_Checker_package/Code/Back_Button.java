package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.io.IOException;

public class Back_Button extends JFrame {

    public void backbutton(){
        Home LF = null;
        try {
            LF = new Home();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        LF.setVisible(true);
    }
}