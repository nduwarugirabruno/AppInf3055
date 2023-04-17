package serviceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Service.PatientService;
import entity.SingletonConnection;
import entity.metier.Medecin;
import entity.metier.Patient;


public class PatientServiceImpl implements PatientService{
    private final SingletonConnection dbConnection = new SingletonConnection();
    private final Connection conn = dbConnection.getConnection();

	/*public UserServiceImpl() {
		this.conn = SingletonConnection.getConnection();
		System.out.println("conn in constructor :"+conn);
	}*/
	//long count = countLigne();
	@Override
	public List<Medecin> getMedecinParMotCle(String mc) {
		List<Medecin> medecins= new ArrayList<Medecin>();

		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Medecin inner join Users where nomUser LIKE ? or profession like ?");
			ps.setString(1,"%"+mc+"%");
			ps.setString(2,"%"+mc+"%");
			ResultSet rs = ps.executeQuery();//on exécute la requete et le résultat est dans un objet de type "Result"
			while (rs.next()) {
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(rs.getLong("idMedecin"));
                medecin.setId_Users(rs.getLong("idUser"));
                medecin.setNom(rs.getNString("nomUser"));
                medecin.setLocalite(rs.getString("localite"));
                medecin.setProfession(rs.getString("profession"));
                medecin.setAge(rs.getInt("age"));
                medecin.setTel(rs.getLong("Tel"));
                medecin.setPoste("poste");
                medecin.setSpecialite("specialite");

                medecins.add(medecin);
			}
            ps.close();
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
		ls.executeUpdate();
        ls.close();

        PreparedStatement ls1 = conn.prepareStatement("INSERT INTO Patients(idPatient, id_User, `Description`, Commentaire) Value(?,?,?,?)");
		ls1.setLong(1, patient.getIdPatient());
		ls1.setLong(2, patient.getId_Users());
		ls1.setString(3, patient.getDescription());
		ls1.setString(4, patient.getCommentaire());
		ls1.executeUpdate();
        ls1.close();

		System.out.println("Le Patient :"+ patient);
		PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(idUser) as MAX_ID FROM Users");
		ResultSet rs = ls2.executeQuery();
		if(rs.next()) {
			patient.setId_Users(rs.getLong("MAX_ID"));
		}
		ls2.close();
		return patient;
	}

	@Override
	public Patient getPatient(Long id) {
		Patient patient = new Patient();

		try {
			PreparedStatement patients= conn.prepareStatement("select * from Patients inner join Users where idPatient = ? and Users.idUser = Patients.id_User");
			patients.setLong(1, id);
			ResultSet rs = patients.executeQuery();
			if  (rs.next()) {
				patient.setIdPatient(rs.getLong("idPatient"));
				patient.setId_Users(rs.getLong("idUser"));
				patient.setDescription(rs.getString("Description"));
				patient.setCommentaire(rs.getString("Commentaire"));
				patient.setNom(rs.getString("nomUser"));
				patient.setTel(rs.getLong("Tel"));
				patient.setAge(rs.getInt("age"));
				patient.setLocalite(rs.getString("localite"));
				patient.setProfession(rs.getString("profession"));
				patient.setNom(rs.getString("login"));
				patient.setPassword(rs.getString("password"));
			}
            patients.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public Patient updatePatients(Patient patient) {
		try {
			PreparedStatement ls = conn.prepareStatement("UPDATE Users SET nomUser = ?, localite = ?, Profession = ?, Tel = ?, age = ?, login = ?, `password` = ? WHERE idUser = ?");

			ls.setString(1, patient.getNom());
			ls.setString(2, patient.getLocalite());
			ls.setString(3, patient.getProfession());
			ls.setLong(4, patient.getTel());
			ls.setInt(5, patient.getAge());
			ls.setString(6, patient.getLogin());
			ls.setString(7, patient.getPassword());
			ls.setLong(8, patient.getId_Users());
			ls.executeUpdate();
			ls.close();

			PreparedStatement ls2 = conn.prepareStatement("UPDATE Patients SET `Description` = ?, Commentaire = ? WHERE idPatient = ?");
			ls2.setString(1, patient.getDescription());
			ls2.setString(2, patient.getCommentaire());
			ls2.setLong(3, patient.getIdPatient());
			ls2.executeUpdate();
			ls2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public Patient deletePatients(Long id) {
		try {
			PreparedStatement ls= conn.prepareStatement("DELETE FROM Patients WHERE idPatient = ?");
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
		 try {
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Users(idUser, nomUser, Tel, age, localite, profession, login, `password`) Value(?,?,?,?,?,?,?,?)");
             PreparedStatement ps1 = conn.prepareStatement("INSERT INTO Patients(idPatient, id_User, `Description`, Commentaire) Value(?,?,?,?)");

             long countUsers = countLigne();
             long countPatient = countLignePat();

             patient.setId_Users(countUsers+1);
             patient.setIdPatient(countPatient+1);
             ps.setLong(1, patient.getId_Users());
             ps.setString(2, patient.getNom());
             ps.setLong(3, patient.getTel());
             ps.setInt(4, patient.getAge());
             ps.setString(5, patient.getLocalite());
             ps.setString(6, patient.getProfession());
             ps.setString(7, patient.getLogin());
             ps.setString(8, patient.getPassword());
             ps.executeUpdate();
             ps.close();

             ps1.setLong(1, patient.getIdPatient());
             ps1.setLong(2, patient.getId_Users());
             ps1.setString(3, patient.getDescription());
             ps1.setString(4, patient.getCommentaire());
             ps1.close();

		  }catch (SQLException e) {
			  e.printStackTrace();
		  }
		  return patient;
	}

	@Override
	public String Commentaire(String commentaire, long idPatient) {
		try {

			PreparedStatement ps = conn.prepareStatement("REPLACE INTO Users(idUser, nomUser, Tel, age, localite, profession, login, `password`) Value(?,?,?,?,?,?,?,?)");
			PreparedStatement ps1 = conn.prepareStatement("REPLACE INTO Patients(idPatient, id_User, `Description`, Commentaire) Value(?,?,?,?)");

			Patient patient = getPatient(idPatient);

			patient.setCommentaire(commentaire);

			ps.setLong(1, patient.getId_Users());
			ps.setString(2, patient.getNom());
			ps.setLong(3, patient.getTel());
			ps.setInt(4, patient.getAge());
			ps.setString(5, patient.getLocalite());
			ps.setString(6, patient.getProfession());
			ps.setString(7, patient.getLogin());
			ps.setString(8, patient.getPassword());
			ps.executeUpdate();
			ps.close();

			ps1.setLong(1, patient.getIdPatient());
			ps1.setLong(2, patient.getId_Users());
			ps1.setString(3, patient.getDescription());
			ps1.setString(4, patient.getCommentaire());
			ps.executeUpdate();
			ps1.close();

			System.out.println("Le Commentaire est :"+ commentaire );
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return commentaire;
	}

	@Override
	public String Description(String description, long idPatient) {
		try {
			PreparedStatement ps = conn.prepareStatement("REPLACE INTO Users(idUser, nomUser, Tel, age, localite, profession, login, `password`) Value(?,?,?,?,?,?,?,?)");
			PreparedStatement ps1 = conn.prepareStatement("REPLACE INTO Patients(idPatient, id_User, `Description`, Commentaire) Value(?,?,?,?)");

			Patient patient = getPatient(idPatient);

			patient.setDescription(description);

			ps.setLong(1, patient.getId_Users());
			ps.setString(2, patient.getNom());
			ps.setLong(3, patient.getTel());
			ps.setInt(4, patient.getAge());
			ps.setString(5, patient.getLocalite());
			ps.setString(6, patient.getProfession());
			ps.setString(7, patient.getLogin());
			ps.setString(8, patient.getPassword());
			ps.executeUpdate();
			ps.close();

			ps1.setLong(1, patient.getIdPatient());
			ps1.setLong(2, patient.getId_Users());
			ps1.setString(3, patient.getDescription());
			ps1.setString(4, patient.getCommentaire());
			ps.executeUpdate();
			ps1.close();
					
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
			System.out.println("Le nombre d'éléments présent dans la table Patient est : "+count);
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
