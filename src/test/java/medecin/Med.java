package medecin;

import entity.metier.Medecin;
import org.junit.jupiter.api.Test;
import serviceImpl.MedecinServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Med {

    private final MedecinServiceImpl medecin = new MedecinServiceImpl();
    private Medecin m = new Medecin("Medecin 1", "Loc 1", "Prof 1", "Poste 1", "Spe 1", 23769L, 21, "Login 1", "Pass 1");
    private final String motCle = "in";


    @Test
    void getMed(){
        List<Medecin> medList = medecin.getMedecinParMotCle(motCle);
        medList.forEach(System.out::println);
    }

    @Test
    void add(){
        medecin.save(m);
    }

    @Test
    void delete(){

    }

    @Test
    void upgrade(){

    }

    @Test
    void modif(){

    }

    @Test
    void save(){

    }
}
