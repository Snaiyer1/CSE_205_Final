package com.CSE_205_Final.CSE_205_Final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class Cse205FinalApplication {

	public static Connection c = null;
	public static Statement stmt = null;
	public static void main(String[] args) {


		SpringApplication.run(Cse205FinalApplication.class, args);

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/postgres",
							"postgres", "123");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		createAccount("Saving");
		login();
	}

	public static boolean createAccount(String type){

		int accountNo;
		float balance  = 0;

		CreateAccount createAccount = new CreateAccount();

		createAccount.createAccount( "Saving44", "zoeyn");
//		createAccount.createCustomer("zoeyn", "1234", "Zoey Naiyer");
//		createAccount.updateCustomerRecord(1234, "shizan");

		return true;
	}


	public static boolean login(){

		Login login = new Login();

		Customer customer = login.login( "shizan", "1234");

		System.out.println(customer.getUsername());

		return true;
	}


}
