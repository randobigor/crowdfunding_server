package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author randobigor
 **/

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
