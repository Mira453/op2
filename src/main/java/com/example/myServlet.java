
package com.example;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class myServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String firstNumber = request.getParameter("firstNumber");
        String secondNumber = request.getParameter("secondNumber");
        PrintWriter out = response.getWriter();
        if(firstNumber.isEmpty() || secondNumber.isEmpty() ){
           out.println("<p>Ви передали порожній параметр</p>");
            out.close();
            return;
        }

        int sum = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
        out.println("<p>"+"Результат :" +" "+sum+"</p>");
        out.close();
    }
}
