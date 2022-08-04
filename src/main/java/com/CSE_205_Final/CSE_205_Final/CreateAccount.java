package com.CSE_205_Final.CSE_205_Final;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;

public class CreateAccount {

    Statement stmt = null;

    public CreateAccount() {

    }

    public Integer[] createAccount(String type, String username){

        int accountNo;
        float balance  = 0;

        accountNo = generateAccount();

        try {
            stmt = Cse205FinalApplication.c.createStatement();
            String sql = ("INSERT INTO accounts_table (number,type,balance) "
                    + "VALUES ('"+ accountNo + "','" + type + "','" + balance + "')");
            stmt.executeUpdate(sql);

        }catch (Exception e){

            System.out.println("Error: " + e);
            return null;
        }

        return updateCustomerRecord(accountNo, username);
    }

    public Integer[] updateCustomerRecord(Integer accountNo, String username) {

        Array accounts = null;
        Integer[] accountArray = null;
        try {
            stmt = Cse205FinalApplication.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer_table where username = '" + username + "';");
            while (rs.next()) {
                accounts = rs.getArray("accounts");
                if(accounts != null)
                accountArray = (Integer[])accounts.getArray();
            }

        }catch (Exception e){

            System.out.println("Exception: " + e);
            return null;
        }

        Integer[] newAccArray = null;
        if(accounts != null) {
            newAccArray = new Integer[accountArray.length + 1];
        }else{
            newAccArray = new Integer[1];
        }

        if((accountArray != null) && (accountArray.length > 1)) {
            for (int i = 0; i < accountArray.length; i++) {

                newAccArray[i] = accountArray[i];
            }
        }

        newAccArray[newAccArray.length-1] = accountNo;

        System.out.println(Arrays.toString(newAccArray));

        try {
            stmt = Cse205FinalApplication.c.createStatement();
            String sqlUpdate = ("UPDATE customer_table SET accounts = " + '{' + Arrays.toString(newAccArray) + '}' + " where username = '" + username + "';");
            stmt.executeUpdate(sqlUpdate);
//            Cse205FinalApplication.c.commit();

        }catch (Exception e){

            System.out.println("Exception, account number update failed: " + e);
            return null;
        }

        return newAccArray;


    }
    public boolean createCustomer(String username, String password, String customerName){

        try {
            stmt = Cse205FinalApplication.c.createStatement();
            String sql = ("INSERT INTO customer_table (customer_name,username, password) "
                    + "VALUES ('"+ customerName + "','" + username + "','" + password + "')");
            stmt.executeUpdate(sql);

            System.out.println("Customer record created successfully");

        }catch (Exception e){

            System.out.println("Error: " + e);
            return false;
        }

        return true;
    }


    public int generateAccount(){

        Integer accountNo = 0;

        Random rand = new Random();

        // Generate random integers in range 0 to 9999
        return rand.nextInt(10000);
    }
}
