package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;

public class ReimbursementDecidedDAOImpl implements SystemDAO<ReimbursementDecided> {

	@Override
	public List<ReimbursementDecided> getAll() {
		ArrayList<ReimbursementDecided> allReimDecided = new ArrayList<ReimbursementDecided>();
		String query = "SELECT decisionID, requestID, managerEmail, date, approved FROM reimbursementDecided";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			boolean approved = false;
			
			while(rs.next()) {
				int decisionID = rs.getInt("decisionID");
				int requestID = rs.getInt("RequestID");
				String managerEmail = rs.getString("managerEmail");
				Timestamp ts = rs.getTimestamp("date");
				double amount = rs.getDouble("amount");
				int approvedID = rs.getInt("approved");
				if(approvedID == 1)
					approved = true;
				LocalDateTime date = ts.toLocalDateTime();
				allReimDecided.add(new ReimbursementDecided(managerEmail, date, approved, decisionID, requestID));
			}
			return allReimDecided;
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}
		return null;
	}

	@Override
	public ReimbursementDecided get(String decidedID) {
		ReimbursementDecided reimDecided = null;
		String query = "SELECT decisionID, requestID, managerEmail, date, approved FROM reimbursementDecided WHERE decisionID = ?";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			int id = Integer.valueOf(decidedID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			boolean approved = false;
			
			while(rs.next()) {
				int decisionID = rs.getInt("decisionID");
				int requestID = rs.getInt("RequestID");
				String managerEmail = rs.getString("managerEmail");
				Timestamp ts = rs.getTimestamp("date");
				double amount = rs.getDouble("amount");
				int approvedID = rs.getInt("approved");
				if(approvedID == 1)
					approved = true;
				LocalDateTime date = ts.toLocalDateTime();
				reimDecided = new ReimbursementDecided(managerEmail, date, approved, decisionID, requestID);
			}
			return reimDecided;
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}catch(NumberFormatException e) {
			System.out.println(decidedID + " is not a valid number.");
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void update(ReimbursementDecided reimDecided) {
		String query = "UPDATE reimbursementDecided SET requestID = ?, managerEmail = ?, date = ?, approved = ? WHERE decisionID = ?";
		
		int approvedNum = (reimDecided.isApproved()) ? (approvedNum = 1): (approvedNum = 0); 
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setInt(1, reimDecided.getRequestID());
			ps.setString(2, reimDecided.getManagerEmail());
			ps.setTimestamp(3, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(4, approvedNum);
			ps.setInt(5, reimDecided.getDecisionID());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}
	}

	@Override
	public void delete(ReimbursementDecided reimDecided) { 
		String query = "DELETE FROM reimbursementDecided WHERE decisionID = ?";
		
		int approvedNum = (reimDecided.isApproved()) ? (approvedNum = 1): (approvedNum = 0); 
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setInt(1, reimDecided.getRequestID());
			ps.setString(2, reimDecided.getManagerEmail());
			ps.setTimestamp(3, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(4, approvedNum);
			ps.setInt(5, reimDecided.getDecisionID());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}
	}

	@Override
	public void save(ReimbursementDecided reimDecided) {
		String query = "INSETR INTO reimbursementDecided (decisionID, requestID, managerEmail, date, approved) VALUES (?,?,?,?,?)";
		
		int approvedNum = (reimDecided.isApproved()) ? (approvedNum = 1): (approvedNum = 0); 
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setInt(1, reimDecided.getDecisionID());
			ps.setInt(2, reimDecided.getRequestID());
			ps.setString(3, reimDecided.getManagerEmail());
			ps.setTimestamp(4, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(5, approvedNum);
			
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}
	}

}
