<%-- 
    Document   : payment
    Created on : 29-Jan-2025, 11:41:07 am
    Author     : theam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    </head>
    <body>
        <h1>Razorpay Payment Integration</h1>

        <button id="pay-button" >Pay</button>
        <a href="index.jsp">clicke here</a>

        <script>
            
            document.getElementById('pay-button').onclick = function () {
                // Create an order by calling the server-side Servlet
                fetch('CreateOrderServlet', {method: 'POST'})
                        .then(response => response.json())
                        .then(data => {
                            const options = {
                                key: "rzp_test_fJv9HsC1So1wiK", // Replace with your Razorpay API Key
                                amount: data.amount, // Amount in paise
                                currency: "INR",
                                name: "Car Parking Management",
                                description: "Payment for parking",
                                order_id: data.orderId, // Order ID generated from Razorpay
                                handler: function (response) {
                                    // Call server-side to validate payment
                                    fetch('VerifyPaymentServlet', {
                                        method: 'POST',
                                        headers: {'Content-Type': 'application/json'},
                                        body: JSON.stringify({
                                            razorpay_order_id: response.razorpay_order_id,
                                            razorpay_payment_id: response.razorpay_payment_id,
                                            razorpay_signature: response.razorpay_signature
                                        })
                                    })
                                            .then(res => res.text())
                                            .then(status => {
                                                if (status === 'success') {
                                                    alert("Payment Successful!");
                                                    window.location.href = 'success.jsp';
                                                } else {
                                                    alert("Payment Failed!");
                                                    window.location.href = 'error.jsp';
                                                }
                                            });
                                },
                                theme: {color: "#1db954"}, // Customize Razorpay UI
                            };
                            const rzp = new Razorpay(options);
                            rzp.open();
                        });
            };
        </script>
    </body>
</html>
