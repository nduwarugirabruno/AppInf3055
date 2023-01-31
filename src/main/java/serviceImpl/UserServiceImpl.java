package serviceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Service.UserService;
import entity.SingletonConnection;
import entity.User;

public class UserServiceImpl implements UserService{
	Connection conn = SingletonConnection.getConnection();
	/*public UserServiceImpl() {
		this.conn = SingletonConnection.getConnection();
		System.out.println("conn in constructor :"+conn);
	}*/
	long count = countLigne();
	
	
	@Override
	public User addUser(User user) throws SQLException {
		 PreparedStatement ls = conn.prepareStatement("INSERT INTO Users(Nom,localité,Profession) Value(?,?,?)");


		   User users = new User();
		  users.setId_Users(count);
		  ls.setString(1, users.getNom());
		  ls.setString(2, users.getLocalite());
		  ls.setString(3, users.getProfession());
		  System.out.println("Le User :"+ users );
		  ls.executeUpdate();
		  PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(id_Users) as MAX_ID FROM Users");
		  ResultSet rs = ls2.executeQuery();
		  if(rs.next()) {
			  users.setId_Users(rs.getLong("MAX_ID"));
		  }
		  ls.close();
		  ls2.close();
		  return user;
	}

	
	@Override
	public List<User> getUsersParMotCle(String mc) {
		List<User> users= new ArrayList<User>();
		
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Users where Nom LIKE ?");
			ps.setString(1,"%"+mc+"%");
			ResultSet rs = ps.executeQuery();//on exécute la requete et le résultat est dans un objet de type "Result"
			while (rs.next()) {
				User user = new User();
				user.setId_Users(rs.getLong("id_Users"));;
				user.setNom(rs.getString("Nom"));
				user.setLocalite(rs.getString("localité"));
				user.setProfession(rs.getString("Profession"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;//on retourne une liste de produits
	}

	 public int countLigne(){
		Statement stmt;
		try {
			stmt = conn.createStatement();

			String query = "select count(*) from Users";
			//Executing the query
			ResultSet rs = stmt.executeQuery(query);
			//Retrieving the result
			rs.next();
			int count = rs.getInt(1);
			System.out.println("Le nombre d'éléments présent dans la table Users est : "+count);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User save(User user) {
		System.out.println("connn :"+conn);
		try {
			PreparedStatement ls = conn.prepareStatement("INSERT INTO Users(id_Users,Nom,localité,Profession) Value(?,?,?,?)");
			long count = countLigne();

			user.setId_Users(count);
			ls.setDouble(1, user.getId_Users());
			ls.setString(2, user.getNom());
			ls.setString(3, user.getLocalite());
			ls.setString(4, user.getProfession());
			System.out.println("Le User :"+ user );
			ls.executeUpdate();
			PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(id_Users) as MAX_ID FROM Users");
			ResultSet rs = ls2.executeQuery();
			if(rs.next()) {
				user.setId_Users(rs.getLong("MAX_ID"));
			}
			ls.close();
			ls2.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUsers(Long id) {
		Connection conn = SingletonConnection.getConnection();
		User user = new User();
		try {
			PreparedStatement users= conn.prepareStatement("select * from Users where id_Users = ?");
			users.setLong(1, id);
			ResultSet rs = users.executeQuery();
			if  (rs.next()) {
				user.setId_Users(rs.getLong("id_Users"));
				user.setNom(rs.getString("Nom"));
				user.setLocalite(rs.getString("localité"));
				user.setProfession(rs.getString("Profession"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public User updateUsers(User user) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ls = conn.prepareStatement("UPDATE Users SET id_Users=?,Nom=?, localité=?,Profession = ? WHERE id_Users=?");
			ls.setString(1, user.getNom());
			ls.setString(2, user.getLocalite());
			ls.setLong(3, user.getId_Users());
			ls.setString(4, user.getProfession());
			ls.executeUpdate();
			ls.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public User deleteUsers(Long id) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ls= conn.prepareStatement("DELETE FROM Users WHERE id_Users = ?");
			ls.setLong(1, id);
			int returnInt = ls.executeUpdate();
			System.out.println("returnInt: "+returnInt);
			ls.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

}
