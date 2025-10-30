package com.h2.hhpluserdjvm.repository;

import com.h2.hhpluserdjvm.entity.GoodsVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsVariantRepository extends JpaRepository<GoodsVariant, Long> {

    List<GoodsVariant> findByGoodsGid(Long gid);

    // 동시성 제어를 위한 Pessimistic Lock
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT v FROM GoodsVariant v WHERE v.vid = :vid")
    Optional<GoodsVariant> findByIdForUpdate(@Param("vid") Long vid);
}