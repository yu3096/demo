package com.ysm.demo.security.repository;

import com.ysm.demo.init.database.tables.COMMON_CODE;
import com.ysm.demo.init.database.tables.dataClasses.CommonCodePks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepository extends JpaRepository<COMMON_CODE, CommonCodePks> {
}
