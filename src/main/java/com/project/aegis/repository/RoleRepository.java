package com.project.aegis.repository;

import com.project.aegis.enums.EnumRole;
import com.project.aegis.model.Role;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Transactional
  Optional<Role> findByName(EnumRole name);

  @Transactional
  Optional<Role> findById(int id);
}
