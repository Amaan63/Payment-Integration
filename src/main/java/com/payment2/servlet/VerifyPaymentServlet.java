/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.payment2.servlet;

import com.razorpay.Utils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.stream.Collectors;

@WebServlet("/VerifyPaymentServlet")
public class VerifyPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader();
            String jsonString = reader.lines().collect(Collectors.joining());
            JSONObject jsonRequest = new JSONObject(jsonString);

            String orderId = jsonRequest.getString("razorpay_order_id");
            String paymentId = jsonRequest.getString("razorpay_payment_id");
            String signature = jsonRequest.getString("razorpay_signature");

            // Validate the payment
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", orderId);
            options.put("razorpay_payment_id", paymentId);
            options.put("razorpay_signature", signature);

            boolean isValid = Utils.verifyPaymentSignature(options, "Mva9ipIyqi0LHDMkkdXlGvlh");

            // Send response based on validation
            if (isValid) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("failure");
        }
    }
}
