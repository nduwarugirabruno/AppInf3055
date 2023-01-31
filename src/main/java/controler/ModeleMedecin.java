package controler;

import entity.Medecin;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class ModeleMedecin {

    private String motCle;
    List<Medecin> medecin = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Medecin> getMedecin() {
        return medecin;
    }

    public void setMedecin(List<Medecin> medecin) {
        this.medecin = medecin;
    }
}
