package fr.devavance.metier.views.interfaces;

import fr.devavance.metier.views.interfaces.IServletView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginView", urlPatterns = {"/login"})
public class LoginView extends HttpServlet implements IServletView {

    /** Méthode utilitaire commune GET/POST : rend la page de login */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String error = (String) request.getAttribute("error");
        if (error == null) error = "";

        response.getWriter().println("""
            <!DOCTYPE html>
            <html lang='fr'>
            <head>
                <meta charset='UTF-8'>
                <title>Authentification</title>
            </head>
            <body>
                <h2>Connexion utilisateur</h2>

                <p style='color:red;'>%s</p>

                <form method='post' action='controller_login'>
                    <label for='login'>Login :</label>
                    <input type='text' id='login' name='login' required><br><br>

                    <label for='password'>Mot de passe :</label>
                    <input type='password' id='password' name='password' required><br><br>

                    <input type='submit' value='Se connecter'>
                </form>
            </body>
            </html>
        """.formatted(error));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // ✅ GET -> même rendu
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // ✅ POST -> même rendu (utile si on revient sur /login via POST)
    }
}
