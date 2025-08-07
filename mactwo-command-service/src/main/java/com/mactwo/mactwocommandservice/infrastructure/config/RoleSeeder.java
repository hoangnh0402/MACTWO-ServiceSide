package com.mactwo.mactwocommandservice.infrastructure.config;


import com.mactwo.mactwocommandservice.domain.model.ERole;
import com.mactwo.mactwocommandservice.domain.model.Role;
import com.mactwo.mactwocommandservice.domain.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        for (ERole role : ERole.values()) {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
                System.out.println("Created role: " + role.name());
            }
        }
    }
}
