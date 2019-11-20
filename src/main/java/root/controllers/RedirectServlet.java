package root.controllers;


import spark.Request;
import spark.Response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class RedirectServlet extends HttpServlet {
    private static RedirectServlet instance ;

    public static RedirectServlet getInstance(){
        if(instance == null){
            instance = new RedirectServlet();
        }
        return instance;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RedirectServlet");
        response.sendRedirect("http://www.google.com");
    }


}
