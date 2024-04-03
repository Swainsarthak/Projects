import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employe_database_management {
    private JPanel Main;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton createButton1;
    private JTextField EmployeeId;
    private JTextField EmployeeName;
    private JTextField EmployeeSalary;
    private JTextField EmployeeAge;
    private JTable Database;
    private JButton ShowAllEmployee;
    private JTextField Search_id;
    private Connection con;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Employe_database_management");
        frame.setContentPane(new Employe_database_management().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void table_load(){
        try {
            con=jdbc_connection.request_connection();
            String query="select * from employee";
            PreparedStatement pr=con.prepareStatement(query);
            ResultSet det=pr.executeQuery();
            Database.setModel(DbUtils.resultSetToTableModel(det));
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employe_database_management() {
//        table_load();

        createButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int employee_id,employee_age;
                String employee_name;
                double employee_salary;
                employee_id= Integer.parseInt(EmployeeId.getText());
                employee_name=EmployeeName.getText();
                employee_salary= Double.parseDouble(EmployeeSalary.getText());
                employee_age= Integer.parseInt(EmployeeAge.getText());
                try {
                    con=jdbc_connection.request_connection();
                    String query="insert into employee (`idEmployee`,`EmployeeName`,`salary`,`age`) values (?,?,?,?)";
                    PreparedStatement pstm=con.prepareStatement(query);
                    pstm.setInt(1,employee_id);
                    pstm.setString(2,employee_name);
                    pstm.setDouble(3,employee_salary);
                    pstm.setInt(4,employee_age);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record added!!!");
                    EmployeeId.setText("");
                    EmployeeName.setText("");
                    EmployeeSalary.setText("");
                    EmployeeAge.setText("");
                    EmployeeId.requestFocus();

                }
                catch (ClassNotFoundException | SQLException f) {
                    throw new RuntimeException(f);
                }
            }
        });
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                id= Integer.parseInt(Search_id.getText());
                try {

                    con=jdbc_connection.request_connection();
                    String query="Select * from employee where idEmployee="+id;
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(query);
                    if(rs.next()){
                        EmployeeId.setText(String.valueOf(rs.getInt(1)));
                        EmployeeName.setText(String.valueOf(rs.getString(2)));
                        EmployeeSalary.setText(String.valueOf(rs.getDouble(3)));
                        EmployeeAge.setText(String.valueOf(rs.getInt(4)));
                    }
                    else {
                        EmployeeId.setText("");
                        EmployeeName.setText("");
                        EmployeeSalary.setText("");
                        EmployeeAge.setText("");
                    }

                }
                catch (SQLException | ClassNotFoundException f) {
                    throw new RuntimeException(f);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                id= Integer.parseInt(Search_id.getText());
                try {
                    con=jdbc_connection.request_connection();
                    String query="Delete from employee where idEmployee="+id;
                    PreparedStatement pr=con.prepareStatement(query) ;
                    int count=pr.executeUpdate();
                    if(count!=0){
                        JOptionPane.showMessageDialog(null,"Record deleted!!!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"invalid id goven.");
                    }

                }
                catch (SQLException | ClassNotFoundException f) {
                    throw new RuntimeException(f);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int employee_id,employee_age;
                String employee_name;
                double employee_salary;
                employee_id= Integer.parseInt(EmployeeId.getText());
                employee_name=EmployeeName.getText();
                employee_salary= Double.parseDouble(EmployeeSalary.getText());
                employee_age= Integer.parseInt(EmployeeAge.getText());
                try {
                    con=jdbc_connection.request_connection();
                    String query="update employee set EmployeeName=?,salary=?,age=? where idEmployee="+employee_id;
                    PreparedStatement pstm=con.prepareStatement(query);
                    pstm.setString(1,employee_name);
                    pstm.setDouble(2,employee_salary);
                    pstm.setInt(3,employee_age);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Updated!!!");

                }
                catch (SQLException|ClassNotFoundException f){
                    throw new RuntimeException(f);
                }
            }
        });
        ShowAllEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
            }
        });
    }

}
