/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.devavance.metier.views.interfaces;

import fr.devavance.metier.controllers.IController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yassine
 */
@WebServlet(name = "SceanceChoiceView", urlPatterns = {IController.VUE_CHOICE})
public class SceanceChoiceView extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Choix Séance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Séance</h1>");
            out.println("<ul>");
            out.println("<li><a href='" + IController.CONTROLLER + "?" + IController.KEY_ACTION + "=" + IController.ACTION_HOME + "'>Home</a></li>");
            out.println("<li><a href='" + IController.CONTROLLER + "?" + IController.KEY_ACTION + "=" + IController.ACTION_LOGOUT + "'>Logout</a></li>");
            out.println("</ul>");
            out.println("<form action='" + IController.CONTROLLER + "' method='POST'>");
            out.println("<select name='" + IController.KEY_ACTION + "'>");
            out.println("  <option value='" + IController.SEANCE_COURS + "'>cours</option>");
            out.println("  <option value='" + IController.SEANCE_TD + "'>td</option>");
            out.println("  <option value='" + IController.SEANCE_TP + "'>tp</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Valider'>");
            out.println("</form>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
