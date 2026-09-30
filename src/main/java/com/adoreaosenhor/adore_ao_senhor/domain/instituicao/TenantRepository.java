package com.adoreaosenhor.adore_ao_senhor.domain.instituicao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Instituicao, Long> {
}
