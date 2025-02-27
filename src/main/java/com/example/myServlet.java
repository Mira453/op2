
package com.example;
import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.calc.calcMyTask;

public class myServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            response.setContentType("text/html; charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));
            double d = Double.parseDouble(request.getParameter("d"));

            double y = calcMyTask(a, b, c, d);
            PrintWriter out = response.getWriter();
            out.println("<p>" + "значення y = " + " " + y + "</p>");
            out.close();
        }catch (NumberFormatException  | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некоректні вхідні дані");
        }

    }
}
