package Service;

import java.sql.SQLException;
import java.util.List;
import entity.metier.User;

public interface UserService {

	List<User> getUsersParMotCle(String mc);
	User save(User user);
	User addUser(User user) throws SQLException;
	User getUsers(Long id);
	User updateUsers(User user);
	User deleteUsers(Long id);
}
