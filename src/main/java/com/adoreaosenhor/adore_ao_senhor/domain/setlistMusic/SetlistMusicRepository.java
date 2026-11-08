package com.adoreaosenhor.adore_ao_senhor.domain.setlistMusic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetlistMusicRepository extends JpaRepository<SetlistMusic, Long> {
    List<SetlistMusic> findAllBySetlistIdAndActiveTrueOrderByExecutionOrderAsc(
            Long setlistId
    );
}
