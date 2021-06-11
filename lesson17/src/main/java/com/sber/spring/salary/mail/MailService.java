package com.sber.spring.salary.mail;

import com.sber.spring.salary.exception.ReportException;

public interface MailService {
    void sendReport(String recipients, String report) throws ReportException;
}
