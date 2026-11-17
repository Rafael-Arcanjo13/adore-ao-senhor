package com.adoreaosenhor.adore_ao_senhor.domain.music;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Music findByName(String name);

    Music findByComposer(String name);

    Optional<Music> findByInstitutionIdAndNameIgnoreCaseAndComposerIgnoreCase(Long Institution, String name, String composer);

    Page<Music> findByInstitutionId(Long institution, Pageable pageable);

    Optional<Music> findByIdAndInstitutionId(Long musicId, Long institutionId);
}
