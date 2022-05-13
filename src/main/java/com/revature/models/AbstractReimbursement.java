package com.revature.models;

import java.util.Objects;

/**
 * This AbstractReimbursement class defines a minimum functionality for
 * interacting with reimbursements in the ERS application.
 *
 * All reimbursements in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Status</li>
 *     <li>Author</li>
 *     <li>Resolver</li>
 *     <li>Amount</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.Reimbursement} class.
 *
 * @author Center of Excellence
 */
public class AbstractReimbursement {

    private int id;
    private Status status;
    private User author;
    private User resolver;
    private double amount;
    private String type;
    private String date_created;
    private String date_resolved;

    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount, String type, String date_created, String date_resolved) {
        super();
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.type = type;
        this.date_created = date_created;
        this.date_resolved = date_resolved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_resolved() {
        return date_resolved;
    }

    public void setDate_resolved(String date_resolved) {
        this.date_resolved = date_resolved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && status == that.status && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, author, resolver, amount, type);
    }

    @Override
    public String toString() {
        return "AbstractReimbursement{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", author=" + author +
                ", resolver=" + resolver +
                ", amount=" + amount +
                '}';
    }
}
