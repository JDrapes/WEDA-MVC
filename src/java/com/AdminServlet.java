/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

public class AdminServlet extends HttpServlet {

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
        String qry = "select * from users";
        //CREATE SESSION IF ONE DOES NOT ALREADY EXIST
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username"); //getting username from session from login

        response.setContentType("text/html;charset=UTF-8");

        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        if ((Connection) request.getServletContext().getAttribute("connection") == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        //ADMIN FUNCTION
        if (request.getParameter("tbl").equals("List Users")) {
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
        } //REGISTRATION FUNCTION
        else if (request.getParameter("tbl").equals("Create a new user")) {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } //CHANGE PASSWORD FUNCTION
        else if (request.getParameter("tbl").equals("Change my password")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
        } //SIGN IN FUNCTION
        else if (request.getParameter("tbl").equals("Sign in")) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } //LOGOUT FUNCTION
        else if (request.getParameter("tbl").equals("Logout")) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } //DELETE A USER FUNCTION
        else if (request.getParameter("tbl").equals("del")) {
            request.setAttribute("username", username);
            request.setAttribute("msg", "del");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Admin profile page")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
        } //Function to take you to the page to manage memberships.
        else if (request.getParameter("tbl").equals("Manage Memberships")) {
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Upgrade provisional member")) {
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.upgradeProvisionalToMember(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);
        } //Logic to suspend a membership
        else if (request.getParameter("tbl").equals("Suspend membership")) {
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.suspendMembership(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } //Logic to resume a suspended member
        else if (request.getParameter("tbl").equals("Resume membership")) {
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.resumeMembership(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } 
        else if (request.getParameter("tbl").equals("Delete a user")) {
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.deleteUser(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } 
        
//CUSTOMER FUNCTIONALITY
        //Check outstanding balance
        else if (request.getParameter("tbl").equals("Check outstanding balance")) {
            request.setAttribute("username", username);

        } //List all payments and claims to date
        else if (request.getParameter("tbl").equals("List all payments and claims to date")) {
            request.setAttribute("username", username);

        } //Make a payment
        else if (request.getParameter("tbl").equals("Make a payment")) {
            request.setAttribute("username", username);

        } //Submit a claim
        else if (request.getParameter("tbl").equals("Submit a claim")) {
            request.setAttribute("username", username);

        } //DEFAULT - CONN ERROR CALL IF THERE'S AN ERROR OR INVALID REDIRECT
        else {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
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
