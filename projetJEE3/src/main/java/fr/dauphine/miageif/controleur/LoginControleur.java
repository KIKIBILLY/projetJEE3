package fr.dauphine.miageif.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.miageif.service.Imetier;
import fr.dauphine.miageif.service.ImetierImplmentation;

/**
 * Servlet implementation class LoginController
 */



public class LoginControleur extends HttpServlet {
 private static final long serialVersionUID = 1L;


 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("login.jsp");
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  
  
  String page = "login.jsp";
  if(username.trim().length() >= 0 && username != null &&
    password.trim().length() >= 0 && password != null) {
   Imetier loginService = new ImetierImplmentation();
   boolean flag = loginService.login(username, password);
   if(flag) {
    System.out.println("Login success!!!");
    request.setAttribute("username", username);
    request.setAttribute("msg", "Login Success.....");
    page = "home.jsp";
   } else {
    request.setAttribute("msg", "Nom d'utilisateur/Mot de passe incorrect !!!");
   }
  } else {
   request.setAttribute("msg", "Introduisez un username/password...");
  }
  request.getRequestDispatcher(page).include(request, response);
 }
}