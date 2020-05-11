package com.ysm.demo.security.repository;

import com.ysm.demo.init.database.tables.USER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<USER, Long> {
    USER findByUsername(String username);
}
