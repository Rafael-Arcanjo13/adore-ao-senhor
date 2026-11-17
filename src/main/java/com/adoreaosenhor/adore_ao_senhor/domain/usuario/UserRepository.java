package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    Page<User> findByInstitutionIdAndActiveTrue(Long institutionId, Pageable pageable);

    Optional<User> findByIdAndInstitutionId(Long userId, Long institutionId);
}
