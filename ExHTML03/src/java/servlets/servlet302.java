package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cdi313
 */
@WebServlet(name = "servlet302", urlPatterns = {"/servlet302"})
public class servlet302 extends HttpServlet {

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
            session.setAttribute("CHS", 0);
            session.setAttribute("HDG", 0);
            session.setAttribute("MLK", 0);

            if (request.getParameter("moreA") != null) {
                session.setAttribute("CHS", ((int) session.getAttribute("CHS") + 1));
            }
            if (request.getParameter("lessA") != null && (int) session.getAttribute("CHS") > 0) {
                session.setAttribute("CHS", ((int) session.getAttribute("CHS") - 1));
            }

            if (request.getParameter("moreB") != null) {
                session.setAttribute("HDG", ((int) session.getAttribute("HDG") + 1));
            }
            if (request.getParameter("lessB") != null && (int) session.getAttribute("HDG") > 0) {
                session.setAttribute("HDG", ((int) session.getAttribute("HDG") - 1));
            }

            if (request.getParameter("moreB") != null) {
                session.setAttribute("MLK", ((int) session.getAttribute("MLK") - 1));
            }
            if (request.getParameter("lessB") != null && (int) session.getAttribute("MLK") > 0) {
                session.setAttribute("MLK", ((int) session.getAttribute("MLK") - 1));
            }

            out.println(" <h3><font color = \"green\">Vous êtes connecté</font></h3><br>\n"
                    + "        <p>Bienvenue admin !</p><br>");

            out.println(" <p>Dans mon panier, j'ai...</p>");

            if ((int) session.getAttribute("CHS") + (int) session.getAttribute("HDG") + (int) session.getAttribute("MLK") == 0) {
                out.println("        <p>... rien du tout</p>");
            }else{
                out.println("<br>");
                out.println("<br>");
            }

            out.println("<form name=\"menu\" action=\"servlet302\" method=\"POST\">\n"
                    + "            <br>" + (int) session.getAttribute("CHS") + " <b>Cheese</b><br>\n"
                    + "            <input type=\"submit\" value=\"+\" name=\"moreA\" />\n"
                    + "            <input type=\"submit\" value=\"-\" name=\"lessA\" />\n"
                    + "            <br>" + (int) session.getAttribute("HDG") + " <b>Hotdog</b><br>\n"
                    + "            <input type=\"submit\" value=\"+\" name=\"moreB\" />\n"
                    + "            <input type=\"submit\" value=\"-\" name=\"lessB\" />\n"
                    + "            <br>" + (int) session.getAttribute("MLK") + " <b>Milkshake</b><br>\n"
                    + "            <input type=\"submit\" value=\"+\" name=\"moreC\" />\n"
                    + "            <input type=\"submit\" value=\"-\" name=\"lessC\" />\n"
                    + "        </form>");

            out.println("<br><br><br>");

            out.println("<form name=\"logOut\" action=\"servlet301\" method=\"POST\">\n"
                    + "            <input type=\"submit\" value=\"Déconnexion\" name=\"logOutOK\" />    \n"
                    + "        </form>");

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
