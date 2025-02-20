
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
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String d = request.getParameter("d");
        PrintWriter out = response.getWriter();

        String regex = "^-?[0-9]+(\\.[0-9]+)?$";//числа тільки через точку
        if(a.matches(regex) != true  ||  b.matches(regex) != true  || c.matches(regex) != true   || d.matches(regex) != true  ){
            out.println("<p>це не число</p>");
            out.close();
            return;
        }
        double aNum = Double.parseDouble(a);
        double bNum = Double.parseDouble(b);
        double cNum = Double.parseDouble(c);
        double dNum = Double.parseDouble(d);

        if (Math.abs(Math.tan(aNum)) > 1e6) {
            out.println("<p>близьке значення до розриву функції тангенса</p>");
            out.close();
            return;
        }
        if (bNum > 1 || bNum < -1){
            out.println("<p>b має бути в межах від -1 до 1 </p>");
            out.close();
            return;
        }

        //завдання 15 з лр2 першого семестру
        double y = 2 * Math.sqrt(Math.tan(aNum) / Math.abs(Math.acos(bNum)))
                - 3 * Math.cbrt(Math.exp(cNum - aNum) * Math.sinh(dNum));
        if(Double.isNaN(y) ){
            throw new IllegalArgumentException("помилка. NaN");
        }
        out.println("<p>"+"значення y = " +" "+y+"</p>");
        out.close();

    }
}
