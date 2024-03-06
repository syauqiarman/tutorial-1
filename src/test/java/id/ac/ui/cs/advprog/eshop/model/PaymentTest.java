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

class PaymentTest {
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", 
            products, 1708560000L, "Tatang Gepeng");
    }

    @Test
    void testContainsWithValidParam() {
        assertTrue(PaymentMethod.contains("VOUCHER_CODE"));
        assertTrue(PaymentMethod.contains("BANK_TRANSFER"));
    }

    @Test
    void testContainsWithInvalidParam() {
        assertFalse(PaymentMethod.contains("INVALID_CODE"));
    }

    @Test
    void testCreateNullOrderPayment() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
                "", null, paymentData);
        });
    }

    @Test
    void testCreatePendingPaymentVoucherStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateSuccessPaymentVoucherStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateRejectedPaymentVoucherStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateInvalidPaymentVoucherStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
                "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreateNullPaymentVoucherStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
                "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePendingPaymentBankTransferStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateSuccessPaymentBankTransferStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateRejectedPaymentBankTransferStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("6c624cd8-83c1-4cab-bd7c-d348d0b48731", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateInvalidPaymentBankTransferStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
                "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreateNullPaymentBankTransferStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
                "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToInvalid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToNull() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToSuccess() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToRejected() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToInvalid() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToNull() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "1234512345");

        Payment payment = new Payment("6c624cd8-83c1-4cab-bd7c-d348d0b48731", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }
}