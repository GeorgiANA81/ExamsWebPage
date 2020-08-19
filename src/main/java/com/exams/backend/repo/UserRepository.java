package com.exams.backend.repo;

import com.exams.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getOneByEmail(String email);

    boolean existsByEmail(String email);
}
