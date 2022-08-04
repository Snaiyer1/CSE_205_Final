package com.CSE_205_Final.CSE_205_Final;

import org.apache.juli.logging.Log;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

    private String customerName;
    private String username;
    private String password;
    private Account[] accounts;
    private Account account;

    public Customer(String username, String password, String customerName) {
        this.username = username;
        this.password = password;
        this.customerName = customerName;
    }

    public String getUsername() {
        return this.username;
    }

    public Account[] getAccounts() {

        return this.accounts;
    }

    public void setName(String userName) {

        this.username = userName;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public Account[] createAccount(String type) {

        CreateAccount createAccount = new CreateAccount();

        Login login = new Login();
        return login.getCustomerAccounts(createAccount.createAccount(type, this.username));

    }

    public Boolean removeAccount(int num) {
        return true;
    }

    /**
     *
     * @param amount
     * @param from
     * @param to
     * @return
     */

    public Boolean transfer(Float amount, int from, int to) {

        Statement stmt1 = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        Double fromBalance = 0.0;
        Double toBalance = 0.0;

        //Read first from account record

        Array accounts = null;
        Integer[] accountArray = null;
        try {
            stmt1 = Cse205FinalApplication.c.createStatement();
            rs = stmt1.executeQuery("SELECT * FROM accounts_table where number = '" + from + "';");
            while (rs.next()) {
                fromBalance = rs.getDouble("balance");
            }

            stmt2 = Cse205FinalApplication.c.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM accounts_table where number = '" + to + "';");
            while (rs.next()) {
                toBalance = rs.getDouble("balance");
            }

        }catch (Exception e){

            System.out.println("Exception: " + e);
            return null;
        }

        //Updating from balance and to balance with the given amount
        fromBalance = fromBalance - amount;
        toBalance = toBalance + amount;


        //Update DB with new from and to balance
        //Add update statement here TODO



        return true;
    }

}
