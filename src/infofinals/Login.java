
package infofinals;

import java.awt.*;
import java.io.File;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Allaiza
 */
public class Login extends JFrame{
    private JPanel pnlLogin;
    private JLabel hdrwelcome, lblsignin, lblstudent,lblpass, lblregister, labelimg, lbllogo1, lbllogo2;
    private JTextField txtstudent;
    private JPasswordField txtpass;
    private JCheckBox checkpass;
    private JButton btnsignin, btnsignup;
    
    private int failedAttempts = 0;
    private boolean isLocked = false;
    
    Login(){
        setTitle("Login");
        setSize(1200,700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //bg 
        ImageIcon image = new ImageIcon("Images/bg.png");
        Image image1 = image.getImage().getScaledInstance(1200,700, Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(image1);
        labelimg = new JLabel(image2);
        
        //panel
        pnlLogin = new JPanel();
        pnlLogin.setBounds(-10,-10,1200,700);
        pnlLogin.setBackground(Color.BLACK);
        pnlLogin.add(labelimg);
        
        hdrwelcome = new JLabel ("Welcome back!");
        hdrwelcome.setFont(new Font("Helvetica World",PLAIN, 38));
        hdrwelcome.setForeground(WHITE);
        hdrwelcome.setBounds(48, 90, 300, 55);
        add(hdrwelcome);
        
        lblsignin = new JLabel ("Please sign in before continuing.");
        lblsignin.setFont(new Font("Poppins",PLAIN, 14));
        lblsignin.setForeground(WHITE);
        lblsignin.setBounds(48, 125, 300, 55);
        add(lblsignin);
        
        //studentid
        lblstudent = new JLabel ("Student ID");
        lblstudent.setFont(new Font("Helvetica World",BOLD, 15));
        lblstudent.setForeground(WHITE);
        lblstudent.setBounds(48, 180, 300, 55);
        add(lblstudent);
        
        txtstudent = new JTextField ();
        txtstudent.setBounds(48, 230, 470, 50);
        addPlaceholderBehavior(txtstudent, "Enter student ID");
        add(txtstudent);
        
        //password
        lblpass = new JLabel ("Password");
        lblpass.setFont(new Font("Helvetica World",BOLD, 15));
        lblpass.setForeground(WHITE);
        lblpass.setBounds(48, 280, 300, 55);
        add(lblpass);
        
        txtpass = new JPasswordField ();
        txtpass.setBounds(48, 330, 470, 50);
        addPlaceholderBehavior(txtpass, "Enter password");
        add(txtpass);
        
        //checkbox
        checkpass = new JCheckBox("Show password");
        checkpass.setForeground(Color.WHITE);
        checkpass.setBounds(48, 390, 150, 30);
        checkpass.setContentAreaFilled(false);
        checkpass.setFocusPainted(false);
        checkpass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtpass.setEchoChar((char) 0);
                } else {
                    txtpass.setEchoChar('\u2022');
                }
            }
        });
        add(checkpass);
        
        //buttonsignin
        btnsignin = new JButton ("Sign In");
        btnsignin.setBounds(48, 430, 470, 50);
        btnsignin.setFont(new Font("Helvetica World",BOLD, 15));
        btnsignin.setBackground(new java.awt.Color(65, 132, 232));
        btnsignin.setForeground(WHITE);
        add(btnsignin);
        
        btnsignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLocked) {
                    JOptionPane.showMessageDialog(null, "Please wait 30 seconds before trying again.", "Locked Out", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String studentID = txtstudent.getText();
                String password = new String(txtpass.getPassword());
                
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcite", "root", "Rachelle");
                    String query = "SELECT * FROM login WHERE studentID = ? AND password = ?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, studentID);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        dispose();
                        new LoadingPage();
                    } else {
                        failedAttempts++;
                        if (failedAttempts >= 3) {
                            isLocked = true;
                            JOptionPane.showMessageDialog(null, "Too many failed attempts. Please wait 30 seconds before trying again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                            Timer timer = new Timer(30000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    isLocked = false;
                                    failedAttempts = 0;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Student ID or Password. Attempt " + failedAttempts + " of 3.", "Login Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(e);
                } 
            }
        });
        
        lblregister = new JLabel ("Don't have an account?");
        lblregister.setFont(new Font("Helvetica World",PLAIN, 14));
        lblregister.setForeground(WHITE);
        lblregister.setBounds(160, 530, 400, 55);
        add(lblregister);
        
        btnsignup = new JButton ("Sign Up Now");
        btnsignup.setBounds(200,530,300,55);
        btnsignup.setFont(new Font("Helvetica World",BOLD, 14));
        btnsignup.setForeground(new java.awt.Color(65, 132, 232));
        btnsignup.setBackground(new java.awt.Color(26, 27, 78));
        btnsignup.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnsignup.setFocusable(false);
        btnsignup.setContentAreaFilled(false);
        btnsignup.setFocusPainted(false);
        add(btnsignup);
        
        btnsignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            dispose();
            new Register();    
            }
        });
        
        add(pnlLogin);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    private void addPlaceholderBehavior(JPasswordField passwordField, String placeholder) {
        passwordField.setEchoChar((char) 0);  
        passwordField.setForeground(Color.GRAY);
        passwordField.setText(placeholder);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('\u2022'); 
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0); 
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                }
            }
        });
    }
}
