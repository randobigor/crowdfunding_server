package com.randob.crowdfunding_server.services;

import com.randob.crowdfunding_server.dto.DonationDto;
import com.randob.crowdfunding_server.model.Payment;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.model.User;
import com.randob.crowdfunding_server.repository.PaymentRepository;
import com.randob.crowdfunding_server.repository.ProjectRepository;
import com.randob.crowdfunding_server.repository.UserRepository;
import com.randob.crowdfunding_server.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author randobigor
 **/

@AllArgsConstructor
@Component
public class PaymentService {
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final PaymentRepository paymentRepository;

  public void makeDonation(DonationDto dto, Authentication authentication) {
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    userRepository.withdrawMoneyFromBalance(dto.getValue(), userDetails.getId());
    projectRepository.updateCollectedMoney(dto.getValue(), dto.getProjectId());

    Payment payment = Payment.builder()
        .user(User.builder().id(userDetails.getId()).build())
        .project(Project.builder().id(dto.getProjectId()).build())
        .value(dto.getValue())
        .anonymous(dto.isAnonymous())
        .build();

    paymentRepository.save(payment);
  }
}
