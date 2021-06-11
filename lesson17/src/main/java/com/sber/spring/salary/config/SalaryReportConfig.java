package com.sber.spring.salary.config;

import com.sber.spring.salary.connection.H2DB;
import com.sber.spring.salary.connection.Source;
import com.sber.spring.salary.employee.EmployeeReport;
import com.sber.spring.salary.employee.EmployeeReportImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sber.spring")
public class SalaryReportConfig {
    @Bean
    public Source dbSource() {
        return new H2DB();
    }

    @Bean
    public EmployeeReport employeeReport(Source dbSource) {
        return new EmployeeReportImpl(dbSource().connection());
    }
}
