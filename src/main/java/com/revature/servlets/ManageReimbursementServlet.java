package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ManageReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageReimbursementServlet extends HttpServlet {
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
        System.out.println("ManageReimbursement Servlet - doGet");
        List<Reimbursement> list = rService.getAllReimbursements();
        String json = mapper.writeValueAsString(list);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ManageReimbursement Servlet - doPut");
        ManageReimbursementDTO mDTO = mapper.readValue(req.getInputStream(), ManageReimbursementDTO.class);
        rService.resolveReimbursement(mDTO.getId(), mDTO.getResolver_id(), Status.valueOf(mDTO.getStatus()), mDTO.getDate_resolved());
        System.out.println(rService.getReimbursementByID(mDTO.getId()));
        resp.setStatus(200);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
