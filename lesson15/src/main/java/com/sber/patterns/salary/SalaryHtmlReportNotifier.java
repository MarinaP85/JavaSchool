package com.sber.patterns.salary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private final Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            EmployeeReport employees = new EmployeeReport(connection);
            ResultSet result = employees.getSalaryReport(departmentId, dateFrom, dateTo);
            MailUtils.sendReport(recipients, HtmlBuilderUtils.reportHtml(result).toString());
        } catch (ReportException e) {
            System.err.println(e.getMessage());
        }
    }
}

