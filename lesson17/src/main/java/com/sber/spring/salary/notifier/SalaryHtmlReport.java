package com.sber.spring.salary.notifier;

import java.time.LocalDate;

public interface SalaryHtmlReport {
    void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients);
}
