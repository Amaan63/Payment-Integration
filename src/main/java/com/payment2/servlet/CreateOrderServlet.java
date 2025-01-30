/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.payment2.servlet;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("amountForBackend") == null) {
                throw new IllegalStateException("Session expired or missing payment information.");
            }
            int amountInPaise = (int) session.getAttribute("amountForBackend");
            RazorpayClient client = new RazorpayClient("Your_api_key", "Your_secret_key");

            // Create an order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInPaise); // Amount in paise (500 INR) 
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_123456"); // Replace with your unique receipt ID

            Order order = client.orders.create(orderRequest);

            // Send order details to the frontend
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("orderId", (String) order.get("id")); // Cast to String
            jsonResponse.put("amount", (Integer) order.get("amount")); // Cast to IntegerF

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            session.removeAttribute("amountForBackend");
        } catch (Exception e) {

            e.printStackTrace();
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Failed to create the order.");
            response.getWriter().write(errorResponse.toString());
        }
    }
}
