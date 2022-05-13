package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private ReimbursementService rService;
    private UserService uService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.rService = new ReimbursementService();
        this.uService = new UserService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet: reimbursementByAuthorID GET");
        List<Reimbursement> list = rService.getReimbursementsByAuthorID(req.getIntHeader("id"));
        String json = mapper.writeValueAsString(list);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Reimbursement Submit POST");
        ReimbursementDTO rDTO = mapper.readValue(req.getInputStream(), ReimbursementDTO.class);
        System.out.println(1);
        Reimbursement rItem = new Reimbursement();
        rItem.setType(rDTO.getType());
        rItem.setStatus(rDTO.getStatus());
        rItem.setAuthor(uService.getByUserID(rDTO.getAuthor_id()).get());
        rItem.setAmount(rDTO.getAmount());
        System.out.println(2);
        rItem.setDate_created(rDTO.getDate_created());
        rItem.setDate_resolved("N/A");
        System.out.println(3);
        rItem = rService.createReimbursement(rItem);
        System.out.println(4);

        String json = mapper.writeValueAsString(rItem);
        resp.setStatus(201);
        resp.getWriter().println(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement rModel = rService.getReimbursementByID(req.getIntHeader("id"));
        rService.delete(rModel);

        resp.setStatus(200);

    }
}
