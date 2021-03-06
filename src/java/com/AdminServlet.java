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
        //Added outstanding balance for making a payment - MC 
        String outstandingbalance = dbBean.returnDatabaseField(username, "outstandingbalance");
        session.setAttribute("outstandingbalance", outstandingbalance);

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
            request.setAttribute("provpassword", ""); //prov password should be invisible unless they are redirected from register
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } //LOGOUT FUNCTION
        else if (request.getParameter("tbl").equals("Logout")) {
            request.setAttribute("provpassword", ""); //prov password should be invisible unless they are redirected from register
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } //DELETE A USER FUNCTION
        else if (request.getParameter("tbl").equals("del")) {
            request.setAttribute("username", username);
            request.setAttribute("msg", "del");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Send bill")) {
            request.setAttribute("username", username);
            String amountbill = (String) request.getParameter("amounttobill");
            if (dbBean.billMembers(amountbill)) {
                //Success
            }
            //Set the query as selecting all from claims table
            qry = "select paymentamount, username, paymentdate, paymenttype, cashdirection from payments";
            String msg = "No payments";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String income = dbBean.calculateIncome();
            String outgoing = dbBean.calculateOutgoing();
            String netturnover = dbBean.calculateTurnover();
            request.setAttribute("netturnover", netturnover);
            request.setAttribute("incometransactions", income);
            request.setAttribute("outgoingtransactions", outgoing);
            request.setAttribute("alltransactions", msg);
            request.getRequestDispatcher("/WEB-INF/turnover.jsp").forward(request, response);
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
        } //List all outstanding balances to the admin 
        else if (request.getParameter("tbl").equals("List all outstanding balances")) {
            request.setAttribute("username", username);
            qry = "select username, outstandingbalance from users where outstandingbalance!=0";//Only want to put username and prof type in the table
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/listOutstandingBalances.jsp").forward(request, response);

        } //List all claims as an admin.
        else if (request.getParameter("tbl").equals("List all claims")) {
            //Set the query as selecting all from claims table
            qry = "select * from claims";
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Retrieve it on the page
            request.setAttribute("query", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/listAllClaims.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Process individual claims")) {
            request.setAttribute("message", "");
            request.setAttribute("claimid", "");
            request.setAttribute("claimnumber", "");
            request.setAttribute("claimusername", "");
            request.setAttribute("claimdate", "");
            request.setAttribute("claimamount", "");
            request.setAttribute("claimstatus", "");
            request.setAttribute("claimdescription", "");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/processIndividualClaims.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Show claim information")) {
            //Need to take the info from the text field and use it to populate rest of fields

            request.setAttribute("message", "");
            request.setAttribute("username", username);
            String claimid = (String) request.getParameter("claimid");
            String claimnumber = dbBean.returnClaimCell(claimid, "claimid");
            request.setAttribute("claimnumber", claimnumber);
            String claimusername = dbBean.returnClaimCell(claimid, "username");
            request.setAttribute("claimusername", claimusername);
            String claimdescription = dbBean.returnClaimCell(claimid, "claimdescription");
            request.setAttribute("claimdescription", claimdescription);
            String claimdate = dbBean.returnClaimCell(claimid, "claimdate");
            request.setAttribute("claimdate", claimdate);
            String claimamount = dbBean.returnClaimCell(claimid, "claimamount");
            request.setAttribute("claimamount", claimamount);
            String claimstatus = dbBean.returnClaimCell(claimid, "claimstatus");
            request.setAttribute("claimstatus", claimstatus);
            request.getRequestDispatcher("/WEB-INF/processIndividualClaims.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Payout claim")) {
            request.setAttribute("username", username);
            //Username mus equal the username of the user whos claim we approving 
            String claimnumber = (String) request.getParameter("claimnumber");
            String claimusername = (String) request.getParameter("claimusername");
            if (dbBean.approveClaim(claimnumber, claimusername)) {
                request.setAttribute("message", "Claim PAID - Enter a new claim number");
            } else {
                request.setAttribute("message", "There was an error with this request");

            }
            request.setAttribute("claimid", "");
            request.setAttribute("claimnumber", "");
            request.setAttribute("claimusername", "");
            request.setAttribute("claimdate", "");
            request.setAttribute("claimamount", "");
            request.setAttribute("claimstatus", "");
            request.setAttribute("claimdescription", "");
            request.getRequestDispatcher("/WEB-INF/processIndividualClaims.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Deny claim")) {
            request.setAttribute("username", username);
            String claimnumber = (String) request.getParameter("claimnumber");
            if (dbBean.denyClaim(claimnumber)) {
                request.setAttribute("message", "Claim DENIED - Enter a new claim number");
            } else {
                request.setAttribute("message", "There was an error with this request");

            }
            request.setAttribute("claimid", "");
            request.setAttribute("claimnumber", "");
            request.setAttribute("claimusername", "");
            request.setAttribute("claimdate", "");
            request.setAttribute("claimamount", "");
            request.setAttribute("claimstatus", "");
            request.setAttribute("claimdescription", "");
            request.getRequestDispatcher("/WEB-INF/processIndividualClaims.jsp").forward(request, response);
        } //Function to take you to the page to manage memberships.
        else if (request.getParameter("tbl").equals("Manage Memberships")) {
            qry = "select username, profiletype, outstandingbalance from users";//Only want to put username and prof type in the table
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select username from users where profiletype='provisional' and outstandingbalance=0.0";//Only want to put username and prof type in the table
            msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
                if (msg == null) {
                    msg = "";
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("outstandingMembers", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Upgrade provisional member")) {
            qry = "select username, profiletype, outstandingbalance from users";//Only want to put username and prof type in the table
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.upgradeProvisionalToMember(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);

            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select username from users where profiletype='provisional' and outstandingbalance=0.0";//Only want to put username and prof type in the table
            msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
                if (msg == null) {
                    msg = "";
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("outstandingMembers", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);
        } //Logic to suspend a membership
        else if (request.getParameter("tbl").equals("Suspend membership")) {
            qry = "select username, profiletype, outstandingbalance from users";//Only want to put username and prof type in the table
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.suspendMembership(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);

            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select username from users where profiletype='provisional' and outstandingbalance=0.0";//Only want to put username and prof type in the table
            msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
                if (msg == null) {
                    msg = "";
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("outstandingMembers", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } //Logic to resume a suspended member
        else if (request.getParameter("tbl").equals("Resume membership")) {
            qry = "select username, profiletype,outstandingbalance from users";//Only want to put username and prof type in the table
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.resumeMembership(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select username from users where profiletype='provisional' and outstandingbalance=0.0";//Only want to put username and prof type in the table
            msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
                if (msg == null) {
                    msg = "";
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("outstandingMembers", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Delete a user")) {
            qry = "select username, profiletype,outstandingbalance from users"; //Only want to put username and prof type in the table
            String upgradeUser = (String) request.getParameter("userToUpgrade");
            dbBean.deleteUser(upgradeUser);
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select username from users where profiletype='provisional' and outstandingbalance=0.0";//Only want to put username and prof type in the table
            msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
                if (msg == null) {
                    msg = "";
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("outstandingMembers", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/upgradeMembers.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Lookup user")) {
            request.setAttribute("username", username);
            request.setAttribute("lookupemail", "");
            request.setAttribute("lookupusername", "");
            request.setAttribute("lookupfullname", "");
            request.setAttribute("lookupprofiletype", "");
            request.setAttribute("lookupdob", "");
            request.setAttribute("lookupdateofregistration", "");
            request.setAttribute("lookupoutstandingbalance", "");
            request.setAttribute("lookupbalance", "");
            request.setAttribute("lookupaddress", "");

            request.getRequestDispatcher("/WEB-INF/lookupUser.jsp").forward(request, response);
            //Show user info
        } else if (request.getParameter("tbl").equals("Show user information")) {
            request.setAttribute("username", username);
            //Get the user input and store as String
            String lookupemail = (String) request.getParameter("lookupemail");
            request.setAttribute("lookupemail", lookupemail);
            //Need to get all the other fields from DB
            String lookupfullname = dbBean.returnDatabaseField(lookupemail, "fullname");
            request.setAttribute("lookupfullname", lookupfullname);
            String lookupprofiletype = dbBean.returnDatabaseField(lookupemail, "profiletype");
            request.setAttribute("lookupprofiletype", lookupprofiletype);
            String lookupdob = dbBean.returnDatabaseField(lookupemail, "dateofbirth");
            request.setAttribute("lookupdob", lookupdob);
            String lookupdateofregistration = dbBean.returnDatabaseField(lookupemail, "dateofregistration");
            request.setAttribute("lookupdateofregistration", lookupdateofregistration);
            String lookupoutstandingbalance = dbBean.returnDatabaseField(lookupemail, "outstandingbalance");
            request.setAttribute("lookupoutstandingbalance", lookupoutstandingbalance);
            String lookupbalance = dbBean.returnDatabaseField(lookupemail, "balance");
            request.setAttribute("lookupbalance", lookupbalance);
            String lookupaddress = dbBean.returnDatabaseField(lookupemail, "address");
            request.setAttribute("lookupaddress", lookupaddress);
            request.getRequestDispatcher("/WEB-INF/lookupUser.jsp").forward(request, response);

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

        } else if (request.getParameter("tbl").equals("Annual turnover")) {
            request.setAttribute("username", username);

            //Set the query as selecting all from claims table
            qry = "select paymentamount, username, paymentdate, paymenttype, cashdirection from payments";
            String msg = "No payments";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String income = dbBean.calculateIncome();
            String outgoing = dbBean.calculateOutgoing();
            String netturnover = dbBean.calculateTurnover();
            request.setAttribute("netturnover", netturnover);
            request.setAttribute("incometransactions", income);
            request.setAttribute("outgoingtransactions", outgoing);
            request.setAttribute("alltransactions", msg);
            request.getRequestDispatcher("/WEB-INF/turnover.jsp").forward(request, response);

        } //CUSTOMER FUNCTIONALITY
        //Check outstanding balance
        else if (request.getParameter("tbl").equals("Check outstanding balance")) {
            request.setAttribute("username", username);

        } //List all payments and claims to date
        else if (request.getParameter("tbl").equals("List all payments and claims to date")) {
            //Set the query as selecting all from claims table
            qry = "select claimid, claimdate, claimdescription, claimamount, claimstatus from claims where username='" + username + "'";
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            qry = "select paymentdate, paymenttype, paymentamount, cashdirection from payments where username='" + username + "'";
            String payments = "No users";
            try {
                payments = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("paymentquery", payments);

            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/listPersonalClaimsAndPayments.jsp").forward(request, response);

        } //Make a payment  *****Max*****   
        else if (request.getParameter("tbl").equals("Make a payment")) {
            request.setAttribute("username", username);
            request.setAttribute("outstandingbalance", outstandingbalance);
            request.setAttribute("address", address);
            request.setAttribute("responseMessage", ""); //set as empty on load
            request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);
        } //Pay with card - does not take from users current balance
        else if (request.getParameter("tbl").equals("Pay with card")) {
            request.setAttribute("username", username);
            request.setAttribute("address", address);
            String amountToPay = (String) request.getParameter("amountToPay"); //get amount as string - function maniuplate it as double
            String cardNumber = (String) request.getParameter("creditcardnumber"); //get card number as string
            if (dbBean.makePaymentFromCard(username, amountToPay, cardNumber)) {
                request.setAttribute("responseMessage", "Succesful payment, thank you!"); //
                //PARAMETERS to add to payments table username, paymenttype, cashdirection, paymentamount
                dbBean.insertPaymentToDB(username, "Paying outstanding balance", "Payment to WEDA", amountToPay);
            } else {
                request.setAttribute("responseMessage", "Payment failed, please check card details"); //

            }
            outstandingbalance = dbBean.returnDatabaseField(username, "outstandingbalance");
            request.setAttribute("outstandingbalance", outstandingbalance);
            request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);
        } //Need to take from existing balance as long as they can afford it
        else if (request.getParameter("tbl").equals("Pay with existing balance")) {
            request.setAttribute("username", username);
            request.setAttribute("outstandingbalance", outstandingbalance);
            String amountToPay = (String) request.getParameter("amountToPay"); //get amount as string - function maniuplate it as double
            if (dbBean.makePaymentFromBalance(username, amountToPay)) {
                //success message
                outstandingbalance = dbBean.returnDatabaseField(username, "outstandingbalance");
                request.setAttribute("outstandingbalance", outstandingbalance);
                request.setAttribute("address", address);
                request.setAttribute("responseMessage", "Succesful payment, thank you!"); //
                //PARAMETERS to add to payments table username, paymenttype, cashdirection, paymentamount
                dbBean.insertPaymentToDB(username, "Paying outstanding balance", "Payment to WEDA", amountToPay);
                request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);

            } else {
                //Fail message
                request.setAttribute("address", address);
                request.setAttribute("responseMessage", "There was a problem processing your payment, ensure you have funds and are not trying to pay more than the outstanding balance"); //
                request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);
            }

            request.getRequestDispatcher("/WEB-INF/makeAPayment.jsp").forward(request, response);
        } //Submit a claim - this is the link on the side panel
        else if (request.getParameter("tbl").equals("Submit a claim")) {
            request.setAttribute("claimamount", "");
            request.setAttribute("claimdescription", "");
            request.setAttribute("responseMessage", "");
            request.setAttribute("username", username);
            request.getRequestDispatcher("WEB-INF/submitAClaim.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Submit my claim")) {
            request.setAttribute("username", username);
            String[] query = new String[3];
            query[0] = (String) request.getAttribute("username");
            query[1] = (String) request.getParameter("claimamount");
            query[2] = (String) request.getParameter("claimdescription");

            if (dbBean.submitClaimToDB(query)) {
                //Reset the form
                request.setAttribute("claimamount", "");
                request.setAttribute("claimdescription", "");
                request.setAttribute("responseMessage", "Claim submitted succesfully");
                request.getRequestDispatcher("WEB-INF/submitAClaim.jsp").forward(request, response);
            } else {
                request.setAttribute("claimamount", "");
                request.setAttribute("claimdescription", "");
                request.setAttribute("responseMessage", "This claim submission was not succesful");
                request.getRequestDispatcher("WEB-INF/submitAClaim.jsp").forward(request, response);
            }

        } else if (request.getParameter("tbl").equals("Withdraw balance")) {
            request.setAttribute("username", username);
            request.setAttribute("balance", balance);
            request.setAttribute("responseMessage", "");

            request.getRequestDispatcher("WEB-INF/withdrawBalance.jsp").forward(request, response);

        } else if (request.getParameter("tbl").equals("Withdraw my cash")) {
            String withdrawamount = request.getParameter("withdrawamount");
            if (dbBean.withdrawCash(username, withdrawamount)) {
                //success
                request.setAttribute("responseMessage", "Your money has been withdraw succesfully!");
            } else {
                //fail                
                request.setAttribute("responseMessage", "There was an error processing your withdraw...");
            }
            //Redirects regardless
            request.setAttribute("username", username);
            request.setAttribute("balance", dbBean.returnDatabaseField(username, "balance"));
            request.getRequestDispatcher("WEB-INF/withdrawBalance.jsp").forward(request, response);
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
