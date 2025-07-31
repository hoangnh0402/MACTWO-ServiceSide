package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface JpaBannerRepository extends JpaRepository<Banner, Long> {
}
