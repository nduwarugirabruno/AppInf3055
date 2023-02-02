package controler;

import Service.MedecinService;
import entity.metier.Medecin;
import entity.metier.User;
import jakarta.servlet.annotation.WebServlet;
import serviceImpl.MedecinServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "cs", urlPatterns = {"/controleur", "*.do"})
public class ControlerMedecin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    MedecinService medecin;

    @Override
    public void init() throws ServletException {
        medecin = new MedecinServiceImpl();
    }

    @SuppressWarnings("static-access")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();

        if (path.equals("/index.do"))
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
        else if (path.equals("/chercher41.do"))
        {
            String motCle = req.getParameter("motCle");
            ModeleMedecin model = new ModeleMedecin();//instance de la classe produit
            model.setMotCle(motCle);
            List<Medecin> Livs = medecin.getMedecinParMotCle(motCle);//prods est une liste de produits retournée par la méthode produitParMC
            model.setMedecin(Livs);
            req.setAttribute("model", model);
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
        }
        else if(path.equals("/saisie.do"))
            req.getRequestDispatcher("saisieHome.jsp").forward(req, resp);

        else if (path.equals("/save.do")  && req.getMethod().equals("POST")) {

            String Nom = req.getParameter("Nom");
            String localite = req.getParameter("localite");
            String Profession = req.getParameter("Profession");
            long tel = Long.parseLong(req.getParameter("Tel"));
            int age = Integer.parseInt(req.getParameter("age"));
            String poste = req.getParameter("poste");
            String specialite = req.getParameter("specialite");

            Medecin l = medecin.save(new Medecin(Nom, localite, Profession, poste, specialite, tel, age));
            req.setAttribute("medecin", l);
            req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
        }
        else if (path.equals("/supprimer.do"))
        {
            Long id= Long.parseLong(req.getParameter("id"));
            medecin.deleteMedecin(id);
            resp.sendRedirect("Home.jsp");

//  			req.getRequestDispatcher("BibliothequeView.jsp").forward(req,resp);
        }
        else if (path.equals("/editer.do")  )
        {
            Long id= Long.parseLong(req.getParameter("id"));
            Medecin l = medecin.getMedecin(id);
            req.setAttribute("medecin", l);
            req.getRequestDispatcher("editerLivres.jsp").forward(req, resp);
        }
        else if (path.equals("/update.do")  )
        {
            Long id = Long.parseLong(req.getParameter("id"));

            String Nom = req.getParameter("Nom");
            String localite = req.getParameter("localite");
            String Profession = req.getParameter("Profession");
            long tel = Long.parseLong(req.getParameter("Tel"));
            int age = Integer.parseInt(req.getParameter("age"));
            String poste = req.getParameter("poste");
            String specialite = req.getParameter("specialite");

            Medecin l = new Medecin();
            l.setId_Users(id);
            l.setNom(Nom);
            l.setLocalite(localite);
            l.setProfession(Profession);
            l.setTel(tel);
            l.setAge(age);
            l.setPoste(poste);
            l.setSpecialite(specialite);
            medecin.updateMedecin(l);
            req.setAttribute("medecin", l);
            req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
