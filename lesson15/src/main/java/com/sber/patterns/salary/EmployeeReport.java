package com.sber.patterns.salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeReport {

    private final Connection connection;

    public EmployeeReport(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws ReportException {
        try {
            // prepare statement with sql
            PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                    "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                    " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
            // inject parameters to sql
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and get the results
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new ReportException("Base error!");
        }
    }
}
