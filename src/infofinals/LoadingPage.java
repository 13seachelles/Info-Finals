/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infofinals;

import java.awt.*;
import static java.awt.Color.WHITE;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;


/**
 *
 * @author Allaiza
 */
public class LoadingPage extends JFrame{
    private JPanel pnlLoad;
    private JProgressBar progressBar;
    private JLabel lblpercent,labelimg, lblLoading;
    
    LoadingPage(){
        setTitle("Loading");
        setSize(1200,700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //bg 
        ImageIcon image = new ImageIcon("Images/load.png");
        Image image1 = image.getImage().getScaledInstance(1200,700, Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(image1);
        labelimg = new JLabel(image2);
        
        //panel
        pnlLoad = new JPanel();
        pnlLoad.setBounds(-10,-10,1200,700);
        pnlLoad.setBackground(Color.BLACK);
        pnlLoad.add(labelimg);
        
        
        //progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(150, 300, 900, 30);
        progressBar.setStringPainted(false);
        add(progressBar);
        
        //loading label
        lblLoading = new JLabel ("Loading...");
        lblLoading.setFont(new Font("Helvetica World", Font.BOLD, 20));
        lblLoading.setForeground(WHITE);
        lblLoading.setBounds(500, 330, 100, 30);
        add(lblLoading);
        
        lblpercent = new JLabel("0%");
        lblpercent.setFont(new Font("Helvetica World", Font.BOLD, 20));
        lblpercent.setForeground(WHITE);
        lblpercent.setBounds(600, 330, 60, 30);
        add(lblpercent);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {
                counter += 1;
                progressBar.setValue(counter);
                lblpercent.setText(counter + "%");
                if (counter >= 100) {
                    timer.cancel();
                    dispose();
                    new Records();
                }
            }
        }, 0, 40);
        
        add(pnlLoad);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
