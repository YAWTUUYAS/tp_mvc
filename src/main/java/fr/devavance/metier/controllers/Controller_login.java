package fr.devavance.metier.controllers;

import fr.devavance.metier.exceptions.CredentialException;
import fr.devavance.metier.exceptions.LoginException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller_login", urlPatterns = {"/controller_login"})
public class Controller_login extends HttpServlet implements IController {

    // -------------------------------------------------------
    // Méthodes principales du cycle HTTP
    // -------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Si l’utilisateur est déjà connecté → redirige vers choix
        if (is_connected(request)) {
            dispatch(CONTROLLER, request, response);
        } else {
            // Sinon, affiche la page de login
            dispatch(VUE_LOGIN, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process_login_user(request, response);
    }

    // -------------------------------------------------------
    // Méthodes internes
    // -------------------------------------------------------

    /** Vérifie si l'utilisateur est connecté via la session. */
    private boolean is_connected(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        Object status = session.getAttribute(KEY_IS_CONNECTED);
        return (status != null && status.equals(CONNECTED));
    }

    /** Traite la tentative de connexion d’un utilisateur. */
    private void process_login_user(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter(KEY_LOGIN);
        String password = request.getParameter(KEY_PASSWORD);

        try {
            verify_credential(login, password); 

            // Si pass
            HttpSession session = request.getSession(true);
            session.setAttribute(KEY_IS_CONNECTED, CONNECTED);
            session.setAttribute(KEY_LOGIN, login);
            
            response.sendRedirect("controller");


        } catch (CredentialException | LoginException e) {
            // Message d'erreur renvoyé à la vue login
            request.setAttribute("error", e.getMessage());
            
            dispatch(VUE_LOGIN, request, response);
        }
    }

    /** Vérifie les identifiants et lève des exceptions si nécessaire. */
    private void verify_credential(String login, String password)
            throws CredentialException, LoginException {

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new CredentialException(CREDENTIALS_EMPTY_MESSAGE);
        }

        // Exemple simple de vérification
        if (!"admin".equals(login) || !"admin".equals(password)) {
            throw new LoginException(ERROR_CREDENTIALS_MESSAGE);
        }
    }

    // -------------------------------------------------------
    // Méthode utilitaire : envoie vers la vue correspondante
    // -------------------------------------------------------
    @Override
    public void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
