package com.sber.spring.salary;


import com.sber.spring.salary.config.SalaryReportConfig;
import com.sber.spring.salary.notifier.SalaryHtmlReportNotifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class HomeWork17 {
    public static void main(String[] args) {
        String departmentId = "12345";
        LocalDate dateFrom = LocalDate.now().minusMonths(1);
        LocalDate dateTo = LocalDate.now();
        String recipients = "user1";
        ApplicationContext context = new AnnotationConfigApplicationContext(SalaryReportConfig.class);
        SalaryHtmlReportNotifier notifier = context.getBean(SalaryHtmlReportNotifier.class);
        notifier.generateAndSendHtmlSalaryReport(departmentId, dateFrom, dateTo, recipients);
    }
}
