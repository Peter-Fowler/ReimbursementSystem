package general;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import creatable.Employee;
import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;
import database.EmployeeDAOImpl;
import database.ReimbursementDecidedDAOImpl;
import database.ReimbursementRequestDAOImpl;
import system.ReimbursementServiceImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestingArea4EmployeeDOAI {

	EmployeeDAOImpl edaoi;
	ReimbursementServiceImpl rsi;
	ReimbursementRequestDAOImpl requestTool;
	ReimbursementDecidedDAOImpl decidedTool;
	ReimbursementDecided decidedGlobal;
	ReimbursementRequest requestGlobal;

	@BeforeEach
	void initEach() {
		edaoi = new EmployeeDAOImpl();
		rsi = new ReimbursementServiceImpl();
	}

	@BeforeAll
	void setUp() throws Exception {
		edaoi = new EmployeeDAOImpl();
		rsi = new ReimbursementServiceImpl();

		Employee fred = new Employee("Peter", "Fowler", "peter.fowler@revature.net", "programmer",
				"big.boss@revature.net", false, "Password");
		edaoi.save(fred);

		Employee bigBoss = new Employee("Big", "Boss", "big.boss@revature.net", "CEO", null, true, "AdminPass");
		rsi.registerEmployee(bigBoss);
	}

	@Test
	void testEmployeeImpl() {
		Employee fred = edaoi.get("peter.fowler@revature.net").remove(0);

		System.out.println("before name test");

		assertEquals("Peter", fred.getFirstName());

		System.out.println("before name change");

		fred.setFirstName("Steve");

		edaoi.update(fred);

		System.out.println("before name change test");

		assertEquals("Steve", fred.getFirstName());

		System.out.println("before password change");

		fred.setPassword("NewP@ssW0rd");

		edaoi.updataPassword(fred);

		System.out.println("before password validation");

		assertTrue(edaoi.validate("peter.fowler@revature.net", "NewP@ssW0rd"));
	}

	@Test
	void testSystemInsert() {
		Employee fred = edaoi.get("peter.fowler@revature.net").remove(0);
		Employee ceo = edaoi.get("big.boss@revature.net").remove(0);

		rsi.createReimbursementRequest(fred, 200.86, "An example to make sure everything works.");

		ReimbursementRequest request = rsi.getReimbursementRequests().remove(0);

		requestGlobal = request;

		assertEquals("peter.fowler@revature.net", request.getEmployeeEmail());

		rsi.judgeReimbursementRequest(request, ceo, true);

		ReimbursementDecided decided = rsi.viewReimbursementDecided().remove(0);

		decidedGlobal = decided;

		assertEquals(ceo.getEmail(), decided.getManagerEmail());

		ArrayList<Employee> allEmp = rsi.viewAllEmployees();

		Employee[] allEmpArr = new Employee[allEmp.size()];
		allEmpArr = (Employee[]) allEmp.toArray(); //look into getting class and see if that allows java to cast to type Employee

		if (allEmpArr[0].equals(ceo)) {
			Employee temp = allEmpArr[0];
			allEmpArr[0] = allEmpArr[1];
			allEmpArr[1] = temp;
		}

		Employee[] knowenEmpArr = new Employee[] { fred, ceo };

		assertArrayEquals(knowenEmpArr, allEmpArr);

		ReimbursementRequest requestByEmail = rsi.viewReimbursementRequestsByEmployee(fred).remove(0);

		assertEquals(request, requestByEmail);

		System.out.println(request.getDate());

	}

	@AfterAll
	void tearDown() {
		requestTool = new ReimbursementRequestDAOImpl();
		decidedTool = new ReimbursementDecidedDAOImpl();

		requestGlobal = new ReimbursementRequest();
		requestGlobal.setRequestID(1);

		decidedGlobal = new ReimbursementDecided();
		decidedGlobal.setRequestID(1);

		decidedTool.delete(decidedGlobal);

		requestTool.delete(requestGlobal);

		Employee fred = new Employee("Peter", "Fowler", "peter.fowler@revature.net", "programmer",
				"big.boss@revature.net", false);
		edaoi.delete(fred);
		Employee bigBoss = new Employee("Big", "Boss", "big.boss@revature.net", "CEO", null, true, "AdminPass");
		edaoi.delete(bigBoss);

	}

}
