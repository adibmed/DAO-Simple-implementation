package tps.tp7.views;

import tps.tp7.DAO.Contact;
import tps.tp7.DAO.ContactDao;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class mainWindow extends JFrame {
    private JPanel leftPanel, rightPanel, bottomPanel, topPanel, mainPanel, leftTopPanel, opsBtnsPanel;
    private JLabel fullNameLabel, telLabel, emailLabel, imageLabel;
    private JTextField fullNameField, phoneField, emailField;
    private JButton addBtn, removeBtn, editBtn, prevBtn, nextBtn, searchBtn;
    private Image addIcon, deteteIcon, editIcon, leftIcon, rightIcon, searchIcon, saveIcon;
    private Image usersImage;

    private ContactDao contactDao = new ContactDao();
    private Vector<Contact> contacts;
    private int currentContactIndex = 0;

    private Boolean editOpen = false, addOpen = false;

    public mainWindow() {
        // load contacts
        contacts = contactDao.getAll();

        // Panel inistialization
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        leftTopPanel = new JPanel();
        mainPanel = new JPanel();
        opsBtnsPanel = new JPanel();

        // Load images and icons
        try {
            deteteIcon = ImageIO.read(getClass().getResource("images/png/delete.png"));
            addIcon = ImageIO.read(getClass().getResource("images/png/add.png"));
            editIcon = ImageIO.read(getClass().getResource("images/png/edit.png"));
            leftIcon = ImageIO.read(getClass().getResource("images/png/left-arrow.png"));
            rightIcon = ImageIO.read(getClass().getResource("images/png/right-arrow.png"));
            searchIcon = ImageIO.read(getClass().getResource("images/png/search.png"));
            saveIcon = ImageIO.read(getClass().getResource("images/png/save.png"));

        } catch (Exception ex) {
            System.out.println(ex);
        }

        // Contact infos
        fullNameLabel = new JLabel("Full Name:");
        telLabel = new JLabel("Phone:");
        emailLabel = new JLabel("Email:");
        imageLabel = new JLabel();
        imageLabel.setBorder(new EmptyBorder(0, 0, 7, 0));
        imageLabel.setSize(10, 10);
        fullNameField = new JTextField(15);
        phoneField = new JTextField(15);
        emailField = new JTextField(15);

        // Operations Buttons
        addBtn = new JButton();
        addBtn.setIcon(new ImageIcon(addIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        removeBtn = new JButton();
        removeBtn.setIcon(new ImageIcon(deteteIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        editBtn = new JButton();
        editBtn.setIcon(new ImageIcon(editIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        prevBtn = new JButton();
        prevBtn.setIcon(new ImageIcon(leftIcon));
        nextBtn = new JButton();
        nextBtn.setIcon(new ImageIcon(rightIcon));
        searchBtn = new JButton();
        searchBtn.setIcon(new ImageIcon(searchIcon));

        // Add label and fields to left panel
        leftTopPanel.setLayout(new GridLayout(2, 1));
        leftPanel.setLayout(new GridLayout(3, 2));
        leftPanel.add(fullNameLabel);
        leftPanel.add(fullNameField);
        leftPanel.add(telLabel);
        leftPanel.add(phoneField);
        leftPanel.add(emailLabel);
        leftPanel.add(emailField);
        leftTopPanel.add(leftPanel);

        // Add label and fields to right panel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(imageLabel);
        opsBtnsPanel.setLayout(new FlowLayout());
        opsBtnsPanel.add(addBtn);
        opsBtnsPanel.add(removeBtn);
        opsBtnsPanel.add(editBtn);
        rightPanel.add(opsBtnsPanel);

        // Add label and fields to bottom panel
        bottomPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        bottomPanel.add(prevBtn);
        bottomPanel.add(searchBtn);
        bottomPanel.add(nextBtn);

        // Add child panel to top panel
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(leftTopPanel);
        topPanel.add(rightPanel);

        // Add other panel to the container panel
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Load first contact to panel
        swipe(0);

        this.add(mainPanel);
        this.setTitle("Contacts");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Contact button events handling
        addBtn.addActionListener(e -> {
            addOpen = !addOpen;
            if (addOpen) {
                addBtn.setIcon(new ImageIcon(saveIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
                fullNameField.setEditable(addOpen);
                fullNameField.setText("");
                phoneField.setEditable(addOpen);
                phoneField.setText("");
                emailField.setEditable(addOpen);
                emailField.setText("");
            }
            else addBtn.setIcon(new ImageIcon(addIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));

            try {
                usersImage = ImageIO.read(getClass().getResource("images/image_0.png"));
                imageLabel.setIcon(new ImageIcon(usersImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            } catch (IOException ioException) {
                System.out.println("Image not found!");
            }
            System.out.println(addOpen);

            if(addOpen == false) {
                System.out.println(fullNameField.getText());
                System.out.println(emailField.getText());
                System.out.println(phoneField.getText());
                if (fullNameField.getText().length() == 0 || emailField.getText().length() == 0 || phoneField.getText().length() == 0 || fullNameField.getText().split(" ").length == 1)
                    JOptionPane.showMessageDialog(null, "Please enter valid fields infos!", "Invalid data", JOptionPane.ERROR_MESSAGE);
                else {
                    Contact contact = new Contact();
                    contact.setFirstName(fullNameField.getText().split(" ")[0]);
                    contact.setLastName(fullNameField.getText().split(" ")[1]);
                    contact.setEmail(emailField.getText());
                    contact.setPhone(phoneField.getText());
                    contact.setImageUrl("image_" + new Random().nextInt(10) + ".jpg");
                    contactDao.add(contact);
                    swipe(currentContactIndex);
                    JOptionPane.showMessageDialog(null, "Contact added successfully", "Success" , JOptionPane.INFORMATION_MESSAGE);
                    contacts = contactDao.getAll();
                }
            }



        });
        editBtn.addActionListener(e -> {
            editOpen = !editOpen;
            if (editOpen)
                editBtn.setIcon(new ImageIcon(saveIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
            else
                editBtn.setIcon(new ImageIcon(editIcon.getScaledInstance(28, 28, Image.SCALE_SMOOTH)));

            fullNameField.setEditable(editOpen);
            phoneField.setEditable(editOpen);
            emailField.setEditable(editOpen);

            try {
                usersImage = ImageIO.read(getClass().getResource("images/image_0.png"));
            } catch (IOException ioException) {
                System.out.println("Image not found!");
            }
            if(editOpen == false) {
                if (fullNameField.getText().length() == 0 || emailField.getText().length() == 0 || phoneField.getText().length() == 0)
                    JOptionPane.showMessageDialog(null, "Please enter valid fields info!", "Invalid data", JOptionPane.ERROR_MESSAGE);
                else {
                    Contact current = contacts.get(currentContactIndex);
                    String email = current.getEmail();
                    current.setFirstName(fullNameField.getText().split(" ")[0]);
                    current.setLastName(fullNameField.getText().split(" ")[1]);
                    current.setEmail(emailField.getText());
                    current.setPhone(phoneField.getText());
                    int confirm = JOptionPane.showConfirmDialog(null, "Save changes?");
                    if (confirm == 0) contactDao.update(current, email);
                }
            }

        });

        removeBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?");
            if (confirm == 0) {
                contactDao.remove(contacts.get(currentContactIndex).getEmail());
                swipe(1);
            }
        });

        searchBtn.addActionListener(e -> {
            String email = JOptionPane.showInputDialog("Enter email");
            if (email != null) {
                Contact contact = contactDao.find(email).get();
                if (contact.getEmail() != null) {
                    fullNameField.setText(contact.getFirstName() + " " + contact.getLastName());
                    phoneField.setText(contact.getPhone());
                    emailField.setText(contact.getEmail());
                    loadImage(contact.getImageUrl());
                    imageLabel.setIcon(new ImageIcon(usersImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                } else JOptionPane.showMessageDialog(null, "No contact found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        prevBtn.addActionListener(e -> {
            swipe(-1);
        });

        nextBtn.addActionListener(e -> {
            swipe(1);
        });
    }

    public void swipe(int i) {
        fullNameField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);
        currentContactIndex += i;
        if (currentContactIndex < 0) currentContactIndex = contacts.size() - 1;
        else if (currentContactIndex > contacts.size() - 1) currentContactIndex = 0;

        fullNameField.setText(contacts.get(currentContactIndex).getFirstName() + " " + contacts.get(currentContactIndex).getLastName());
        phoneField.setText(contacts.get(currentContactIndex).getPhone());
        emailField.setText(contacts.get(currentContactIndex).getEmail());
        loadImage(contacts.get(currentContactIndex).getImageUrl());
        imageLabel.setIcon(new ImageIcon(usersImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
    }

    public void loadImage(String image) {
        try {
            usersImage = ImageIO.read(getClass().getResource("images/" + image));
            if (usersImage == null) usersImage = ImageIO.read(getClass().getResource("images/image_0.png"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
