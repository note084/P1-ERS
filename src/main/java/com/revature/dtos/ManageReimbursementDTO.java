package com.revature.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.models.Status;

@JsonIgnoreProperties
public class ManageReimbursementDTO {
    private int id;
    private int resolver_id;
    private String status;
    private String date_resolved;

    public ManageReimbursementDTO() {
    }

    public ManageReimbursementDTO(int id, int resolver_id, String status, String date_resolved) {
        this.id = id;
        this.resolver_id = resolver_id;
        this.status = status;
        this.date_resolved = date_resolved;
    }

    public String getDate_resolved() {
        return date_resolved;
    }

    public void setDate_resolved(String date_resolved) {
        this.date_resolved = date_resolved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(int resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
