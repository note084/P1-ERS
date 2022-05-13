package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.EditReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateReimbursementServlet extends HttpServlet {
    private ReimbursementService rService;
    private UserService uService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.rService = new ReimbursementService();
        this.uService = new UserService();
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet: reimbursementByID GET");
        Reimbursement rModel = rService.getReimbursementByID(req.getIntHeader("id"));
        String json = mapper.writeValueAsString(rModel);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPut: reimbursement");
        EditReimbursementDTO rDTO = mapper.readValue(req.getInputStream(), EditReimbursementDTO.class);
        Reimbursement rToBeUpdated = rService.getReimbursementByID(rDTO.getId());
        rToBeUpdated.setAmount(rDTO.getAmount());
        rToBeUpdated.setType(rDTO.getType());
        rToBeUpdated = rService.editReimbursement(rToBeUpdated);
        String json = mapper.writeValueAsString(rToBeUpdated);
        resp.getWriter().print(json);
        resp.setStatus(201);

    }
}
