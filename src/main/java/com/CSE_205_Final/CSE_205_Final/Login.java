package com.CSE_205_Final.CSE_205_Final;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

    Statement stmt_login = null;

    public Login(){
    }

    public Customer login(String username, String inputPassword) {

        String userName = "";
        String dbPassword = "";
        String custName = "";
        Array accountsRS = null;
        Integer[] accountArray = null;


        try {
            stmt_login = Cse205FinalApplication.c.createStatement();

            Statement stmt;
            ResultSet rs = stmt_login.executeQuery("SELECT * FROM customer_table where username = '" + username + "';");
            while (rs.next()) {
                userName = rs.getString("username");
                dbPassword = rs.getString("password");
                custName = rs.getString("customer_name");
                accountsRS = rs.getArray("accounts");
                accountArray = (Integer[]) accountsRS.getArray();
            }

        } catch (Exception e) {

            System.out.println("Invalid username, record not found: " + e);
            return null;
        }

        if ((!userName.equals(username)) || (!dbPassword.equals(inputPassword))) {

            System.out.println("Invalid username or password");
            return null;
        }

        Customer customer = new Customer(userName, dbPassword, custName);

        customer.setAccounts(getCustomerAccounts(accountArray));

        return customer;

    }

        /**
         * Retrieve all accounts for the customer
         */

        public Account[] getCustomerAccounts(Integer[] accountArray) {

            Integer accountNum = 0;
            String accountType = "";
            double balance = 0;
             Account[] accounts = new Account[accountArray.length];

             for (int i = 0; i < accountArray.length; i++) {

            Account account = new Account();
            try {
                stmt_login = Cse205FinalApplication.c.createStatement();

                Statement stmt;
                ResultSet rs = stmt_login.executeQuery("SELECT * FROM accounts_table where number = '" + accountArray[i] + "';");
                while (rs.next()) {
                    accountNum = rs.getInt("number");
                    accountType = rs.getString("type");
                    balance = rs.getDouble("balance");

                    account.setNum(accountNum);
                    account.setBalance(balance);
                    account.setType(accountType);

                    accounts[i] = account;
                }

            }catch (Exception e) {

                System.out.println("Record not found for the account no: " + e);
                return null;
            }

        }

             return accounts;
    }
}
