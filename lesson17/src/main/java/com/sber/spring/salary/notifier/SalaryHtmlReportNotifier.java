package com.sber.spring.salary.notifier;

import com.sber.spring.salary.employee.EmployeeReport;
import com.sber.spring.salary.exception.ReportException;
import com.sber.spring.salary.html.HtmlBuilderService;
import com.sber.spring.salary.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.time.LocalDate;

@Component
public class SalaryHtmlReportNotifier implements SalaryHtmlReport {

    //private final Connection connection;
    private final EmployeeReport employees;
    private final HtmlBuilderService htmlBuilderService;
    private final MailService mailService;

    @Autowired
    public SalaryHtmlReportNotifier(EmployeeReport employees,
                                    HtmlBuilderService htmlBuilderService, MailService mailService) {
        this.employees = employees;
        this.htmlBuilderService = htmlBuilderService;
        this.mailService = mailService;
    }

    @Override
    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            ResultSet result = employees.getSalaryReport(departmentId, dateFrom, dateTo);
            mailService.sendReport(recipients, htmlBuilderService.reportHtml(result).toString());
        } catch (ReportException e) {
            System.err.println(e.getMessage());
        }
    }
}

