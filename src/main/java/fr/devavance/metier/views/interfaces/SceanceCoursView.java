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
@WebServlet(name = "SceanceCoursView", urlPatterns = {"/"+ IController.VUE_COURS})
public class SceanceCoursView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TP2 - Authentification</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Séance de cours</h1>");
            out.println("<ul>");
            out.println("<li><a href='" + IController.CONTROLLER + "?" + IController.KEY_ACTION + "=" + IController.ACTION_HOME + "'>Home</a></li>");
            out.println("<li><a href='" + IController.CONTROLLER + "?" + IController.KEY_ACTION + "=" + IController.ACTION_LOGOUT + "'>Logout</a></li>");
            out.println("</ul>");
            out.println("<h2>Ceci est un cours</h2>");  
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
