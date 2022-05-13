package com.revature.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.models.Status;

@JsonIgnoreProperties
public class ReimbursementDTO {
    private String type;
    private double amount;
    private Status status;
    private int author_id;
    private String date_created;

    public ReimbursementDTO() {
    }

    public ReimbursementDTO(String type, double amount, Status status, int author_id, String date_created) {
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.author_id = author_id;
        this.date_created = date_created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
