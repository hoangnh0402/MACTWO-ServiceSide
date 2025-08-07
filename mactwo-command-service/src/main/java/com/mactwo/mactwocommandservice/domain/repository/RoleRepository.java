package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.ERole;
import com.mactwo.mactwocommandservice.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(ERole name);
    boolean existsByName(ERole name);

    Role save(Role role);
}
