package Service;

import java.sql.SQLException;
import java.util.List;
import entity.User;

public interface UserService {

	public List<User> getUsersParMotClé(String mc);
	public User save(User user);
	public User addUser(User user) throws SQLException;
	public User getUsers(Long id);
	public User updateUsers(User user);
	public User deleteUsers(Long id);
}
