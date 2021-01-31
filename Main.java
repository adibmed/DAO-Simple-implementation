package tps.tp7;

import tps.tp7.DAO.Contact;
import tps.tp7.DAO.ContactDao;


public class Main {
    public static void main(String[] args) {
//        new ContactDao().getAll();
//        System.out.println(new ContactDao().find("changed@gmail.com"));
        Contact c = new Contact();
        c.setFirstName("Mohamed");
        c.setLastName("Adibe");
        c.setPhone("+21264356983");
        c.setEmail("adibe@gmail.com");
        c.setImageUrl("adibe.png");
        new ContactDao().update(c, "email='changed@gmail.com");

        System.out.println(new ContactDao().find("adibe@gmail.com"));

//        new mainWindow();
    }
}
