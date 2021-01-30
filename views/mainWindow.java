package tps.tp7.views;

import tps.tp5.Contact;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class mainWindow extends JFrame {
    private JPanel leftPanel, rightPanel, bottomPanel, topPanel;
    private JLabel fullNameLabel, telLabel, emailLabel, imageLabel;
    private JTextField fullNameField, telField, emailField;
    private JButton addBtn, removeBtn, editBtn, leftBtn, rightBtn, centerBtn;

    // data
    private Vector<Contact> contacts;

    public mainWindow() {

        leftPanel = new JPanel();
        rightPanel = new JPanel();
        topPanel = new JPanel();

        // Labels and Field init
        fullNameLabel = new JLabel("Full Name:");
        telLabel = new JLabel("Tel:");
        emailLabel = new JLabel("Email:");
        imageLabel = new JLabel("^_^ ^_^ ^_^ ^_^ ^_^ ^_^");
        imageLabel.setBackground(Color.orange);
        imageLabel.setForeground(Color.MAGENTA);
        fullNameField = new JTextField(10);
        telField = new JTextField(10);
        emailField = new JTextField(10);

        // Btns inti
        addBtn = new JButton("Add");
        removeBtn = new JButton("Delete");
        editBtn = new JButton("Edit");
        leftBtn = new JButton("<<");
        rightBtn = new JButton(">>");
        centerBtn = new JButton("Search");

        // Add label and fields to left panel
        leftPanel.setLayout(new GridLayout(3, 2));
        leftPanel.add(fullNameLabel);
        leftPanel.add(fullNameField);
        leftPanel.add(telLabel);
        leftPanel.add(telField);
        leftPanel.add(emailLabel);
        leftPanel.add(emailField);

        // Add label and fields to right panel
        rightPanel.setLayout(new GridLayout(4, 1));
        rightPanel.add(imageLabel);
        rightPanel.add(addBtn);
        rightPanel.add(removeBtn);
        rightPanel.add(editBtn);

        // Add label and fields to bottom panel
        bottomPanel = new JPanel();
        bottomPanel.add(leftBtn);
        bottomPanel.add(centerBtn);
        bottomPanel.add(rightBtn);



        this.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(leftPanel);
        topPanel.add(rightPanel);

        this.add(topPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);



        this.setTitle("Contacts");
//        this.setSize(500, 400);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setContact(Contact contact) {
        fullNameField.setText(contact.getNom());
        telField.setText(contact.getTel());
        emailField.setText(contact.getEmail());
        Icon img = new ImageIcon(contact.getImage());
        imageLabel.setIcon(img);
    }
}
