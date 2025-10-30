package com.h2.hhpluserdjvm.repository;

import com.h2.hhpluserdjvm.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    List<UserCoupon> findByUid(Long uid);
    List<UserCoupon> findByUidAndState(Long uid, UserCoupon.CouponState state);
}