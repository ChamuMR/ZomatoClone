package com.example.foodapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submit_registration")
public class HelloServlet extends HttpServlet {
    String  INSERT = "insert into usersdatabase (userId,userName,email,password,address) values (?,?,?,?,?)";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","9032");
            PreparedStatement preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,userName);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,address);
            int rows = preparedStatement.executeUpdate();

            if(rows == 0){
                resp.sendRedirect("failure.html");
            }else{
                resp.sendRedirect("success.html");
            }

        }catch (SQLException a){
            a.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}