package patient;

import entity.metier.Medecin;
import entity.metier.Patient;

import org.junit.jupiter.api.Test;
import serviceImpl.PatientServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Pat {

    private final PatientServiceImpl Patient = new PatientServiceImpl();
    private Patient u = new Patient("Dave", "Mvan", "Professeur", 239874, 71, "Link's", "#Link's", "I'm the best","Just me");
    private final String motCle = "ru";
    
    @Test
    void save(){
        Patient.save(u);
    }

    @Test
    void delete(){
        Patient.deletePatients(3L);
    }

    @Test
    void update(){
        Patient p = Patient.getPatient(2L);
        p.setNom("Link64");
        p.setId_Users(2L); p.setIdPatient(2L); p.setLocalite("Elig-Sono"); p.setDescription("I'm computer scientist");
        p.setProfession("Student"); p.setLogin("Temp"); p.setPassword("Temp");
        Patient.updatePatients(p);
    }

    @Test
    void getMed(){
        List<Medecin> medList = Patient.getMedecinParMotCle(motCle);
        medList.forEach(medecin -> System.out.println("medecin, "+medecin));
        for (Medecin m : medList) {
            System.out.println(m);
        }
    }

    @Test
    void addPatient() throws SQLException {
        Patient.addPatient(u);
    }
}
