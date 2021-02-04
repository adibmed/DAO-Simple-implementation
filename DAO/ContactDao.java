package tps.tp7.DAO;

import java.sql.*;
import java.util.Optional;
import java.util.Vector;

public class ContactDao implements IServices<Contact> {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preStatement = null;
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbURL = "jdbc:mysql://localhost:3306/school";
    private String dbUser = "adibe";
    private String dbPwd = "@dmin_2120";
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

    }

    @Override
    public void remove(String email) {

    }

    @Override
    public Vector<Contact> getAll() {
        Vector<Contact> contacts = new Vector<>();
        try {
            resultSet = statement.executeQuery("select * from contacts");
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setEmail(resultSet.getString("email"));
                contact.setImageUrl(resultSet.getString("image_url"));
                contact.setPhone(resultSet.getString("phone"));
                contacts.add(contact);
                System.out.println(contact);
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
            resultSet = statement.executeQuery("select * from contacts where email='"+email+"'");
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
        System.out.println(contact);
        String updateQuery = "update contacts set first_name=?, last_name=?, phone=?, email=?, image_url=? where email=?";
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