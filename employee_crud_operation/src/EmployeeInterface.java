import java.sql.SQLException;

public interface EmployeeInterface {
    public void createEmployee(Employee new_emp) throws SQLException;//creates an employee
    public void deleteEmployee(int id);//delete an employee according to the id
    public void showall();//show all the employee data
    public void showEmployeeBasedOnId(int id);//this show only the perticular employee name according to the id
    public void updateEmployee(int id); //this updates the employee details
}
