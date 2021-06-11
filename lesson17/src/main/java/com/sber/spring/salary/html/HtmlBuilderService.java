package com.sber.spring.salary.html;

import com.sber.spring.salary.exception.ReportException;

import java.sql.ResultSet;

public interface HtmlBuilderService {
    StringBuilder reportHtml(ResultSet results) throws ReportException;
}
