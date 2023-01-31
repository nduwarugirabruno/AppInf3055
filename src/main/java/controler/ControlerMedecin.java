package controler;

import Service.MedecinService;
import jakarta.servlet.annotation.WebServlet;
import serviceImpl.MedecinServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "cs", urlPatterns = {"/controleur", "*.do"})
public class ControlerMedecin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    MedecinService medecin;

    @Override
    public void init() throws ServletException {
        medecin = new MedecinServiceImpl();
    }
}
