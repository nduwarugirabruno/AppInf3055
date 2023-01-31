package test;

import java.sql.SQLException;
import java.util.List;

import entity.User;
import serviceImpl.UserServiceImpl;

public class Test {

	public static void main(String[] args) throws SQLException {
		UserServiceImpl user = new UserServiceImpl();
		
		User use = user.addUser(new User("Dave", "Mvan", "Professeur", tel, age));
		System.out.println(use.getNom());
        System.out.println("le user ajouté est : " + use.toString());

		List<User> users = user.getUsersParMotCle("Elena");//nous testons une recherche
		
		for (User l : users) {
			System.out.println(l.getNom());
		}
}
}
