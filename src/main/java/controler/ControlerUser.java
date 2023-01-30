package controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Service.UserService;
import entity.User;
import serviceImpl.UserServiceImpl;



@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControlerUser extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     UserService user;
    
     @Override
     public void init() throws ServletException {
    	 user = new UserServiceImpl();//implémentation de la servlet
     }
     
     @SuppressWarnings("static-access")
	@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	 String path = request.getServletPath();

		System.out.println("path: "+path);
		
    	 if(path.equals("/index.do"))
    	 {
    		 request.getRequestDispatcher("Home.jsp").forward(request, response);//appel la vue .jsp
    	 }
    	 else if (path.equals("/chercher41.do"))
    	 {
    		 String motCle = request.getParameter("motCle");
    		 ModeleUser model = new ModeleUser();//instance de la classe produit
    		 model.setMotCle(motCle);
    		 List<User> Livs = user.getUsersParMotClé(motCle);//prods est une liste de produits retournée par la méthode produitParMC 
    		 model.setUser(Livs);
    		 request.setAttribute("model", model);
    		 request.getRequestDispatcher("Home.jsp").forward(request, response);
    	 }
    	 else if (path.equals("/saisie.do")  )
  		{
  			request.getRequestDispatcher("saisieHome.jsp").forward(request,response);
  		}
  		else if (path.equals("/save.do")  && request.getMethod().equals("POST"))
  		{
  		    String Nom =request.getParameter("Nom");
  			String localite = request.getParameter("localite");
  			String Profession = request.getParameter("Profession");
   			User l = user.save(new User(Nom, localite, Profession));
  			request.setAttribute("user", l);
  			request.getRequestDispatcher("confirmation.jsp").forward(request,response);
  		}
  		else if (path.equals("/supprimer.do"))
  		{
  		    Long id= Long.parseLong(request.getParameter("id"));
  		    user.deleteUsers(id);
  		    response.sendRedirect("Home.jsp");	
  		    
//  			request.getRequestDispatcher("BibliothequeView.jsp").forward(request,response);
  		}
  		else if (path.equals("/editer.do")  )
 		{
 			Long id= Long.parseLong(request.getParameter("id"));
 		    User l = user.getUsers(id);
 		    request.setAttribute("user", l);
 			request.getRequestDispatcher("editerLivres.jsp").forward(request,response);
 		}
 		else if (path.equals("/update.do")  )
 		{
 			 Long id = Long.parseLong(request.getParameter("id"));
 			 String Nom =request.getParameter("Nom");
   			 String localité = request.getParameter("localité");
   			 String Profession = request.getParameter("Profession");
 			 User l = new User();
 			 l.setId_Users(id);
 			 l.setNom(Nom);
 			 l.setLocalite(localité);
 			 l.setProfession(Profession);
 			 user.updateUsers(l);
 			 request.setAttribute("user", l);
 			 request.getRequestDispatcher("confirmation.jsp").forward(request,response);
 		} 
     }
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 	}
}
