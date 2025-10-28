# 📘 ERD 테이블 정의서

## MEMBER (회원)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| member_id | BIGINT | ✅ |  | N | 회원 고유 ID |
| email | VARCHAR |  | UNIQUE | N | 이메일 |
| name | VARCHAR |  |  | N | 회원 이름 |
| status | VARCHAR |  |  | N | 상태 (ACTIVE, INACTIVE) |
| created_at | DATETIME |  |  | N | 가입일시 |

---

## PRODUCT (상품)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| product_id | BIGINT | ✅ |  | N | 상품 고유 ID |
| product_name | VARCHAR |  |  | N | 상품명 |
| base_price | INT |  |  | N | 기본 가격 |
| status | VARCHAR |  |  | N | 상태 (ACTIVE, SOLD_OUT) |
| created_at | DATETIME |  |  | N | 등록일시 |

---

## PRODUCT_OPTION (상품 옵션)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| option_id | BIGINT | ✅ |  | N | 옵션 고유 ID |
| product_id | BIGINT |  | ✅ | N | 상품 ID |
| option_name | VARCHAR |  |  | N | 옵션명 (예: 빨강-L) |
| additional_price | INT |  |  | N | 추가 금액 |
| status | VARCHAR |  |  | N | 상태 (ACTIVE, INACTIVE) |
| created_at | DATETIME |  |  | N | 등록일시 |

---

## STOCK (재고)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| stock_id | BIGINT | ✅ |  | N | 재고 ID |
| option_id | BIGINT |  | ✅ | N | 옵션 ID (유니크) |
| available_quantity | INT |  |  | N | 판매 가능 수량 |
| reserved_quantity | INT |  |  | N | 예약 중 수량 |
| version | BIGINT |  |  | N | 버전 (낙관적 락용) |
| updated_at | DATETIME |  |  | N | 수정 일시 |

---

## COUPON (쿠폰)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| coupon_id | BIGINT | ✅ |  | N | 쿠폰 ID |
| coupon_name | VARCHAR |  |  | N | 쿠폰명 |
| discount_type | VARCHAR |  |  | N | 할인 타입 (RATE, AMOUNT) |
| discount_value | INT |  |  | N | 할인 값 |
| min_order_amount | INT |  |  | N | 최소 주문 금액 |
| max_discount_amount | INT |  |  | N | 최대 할인 금액 |
| total_quantity | INT |  |  | N | 총 발급 수량 |
| issued_quantity | INT |  |  | N | 발급 완료 수량 |
| valid_from | DATETIME |  |  | N | 사용 시작 일시 |
| valid_until | DATETIME |  |  | N | 사용 만료 일시 |
| created_at | DATETIME |  |  | N | 생성 일시 |

---

## MEMBER_COUPON (회원 쿠폰)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| member_coupon_id | BIGINT | ✅ |  | N | 회원 쿠폰 ID |
| member_id | BIGINT |  | ✅ | N | 회원 ID |
| coupon_id | BIGINT |  | ✅ | N | 쿠폰 ID |
| status | VARCHAR |  |  | N | 상태 (UNUSED, USED, EXPIRED) |
| issued_at | DATETIME |  |  | N | 발급 일시 |
| used_at | DATETIME |  |  | Y | 사용 일시 |
| expired_at | DATETIME |  |  | Y | 만료 일시 |

---

## ORDERS (주문)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| order_id | BIGINT | ✅ |  | N | 주문 ID |
| member_id | BIGINT |  | ✅ | N | 회원 ID |
| member_coupon_id | BIGINT |  | ✅ | Y | 사용한 회원 쿠폰 ID |
| order_number | VARCHAR |  | UNIQUE | N | 주문 번호 |
| status | VARCHAR |  |  | N | 상태 (PENDING, CONFIRMED, CANCELLED) |
| product_total_amount | INT |  |  | N | 상품 금액 합계 |
| discount_amount | INT |  |  | N | 할인 금액 |
| final_amount | INT |  |  | N | 최종 결제 금액 |
| created_at | DATETIME |  |  | N | 주문 일시 |

---

## ORDER_ITEM (주문 상품)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| order_item_id | BIGINT | ✅ |  | N | 주문 상품 ID |
| order_id | BIGINT |  | ✅ | N | 주문 ID |
| product_id | BIGINT |  | ✅ | N | 상품 ID |
| option_id | BIGINT |  | ✅ | N | 옵션 ID |
| quantity | INT |  |  | N | 주문 수량 |
| unit_price | INT |  |  | N | 단가 |
| total_price | INT |  |  | N | 상품별 총 금액 |
| created_at | DATETIME |  |  | N | 생성 일시 |

---

## STOCK_HISTORY (재고 이력)
| 컬럼명 | 타입 | PK | FK | NULL 허용 | 설명 |
|--------|------|----|----|------------|------|
| history_id | BIGINT | ✅ |  | N | 이력 ID |
| stock_id | BIGINT |  | ✅ | N | 재고 ID |
| order_id | BIGINT |  | ✅ | Y | 주문 ID |
| change_type | VARCHAR |  |  | N | 변경 타입 (RESERVE, CONFIRM, CANCEL) |
| quantity_change | INT |  |  | N | 변경 수량 |
| before_quantity | INT |  |  | N | 변경 전 수량 |
| after_quantity | INT |  |  | N | 변경 후 수량 |
| reason | VARCHAR |  |  | Y | 변경 사유 |
| created_at | DATETIME |  |  | N | 생성 일시 |

---

## 🔗 관계 요약
| 관계 | 설명 |
|------|------|
| MEMBER 1 — N MEMBER_COUPON | 한 회원이 여러 쿠폰을 가질 수 있음 |
| COUPON 1 — N MEMBER_COUPON | 한 쿠폰이 여러 회원에게 발급될 수 있음 |
| MEMBER 1 — N ORDERS | 회원은 여러 주문을 할 수 있음 |
| MEMBER_COUPON 1 — 1 ORDERS | 주문 시 회원 쿠폰을 사용할 수 있음 |
| ORDERS 1 — N ORDER_ITEM | 한 주문에 여러 상품이 포함됨 |
| PRODUCT 1 — N PRODUCT_OPTION | 상품은 여러 옵션을 가질 수 있음 |
| PRODUCT_OPTION 1 — 1 STOCK | 옵션별로 재고가 하나씩 존재함 |
| PRODUCT_OPTION 1 — N ORDER_ITEM | 주문 시 옵션 포함 |
| STOCK 1 — N STOCK_HISTORY | 재고별로 여러 변경 이력 존재 |
| ORDERS 1 — N STOCK_HISTORY | 주문에 의해 재고 이력이 남음 |
