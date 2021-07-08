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

public class ReimbursementDecidedDAOImpl implements SystemDAO<ReimbursementDecided> {

	@Override
	public ArrayList<ReimbursementDecided> getAll() {
		ArrayList<ReimbursementDecided> allReimDecided = new ArrayList<ReimbursementDecided>();
		String query = "SELECT decisionID, requestID, managerEmail, dateDecided, decision FROM reimbursementDecided";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			boolean approved = false;
			
			while(rs.next()) {
				int decisionID = rs.getInt("decisionID");
				int requestID = rs.getInt("RequestID");
				String managerEmail = rs.getString("managerEmail");
				Timestamp ts = rs.getTimestamp("dateDecided");
				int approvedID = rs.getInt("decision");
				if(approvedID == 1)
					approved = true;
				LocalDateTime date = ts.toLocalDateTime();
				allReimDecided.add(new ReimbursementDecided(managerEmail, date, approved, decisionID, requestID));
			}
			return allReimDecided;
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement deided requests " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<ReimbursementDecided> get(String decidedID) {
		ArrayList<ReimbursementDecided> reimDecided = new ArrayList<ReimbursementDecided>();
		String query = "SELECT decisionID, requestID, managerEmail, dateDecided, decision FROM reimbursementDecided WHERE decisionID = ?";
		
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
				Timestamp ts = rs.getTimestamp("dateDecided");
				int approvedID = rs.getInt("decision");
				if(approvedID == 1)
					approved = true;
				LocalDateTime date = ts.toLocalDateTime();
				reimDecided.add(new ReimbursementDecided(managerEmail, date, approved, decisionID, requestID));
			}
			return reimDecided;
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement deided requests by ID " + e.getMessage());
		}catch(NumberFormatException e) {
			System.out.println(decidedID + " is not a valid number.");
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void update(ReimbursementDecided reimDecided) {
		String query = "UPDATE reimbursementDecided SET requestID = ?, managerEmail = ?, dateDecided = ?, decision = ? WHERE decisionID = ?";
		
		int success = 0;
		
		int approvedNum = (reimDecided.isApproved()) ? 1 : 0; 
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setInt(1, reimDecided.getRequestID());
			ps.setString(2, reimDecided.getManagerEmail());
			ps.setTimestamp(3, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(4, approvedNum);
			ps.setInt(5, reimDecided.getDecisionID());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to update reimbursement deided request " + e.getMessage());
		}
	}

	@Override
	public void delete(ReimbursementDecided reimDecided) { 
		String query = "DELETE FROM reimbursementDecided WHERE decisionID = ?";
		
		int success = 0;
		
		int approvedNum = (reimDecided.isApproved()) ?  1 : 0; 
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setInt(1, reimDecided.getRequestID());
			ps.setString(2, reimDecided.getManagerEmail());
			ps.setTimestamp(3, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(4, approvedNum);
			ps.setInt(5, reimDecided.getDecisionID());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to delete reimbursement deided request " + e.getMessage());
		}
	}

	@Override
	public void save(ReimbursementDecided reimDecided) {
		String query = "INSERt INTO reimbursementDecided (requestID, managerEmail, dateDecided, decision) VALUES(?,?,?,?)";
		
		int success = 0;
		
		int approvedNum = (reimDecided.isApproved()) ? 1 : 0; 
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			System.out.println(reimDecided.getDecisionID() + " the DID " + reimDecided.getManagerEmail() + " the manEmail " + reimDecided.getRequestID() 
			+ " the RID " + reimDecided.getDate() + " the date");
			
			ps.setInt(1, reimDecided.getRequestID());
			ps.setString(2, reimDecided.getManagerEmail());
			ps.setTimestamp(3, Timestamp.valueOf(reimDecided.getDate()));
			ps.setInt(4, approvedNum);
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to save a reimbursement deided request " + e.getMessage());
		}
	}

}
