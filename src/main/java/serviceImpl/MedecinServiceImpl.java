package serviceImpl;

import Service.MedecinService;
import entity.metier.Medecin;
import entity.SingletonConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinServiceImpl implements MedecinService {

    private final Connection conn = SingletonConnection.getConnection();

    @Override
    public List<Medecin> getMedecinParMotCle(String mc) {
        List<Medecin> medecins = new ArrayList<>();

        try {
            PreparedStatement pStmt = conn.prepareStatement("Select * from Users inner join Medecin where nomUser LIKE ? and Medecin.idUser = Users.idUser");

            pStmt.setString(1,"%"+mc+"%");
            ResultSet rs = pStmt.executeQuery();
            Medecin med;
            while (rs.next()) {
                med = new Medecin();
                med.setId_Users(rs.getLong("idUser"));
                med.setIdMedecin(rs.getLong("idMedecin"));
                med.setNom(rs.getString("nomUser"));
                med.setTel(rs.getLong("Tel"));
                med.setAge(rs.getInt("age"));
                med.setLocalite(rs.getString("localite"));
                med.setProfession(rs.getString("profession"));
                med.setPoste(rs.getString("poste"));
                med.setSpecialite(rs.getString("specialite"));
                medecins.add(med);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public Medecin save(Medecin medecin) {
        try {
            PreparedStatement ls = conn.prepareStatement("INSERT INTO Users(idUser, nomUser, Tel, age, localite, profession, login, password) Value(?,?,?,?,?,?,?,?)");
            PreparedStatement ls1 = conn.prepareStatement("INSERT INTO Medecin(idMedecin, idUser, poste, specialite) Value(?,?,?,?)");

            long countUsers = countLigne();
            long countMedecin = countLigneMed();

            medecin.setId_Users(countUsers);
            medecin.setIdMedecin(countMedecin);
            ls.setLong(1, medecin.getId_Users());
            ls.setString(2, medecin.getNom());
            ls.setLong(3, medecin.getTel());
            ls.setInt(4, medecin.getAge());
            ls.setString(5, medecin.getLocalite());
            ls.setString(6, medecin.getProfession());
            ls.setString(7, medecin.getLogin());
            ls.setString(8, medecin.getPassword());
            ls.executeUpdate();
            ls.close();

            ls1.setLong(1, medecin.getIdMedecin());
            ls1.setLong(2, medecin.getId_Users());
            ls1.setString(3, medecin.getPoste());
            ls1.setString(4, medecin.getSpecialite());
            System.out.println("Le Medecin :"+ medecin );
            ls.executeUpdate();
            ls1.close();

            PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(idMedecin) as MAX_ID FROM Medecin");
            ResultSet rs = ls2.executeQuery();
            if(rs.next()) {
                medecin.setId_Users(rs.getLong("MAX_ID"));
            }
            ls2.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }

    @Override
    public Medecin addMedecin(Medecin medecin) throws SQLException {

        PreparedStatement ls = conn.prepareStatement("INSERT INTO Medecin (idMedecin, idUser, poste, specialite) Value(?,?,?,?)");

        long countUsers = countLigne();
        long countMedecin = countLigneMed();

        medecin.setId_Users(countUsers);
        medecin.setIdMedecin(countMedecin);

        ls.setLong(1, medecin.getIdMedecin());
        ls.setLong(2, medecin.getId_Users());
        ls.setString(3, medecin.getPoste());
        ls.setString(4, medecin.getSpecialite());
        System.out.println("Le Medecin :"+ medecin);
        ls.executeUpdate();
        PreparedStatement ls2 = conn.prepareStatement("SELECT MAX(idMedecin) as MAX_ID FROM Medecin");
        ResultSet rs = ls2.executeQuery();
        if(rs.next()) {
            medecin.setId_Users(rs.getLong("MAX_ID"));
        }
        ls.close();
        ls2.close();
        return medecin;
    }

    @Override
    public Medecin getMedecin(Long id) {

        Medecin med = new Medecin();
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from Medecin inner join Users where idMedecin = ? and Users.idUser = Medecin.idUser");
            pStmt.setLong(1, id);
            ResultSet rs = pStmt.executeQuery();
            if  (rs.next()) {
                med.setIdMedecin(rs.getLong("idMedecin"));
                med.setId_Users(rs.getLong("idUser"));
                med.setPoste(rs.getString("poste"));
                med.setSpecialite(rs.getString("specialite"));
                med.setNom(rs.getString("nomUser"));
                med.setTel(rs.getLong("Tel"));
                med.setAge(rs.getInt("age"));
                med.setLocalite(rs.getString("localite"));
                med.setProfession(rs.getString("profession"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return med;
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        try {
            PreparedStatement ls = conn.prepareStatement("UPDATE Users SET nomUser = ?, localite = ?, Profession = ?, Tel = ?, age = ? WHERE idUser = ?");

            ls.setString(1, medecin.getNom());
            ls.setString(2, medecin.getLocalite());
            ls.setString(3, medecin.getProfession());
            ls.setLong(4, medecin.getTel());
            ls.setInt(5, medecin.getAge());
            ls.setLong(6, medecin.getId_Users());
            ls.executeUpdate();
            ls.close();

            PreparedStatement ls2 = conn.prepareStatement("UPDATE Medecin SET poste = ?, specialite = ? WHERE idUser = ?");
            ls2.setString(1, medecin.getPoste());
            ls2.setString(2, medecin.getSpecialite());
            ls2.setLong(3, medecin.getIdMedecin());
            ls2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }

    @Override
    public Medecin deleteMedecin(Long id) {
        try {
            PreparedStatement ls0 = conn.prepareStatement("SELECT * FROM Medecin WHERE idMedecin = ? ");
            ls0.setLong(1, id);
            ResultSet result = ls0.executeQuery();
            if (result.next()) {
                int idUser = result.getInt("idUser");
                PreparedStatement ls1 = conn.prepareStatement("DELETE FROM Users WHERE idUser = ?");
                ls1.setLong(1, idUser);
                ls1.executeUpdate();
                ls1.close();
            }
            ls0.close();

            PreparedStatement ls = conn.prepareStatement("DELETE FROM Medecin WHERE idMedecin = ?");
            ls.setLong(1, id);
            int returnInt = ls.executeUpdate();

            System.out.println("result: "+ returnInt);
            ls.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private long countLigneMed() {
        Statement stmt;
        try {
            stmt = conn.createStatement();

            String query = "SELECT count(*) FROM Medecin";
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

