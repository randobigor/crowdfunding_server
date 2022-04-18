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
  @Query("update User u set u.picture = ?1 where u.id = ?2")
  void updateUserPhotoById(String picture, Long id);

  @Modifying
  @Query("update User u set u.balance = u.balance + ?1 where u.id = ?2")
  void addFundsToAccountById(Float value, Long id);
}
