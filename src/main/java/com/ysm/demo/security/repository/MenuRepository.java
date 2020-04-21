package com.ysm.demo.security.repository;

import com.ysm.demo.init.database.tables.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, MenuPks> {

}
