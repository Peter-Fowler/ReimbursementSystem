package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import creatable.Employee;

public class EmployeeDAOImpl implements SystemDAO<Employee> {

	@Override
	public List<Employee> getAll() {
		String query = "SELECT name, email, jobTitle, manager FROM employee";
		
		ArrayList<Employee> allEmp = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String jobTitle = rs.getString("jobTitle");
				String manager = rs.getString("manager");
				allEmp.add(new Employee(name, email, jobTitle, manager));
			}
			
		} catch (SQLException e) {
			System.out.println("Failed to get list of employees" + e.getMessage());
		}
		return allEmp;
	}

	@Override
	public Employee get(String email) {
		String query = "SELECT name, email, jobTitle, manager FROM employee WHERE email = ?";
		
		Employee fred = new Employee();
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email2 = rs.getString("email");
				String jobTitle = rs.getString("jobTitle");
				String manager = rs.getString("manager");
				fred =new Employee(name, email2, jobTitle, manager);
			}
			
		} catch (SQLException e) {
			System.out.println("Failed to get employee" + e.getMessage());;
		}
		if(fred.getName() == null)
			return null;
		return fred;
	}

	@Override
	public void update(Employee fred) {
		String query = "UPDATE employee SET name = ?, jpbTitle = ?, manager = ? WHERE email = ?";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getName());
			ps.setString(2, fred.getJobTitle());
			ps.setString(3, fred.getManager());
			ps.setString(4, fred.getEmail());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failed to update employee" + e.getMessage());
		}
	}

	@Override
	public void delete(Employee fred) {
		String query = "DELETE employee WHERE email = ?";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getEmail());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failed to delete employee" + e.getMessage());
		}
		
	}

	@Override
	public void save(Employee fred) {
		String query = "INSERT INTO employee (name, email, jobTitle, manager) VALUE(?,?,?,?)";
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, fred.getName());
			ps.setString(2, fred.getEmail());
			ps.setString(3, fred.getJobTitle());
			ps.setString(4, fred.getManager());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Failed to save employee" + e.getMessage());
		}
		
	}

}
