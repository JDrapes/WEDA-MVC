/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdbc {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;

    public Jdbc(String query) {
        //this.query = query;
    }

    public Jdbc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void connect(Connection con) {
        connection = con;
    }

    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String[] s = new String[cols];
            for (int i = 1; i <= cols; i++) {
                s[i - 1] = rs.getString(i);
            }
            aList.add(s);
        } // while    
        return aList;
    } //rsToList

    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
            b.append("<tr>");
            row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
            b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable

    private void select(String query) {
        //Statement statement = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }

    public String retrieve(String query) throws SQLException {
        String results = "";
        select(query);
//        try {
//            
//            if (rs==null)
//                System.out.println("rs is null");
//            else
//                results = makeTable(rsToList());
//        } catch (SQLException ex) {
//            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return makeTable(rsToList());//results;
    }

    //Created boolean that takes username+pasword entry and validates against database
    public int loginSuccess(String user, String password) {
        boolean bool = false;
        try {
            select("select * from users");
            while (rs.next()) {
                if (rs.getString("username").equals(user) && rs.getString("password").equals(password)) {
                    if (rs.getString("profiletype").equals("admin")) { //return int 1 for admin
                        return 1;
                    } else if (rs.getString("profiletype").equals("customer") || rs.getString("profiletype").equals("provisional")) { //return int 2 for reg customer
                        return 2;
                    } else {
                        //If they don't have a profile in database or another issue occurred
                        return 0;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //This function checks if the user exists in the database - part of registration 

    public boolean exists(String user) {
        boolean bool = false;
        try {
            select("select username from users where username='" + user + "'");
            if (rs.next()) {
                System.out.println("TRUE");
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    public boolean claimExists(String claimid) {
        boolean bool = false;
        try {
            select("select username from claims where claimid='" + claimid + "'");
            if (rs.next()) {
                System.out.println("TRUE");
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    //This inserts the record to the database when registering on the register page
    public void insert(String[] str) {
        PreparedStatement ps = null;
        try {
            //Get todays date for the date of registration
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            Date sqlDateBirth = Date.valueOf(str[3]);

            ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); //Username (email)
            ps.setString(2, str[1]); //Password
            ps.setString(3, str[2]); //Profile type
            ps.setString(4, " "); //Fullname 
            ps.setDate(5, sqlDateBirth); //Date of birth
            ps.setDate(6, sqlDate); //Date of registration 
            ps.setDouble(7, 0.00); //Balance
            ps.setString(8, " "); //Address
            ps.setDouble(9, 10); //Outstanding balance is 10 which is the yearly membership fee.
            ps.executeUpdate();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This updates the password in the database based on the username
    public void update(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim());
            ps.setString(2, str[0].trim());
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //have to check how many entries are in the claims DB and then +1 to this and return the number

    public String nextPaymentID() throws SQLException {
        String result = "";
        int rowCount = 0;
        select("select COUNT (*) from payments");
        while (rs.next()) {
            rowCount = rs.getInt(1);
        }
        rowCount = rowCount + 1; //Add 1 for return the next row
        return Integer.toString(rowCount); //Turn the int to a string as a return - DB uses VARCHAR

    }

    //This inserts the record to the database when registering on the register page
    //PARAMETERS username, paymenttype, cashdirection, paymentamount
    public boolean insertPaymentToDB(String username, String paymenttype, String cashdirection, String paymentamount) {
        if (!validateStringToDouble(paymentamount)) {
            //invalid string to double returns false
            return false;
        }
        PreparedStatement ps = null;
        try {
            //Get todays date for the date of registration
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps = connection.prepareStatement("INSERT INTO Payments VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nextPaymentID()); //Payment ID, generated value
            ps.setString(2, username); //Username
            ps.setDate(3, sqlDate); //Payment date (today)
            ps.setString(4, paymenttype); //Payment type e.g. cash withdrawl or paying their membership fees
            ps.setString(5, cashdirection); //Cash direction e.g. income or outgoing
            ps.setDouble(6, Double.parseDouble(paymentamount)); //Payment amount
            ps.executeUpdate();
            ps.close();
            System.out.println("1 row added.");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean withdrawCash(String username, String withdrawamount) throws SQLException{
        if (!validateStringToDouble(withdrawamount)) {
            //invalid string to double returns false
            return false;
        }
        //Need to convert the string to an double
        Double amountWithdrawing = Double.parseDouble(withdrawamount);
        //Get users current balance
        Double currentBalance = Double.parseDouble(returnDatabaseField(username, "balance"));
        Double newBalance = 0.00;
        //Set their current balance = current balance - amount withdrawing as long as they can afford it 
        if(currentBalance-amountWithdrawing>0){
            newBalance = currentBalance-amountWithdrawing;
        }       
        //Need to put the new balance into the database and return true after the operation
        PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("Update Users Set balance=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, newBalance);
                ps.setString(2, username);
                ps.executeUpdate();
                ps.close();
                System.out.println("1 rows updated.");
            } catch (SQLException ex) {
                Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
            }               
            //before returning true must add this to the payments table to track cashflow.
             ps = null;
        try {
            //Get todays date for the date of registration
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps = connection.prepareStatement("INSERT INTO Payments VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nextPaymentID()); //Payment ID, generated value
            ps.setString(2, username); //Username
            ps.setDate(3, sqlDate); //Payment date (today)
            ps.setString(4, "PayPal cash withdrawal"); //Payment type e.g. cash withdrawl or paying their membership fees
            ps.setString(5, "Payment from WEDA"); //Cash direction e.g. income or outgoing
            ps.setDouble(6, Double.parseDouble(withdrawamount)); //Payment amount
            ps.executeUpdate();
            ps.close();
            System.out.println("1 row added.");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        return false;
    }
    
    //Pass the parameter of username and the table column to return the individual record.
    //this is used on the profile page!
    public String returnDatabaseField(String username, String column) throws SQLException {
        String result = "";
        select("select * from users where username='" + username + "'");
        while (rs.next()) {
            result = rs.getString(column);
        }
        return result;
    }

    //Database query on the claim table.
    public String returnClaimCell(String claimid, String column) throws SQLException {
        String result = "";
        select("select * from claims where claimid='" + claimid + "'");
        while (rs.next()) {
            result = rs.getString(column);
        }
        return result;
    }

    public boolean validateStringToDouble(String check) {
        try {
            double d = Double.valueOf(check);
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return false;
    }

    public boolean makePaymentFromCard(String username, String amountToPay) throws SQLException {
        if (!validateStringToDouble(amountToPay)) {
            //invalid string to double returns false
            return false;
        }
        //Convert the string amount into a double to work with the database
        Double AmountPaying = Double.parseDouble(amountToPay);
        Double outstandingBalance = Double.parseDouble(returnDatabaseField(username, "outstandingbalance"));

        if ((AmountPaying <= outstandingBalance)) {
            //new outstanding balance after paying off the amount
            outstandingBalance = outstandingBalance - AmountPaying;
            //Update to database
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("Update Users Set outstandingbalance=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, outstandingBalance);
                ps.setString(2, username);
                ps.executeUpdate();
                ps.close();
                System.out.println("1 rows updated.");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;
    }

    //Bool function for user making a payment from existing balance
    public boolean makePaymentFromBalance(String username, String amountToPay) throws SQLException {
        if (!validateStringToDouble(amountToPay)) {
            //invalid string to double returns false
            return false;
        }
        //Convert the string amount into a double to work with the database
        Double AmountPaying = Double.parseDouble(amountToPay);
        //Return the users current balance to make sure they can afford to pay
        Double currentBalance = Double.parseDouble(returnDatabaseField(username, "balance"));
        Double outstandingBalance = Double.parseDouble(returnDatabaseField(username, "outstandingbalance"));
        //Making sure they have enough balance and they are not trying to overpay the amount they owe
        if ((currentBalance >= AmountPaying) && (AmountPaying <= outstandingBalance)) {
            //Update the outstanding balance 
            outstandingBalance = outstandingBalance - AmountPaying;
            currentBalance = currentBalance - AmountPaying;
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("Update Users Set outstandingbalance=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, outstandingBalance);
                ps.setString(2, username);
                ps.executeUpdate();
                ps.close();
                System.out.println("1 rows updated.");
            } catch (SQLException ex) {
                Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Update the current balance
            ps = null;
            try {
                ps = connection.prepareStatement("Update Users Set balance=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, currentBalance);
                ps.setString(2, username);
                ps.executeUpdate();
                ps.close();
                System.out.println("1 rows updated.");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //User cannot afford the payment they tried to make.
            return false;
        }
        return false;
    }

    //have to check how many entries are in the claims DB and then +1 to this and return the number
    public String nextClaimID() throws SQLException {
        String result = "";
        int rowCount = 0;
        select("select COUNT (*) from claims");
        while (rs.next()) {
            rowCount = rs.getInt(1);
        }
        rowCount = rowCount + 1; //Add 1 for return the next row
        return Integer.toString(rowCount); //Turn the int to a string as a return - DB uses VARCHAR

    }

    public boolean submitClaimToDB(String[] str) {
        if (!validateStringToDouble(str[1])) {
            //invalid string to double returns false
            return false;
        }
        PreparedStatement ps = null;
        try {
            //Get todays date for the date of registration
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            ps = connection.prepareStatement("INSERT INTO Claims VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nextClaimID()); //Claim ID - It's type VARCHAR so need to do int to string when pushing it
            ps.setString(2, str[0]); //Username
            ps.setString(3, str[2]); //Claim description
            ps.setDate(4, sqlDate); //Claim Date - todays date
            ps.setDouble(5, Double.parseDouble(str[1])); //Claim Amount
            ps.setString(6, "New"); //Claim Status - all claims start as new status
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Function to deny a claim
    public boolean denyClaim(String claimid) throws SQLException {
        //Need to check if claimid is a legit entry in database, if so execute below.

        if (claimExists(claimid)) {
            String claimStatus = returnClaimCell(claimid, "claimstatus"); //Get status of the claim
            if (claimStatus.toLowerCase().equals("new") || claimStatus.toLowerCase().equals("in progress")) { //if it's new or in progress we can deny it
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement("Update Claims Set claimstatus=? where claimid=?", PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, "Denied and closed"); //claim status
                    ps.setString(2, claimid); //claimid
                    ps.executeUpdate();
                    ps.close();
                    System.out.println("1 rows updated.");
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    //Function to approve a claim
    public boolean approveClaim(String claimid, String username) throws SQLException {
        //Need to check if claim ID is legit in database before proceeding
        if (claimExists(claimid)) {
            String claimStatus = returnClaimCell(claimid, "claimstatus"); //Get status of the claim
            if (claimStatus.toLowerCase().equals("new") || claimStatus.toLowerCase().equals("in progress")) { //if it's new or in progress we can deny it
                //Get current balance and how much the claim is worth
                double currentBalance = Double.parseDouble(returnDatabaseField(username, "balance"));
                double claimMoney = Double.parseDouble(returnClaimCell(claimid, "claimamount"));
                //add the values together and this is their new balance
                double newBalance = currentBalance + claimMoney;
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement("Update Users Set balance=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setDouble(1, newBalance); //balance
                    ps.setString(2, username); //username
                    ps.executeUpdate();

                    ps.close();
                    System.out.println("1 rows updated.");
                } catch (SQLException ex) {
                    Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Need to update the status to say approved
                ps = null;
                try {
                    ps = connection.prepareStatement("Update Claims Set claimstatus=? where claimid=?", PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, "Approved and closed"); //claim status
                    ps.setString(2, claimid); //claimid
                    ps.executeUpdate();

                    ps.close();
                    System.out.println("1 rows updated.");
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    //This updates the password in the database based on the username
    public void updatePersonalDetails(String[] str, String DOB) {
        Date date = Date.valueOf(DOB);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users set username=? , fullname=? , dateofbirth=? , address=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0]); //username
            ps.setString(2, str[1]); //fullname
            ps.setDate(3, date); //date
            ps.setString(4, str[2]); //address
            ps.setString(5, str[0]); //username 
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean upgradeProvisionalToMember(String upgradeUser) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set profiletype=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, "customer");
            ps.setString(2, upgradeUser);

            select("select * from users"); //Before changing to "customer" check that they are actually provisional
            while (rs.next()) {
                if (rs.getString("username").equals(upgradeUser)) {
                    if (rs.getString("profiletype").equals("provisional")) {
                        //If user exists in database & is provisional then we can execute the statement.
                        ps.executeUpdate();
                        ps.close();
                        System.out.println("Member upgraded.");
                        return true;

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean resumeMembership(String upgradeUser) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set profiletype=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, "provisional");
            ps.setString(2, upgradeUser);

            select("select * from users"); //Before changing to "customer" check that they are actually suspended
            while (rs.next()) {
                if (rs.getString("username").equals(upgradeUser)) {
                    if (rs.getString("profiletype").equals("suspended")) {
                        //If user exists in database & is provisional then we can execute the statement.
                        ps.executeUpdate();
                        ps.close();
                        System.out.println("Member upgraded.");
                        return true;

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean suspendMembership(String upgradeUser) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set profiletype=? where username=?", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, "suspended");
            ps.setString(2, upgradeUser);

            select("select * from users"); //Suspend the selected member
            while (rs.next()) {
                if (rs.getString("username").equals(upgradeUser)) {
                    //If user exists in database & is provisional then we can execute the statement.
                    ps.executeUpdate();
                    ps.close();
                    System.out.println("Member upgraded.");
                    return true;

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //this function deletes a record frm database, parameter is the username
    public void deleteUser(String upgradeUser) {

        String query = "DELETE FROM Users "
                + "WHERE username = '" + upgradeUser.trim() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }

    //Logout function
    public boolean logout() {
        try {
            rs.close();
            statement.close();
            connection.close();
            return true; //Successful logout
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; //Failed to logout
    }

    //Closes all conections
    public void closeAll() {
        try {
            rs.close();
            statement.close();
            //connection.close();                                         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Function to generate random password on registration
    public String generateRandomPassword() {
        //Generates a random 6 string password for registering users
        int PASS_LENGTH = 6;
        String password = "";
        int[] passwordArray = new int[6];
        Random rand = new Random();

        for (int x = 0; x < PASS_LENGTH; x++) {
            passwordArray[x] = rand.nextInt(10);
            password += (Integer.toString(passwordArray[x]));
        }
        return password;
    }

    public static void main(String[] args) throws SQLException {

    }
}
