package com.adoreaosenhor.adore_ao_senhor.domain.userMusic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMusicRepository extends JpaRepository<UserMusic, Long> {
    boolean existsByUserIdAndMusicId(Long user, Long music);

    @Query(
            value = """
                SELECT um
                FROM UserMusic um
                JOIN FETCH um.music m
                WHERE um.user.id = :userId
                AND um.active = true
                AND m.institution.id = :institutionId
                """,
            countQuery = """
                SELECT COUNT(um)
                FROM UserMusic um
                JOIN um.music m
                WHERE um.user.id = :userId
                AND um.active = true
                AND m.institution.id = :institutionId
                """
    )

    Page<UserMusic> findActiveMusicsByUserAndInstitution(
            @Param("userId") Long userId,
            @Param("institutionId") Long institutionId,
            Pageable pageable
    );

    Page<UserMusic> findByUserIdAndActiveTrue(Long id, Pageable pageable);
}
