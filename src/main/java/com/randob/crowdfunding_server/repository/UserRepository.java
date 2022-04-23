package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author randobigorwd
 **/

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  @Modifying
  @Query("UPDATE User u SET u.picture = ?1 WHERE u.id = ?2")
  void updateUserPhoto(String picture, Long id);

  @Modifying
  @Query("UPDATE User u SET u.balance = u.balance + ?1 WHERE u.id = ?2")
  void addFundsToAccount(Float value, Long id);

  @Modifying
  @Query("UPDATE User u SET u.balance = u.balance - ?1 WHERE u.id = ?2")
  void withdrawMoneyFromBalance(Float value, Long id);
}
