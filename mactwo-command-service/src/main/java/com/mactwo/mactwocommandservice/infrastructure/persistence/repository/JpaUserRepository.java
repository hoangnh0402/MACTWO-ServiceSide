package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
