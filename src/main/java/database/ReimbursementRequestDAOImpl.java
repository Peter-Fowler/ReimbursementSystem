package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import creatable.ReimbursementRequest;

public class ReimbursementRequestDAOImpl implements SystemDAO<ReimbursementRequest> {

	@Override
	public List<ReimbursementRequest> getAll() {
		ArrayList<ReimbursementRequest> allReimRequests = new ArrayList<ReimbursementRequest>();
		String query = "SELECT requestID, employeeEmail, date, amount, description FROM reinmursementRequest";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int requestID = rs.getInt("RequestID");
				String employeeEmail = rs.getString("employeeEmail");
				Timestamp ts = rs.getTimestamp("date");
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
	public ReimbursementRequest get(String requestID) {
		String query = "SELECT requestID, employeeEmail, date, amount, description FROM reinmursementRequest WHERE requestID = ?";
		ReimbursementRequest request = null;
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			int id = Integer.valueOf(requestID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String employeeEmail = rs.getString("employeeEmail");
				Timestamp ts = rs.getTimestamp("date");
				double amount = rs.getDouble("amount");
				String description = rs.getString("description");
				LocalDateTime date = ts.toLocalDateTime();
				request = new ReimbursementRequest(employeeEmail, date, amount, description, id);
			}
			return request;
			
		}catch(SQLException e) {
			System.out.println("Failer to get reimbursement request " + e.getMessage());
		}catch(NumberFormatException e) {
			System.out.println(requestID + " is not a valid number.");
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void update(ReimbursementRequest request) {
		String query = "UPDATE reinmursementRequest SET employeeEmail = ?, date = ?, amount = ?, description = ?  WHERE requestID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			ps.setTimestamp(2, Timestamp.valueOf(request.getDate()));
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getDescription());
			ps.setInt(5, request.getRequestID());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get reimbursement request " + e.getMessage());
		}
			
		
	}
	/*String employeeEmail;
	LocalDateTime date;
	double amount;
	String description;
	int requestID;
	*/
	@Override
	public void delete(ReimbursementRequest request) {
		String query = "DELETE reinmursementRequest WHERE requestID = ?";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			ps.setTimestamp(2, Timestamp.valueOf(request.getDate()));
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getDescription());
			ps.setInt(5, request.getRequestID());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get reimbursement request " + e.getMessage());
		}
		
	}

	@Override
	public void save(ReimbursementRequest request) {
		String query = "INSERT INTO reinmursementRequest (employeeEmail, date, amount, description) VALUE (?,?,?,?)";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, request.getEmployeeEmail());
			ps.setTimestamp(2, Timestamp.valueOf(request.getDate()));
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getDescription());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failer to get reimbursement request " + e.getMessage());
		}
		
	}

}
