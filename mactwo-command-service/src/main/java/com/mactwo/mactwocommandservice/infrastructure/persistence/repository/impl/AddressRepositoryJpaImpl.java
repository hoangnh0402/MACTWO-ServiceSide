package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.Address;
import com.mactwo.mactwocommandservice.domain.repository.AddressRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaAddressRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryJpaImpl implements AddressRepository {
    private final JpaAddressRepository jpaRepository;

    public AddressRepositoryJpaImpl(JpaAddressRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Address save(Address address) {
        return jpaRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }
}
