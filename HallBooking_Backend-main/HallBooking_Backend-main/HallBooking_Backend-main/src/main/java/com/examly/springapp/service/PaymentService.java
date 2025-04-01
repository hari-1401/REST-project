package com.examly.springapp.service;

import com.examly.springapp.entity.Payment;
import com.examly.springapp.repository.PaymentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment updatedPayment = existingPayment.get();
            updatedPayment.setBookingId(payment.getBookingId());
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setPaymentStatus(payment.getPaymentStatus());
            updatedPayment.setPaymentDate(payment.getPaymentDate());
            return paymentRepository.save(updatedPayment);
        } else {
            throw new RuntimeException("Payment not found with ID: " + id);
        }
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
    }
    public Page<Payment> getAllPage(Pageable pg)
   {
      return paymentRepository.findAll(pg);
   }

    public Payment getPayment(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPayment'");
    }
}
