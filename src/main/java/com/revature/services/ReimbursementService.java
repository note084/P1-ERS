package com.revature.services;

import com.revature.DBConnection.ConnectionManager;
import com.revature.exceptions.NoAccessException;
import com.revature.exceptions.ReimbursementDoesNotExist;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */

    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        ReimbursementDAO rDao = new ReimbursementDAO();
        Reimbursement rItem = new Reimbursement();

        try {
            if(rDao.getByID(unprocessedReimbursement.getId()) == null){
                throw new ReimbursementDoesNotExist("Reimbursement does not exist");
            }
            if(resolver.getRole() != Role.FINANCE_MANAGER) {
                throw new NoAccessException("User is not logged in as a Finance Manager. NO ACCESS");
            }
            else {
                unprocessedReimbursement.setStatus(finalStatus);
                unprocessedReimbursement.setResolver(resolver);
                rItem = rDao.update(unprocessedReimbursement);

                return rItem;

            }
        }catch (ReimbursementDoesNotExist | NoAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        ReimbursementDAO rDao = new ReimbursementDAO();

        return rDao.getByStatus(status);
    }

    public List<Reimbursement> getReimbursementsByAuthorID(int id) {
        ReimbursementDAO rDao = new ReimbursementDAO();

        return rDao.getByAuthorId(id);
    }

    public Reimbursement createReimbursement(Reimbursement reimbursementToBeCreated) {
        ReimbursementDAO rDao = new ReimbursementDAO();

        return rDao.create(reimbursementToBeCreated);
    }

    public Reimbursement getReimbursementByID (int id) {
        ReimbursementDAO rDao = new ReimbursementDAO();
        return rDao.getByID(id);
    }

    public Reimbursement editReimbursement (Reimbursement reimbursementToBeEdited) {
        ReimbursementDAO rDao = new ReimbursementDAO();
        return rDao.update(reimbursementToBeEdited);
    }

    public void delete (Reimbursement reimbursementToBeDeleted) {
        ReimbursementDAO rDao = new ReimbursementDAO();
        rDao.delete(reimbursementToBeDeleted);
    }

    public List<Reimbursement> getAllReimbursements() {
        ReimbursementDAO rDao = new ReimbursementDAO();
        return rDao.getAllReimbursements();
    }

    public void resolveReimbursement(int id, int resolver_id, Status status, String date_resolved) {
        ReimbursementDAO rDao = new ReimbursementDAO();
        rDao.resolveReimbursement(id, resolver_id, status, date_resolved);
    }

}
