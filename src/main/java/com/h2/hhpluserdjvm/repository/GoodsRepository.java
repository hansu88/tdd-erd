package com.h2.hhpluserdjvm.repository;

import com.h2.hhpluserdjvm.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}