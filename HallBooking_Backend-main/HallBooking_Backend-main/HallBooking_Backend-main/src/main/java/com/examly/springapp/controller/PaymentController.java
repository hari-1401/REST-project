package com.examly.springapp.controller;


import com.examly.springapp.entity.Payment;
import com.examly.springapp.service.PaymentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    
    // @GetMapping("/{id}")
    // public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
    //     return ResponseEntity.ok(paymentService.getPaymentById(id));
    // }

    
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.updatePayment(id, payment));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }
     @GetMapping("/paginate")
     public Page<Payment> getAllPage(Pageable pg)
     {
        return paymentService.getAllPage(pg);
     }

     @GetMapping("{id}")
    public ResponseEntity<Object> getPayment(@PathVariable("id") long id) {
    Payment payment= paymentService.getPayment(id);
    if (payment == null)
    {
        return ResponseEntity.status(200).body("Book not found with ID: " + id);
    }
    return ResponseEntity.ok(payment);
    }
}
