package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.AuthDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private AuthService authService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.authService = new AuthService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Auth Login");
        AuthDTO userAuth = mapper.readValue(req.getInputStream(), AuthDTO.class);
        User user = authService.login(userAuth.getUsername(), userAuth.getPassword());
        System.out.println(user);
        if (user.getUsername() == null) {
            resp.setStatus(308);
        }
        else if (user.getPassword() == null){
            resp.setStatus(309);
        }
        else {
            resp.setHeader("access-control-expose-headers", "authToken");
            resp.setHeader("authToken", user.getUsername());
            resp.setStatus(200);
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
