package tps.tp7.views;

import tps.tp5.Contact;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class mainWindow extends JFrame {
    private JPanel leftPanel, rightPanel, bottomPanel, topPanel;
    private JLabel fullNameLabel, telLabel, emailLabel, imageLabel;
    private JTextField fullNameField, telField, emailField;
    private JButton addBtn, removeBtn, editBtn, leftBtn, rightBtn, centerBtn;
    private Image addIcon, deteteIcon, editIcon, leftIcon, rightIcon, searchIcon;
    private Image usersImage;

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

//        Image image = new ImageIcon(this.getClass().getResource("add.png")).getImage();

        try {
            deteteIcon = ImageIO.read(getClass().getResource("images/png/delete.png"));
            addIcon = ImageIO.read(getClass().getResource("images/png/add.png"));
            editIcon = ImageIO.read(getClass().getResource("images/png/edit.png"));
            leftIcon = ImageIO.read(getClass().getResource("images/png/left-arrow.png"));
            rightIcon = ImageIO.read(getClass().getResource("images/png/right-arrow.png"));
            searchIcon = ImageIO.read(getClass().getResource("images/png/search.png"));
            usersImage = ImageIO.read(getClass().getResource("image/png/XzA2OTYwNTcuanBn.jpg"));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        addBtn = new JButton();
        addBtn.setIcon(new ImageIcon(addIcon));
        removeBtn = new JButton();
        removeBtn.setIcon(new ImageIcon(deteteIcon));
        editBtn = new JButton();
        editBtn.setIcon(new ImageIcon(editIcon));
        leftBtn = new JButton();
        leftBtn.setIcon(new ImageIcon(leftIcon));
        rightBtn = new JButton();
        rightBtn.setIcon(new ImageIcon(rightIcon));
        centerBtn = new JButton();
        centerBtn.setIcon(new ImageIcon(searchIcon));

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
