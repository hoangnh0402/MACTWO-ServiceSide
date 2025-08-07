package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.ERole;
import com.mactwo.mactwocommandservice.domain.model.Role;
import com.mactwo.mactwocommandservice.domain.repository.RoleRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryJpaImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryJpaImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByName(ERole name) {
        return jpaRoleRepository.findByName(name);
    }

    @Override
    public boolean existsByName(ERole name) {
        return jpaRoleRepository.existsByName(name);
    }

    @Override
    public Role save(Role role) {
        return jpaRoleRepository.save(role);
    }

}
