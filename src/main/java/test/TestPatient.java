package test;

import java.util.List;

import entity.metier.Patient;
import serviceImpl.PatientServiceImpl;

public class TestPatient {

	public static void main(String[] args) {
		
		PatientServiceImpl patient = new PatientServiceImpl();
		Patient pat = patient.getPatient(3L);
		//System.out.println("le nouveau patient est : " + pat);
		pat.setLogin("toto");
		patient.updatePatients(pat);
		System.out.println("le patient mis à jour : " + pat);
       /* Patient pat = patient.save(new Patient("pliss","Plisseken",5L));
        System.out.println("le nouveau patient est : " + pat);*/
       // List<Medecin> medecins = patient.getMedecinParMotCle("dd");//nous testons une recherche
		
		//for (User l : medecins) {
		//	System.out.println(l);
		//}
        
	}

}
