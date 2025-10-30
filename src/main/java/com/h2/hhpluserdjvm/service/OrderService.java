package com.h2.hhpluserdjvm.service;

import com.h2.hhpluserdjvm.dto.cart.CartDto;
import com.h2.hhpluserdjvm.dto.coupon.CouponDto;
import com.h2.hhpluserdjvm.dto.coupon.MemberCouponDto;
import com.h2.hhpluserdjvm.dto.member.MemberDto;
import com.h2.hhpluserdjvm.dto.order.OrderDto;
import com.h2.hhpluserdjvm.dto.order.OrderResponseDto;
import com.h2.hhpluserdjvm.dto.product.ProductDto;
import com.h2.hhpluserdjvm.dto.product.ProductOptionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final String MOCK_API_BASE = "http://localhost:3000";

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
     * 주문 생성 (결제 제외)
     */
    public OrderResponseDto createOrder(Long memberId, Long memberCouponId) {
        CartDto[] carts = restTemplate.getForObject(MOCK_API_BASE + "/carts?memberId=" + memberId, CartDto[].class);
        if (carts == null || carts.length == 0) throw new RuntimeException("장바구니가 비어있음");

        int total = 0;
        for (CartDto cart : carts) {
            ProductOptionDto[] optionsArr = restTemplate.getForObject(
                    MOCK_API_BASE + "/productOptions?optionId=" + cart.getOptionId(), ProductOptionDto[].class);
            ProductOptionDto option = optionsArr != null && optionsArr.length > 0 ? optionsArr[0] : null;
            if (option == null) throw new RuntimeException("옵션 없음");

            ProductDto[] prodArr = restTemplate.getForObject(
                    MOCK_API_BASE + "/products?productId=" + option.getProductId(), ProductDto[].class);
            ProductDto product = prodArr != null && prodArr.length > 0 ? prodArr[0] : null;
            if (product == null) throw new RuntimeException("상품 없음");

            // 재고 체크 없이 가격 계산만
            total += (product.getBasePrice() + option.getAdditionalPrice()) * cart.getQuantity();
        }
        System.out.println(total);

        int discount = 0;
        if (memberCouponId != null) {
            // 회원 쿠폰 조회
            MemberCouponDto[] mcArr = restTemplate.getForObject(
                    MOCK_API_BASE + "/memberCoupons?memberCouponId=" + memberCouponId, MemberCouponDto[].class);
            MemberCouponDto mc = null;
            if (mcArr != null && mcArr.length > 0) {
                mc = mcArr[0];
            }

            if (mc == null || !"UNUSED".equals(mc.getStatus())) {
                throw new RuntimeException("쿠폰 사용 불가");
            }

            // 쿠폰 정보 조회
            CouponDto[] coupons = restTemplate.getForObject(
                    MOCK_API_BASE + "/coupons?couponId=" + mc.getCouponId(),
                    CouponDto[].class
            );

            if (coupons == null || coupons.length == 0) {
                throw new RuntimeException("쿠폰 정보 없음");
            }

            CouponDto cpn = coupons[0]; // 배열 첫 번째 요소 사용

            if ("RATE".equals(cpn.getDiscountType())) {
                discount = total * cpn.getDiscountValue() / 100;
            } else if ("AMOUNT".equals(cpn.getDiscountType())) {
                discount = cpn.getDiscountValue();
            } else {
                discount = 0; // DELIVERY 등 다른 타입은 할인 없음
            }
        }

        int finalAmount = total - discount;

        OrderDto created = new OrderDto();
        created.setOrderId(999L); // 임의의 주문 ID
        created.setMemberId(memberId);
        created.setStatus("PAID");
        created.setFinalAmount(finalAmount);
        created.setCreatedAt(LocalDateTime.now());

        return new OrderResponseDto(
                created.getOrderId(),
                created.getStatus(),
                total,
                discount,
                finalAmount,
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }
}