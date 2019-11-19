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

    //This inserts the record to the database when registering on the register page
    public void insert(String[] str) {
        PreparedStatement ps = null;
        try {
            //Get todays date for the date of registration
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); //Username (email)
            ps.setString(2, str[1]); //Password
            ps.setString(3, str[2]); //Profile type
            ps.setString(4, " "); //Fullname 
            ps.setString(5, str[3]); //Date of birth
            ps.setDate(6, sqlDate); //Date of registration 
            ps.setDouble(7, 0.00); //Balance
            ps.setString(8, " "); //Address
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
    
        //This updates the password in the database based on the username
    public void updatePersonalDetails(String[] str, String DOB) {         
        Date date=Date.valueOf(DOB);                        
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
