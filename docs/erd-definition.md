# HHPlus 최소 ERD 정의

## 개요
- 스프링 기반 이커머스 최소 구현용
- 상품 조회 → 장바구니 → 주문 → 쿠폰 흐름
- 재고, 동시성, 락 등은 점진적 고도화 가능

## ERD 다이어그램 (Mermaid)

```mermaid
erDiagram
    MEMBER {
        bigint member_id PK "회원ID"
        varchar email "이메일"
        varchar name "회원명"
        int balance "잔액"
        varchar status "상태(ACTIVE,INACTIVE)"
        datetime created_at "가입일시"
    }

    PRODUCT {
        bigint product_id PK "상품ID"
        varchar product_name "상품명"
        int base_price "기본가격"
        varchar status "상태(ACTIVE,SOLD_OUT)"
        datetime created_at "등록일시"
    }

    PRODUCT_OPTION {
        bigint option_id PK "옵션ID"
        bigint product_id FK "상품ID"
        varchar option_name "옵션명"
        int additional_price "추가금액"
        varchar status "상태(ACTIVE,INACTIVE)"
        int available_stock "재고수량"
    }

    CART {
        bigint cart_id PK "장바구니ID"
        bigint member_id FK "회원ID"
        bigint option_id FK "옵션ID"
        int quantity "수량"
        datetime created_at "담은일시"
    }

    COUPON {
        bigint coupon_id PK "쿠폰ID"
        varchar coupon_name "쿠폰명"
        varchar discount_type "할인타입(RATE,AMOUNT)"
        int discount_value "할인값"
        int total_quantity "총발급수량"
        int issued_quantity "발급완료수량"
        datetime valid_from "사용시작일시"
        datetime valid_until "사용만료일시"
    }

    MEMBER_COUPON {
        bigint member_coupon_id PK "회원쿠폰ID"
        bigint member_id FK "회원ID"
        bigint coupon_id FK "쿠폰ID"
        varchar status "상태(UNUSED,USED,EXPIRED)"
        datetime issued_at "발급일시"
        datetime used_at "사용일시"
    }

    ORDERS {
        bigint order_id PK "주문ID"
        bigint member_id FK "회원ID"
        bigint member_coupon_id FK "사용한회원쿠폰ID(nullable)"
        varchar status "상태(PENDING,CONFIRMED,CANCELLED)"
        int final_amount "최종결제금액"
        datetime created_at "주문일시"
    }

    ORDER_ITEM {
        bigint order_item_id PK "주문상품ID"
        bigint order_id FK "주문ID"
        bigint product_id FK "상품ID"
        bigint option_id FK "옵션ID"
        int quantity "주문수량"
        int unit_price "단가"
        int total_price "상품총금액"
    }

    MEMBER ||--o{ CART : "장바구니담기"
    MEMBER ||--o{ MEMBER_COUPON : "쿠폰발급"
    MEMBER ||--o{ ORDERS : "주문"
    COUPON ||--o{ MEMBER_COUPON : "회원발급"
    MEMBER_COUPON ||--o| ORDERS : "사용"
    ORDERS ||--o{ ORDER_ITEM : "포함"
    PRODUCT ||--o{ PRODUCT_OPTION : "옵션보유"
    PRODUCT_OPTION ||--o{ CART : "장바구니담김"
    PRODUCT ||--o{ ORDER_ITEM : "주문포함"
    PRODUCT_OPTION ||--o{ ORDER_ITEM : "주문옵션"