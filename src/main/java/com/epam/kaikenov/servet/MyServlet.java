package com.epam.kaikenov.servet;

import com.epam.kaikenov.connection.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "MyServlet", urlPatterns = "/controller")
public class MyServlet extends HttpServlet {

    static final Logger jog = Logger.getLogger(MyServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection cn = ConnectionPool.getInstanceConnection();
            ResultSet rs;
            Statement statement = cn.createStatement();
            rs = statement.executeQuery("SELECT T1.CITY AS CITY_FROM, T2.CITY AS CITY_TO, FLIGHTS.ARRIVAL,\n" +
                    "FLIGHTS.DEPARTURE, FLIGHTS.\"Flight number\" FROM FLIGHTS, (SELECT * FROM CITIES) T1,\n" +
                    "(SELECT * FROM CITIES) T2 WHERE (FLIGHTS.CITY_FROM = T1.ID) AND (FLIGHTS.CITY_TO = T2.ID)");
            response.getWriter().println("<table>");
            while (rs.next()) {
                String city_from = rs.getString("city_from");
                String city_to = rs.getString("city_to");
                response.getWriter().println("<tr> <td>"
                        + "From: " + city_from + "</td> <td>"
                        + "To: " + city_to + "</td> </tr>");
            }
            response.getWriter().println("</table>" + "<hr>");
        } catch (SQLException e) {
            throw new RuntimeException("Statement error " + e);
        }

        String login = request.getParameter("Login");
        String pass = request.getParameter("password");
        response.getWriter().println("Login: " + login + "<br>");
        response.getWriter().println("Password: " + pass);
    }
}

