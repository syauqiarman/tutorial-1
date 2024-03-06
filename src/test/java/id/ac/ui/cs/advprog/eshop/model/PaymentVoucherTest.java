package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentVoucherTest {
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Tatang Gepeng");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Parman Suparman");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
            products, 1708570000L, "Asep Surasep");
        orders.add(order3);
    }

    @Test
    void testCreatePaymentSuccessfulVoucher() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new PaymentVoucher("814eed34-ae70-498f-8ede-271b4cbb057b", orders.get(1), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        assertSame(orders.get(1), payment.getOrder());
        assertEquals("814eed34-ae70-498f-8ede-271b4cbb057b", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentDataVoucher, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherWithStatus() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        PaymentVoucher paymentVoucherCode = new PaymentVoucher("06c4667e-489c-462e-bcb5-d44cff1d739c", orders.get(0), 
        PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), paymentVoucherCode.getOrder());
        assertEquals("06c4667e-489c-462e-bcb5-d44cff1d739c", paymentVoucherCode.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), paymentVoucherCode.getMethod());
        assertEquals(paymentDataVoucher, paymentVoucherCode.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentVoucherCode.getStatus());
    }

    @Test
    void testCreatePaymentVoucherFailed16Length() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC567");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentVoucher("06c4667e-489c-462e-bcb5-d44cff1d739c",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherFailedESHOPStart() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "SHOPE1234ABC5678");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentVoucher("06c4667e-489c-462e-bcb5-d44cff1d739c",orders.get(1),
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherFailed8Numerical() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABCD567");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentVoucher("06c4667e-489c-462e-bcb5-d44cff1d739c",orders.get(1),
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }
}