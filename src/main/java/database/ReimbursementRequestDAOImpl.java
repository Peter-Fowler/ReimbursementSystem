package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import creatable.ReimbursementRequest;

public class ReimbursementRequestDAOImpl implements SystemDAO<ReimbursementRequest> {

	@Override
	public ArrayList<ReimbursementRequest> getAll() {
		ArrayList<ReimbursementRequest> allReimRequests = new ArrayList<ReimbursementRequest>();
		String query = "SELECT requestID, employeeEmail, dateSubmited, amount, description FROM ReimbursementRequest";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				int requestID = rs.getInt("requestID");
				String employeeEmail = rs.getString("employeeEmail");
				Timestamp ts = rs.getTimestamp("dateSubmited");
				double amount = rs.getDouble("amount");
				String description = rs.getString("description");
				LocalDateTime date = ts.toLocalDateTime();
				allReimRequests.add(new ReimbursementRequest(employeeEmail, date, amount, description, requestID));
			}
			return allReimRequests;
			
		}catch(SQLException e) {
			System.out.println("Failer to get list of reimbursement requests " + e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<ReimbursementRequest> get(String fredEmail) {
		String query = "SELECT requestID, employeeEmail, dateSubmited, amount, description FROM reimbursementRequest WHERE employeeEmail = ?";
		ArrayList<ReimbursementRequest> request = new ArrayList<ReimbursementRequest>();
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			
			ps.setString(1, fredEmail);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String employeeEmail = rs.getString("employeeEmail");
				Timestamp ts = rs.getTimestamp("dateSubmited");
				double amount = rs.getDouble("amount");
				String description = rs.getString("description");
				int requestID = rs.getInt("requestID");
				LocalDateTime date = ts.toLocalDateTime();
				request.add(new ReimbursementRequest(employeeEmail, date, amount, description, requestID));
			}
			return request;
			
		}catch(SQLException e) {
			System.out.println("Failer to get reimbursement request by ID " + e.getMessage());
		}
		return null;
	}

	@Override
	public void update(ReimbursementRequest request) {
		String query = "UPDATE reimbursementRequest SET employeeEmail = ?, dateSubmited = ?, amount = ?, description = ?  WHERE requestID = ?";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			ps.setTimestamp(2, Timestamp.valueOf(request.getDate()));
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getDescription());
			ps.setInt(5, request.getRequestID());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to update reimbursement request " + e.getMessage());
		}
			
		
	}
	
	@Override
	public void delete(ReimbursementRequest request) {
		String query = "DELETE reimbursementRequest WHERE requestID = ?";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to delete reimbursement request " + e.getMessage());
		}
		
	}

	@Override
	public void save(ReimbursementRequest request) {
		String query = "INSERT INTO reimbursementRequest (employeeEmail, dateSubmited, amount, description) VALUES(?,?,?,?)";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			ps.setTimestamp(2, Timestamp.valueOf(request.getDate()));
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getDescription());
			
			success = ps.executeUpdate();

			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failer to save reimbursement request " + e.getMessage());
		}
		
	}

}
