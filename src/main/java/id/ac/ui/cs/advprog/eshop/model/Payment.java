package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;

    String status;
    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
            this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
        }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        } else {
            this.order = order;
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        if (PaymentMethod.contains(this.method)) {
            throw new IllegalArgumentException("Unable to assign payment data specific to a method when the payment method is not specified");
        } else {
            this.paymentData = null;
        }
    }
}