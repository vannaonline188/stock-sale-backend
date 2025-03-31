package com.pdx.stock_sale.repository;

import com.pdx.stock_sale.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {
}
