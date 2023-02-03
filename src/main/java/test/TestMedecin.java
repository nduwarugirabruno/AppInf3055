package test;

import entity.metier.Medecin;
import entity.metier.User;
import serviceImpl.MedecinServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class TestMedecin {

	public static void main(String[] args) throws SQLException {

		MedecinServiceImpl medecins = new MedecinServiceImpl();

/*		Medecin med = new Medecin("Tmp","te","Étudiant","Licence 3","Génie Logiciel",695156866,22);

		med.setLogin("#Link's");
		med.setPassword("123Link's");

		Medecin medecin = medecins.addMedecin(med);

		System.out.println(medecin.getNom());
        System.out.println("le medecin ajouté est : " + medecin);*/

		List<Medecin> medecinUsers = medecins.getMedecinParMotCle("o");//nous testons une recherche
		
		for (User l : medecinUsers) {
			System.out.println(l.getNom());
		}
}
}
