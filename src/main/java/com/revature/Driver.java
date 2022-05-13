package com.revature;

import com.revature.DBConnection.ConnectionManager;
import com.revature.models.*;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
/*        User test = new User();
        test.setFirst_name("gio");
        test.setLast_name(("amirajibi"));
        test.setRole(Role.FINANCE_MANAGER);
        test.setUsername("gio1");
        test.setPassword("password");
        test.setEmail("gio@gmail.com");

        UserDAO dao = new UserDAO();
        dao.create(test);
        System.out.println(dao.getByUsername(test.getUsername()));*/

/*      UserDAO userModel = new UserDAO();
        ReimbursementDAO reimburse = new ReimbursementDAO();
        Reimbursement item = new Reimbursement();

        item = reimburse.getByID(1);
        item.setId(1);
        item.setAmount(1.00);
        item.setAuthor(userModel.getByUserID(1));
        item.setResolver(userModel.getByUserID(2));
        item.setStatus(Status.APPROVED);

        reimburse.update(item);

        List<Reimbursement> list = reimburse.getByAuthorId(1);
        for (Reimbursement temp : list) {
            System.out.println( "Reimbursement ID: " + temp.getId() + "\n" +
                                "Status: " + temp.getStatus() + "\n" +
                                "Author: " + temp.getAuthor() + "\n" +
                                "Resolver: " + temp.getResolver() + "\n" +
                                String.format("Amount: $%.2f", temp.getAmount()));
        }*/
        AuthService user = new AuthService();
        user.login("admin", "password");
        UserDAO userModel = new UserDAO();
 /*       User userToBeRegistered = new User();
        userToBeRegistered.setFirst_name("Bob");
        userToBeRegistered.setLast_name("Trueman");
        userToBeRegistered.setEmail("Bob@gmail.com");
        userToBeRegistered.setUsername("Bob991");
        userToBeRegistered.setPassword("bobpass");
        userToBeRegistered.setRole(Role.FINANCE_MANAGER);
        userToBeRegistered.setId(0);

        user.register(userToBeRegistered);*/



    }
}