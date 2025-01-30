# Payment-Integration
# Payment Integration Project using JSP, Servlet, and Razorpay

## **Project Overview**
This project demonstrates a payment gateway integration in a Java web application using JSP, Servlets, and the Razorpay API. The system allows users to make secure online payments, enhancing the functionality of web applications requiring e-commerce or transactional services.

---

## **Key Features**
- Secure payment processing with Razorpay API.
- Dynamic handling of payment amounts and transaction details.
- Payment success and error handling pages.
- Clean and modular code structure following MVC architecture.

---

## **Technologies Used**
- **Backend:** Java Servlets, JSP
- **Frontend:** HTML, CSS, Bootstrap (for UI enhancements)
- **Payment Gateway:** Razorpay API
- **Build Tool:** Maven
- **Server:** Apache Tomcat

---

## **Setup Instructions**

### **1. Clone the Repository:**
```bash
$ git clone <repository-url>
$ cd <project-directory>
```

### **2. Configure Razorpay API:**
1. Create an account on [Razorpay](https://razorpay.com).
2. Generate API Key and Secret from Razorpay Dashboard.
3. Update the API key and other payment parameters in your JSP file:
   ```jsp
   var options = {
       "key": "YOUR_API_KEY",
       "amount": paymentAmount,
       "currency": "INR",
       "name": "Your Application Name",
       "description": "Payment Test",
       "handler": function (response) {
           // Payment Success Logic
       }
   };
   ```



### **3. Deploy on Tomcat Server:**
1. Build the project using Maven:
   ```bash
   $ mvn clean install
   ```
2. Deploy the generated WAR file to Apache Tomcat.

### **4. Run the Application:**
Access the application at:
```
http://localhost:8080/payment-integration
```

---

## **Application Flow**
1. User navigates to the payment page.
2. Razorpay Checkout form is triggered upon form submission.
3. Successful payments are handled by the `SuccessServlet`, which processes and verifies the payment.
4. Errors are redirected to `error.jsp`.

---

## **Project Directory Structure**
```
Payment-Integration/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── servlets/
│   │   │       ├── AmountSettingServlet.java
│   │   │       ├── CreateOrderServlet.java
│   │   │       └── VerifyPaymentServlet.java
│   │   └── webapp/
│   │       ├── index.jsp
│   │       ├── payment.jsp
│   │       ├── success.jsp
│   │       └── error.jsp
└── pom.xml
```

---

## **Usage Instructions**
1. Go to the payment page.
2. Enter the required payment details.
3. Click "Pay" to complete the transaction.
4. Upon successful payment, view the transaction confirmation page.

---

## **Important Notes**
- Ensure the API keys are kept secure.
- Handle database and payment errors gracefully.
- Razorpay API has a sandbox environment for testing before moving to production.

---

## **Contact**
For any queries or contributions, please reach out via GitHub Issues.

---

### **License**
This project is licensed under the MIT License – feel free to use, modify, and distribute.
