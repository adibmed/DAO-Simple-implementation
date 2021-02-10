package tps.tp7.DAO;

import java.sql.*;
import java.util.Optional;
import java.util.Vector;

public class ContactDao implements IServices<Contact> {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preStatement = null;
    private final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final String dbURL = "jdbc:mysql://localhost:3306/school";
    private final String dbUser = "adibe";
    private final String dbPwd = "@dmin_2120";
    private final String tableName = "contacts";
    public ContactDao() {
        try {
            Class.forName(dbDriver);
            connect = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("Error Connecting to database\n" + e);
        }
    }


    @Override
    public void add(Contact contact) {
        try {
            preStatement = connect.prepareStatement("INSERT INTO "+tableName+" values(?,?,?,?,?)");
            preStatement.setString(1, contact.getFirstName());
            preStatement.setString(2, contact.getLastName());
            preStatement.setString(3, contact.getEmail());
            preStatement.setString(4, contact.getPhone());
            preStatement.setString(5, contact.getImageUrl());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(String email) {
        try {
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM "+tableName+" WHERE email='" + email + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Vector<Contact> getAll() {
        Vector<Contact> contacts = new Vector<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setEmail(resultSet.getString("email"));
                contact.setImageUrl(resultSet.getString("image_url"));
                contact.setPhone(resultSet.getString("phone"));
                contacts.add(contact);
            }
        } catch (Exception e) {
            System.out.println("Error Getting data\n" + e);
        }

        return contacts;
    }

    @Override
    public Optional<Contact> find(String email) {
        Contact contact = new Contact();
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE email='"+email+"'");
            if(resultSet.next()) {
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setEmail(resultSet.getString("email"));
                contact.setImageUrl(resultSet.getString("image_url"));
                contact.setPhone(resultSet.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("Error Getting data\n" + e);
        }
        return Optional.of(contact);
    }

    @Override
    public void update(Contact contact, String email) {
        String updateQuery = "UPDATE "+tableName+" SET first_name=?, last_name=?, phone=?, email=?, image_url=? WHERE email=?";
        try {
            preStatement = connect.prepareStatement(updateQuery);
            preStatement.setString(1, contact.getFirstName());
            preStatement.setString(2, contact.getLastName());
            preStatement.setString(3, contact.getPhone());
            preStatement.setString(4, contact.getEmail());
            preStatement.setString(5, contact.getImageUrl());
            preStatement.setString(6, email);
            preStatement.executeUpdate();

        } catch (Exception e)
        {
            System.out.println("Error updating data\n" + e);
        }

    }
}