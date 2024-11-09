package com.easyTickets.user_service.userRepository;

import com.easyTickets.user_service.userModel.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> findByEmail(String email);

}
