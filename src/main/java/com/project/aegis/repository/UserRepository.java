package com.project.aegis.repository;

import com.project.aegis.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Transactional
  Optional<User> findByUsername(String username);

  @Transactional
  Optional<User> findByUsernameAndIsActive(String username, boolean isActive);

  @Transactional
  Boolean existsByUsername(String username);

  @Transactional
  User findById(long id);


  @Modifying
  @Transactional
  @Query("update User u set u.isActive = ?1 where u.id = ?2")
  int setIsActive(boolean isAcvtive, Long id);

  @Modifying
  @Transactional
  @Query("update User u set u.isDelete = ?1, u.deletedAt = CURRENT_TIMESTAMP where u.id = ?2")
  int deleteUser(boolean banned, Long id);


}
