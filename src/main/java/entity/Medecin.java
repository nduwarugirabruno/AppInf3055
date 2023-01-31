package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Medecin extends User implements Serializable {
    private long idMedecin;
    private String Poste;
    private String Specialite;

    public Medecin() {
        super();
    }

    public Medecin(String nom, String localite, String profession, long idMedecin, String poste, String specialite) {
        super(nom, localite, profession);
        this.idMedecin = idMedecin;
        Poste = poste;
        Specialite = specialite;
    }

    public long getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(long idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getPoste() {
        return Poste;
    }

    public void setPoste(String poste) {
        Poste = poste;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        Specialite = specialite;
    }

    @Override
    public String toString() {
        return "Medecin [" +
                "idMedecin= " + idMedecin +
                ", Poste= " + Poste +
                ", Specialite= " + Specialite +
                ", id_Users= " + this.getId_Users() +
                ", Nom= " + this.getNom() +
                ", localite= " + this.getLocalite() +
                ", Profession= " + this.getProfession() + " ]";
    }
}
