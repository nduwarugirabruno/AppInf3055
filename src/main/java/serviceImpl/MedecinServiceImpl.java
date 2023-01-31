package serviceImpl;

import Service.MedecinService;
import entity.Medecin;
import entity.SingletonConnection;

import java.sql.*;
import java.util.List;

public class MedecinServiceImpl implements MedecinService {

    Connection conn = SingletonConnection.getConnection();

    long countUsers = countLigne();
    long countMedecin = countLigneMed();

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

    @Override
    public List<Medecin> getUsersParMotCle(String mc) {
        return null;
    }

    @Override
    public Medecin save(Medecin medecin) {
        return null;
    }

    @Override
    public Medecin addUser(Medecin medecin) throws SQLException {
        PreparedStatement ls = conn.prepareStatement("INSERT INTO Medecin (idMedecin, idUser, poste, specialite) Value(?,?,?,?)");

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
    public Medecin getUsers(Long id) {
        return null;
    }

    @Override
    public Medecin updateUsers(Medecin medecin) {
        return null;
    }

    @Override
    public Medecin deleteUsers(Long id) {
        return null;
    }
}
