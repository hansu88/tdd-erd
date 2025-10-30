package com.h2.hhpluserdjvm.repository;

import com.h2.hhpluserdjvm.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUid(Long uid);
}