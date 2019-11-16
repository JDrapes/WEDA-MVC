/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author jordandraper
 */
@WebServlet(name = "Signin", urlPatterns = {"/Signin.do"})
public class Signin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
         HttpSession session = request.getSession(false);
        
        Jdbc jdbc = (Jdbc)session.getAttribute("dbbean"); 
        if (jdbc == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        else {
            //If we have a connection then we need to validate the credentials and eventually check the profile type.
           
            String [] query = new String[2];
            query[0] = (String)request.getParameter("txtemail");
            query[1] = (String)request.getParameter("txtpassword");
            
            switch (jdbc.loginSuccess(query[0],query[1])) {
                case 1: //Admin panel
                    request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
                    request.setAttribute("msg", "Succesful login");
                    break;
                case 2: //Customer panel
                    request.getRequestDispatcher("customerPanel.jsp").forward(request, response);
                    request.setAttribute("msg", "Succesful login");
                    break;
                default: //Invalid - something is broken, check database for if user has an assigned profile
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    request.setAttribute("msg", "Invalid login");
                    break;
            }
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
