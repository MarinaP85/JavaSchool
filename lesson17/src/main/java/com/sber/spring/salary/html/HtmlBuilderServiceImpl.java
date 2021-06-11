package com.sber.spring.salary.html;

import com.sber.spring.salary.exception.ReportException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HtmlBuilderServiceImpl implements HtmlBuilderService {

    @Override
    public StringBuilder reportHtml(ResultSet results) throws ReportException {
        try {
            // create a StringBuilder holding a resulting html
            StringBuilder resultingHtml = new StringBuilder();
            resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
            double totals = 0;
            while (results.next()) {
                // process each row of query results
                resultingHtml.append("<tr>"); // add row start tag
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
                resultingHtml.append("</tr>"); // add row end tag
                totals += results.getDouble("salary"); // add salary to totals
            }
            resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
            resultingHtml.append("</table></body></html>");
            return resultingHtml;
        } catch (SQLException e) {
            throw new ReportException("Html Builder error!");
        }
    }
}
