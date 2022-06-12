package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.Payment;
import com.randob.crowdfunding_server.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author randobigor
 **/

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
  private final PaymentRepository paymentRepository;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/{id}")
  public List<Payment> getPaymentByProjectId(@PathVariable Long id) {
    return paymentRepository.getAllByProjectId(id);
  }
}
