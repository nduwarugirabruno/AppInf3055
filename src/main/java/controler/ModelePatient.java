package controler;

import java.util.ArrayList;
import java.util.List;

import entity.metier.Medecin;
import entity.metier.Patient;

public class ModelePatient {

	 private String motCle;
	   Patient patient = new Patient();
	    List<Medecin> medecin = new ArrayList<>();
	    public String getMotCle() {
	    	return motCle;
	    }
	    public void setMotCle(String motCle) {
	    	this.motCle = motCle;
	    }
	    public void setUser(Patient patients) {
	    	this.patient= patients;
	    }
}
