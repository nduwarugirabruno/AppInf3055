package serviceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Service.PatientService;
import entity.SingletonConnection;
import entity.metier.Medecin;
import entity.metier.Patient;


public class PatientServiceImpl implements PatientService{

	Connection conn = SingletonConnection.getConnection();
	/*public UserServiceImpl() {
		this.conn = SingletonConnection.getConnection();
		System.out.println("conn in constructor :"+conn);
	}*/
	//long count = countLigne();
	@Override
	public List<Medecin> getMedecinParMotCle(String mc) {
		List<Medecin> medecins= new ArrayList<Medecin>();

		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select * from Medecin inner join Users where nomUser LIKE ?");
			ps.setString(1,"%"+mc+"%");
			ResultSet rs = ps.executeQuery();//on exécute la requete et le résultat est dans un objet de type "Result"
			while (rs.next()) {
				    Medecin medecin = new Medecin();
				    medecin.setIdMedecin(rs.getLong("idMedecin"));;
				    medecin.setId_Users(rs.getLong("idUser"));;
				    medecin.setNom(rs.getNString("nomUser"));
				    medecin.setLocalite(rs.getString("localite"));;
				    medecin.setProfession(rs.getString("profession"));
				    medecin.setAge(rs.getInt("age"));
				    medecin.setTel(rs.getLong("Tel"));
					medecin.setPoste("poste");
					medecin.setSpecialite("specialite");

					medecins.add(medecin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecins;//on;
	}

	@Override
	public Patient addPatient(Patient patient) throws SQLException {
		PreparedStatement ls = conn.prepareStatement("INSERT INTO Users(idUser, nomUser, Tel, age, localite, profession, login, `password`) Value(?,?,?,?,?,?,?,?)");

		long countUsers = countLigne();
		long countPatient = countLignePat();

		patient.setId_Users(countUsers);
		patient.setIdPatient(countPatient);

		ls.setLong(1, patient.getId_Users());
		ls.setString(2, patient.getNom());
		ls.setLong(3, patient.getTel());
		ls.setInt(4, patient.getAge());
		ls.setString(5, patient.getLocalite());
		ls.setString(6, patient.getProfession());
		ls.setString(7, patient.getLogin());
		ls.setString(8, patient.getPassword());

		System.out.println("Le Patient :"+ patient);
		ls.executeUpdate();
		PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(idUser) as MAX_ID FROM Users");
		ResultSet rs = ls2.executeQuery();
		if(rs.next()) {
			patient.setId_Users(rs.getLong("MAX_ID"));
		}
		ls.close();
		ls2.close();
		return patient;
	}

	@Override
	public Patient getPatient(Long id) {
		 Connection conn = SingletonConnection.getConnection();
		    Patient patient = new Patient();
	       try {
			PreparedStatement patients= (PreparedStatement) conn.prepareStatement("select * from Patients where idPatient = ?");
			patients.setLong(1, id);
			ResultSet rs = patients.executeQuery();
			if  (rs.next()) {
				
				patient.setIdPatient(rs.getLong("idPatient"));
				patient.setNom(rs.getString("login"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return patient;
	}

	@Override
	public Patient updatePatients(Patient patient) {
	       try {
			PreparedStatement ls = conn.prepareStatement("UPDATE Patients SET idPatient= ? ,Nom= ?, localite= ?,Profession = ?, age = ?,Tel = ? WHERE id_User=?");
			
			ls.setString(1, patient.getNom());
			ls.setString(2, patient.getLocalite());
			ls.setString(3, patient.getProfession());
			ls.setInt(4, patient.getAge());
			ls.setLong(5, patient.getTel());
			ls.executeUpdate();
			ls.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public Patient deletePatients(Long id) {
		Connection conn = SingletonConnection.getConnection();
	       try {
			PreparedStatement ls= (PreparedStatement) conn.prepareStatement("DELETE FROM PATIENTS WHERE idPatient = ?");
			ls.setLong(1, id);
			int returnInt = ls.executeUpdate();
			System.out.println("returnInt: "+returnInt);
			ls.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Patient save(Patient patient) {
		Connection conn = SingletonConnection.getConnection();
		 try {
			  PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO PATIENTS(login,password,id_User) VALUES(?,?,?)");
			  //long count = countLigne();
			  
			 // patient.setIdPatient(count);
			  ps.setString(1, patient.getLogin());
			  ps.setString(2, patient.getPassword());
			  ps.setLong(3, patient.getId_Users());
			  System.out.println("Le Patient  :"+ patient );
			  ps.executeUpdate();
			  PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement("SELECT MAX(idPatient) as MAX_ID FROM PATIENTS");
			  ResultSet rs = ps2.executeQuery();
			  if(rs.next()) {
				  patient.setIdPatient(rs.getLong("MAX_ID"));
			  }
			  ps.close();
			  ps2.close();
		  }catch (SQLException e) {
			  e.printStackTrace();
		  }
		  return patient;	}

	@Override
	public String Commentaire(String commentaire,Long idPatient) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO PATIENTS (Commentaire) VALUES(?) WHERE idPatient = ?");
			ps.setString(2, commentaire);
			ps.executeUpdate();
		System.out.println("Le Commentaire est :"+ commentaire );
		 }catch (SQLException e) {
			  e.printStackTrace();
		  }
		  return commentaire;
	}

	@Override
	public String Description(String description) {
		Connection conn = SingletonConnection.getConnection();
		Patient patient = new Patient();
	       try {
			PreparedStatement ls = (PreparedStatement) conn.prepareStatement("UPDATE PATIENTS SET Description=? WHERE idPatient=?");
			
			ls.setString(1, patient.getDescription());
			ls.executeUpdate();
			ls.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return description;
	}

	private long countLignePat() {
		Statement stmt;
		try {
			stmt = conn.createStatement();

			String query = "SELECT count(*) FROM Patients";
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

	private long countLigne() {
		Statement stmt;
		try {
			stmt = conn.createStatement();

			String query = "SELECT count(*) FROM Users";
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

}
