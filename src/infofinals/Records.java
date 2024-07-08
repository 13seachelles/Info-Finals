/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infofinals;

import java.awt.*;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Allaiza
 */
public class Records extends JFrame{
        private JPanel pnlRecords;
        private JTable tbl;
        private DefaultTableModel model;
        private JLabel hdrRecords,labelimg;
        private JButton btnlogout, btndelete,btnsearch;
        private JTextField txtsearch;
        
    Records(){
        setTitle("Login");
        setSize(1200,700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //bg 
        ImageIcon image = new ImageIcon("Images/recordsbg.png");
        Image image1 = image.getImage().getScaledInstance(1200,700, Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(image1);
        labelimg = new JLabel(image2);
        
        //panel
        pnlRecords = new JPanel();
        pnlRecords.setBounds(-10,-10,1200,700);
        pnlRecords.setBackground(Color.BLACK);
        pnlRecords.add(labelimg);
        
        //hdrRecords
        hdrRecords = new JLabel("Student Records",SwingConstants.CENTER);
        hdrRecords.setFont(new Font("Helvetica World",PLAIN, 39));
        hdrRecords.setForeground(WHITE);
        hdrRecords.setBounds(355, 45, 500, 55);
        add(hdrRecords);
        
        // search field
        txtsearch = new JTextField();
        txtsearch.setBounds(480, 125, 150, 30);
        add(txtsearch);

        // search button
        btnsearch = new JButton("Search");
        btnsearch.setBounds(630, 125, 100, 30);
        btnsearch.setFont(new Font("Helvetica World", BOLD, 14));
        btnsearch.setForeground(Color.WHITE);
        btnsearch.setBackground(new java.awt.Color(65, 132, 232));
        add(btnsearch);

        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = txtsearch.getText();
                searchTable(query);
            }
        });
        
        //table
        Object[] columns = {"Student ID", "First Name", "Last Name", "Contact Number", "Birthday", "Address"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        
        tbl = new JTable(model);
        tbl.setBackground(Color.gray);
        tbl.setForeground(Color.white);
        tbl.setRowHeight(40);
        
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setBounds(110, 160, 1030, 400); 
        add(scrollPane);
        
        //button delete
        btndelete = new JButton (" Delete");
        btndelete.setBounds(430, 570, 130, 30);
        btndelete.setFont(new Font("Helvetica World",BOLD, 20));
        btndelete.setForeground(WHITE);
        btndelete.setBackground(new java.awt.Color(65, 132, 232));
        btndelete.setFocusable(false);
        add(btndelete);
        
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbl.getSelectedRow();
                if (i >= 0) {
                    String employeeName = model.getValueAt(i, 0).toString();
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcite", "root", "Rachelle");
                        String query = "DELETE FROM register WHERE studentID = ?";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.setString(1, employeeName);
                        pst.executeUpdate();
                        
                        model.removeRow(i);
                    } catch (SQLException ex) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("No row selected for deletion!");
                }
            }
        });
        
        //button logout
        btnlogout = new JButton (" Logout");
        btnlogout.setBounds(640, 570, 130, 30);
        btnlogout.setFont(new Font("Helvetica World",BOLD, 20));
        btnlogout.setForeground(WHITE);
        btnlogout.setBackground(new java.awt.Color(65, 132, 232));
        btnlogout.setFocusable(false);
        add(btnlogout);
        
        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                            dispose();
                new Login();

            }
            }
        });
        
        add(pnlRecords);
        
        setLocationRelativeTo(null);
        setVisible(true);
        
        loadDataFromRegister();
    }
    private void loadDataFromRegister() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcite", "root", "Rachelle");
            String query = "SELECT studentID, firstname, lastname, contactnumber, birthday, address FROM register";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            model.setRowCount(0);
            
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String contactnumber = rs.getString("contactnumber");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");

                model.addRow(new Object[]{studentID, firstname, lastname, contactnumber, birthday, address});
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }  
    }    
    private void searchTable(String query) {
        DefaultTableModel filteredModel = new DefaultTableModel();
        filteredModel.setColumnIdentifiers(new Object[]{"Student ID", "First Name", "Last Name", "Contact Number", "Birthday", "Address"});

        for (int i = 0; i < model.getRowCount(); i++) {
            String studentID = model.getValueAt(i, 0).toString();
            String firstName = model.getValueAt(i, 1).toString();
            String lastName = model.getValueAt(i, 2).toString();
            String contactNumber = model.getValueAt(i, 3).toString();
            String birthday = model.getValueAt(i, 4).toString();
            String address = model.getValueAt(i, 5).toString();

            if (studentID.contains(query) || firstName.contains(query) || lastName.contains(query) || contactNumber.contains(query) || birthday.contains(query) || address.contains(query)) {
                filteredModel.addRow(new Object[]{studentID, firstName, lastName, contactNumber, birthday, address});
            }
        }

        tbl.setModel(filteredModel);
    }
}
