package com.sber.spring.salary.employee;

import com.sber.spring.salary.exception.ReportException;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface EmployeeReport {
    ResultSet getSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws ReportException;
}
