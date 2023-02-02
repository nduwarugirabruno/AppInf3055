package Service;

import java.sql.SQLException;
import java.util.List;

import entity.metier.Medecin;
import entity.metier.Patient;

public interface PatientService {
   
	List<Medecin> getMedecinParMotCle(String mc);
	Patient save(Patient patient);
	Patient addPatient(Patient patient) throws SQLException;
	Patient getPatient(Long id);
	Patient updatePatients(Patient patient);
	Patient deletePatients(Long id);
	String  Commentaire(String commentaire,Long idPatient);
	String Description(String description);
}
