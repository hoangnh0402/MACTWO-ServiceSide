package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.Banner;
import com.mactwo.mactwocommandservice.domain.repository.BannerRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaBannerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BannerRepositoryJpaImpl implements BannerRepository {
    private final JpaBannerRepository jpaRepository;

    public BannerRepositoryJpaImpl(JpaBannerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Banner save(Banner banner) {
        return jpaRepository.save(banner);
    }

    @Override
    public Optional<Banner> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
