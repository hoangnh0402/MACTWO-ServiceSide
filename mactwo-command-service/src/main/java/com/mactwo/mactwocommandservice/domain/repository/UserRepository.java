package com.mactwo.mactwocommandservice.domain.repository;

import java.util.Optional;
import com.mactwo.mactwocommandservice.domain.model.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
