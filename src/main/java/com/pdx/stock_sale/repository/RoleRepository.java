package com.pdx.stock_sale.repository;

import com.pdx.stock_sale.model.master.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    boolean existsByName(String name);
}
