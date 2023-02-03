import entity.metier.User;
import org.junit.jupiter.api.Test;
import serviceImpl.UserServiceImpl;

import java.sql.SQLException;

public class Use {

    private final UserServiceImpl uses = new UserServiceImpl();
    private User u = new User("Dave", "Mvan", "Professeur", 239874, 71, "Link's", "#Link's");
    private String motCle = "ru";
    @Test
    public void add(){
        try {
            System.out.println(uses.addUser(u));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void delete() {
        System.out.println(uses.deleteUsers(4L));
    }

    @Test
    public void getUsersMotCle() {
        System.out.println(uses.getUsersParMotCle(motCle));
    }

    @Test
    public void update() {
        User user = uses.getUsers(1L);
        user.setNom("Sheik's");
        user.setLogin("Login 1");
        user.setPassword("Password 1");
        System.out.println(uses.updateUsers(user));
    }

}
