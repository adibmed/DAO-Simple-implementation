package tps.tp7;

import tps.tp7.DAO.Contact;
import tps.tp7.DAO.ContactDao;
import tps.tp7.views.mainWindow;


public class Main {
    public static void main(String[] args) {
        new ContactDao().getAll();
        System.out.println(new ContactDao().find("changed@gmail.com"));
        Contact c = new Contact();
        c.setFirstName("Mohamed");
        c.setLastName("Adibe");
        c.setPhone("+212643563");
        c.setEmail("kkkkkkkkkk@gmail.com");
        c.setImageUrl("adibe.png");
//        new ContactDao().update(c, "yoooooooooooooo@gmail.com");
//        new ContactDao().add(c);
//        new ContactDao().remove("+212643563");

//        System.out.println(new ContactDao().find(c.getEmail()));

//        new mainWindow();

    }
}
