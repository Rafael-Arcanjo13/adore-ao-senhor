package com.adoreaosenhor.adore_ao_senhor.domain.userMusic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMusicRepository extends JpaRepository<UserMusic, Long> {
    boolean existsByUserIdAndMusicId(Long user, Long music);

    Page<UserMusic> findByUserIdAndActiveTrue(Long userId, Pageable pageable);
}
