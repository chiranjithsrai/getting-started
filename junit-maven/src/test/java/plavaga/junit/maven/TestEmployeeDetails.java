/**
 * 
 */
package plavaga.junit.maven;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author chiranjithsrai
 *
 */
public class TestEmployeeDetails {
	EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
	EmployeeDetails employee = new EmployeeDetails();

	// test to check appraisal
	@Test
	public void testCalculateAppriasal() {
		employee.setName("Chiru");
		employee.setAge(22);
		employee.setMonthlySalary(8000);

		double appraisal = empBusinessLogic.calculateAppraisal(employee);
		assertEquals(500, appraisal, 0.0);
	}

	// test to check yearly salary
	@Test
	public void testCalculateYearlySalary() {
		employee.setName("Chiru");
		employee.setAge(22);
		employee.setMonthlySalary(8000);

		double salary = empBusinessLogic.calculateYearlySalary(employee);
		assertEquals(96000, salary, 0.0);
		assertNotNull(salary);
	}
}
