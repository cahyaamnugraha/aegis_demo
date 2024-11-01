package com.project.aegis.repository;

import com.project.aegis.model.Retur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturRepository extends JpaRepository<Retur, Long> {

}
