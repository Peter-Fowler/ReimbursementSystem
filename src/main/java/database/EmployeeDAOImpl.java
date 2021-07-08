package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import security.Encryption;
import creatable.Employee;

public class EmployeeDAOImpl implements SystemDAO<Employee> {

	@Override
	public ArrayList<Employee> getAll() {
		String query = "SELECT firstName, lastName, email, jobTitle, manager, managerStatus FROM employee";
		
		ArrayList<Employee> allEmp = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String jobTitle = rs.getString("jobTitle");
				String manager = rs.getString("manager");
				int managerByNumber = rs.getInt("managerStatus");
				boolean managerStatus = (managerByNumber == 1) ? true : false;
				allEmp.add(new Employee(firstName, lastName, email, jobTitle, manager, managerStatus));
			}
			return allEmp;
		} catch (SQLException e) {
			System.out.println("Failed to get list of employees" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Employee> get(String email) {
		String query = "SELECT firstName, lastName, email, jobTitle, manager, managerStatus FROM employee WHERE email = ?";
		
		List<Employee> fred = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email2 = rs.getString("email");
				String jobTitle = rs.getString("jobTitle");
				String manager = rs.getString("manager");
				int managerByNumber = rs.getInt("managerStatus");
				boolean managerStatus = (managerByNumber == 1) ? true : false;
				fred.add(new Employee(firstName, lastName, email, jobTitle, manager, managerStatus));
			}
			return fred;
		} catch (SQLException e) {
			System.out.println("Failed to get employee" + e.getMessage());;
		}
			return null;
	}

	@Override
	public void update(Employee fred) {
		String query = "UPDATE employee SET firstName = ?, lastName = ?, jobTitle = ?, "
				+ "manager = ?, managerStatus = ? WHERE email = ?";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getFirstName());
			ps.setString(2, fred.getLastName());
			ps.setString(3, fred.getJobTitle());
			ps.setString(4, fred.getManager());
			ps.setInt(5, fred.booleanToInt(fred.isManagerStatus()));
			ps.setString(6, fred.getEmail());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failed to update employee" + e.getMessage());
		}
	}

	@Override
	public void delete(Employee fred) {
		String query = "DELETE employee WHERE email = ?";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getEmail());
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failed to delete employee" + e.getMessage());
		}
		
	}

	@Override
	public void save(Employee fred) {
		String query = "INSERT INTO employee (firstName, lastName, email, jobTitle, manager, managerStatus, password) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getFirstName());
			ps.setString(2, fred.getLastName());
			ps.setString(3, fred.getEmail());
			ps.setString(4, fred.getJobTitle());
			ps.setString(5, fred.getManager());
			ps.setInt(6, fred.booleanToInt(fred.isManagerStatus()));
			ps.setString(7, Encryption.makeSecure(fred.getPassword()));
			
			success = ps.executeUpdate();
			
			if(success == 1) {
				String COMMIT = "COMMIT";
				PreparedStatement cps = conn.prepareStatement(COMMIT);
				success = cps.executeUpdate();
			}
			
		}catch(SQLException e) {
			System.out.println("Failed to save employee" + e.getMessage());
		}
		
	}
	
	public void updataPassword(Employee fred) {
		String query = "UPDATE employee SET password = ? WHERE email = ?";
		
		int success = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){ 
	
		ps.setString(1, Encryption.makeSecure(fred.getPassword()));
		ps.setString(2, fred.getEmail());
		
		success = ps.executeUpdate();
		
		if(success == 1) {
			String COMMIT = "COMMIT";
			PreparedStatement cps = conn.prepareStatement(COMMIT);
			success = cps.executeUpdate();			
		}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("failed to update password");
		}
		
	}
	
	public boolean validate(String email, String password) {

		String query = "SELECT password FROM employee WHERE email = ?";
		
		String passHash = Encryption.makeSecure(password);
		
		String securit = null;
		
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				securit = rs.getString("password");
			}
			
			if(securit.equals(passHash))
				return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("password from database not found");
		}
		return false;
	}
	
}
