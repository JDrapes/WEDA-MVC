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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            throws ServletException, IOException, SQLException, ParseException {
        String qry = "select * from users";
        //CREATE SESSION IF ONE DOES NOT ALREADY EXIST
        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        if ((Connection) request.getServletContext().getAttribute("connection") == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        //When we pass the login page we need to assign the profile page values from DB to session.
        String username = (String) session.getAttribute("username"); //getting username from session from login
        String fullname = dbBean.returnDatabaseField(username, "fullname");
        session.setAttribute("fullname", fullname);
        String profiletype = dbBean.returnDatabaseField(username, "profiletype");
        session.setAttribute("profiletype", profiletype);
        String dateofbirth = dbBean.returnDatabaseField(username, "dateofbirth");
        session.setAttribute("dateofbirth", dateofbirth);
        String dateofregistration = dbBean.returnDatabaseField(username, "dateofregistration");
        session.setAttribute("dateofregistration", dateofregistration);
        String balance = dbBean.returnDatabaseField(username, "balance");
        session.setAttribute("balance", balance);
        String address = dbBean.returnDatabaseField(username, "address");
        session.setAttribute("address", address);

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

        } else if (request.getParameter("tbl").equals("Customer profile page")) {
            //gets the session items
            request.setAttribute("username", username);
            request.setAttribute("profiletype", profiletype);
            request.setAttribute("fullname", fullname);
            request.setAttribute("dateofbirth", dateofbirth);
            request.setAttribute("dateofregistration", dateofregistration);
            request.setAttribute("balance", balance);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/customerPanel.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Admin profile page")) {
            //gets the session items
            request.setAttribute("username", username);
            request.setAttribute("profiletype", profiletype);
            request.setAttribute("fullname", fullname);
            request.setAttribute("dateofbirth", dateofbirth);
            request.setAttribute("dateofregistration", dateofregistration);
            request.setAttribute("balance", balance);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
        } //List all claims as an admin.
        else if (request.getParameter("tbl").equals("List all claims")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/listAllClaims.jsp").forward(request, response);

        }
        else if (request.getParameter("tbl").equals("Process individual claims")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/processIndividualClaims.jsp").forward(request, response);

        }//Function to take you to the page to manage memberships.
        else if (request.getParameter("tbl").equals("Manage Memberships")) {
            qry = "select username, profiletype from users";//Only want to put username and prof type in the table
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
            qry = "select username, profiletype from users";//Only want to put username and prof type in the table
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
            qry = "select username, profiletype from users";//Only want to put username and prof type in the table
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
            qry = "select username, profiletype from users";//Only want to put username and prof type in the table
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

        } else if (request.getParameter("tbl").equals("Delete a user")) {
            qry = "select username, profiletype from users"; //Only want to put username and prof type in the table
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

        } //When updating profile page need to update details and redirect back to admin/user panel
        else if (request.getParameter("tbl").equals("Update profile details")) {

            String[] query = new String[3];

            query[0] = (String) request.getParameter("username");
            query[1] = (String) request.getParameter("fullname");
            query[2] = (String) request.getParameter("address");
            String stringdateofbirth = request.getParameter("dateofbirth");
            dbBean.updatePersonalDetails(query, stringdateofbirth);

            //gets the session items
            request.setAttribute("username", query[0]);
            request.setAttribute("profiletype", profiletype);
            request.setAttribute("fullname", query[1]);
            request.setAttribute("dateofbirth", stringdateofbirth);
            request.setAttribute("dateofregistration", dateofregistration);
            request.setAttribute("balance", balance);
            request.setAttribute("address", query[2]);
            //reload back onto the correct panel

            if (profiletype.equals("admin")) {
                request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/customerPanel.jsp").forward(request, response);

            }

        } //CUSTOMER FUNCTIONALITY
        //Check outstanding balance
        else if (request.getParameter("tbl").equals("Check outstanding balance")) {
            request.setAttribute("username", username);

        } //List all payments and claims to date
        else if (request.getParameter("tbl").equals("List all payments and claims to date")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/listPersonalClaimsAndPayments.jsp").forward(request, response);

        } //Make a payment
        else if (request.getParameter("tbl").equals("Make a payment")) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
