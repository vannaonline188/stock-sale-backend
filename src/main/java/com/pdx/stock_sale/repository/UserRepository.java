package com.pdx.stock_sale.repository;

import com.pdx.stock_sale.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByUsername(String name);
}
