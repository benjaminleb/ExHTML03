package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cdi313
 */
@WebServlet(name = "servlet301", urlPatterns = {"/servlet301"})
public class servlet301 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected String printFormLog() {
        String formLog = "<h3>Identification</h3><br>\n"
                + "        <form name=\"formLog\" action=\"servlet301\" method=\"POST\">\n"
                + "            Identifiant : <input type=\"text\" name=\"id\" value=\"\" /><br>\n"
                + "            Mot de passe : <input type=\"password\" name=\"pwd\" value=\"\" /><br>\n"
                + "            <input type=\"submit\" value=\"Connexion\" name=\"formOK\" />\n"
                + "        </form>";
        return formLog;
    }

    protected Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Coin Corp</title>");
            out.println("</head>");
            out.println("<body>");
            Cookie idState;

            if (getCookie(request.getCookies(), "IDSTATE") == null) {
                idState = new Cookie("IDSTATE", "0");
                response.addCookie(idState);
            } else {
                idState = getCookie(request.getCookies(), "IDSTATE");
            }
            idState.setMaxAge(5 * 60);

            if (request.getParameter("formOK") == null && !(idState.getValue().equalsIgnoreCase("-1"))) {
                out.println("<meta http-equiv=Refresh content=0;url=form301.html>");
            } else if (Integer.valueOf(idState.getValue()) > 2) {
                out.println("<h3>Tentatives de connexions atteintes</h3>");
                out.println("<br>");
                out.println("<a href='form301.html'>-> Retour à l'identification</a>");
            } else if (idState.getValue().equalsIgnoreCase("-1")) {
                out.println("<meta http-equiv=Refresh content=0;url=logged301.html>");
            } else if (request.getParameter("id").equals("admin") && request.getParameter("pwd").equals("root")) {
                idState.setValue("-1");
                idState.setMaxAge(-1);
                response.addCookie(idState);
                System.out.println("idState = " + idState.getValue());
                out.println("<meta http-equiv=Refresh content=0;url=logged301.html>");
            } else {
                idState.setValue(String.valueOf((Integer.valueOf(idState.getValue())) + 1));
                idState.setMaxAge(5 * 60);
                response.addCookie(idState);

                System.out.println("idState = " + idState.getValue());

                out.println(printFormLog());
                out.println("<br>");
                out.println("<font color = \"red\">Mauvais identifiant/mot de passe !</font>");
                out.println("<br>");
                out.println((4 - (Integer.valueOf(idState.getValue()))) + " tentatives restantes");
            }

            if (request.getParameter("logOutOK") != null) {
                idState.setValue("0");
                idState.setMaxAge(5 * 60);
                response.addCookie(idState);
                out.println("<meta http-equiv=Refresh content=0;url=form301.html>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
