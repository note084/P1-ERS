package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.AuthDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserService userService;
    private AuthService authService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.authService = new AuthService();
        this.mapper = new ObjectMapper();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet: getByUsername - UserServlet GET");
        User user = userService.getByUsername(req.getHeader("username")).get();
        String json = mapper.writeValueAsString(user);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User Register POST");
        User userToBeRegistered = mapper.readValue(req.getInputStream(), User.class);
        User user = userService.createUser(userToBeRegistered);

        if (user.getUsername() == null) {
            String json = mapper.writeValueAsString(user);
            resp.setStatus(409);

        }
        else if (user.getEmail() == null) {
            resp.setStatus(408);
        }
        else {
            String json = mapper.writeValueAsString(user);
            resp.setStatus(201);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
