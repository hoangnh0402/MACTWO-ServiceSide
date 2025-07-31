package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}
