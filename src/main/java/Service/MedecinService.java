package Service;

import entity.metier.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface MedecinService {

    List<Medecin> getMedecinParMotCle(String mc);
    Medecin save(Medecin medecin);
    Medecin addMedecin(Medecin medecin) throws SQLException;
    Medecin getMedecin(Long id);
    Medecin updateMedecin(Medecin medecin);
    Medecin deleteMedecin(Long id);
}
