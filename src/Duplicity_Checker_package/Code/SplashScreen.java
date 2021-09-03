package Duplicity_Checker_package.Code;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    JFrame frame;
    JLabel image = new JLabel(new ImageIcon(this.getClass().getResource("Picture//Splash_Demo_Picture.PNG")));

    JLabel text = new JLabel("Duplicity Checker");
    JProgressBar progressBar = new JProgressBar();
    JLabel message = new JLabel();
    JLabel developer_message = new JLabel("Developed by Team BX-12");

    SplashScreen() {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        add_developer_message();
        runningPBar();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setBounds(350, 25, 650, 680);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    public void addImage() {
        image.setBounds(80, 25, 550, 400);
        frame.add(image);
    }

    public void addText() {
        text.setFont(new Font("AlGERIAN", Font.ITALIC, 43));
        text.setBounds(122, 448, 600, 45);
        text.setForeground(Color.BLUE);
        frame.add(text);
    }

    public void addMessage() {
        message.setBounds(275, 570, 200, 40);
        message.setForeground(Color.BLACK);
        message.setFont(new Font("arial", Font.BOLD, 15));
        frame.add(message);
    }

    public void addProgressBar() {
        progressBar.setBounds(125, 520, 400, 28);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.BLUE);
        progressBar.setValue(0);
        frame.add(progressBar);
    }

    public void add_developer_message() {
        developer_message.setBounds(430, 650, 200, 25);
        developer_message.setForeground(Color.BLACK);
        developer_message.setFont(new Font("arial", Font.BOLD, 15));
        frame.add(developer_message);
    }

    public void runningPBar() {
        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(50);
                progressBar.setValue(i);
                message.setText("LOADING " + Integer.toString(i) + "%");
                i++;
                if (i >= 50) {
                    i = i + 2;
                }
                if (i == 100) {
                    frame.dispose();
                    Home home_page = new Home();
                    home_page.setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}