package com.revature.repositories;

import com.revature.DBConnection.ConnectionManager;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.UserService;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public List<Reimbursement> getByAuthorId(int id) {
        List<Reimbursement> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reimbursements WHERE author_id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Reimbursement item = new Reimbursement();
                UserDAO userModel = new UserDAO();
                item.setId(rs.getInt("id"));
                item.setStatus(Status.valueOf(rs.getString("status")));
                item.setAmount(rs.getDouble("amount"));
                item.setAuthor(userModel.getByUserID(id).get());
                item.setType(rs.getString("type"));

                if (item.getStatus().equals(Status.PENDING)) {
                    item.setResolver(null);
                }
                else {
                    item.setResolver(userModel.getByUserID(rs.getInt("resolver_id")).get());
                }
                item.setDate_created(rs.getString("date_created"));
                item.setDate_resolved(rs.getString("date_resolved"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        List<Reimbursement> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE status = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, String.valueOf(status));
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Reimbursement item = new Reimbursement();
                UserDAO user = new UserDAO();
                item.setId(rs.getInt("id"));
                item.setStatus(Status.valueOf(rs.getString("status")));
                item.setAmount(rs.getDouble("amount"));
                item.setAuthor(user.getByUserID(rs.getInt("author_id")).get());
                item.setType((rs.getString("type")));

                if (status == Status.PENDING){
                    item.setResolver(null);
                }
                else {
                    item.setResolver(user.getByUserID(rs.getInt("resolver_id")).get());
                }
                item.setDate_resolved(rs.getString("date_resolved"));
                item.setDate_created(rs.getString("date_created"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
        try {
            String sql = "UPDATE reimbursements SET status = ?, author_id = ?, amount = ?, type = ? WHERE id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setString(1, String.valueOf(unprocessedReimbursement.getStatus()));

            pstmt.setInt(2, unprocessedReimbursement.getAuthor().getId());
            pstmt.setDouble(3, unprocessedReimbursement.getAmount());
            pstmt.setString(4, unprocessedReimbursement.getType());
            pstmt.setInt(5, unprocessedReimbursement.getId());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return getByID(unprocessedReimbursement.getId());
    }

    public Reimbursement getByID(int id) {
        Reimbursement item = new Reimbursement();
        try {
            String sql = "SELECT * FROM reimbursements WHERE id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                UserDAO userModel = new UserDAO();
                item.setId(rs.getInt("id"));
                item.setStatus(Status.valueOf(rs.getString("status")));
                item.setAuthor(userModel.getByUserID(rs.getInt("author_id")).get());
                if (item.getStatus().equals(Status.PENDING)){
                    item.setResolver(null);
                }
                else {
                    item.setResolver(userModel.getByUserID(rs.getInt("resolver_id")).get());
                }
                item.setAmount(rs.getDouble("amount"));
                item.setType((rs.getString("type")));
                item.setDate_created(rs.getString("date_created"));
                item.setDate_resolved(rs.getString("date_resolved"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public Reimbursement create (Reimbursement reimbursementToBeCreated) {
        try {
            String sql =    "INSERT into " +
                    "reimbursements (type, amount, author_id, status, date_created, date_resolved) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, reimbursementToBeCreated.getType());
            pstmt.setDouble(2, reimbursementToBeCreated.getAmount());
            pstmt.setInt(3, reimbursementToBeCreated.getAuthor().getId());
            pstmt.setString(4, "PENDING");
            pstmt.setString(5, reimbursementToBeCreated.getDate_created());
            pstmt.setString(6, reimbursementToBeCreated.getDate_resolved());

            pstmt.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public void delete(Reimbursement reimbursementToBeDeleted) {
        try {
            String sql = "DELETE from reimbursements where id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, reimbursementToBeDeleted.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Did not delete reimbursement");
        }
    }

    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> list = new ArrayList<>();
        try {
            UserDAO uDAO = new UserDAO();
            String sql = "SELECT * from reimbursements";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Reimbursement rItem = new Reimbursement();
                rItem.setId(rs.getInt("id"));
                rItem.setType(rs.getString("type"));
                rItem.setStatus(Status.valueOf(rs.getString("status")));
                rItem.setAmount(rs.getDouble("amount"));
                rItem.setAuthor(uDAO.getByUserID(rs.getInt("author_id")).get());
                if (rItem.getStatus().equals(Status.PENDING)) {
                    rItem.setResolver(null);
                } else {
                    rItem.setResolver(uDAO.getByUserID(rs.getInt("resolver_id")).get());
                }
                rItem.setDate_resolved(rs.getString("date_resolved"));
                rItem.setDate_created(rs.getString("date_created"));
                list.add(rItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void resolveReimbursement(int id, int resolver_id, Status status, String date_resolved) {
        try {
            String sql = "UPDATE reimbursements SET resolver_id = ?, status = ?, date_resolved = ? where id = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, resolver_id);
            pstmt.setString(2, String.valueOf(status));
            pstmt.setString(3, date_resolved);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
