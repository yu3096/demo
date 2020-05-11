package com.ysm.demo.security.repository;

import com.ysm.demo.init.database.tables.MENU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MENU, Long> {

}
