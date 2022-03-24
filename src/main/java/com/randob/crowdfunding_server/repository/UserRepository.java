package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author randobigor
 **/

public interface UserRepository extends JpaRepository<User, Long> {
}
