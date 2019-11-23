/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, SQLException {
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
            String username;
            
            switch (jdbc.loginSuccess(query[0],query[1])) {
                
                case 1: //Admin panel
                    request.getSession(); //GET THE SESSION IF ONE EXISTS, OTHERWISE CREATE ONE
                    //must update the session attributes on the admin servlet also on the case 1 and 2
                    //Session and request set the username (email)
                    session.setAttribute("username",query[0]); //Set username for the session
                    username=(String)session.getAttribute("username"); //getting username from session from login
                    request.setAttribute("username",username); //Set username for the forwarding request
                    
                    //Session and request set the fullname
                    String fullname = jdbc.returnDatabaseField(username, "fullname");
                    session.setAttribute("fullname", fullname);
                    request.setAttribute("fullname",fullname);
                    
                    //Session and request set the profiletype
                    String profiletype = jdbc.returnDatabaseField(username, "profiletype");
                    session.setAttribute("profiletype", profiletype);
                    request.setAttribute("profiletype",profiletype);
                    
                    //Session and request set the dateofbirth
                    String dateofbirth = jdbc.returnDatabaseField(username, "dateofbirth");
                    session.setAttribute("dateofbirth", dateofbirth);
                    request.setAttribute("dateofbirth",dateofbirth);
                    
                    //Session and request set the dateofregistration
                    String dateofregistration = jdbc.returnDatabaseField(username, "dateofregistration");
                    session.setAttribute("dateofregistration", dateofregistration);
                    request.setAttribute("dateofregistration",dateofregistration);
                    
                    //Session and request set the balance
                    String balance = jdbc.returnDatabaseField(username, "balance");
                    session.setAttribute("balance", balance);
                    request.setAttribute("balance",balance);
                    
                    //Session and request set the balance
                    String address = jdbc.returnDatabaseField(username, "address");
                    session.setAttribute("address", address);
                    request.setAttribute("address",address);

                    
                    
                    //Dispatch to the admin panel
                    request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                    request.setAttribute("msg", "Succesful login");
                      
                    break;
                case 2: //Customer panel
                    request.getSession(); //GET THE SESSION IF ONE EXISTS, OTHERWISE CREATE ONE 
                    session.setAttribute("username",query[0]); //Set username for the session
                    username=(String)session.getAttribute("username"); //getting username from session from login
                    request.setAttribute("username",username); //Set username for the forwarding request
                    
                    //Session and request set the fullname
                    fullname = jdbc.returnDatabaseField(username, "fullname");
                    session.setAttribute("fullname", fullname);
                    request.setAttribute("fullname",fullname);
                    
                    //Session and request set the profiletype
                    profiletype = jdbc.returnDatabaseField(username, "profiletype");
                    session.setAttribute("profiletype", profiletype);
                    request.setAttribute("profiletype",profiletype);
                    
                    //Session and request set the dateofbirth
                    dateofbirth = jdbc.returnDatabaseField(username, "dateofbirth");
                    session.setAttribute("dateofbirth", dateofbirth);
                    request.setAttribute("dateofbirth",dateofbirth);
                    
                    //Session and request set the dateofregistration
                    dateofregistration = jdbc.returnDatabaseField(username, "dateofregistration");
                    session.setAttribute("dateofregistration", dateofregistration);
                    request.setAttribute("dateofregistration",dateofregistration);
                    
                    //Session and request set the balance
                    balance = jdbc.returnDatabaseField(username, "balance");
                    session.setAttribute("balance", balance);
                    request.setAttribute("balance",balance);
                    
                    //Session and request set the balance
                    address = jdbc.returnDatabaseField(username, "address");
                    session.setAttribute("address", address);
                    request.setAttribute("address",address);
                    request.getRequestDispatcher("/WEB-INF/customerPanel.jsp").forward(request, response);
                    request.setAttribute("msg", "Succesful login");
                     

                    break;
                default: //Invalid - something is broken, check database for if user has an assigned profile
                    request.setAttribute("provpassword", "Invalid login"); //Setting invalid credentials message
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
