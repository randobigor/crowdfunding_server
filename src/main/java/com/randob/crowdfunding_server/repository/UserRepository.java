package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author randobigor
 **/

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("update User u set u.picture = ?1 where u.id = ?2")
  void updateUserPhotoById(String picture, Long id);
}
