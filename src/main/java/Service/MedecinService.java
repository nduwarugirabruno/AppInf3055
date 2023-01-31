package Service;

import entity.Medecin;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface MedecinService {

    List<Medecin> getUsersParMotCle(String mc);
    Medecin save(Medecin medecin);
    Medecin addUser(Medecin medecin) throws SQLException;
    Medecin getUsers(Long id);
    Medecin updateUsers(Medecin medecin);
    Medecin deleteUsers(Long id);
}
