package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.Banner;
import java.util.Optional;

public interface BannerRepository {
    Banner save(Banner banner);
    Optional<Banner> findById(Long id);
    void deleteById(Long id);
}
