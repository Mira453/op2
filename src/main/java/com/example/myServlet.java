
package com.example;
import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.calc.calcMyTask;

@WebServlet("/myServlet")
public class myServlet extends HttpServlet {

    private static final int COOKIE_MAX_AGE = 2 * 24 * 60 * 60;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        // Отримання cookie
        Cookie[] cookies = request.getCookies();
        String a = "", b = "", c = "", d = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "a":
                        a = cookie.getValue();
                        break;
                    case "b":
                        b = cookie.getValue();
                        break;
                    case "c":
                        c = cookie.getValue();
                        break;
                    case "d":
                        d = cookie.getValue();
                        break;
                }
            }
        }

        // Відображення форми з останніми введеними значеннями
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action='/myServlet' method='post'>");
        out.println("A: <input type='text' name='a' value='" + a + "'><br>");
        out.println("B: <input type='text' name='b' value='" + b + "'><br>");
        out.println("C: <input type='text' name='c' value='" + c + "'><br>");
        out.println("D: <input type='text' name='d' value='" + d + "'><br>");
        out.println("<input type='submit' value='Обчислити'>");
        out.println("</form>");
        out.println("</body></html>");
        out.close();
    }


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

            saveCookie(response, "a", String.valueOf(a));
            saveCookie(response, "b", String.valueOf(b));
            saveCookie(response, "c", String.valueOf(c));
            saveCookie(response, "d", String.valueOf(d));

            double y = calcMyTask(a, b, c, d);
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<p>" + "значення y = " + " " + y + "</p>");
            out.println("<a href='/myServlet'>Назад</a>");
            out.println("</body></html>");
            out.close();

        }catch (NumberFormatException  | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некоректні вхідні дані");
        }

    }

    private void saveCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
