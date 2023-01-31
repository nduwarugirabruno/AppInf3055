package controler;

import java.util.ArrayList;
import java.util.List;
import entity.User;

public class ModeleUser {

	private String motCle;
	List<User> user = new ArrayList<>();

	public String getMotCle() {
	    	return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<User> getUsers() {
		return user;
	}

	public void setUser(List<User> users) {
		this.user= users;
	}
}
