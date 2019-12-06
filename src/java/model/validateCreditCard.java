/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Pattern;

/**
 *
 * @author jordandraper
 */
public class validateCreditCard {

    public static boolean validateCard(String card) {
        String s = card;
        s = s.replace("-", ""); //replace the -'s with nospace.
        s = s.replace(" ", ""); //replace the -'s with nospace.
        
        //REGEX for a few credit card formats
        if (Pattern.matches("^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|\n"
                + "		(?<mastercard>5[1-5][0-9]{14})|\n"
                + "		(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|\n"
                + "		(?<amex>3[47][0-9]{13})|\n"
                + "		(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|\n"
                + "		(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$", s)) {

            //Rexeg first allows valid card numbers only, then runs check function on the number.
            if (Check(s) == true) {
                System.out.println("Valid credit card");
                return true;
            }
        } else {
            //Invalid entry
            System.out.println("Invalid credit card");
            return false;
        }
        return false;

    }

    //Created function for checking credit card number
    public static boolean Check(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) //working backwards from last number
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2; //double every alternate digit
                if (n > 9) {
                    n = (n % 10) + 1; //if greater than 10 mod 10 and add 1
                }
            }
            sum += n; //add it to the calculations 
            alternate = !alternate; //invert the boolean of alternation
        }
        return (sum % 10 == 0);
        //if 0 returns true for valid number
    }

}
