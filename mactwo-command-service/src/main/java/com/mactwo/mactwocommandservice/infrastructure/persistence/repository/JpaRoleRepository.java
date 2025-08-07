package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.ERole;
import com.mactwo.mactwocommandservice.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);

    boolean existsByName(ERole name);
}
