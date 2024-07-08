/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infofinals;

import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Font;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Allaiza
 */
public class Register extends JFrame{
    private JPanel pnlRegister;
    private JLabel hrdcreate, lblsignup,lblstudent, lblname, lblcontact, lblbirthday, lblpass,lbladdress,lblhaveacc, labelimg;
    private JTextField txtstudent, txtFname,txtLname,txtcontact, txtbirthday, txtaddress;
    private JPasswordField txtpass;
    private JButton btnsignup, btnsignin;
    private JCheckBox checkpass;
    
    Register(){
        setTitle("Register");
        setSize(1200,700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //bg 
        ImageIcon image = new ImageIcon("Images/registerbg.png");
        Image image1 = image.getImage().getScaledInstance(1200,700, Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(image1);
        labelimg = new JLabel(image2);
        
        //panel
        pnlRegister = new JPanel();
        pnlRegister.setBounds(-10,-10,1200,700);
        pnlRegister.setBackground(Color.BLACK);
        pnlRegister.add(labelimg);
        
        hrdcreate = new JLabel ("Create new Account");
        hrdcreate.setFont(new Font("Helvetica World",PLAIN, 38));
        hrdcreate.setForeground(WHITE);
        hrdcreate.setBounds(48, 90, 500, 55);
        add(hrdcreate);
        
        lblsignup = new JLabel ("Please sign up before continuing.");
        lblsignup.setFont(new Font("Poppins",PLAIN, 14));
        lblsignup.setForeground(WHITE);
        lblsignup.setBounds(48, 125, 300, 55);
        add(lblsignup);
        
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
        
        //contact
        lblcontact = new JLabel ("Contact Number");
        lblcontact.setFont(new Font("Helvetica World",BOLD, 15));
        lblcontact.setForeground(WHITE);
        lblcontact.setBounds(650, 180, 300, 55);
        add(lblcontact);
        
        txtcontact = new JTextField ();
        txtcontact.setBounds(650, 230, 470, 50);
        addPlaceholderBehavior(txtcontact, "Enter contact number");
        add(txtcontact);
        
        //name
        lblname = new JLabel ("Name");
        lblname.setFont(new Font("Helvetica World",BOLD, 15));
        lblname.setForeground(WHITE);
        lblname.setBounds(48, 280, 300, 55);
        add(lblname);
        
        txtFname = new JTextField ();
        txtFname.setBounds(48, 330, 230, 50);
        addPlaceholderBehavior(txtFname, "Enter first name");
        add(txtFname);
        
        txtLname = new JTextField ();
        txtLname.setBounds(290, 330, 230, 50);
        addPlaceholderBehavior(txtLname, "Enter last name");
        add(txtLname);
        
        //birthday
        lblbirthday = new JLabel ("Birthday");
        lblbirthday.setFont(new Font("Helvetica World",BOLD, 15));
        lblbirthday.setForeground(WHITE);
        lblbirthday.setBounds(650, 280, 300, 55);
        add(lblbirthday);
        
        txtbirthday = new JTextField ();
        txtbirthday.setBounds(650, 330, 470, 50);
        addPlaceholderBehavior(txtbirthday, "MM/DD/YY");
        add(txtbirthday);
        
        //email
        lbladdress = new JLabel ("Address");
        lbladdress.setFont(new Font("Helvetica World",BOLD, 15));
        lbladdress.setForeground(WHITE);
        lbladdress.setBounds(48, 380, 300, 55);
        add(lbladdress);
        
        txtaddress = new JTextField ();
        txtaddress.setBounds(48, 430, 470, 50);
        addPlaceholderBehavior(txtaddress, "Enter address");
        add(txtaddress);
        
        //password
        lblpass = new JLabel ("Password");
        lblpass.setFont(new Font("Helvetica World",BOLD, 15));
        lblpass.setForeground(WHITE);
        lblpass.setBounds(650, 380, 300, 55);
        add(lblpass);
        
        
        txtpass = new JPasswordField ();
        txtpass.setBounds(650, 430, 470, 50);
        addPlaceholderBehavior(txtpass, "Enter password");
        add(txtpass);
        
        //checkbox
        checkpass = new JCheckBox("Show password");
        checkpass.setForeground(Color.WHITE);
        checkpass.setBounds(650, 480, 150, 30);
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
        
        //buttonsignup
        btnsignup = new JButton ("Sign Up");
        btnsignup.setBounds(650, 530, 470, 50);
        btnsignup.setFont(new Font("Helvetica World",BOLD, 15));
        btnsignup.setBackground(new java.awt.Color(65, 132, 232));
        btnsignup.setForeground(WHITE);
        add(btnsignup);
        
        btnsignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
   
                String studentID = txtstudent.getText();
                String firstname = txtFname.getText();
                String lastname = txtLname.getText();
                String contactnumber = txtcontact.getText();
                String birthday = txtbirthday.getText();
                String address = txtaddress.getText();
                String password = new String(txtpass.getPassword());

                     try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcite", "root", "Rachelle")) {
            // Insert into register table
            String query = "INSERT INTO register (studentID, firstname, lastname, contactnumber, birthday, address) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, studentID);
                pst.setString(2, firstname);
                pst.setString(3, lastname);
                pst.setString(4, contactnumber);
                pst.setString(5, birthday);
                pst.setString(6, address);
                pst.executeUpdate();
            }

            // Insert into tbl_login table
            String loginQuery = "INSERT INTO login (studentID, password) VALUES (?, ?)";
            try (PreparedStatement prLogin = connection.prepareStatement(loginQuery)) {
                prLogin.setString(1, studentID);
                prLogin.setString(2, password);
                prLogin.executeUpdate();
            }
            } catch (Exception exception) {
               exception.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Sign up completed!", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoadingPage();
        }    
    });
        
        lblhaveacc = new JLabel ("Already have an account?");
        lblhaveacc.setFont(new Font("Helvetica World",PLAIN, 14));
        lblhaveacc.setForeground(WHITE);
        lblhaveacc.setBounds(140, 530, 400, 55);
        add(lblhaveacc);
        
        btnsignin = new JButton ("Sign In Now");
        btnsignin.setBounds(195,530,300,55);
        btnsignin.setFont(new Font("Helvetica World",BOLD, 14));
        btnsignin.setForeground(new java.awt.Color(65, 132, 232));
        btnsignin.setBackground(new java.awt.Color(26, 27, 78));
        btnsignin.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnsignin.setFocusable(false);
        btnsignin.setContentAreaFilled(false);
        btnsignin.setFocusPainted(false);
        add(btnsignin);
        
        btnsignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            dispose();
            new Login();    
            }
        });
        
        add(pnlRegister);
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
