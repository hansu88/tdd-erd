# Mock API 기반 시퀀스 다이어그램

현재 구현된 Mock API (RestTemplate + json-server) 기반의 시퀀스 다이어그램입니다.

## 구현된 API (6개)

### 상품 관리 (3개)
- `product-list.mmd` - 상품 목록 조회
- `product-stock.mmd` - 상품 재고 조회
- `product-popular.mmd` - 인기 상품 조회

### 장바구니 (2개)
- `cart-all.mmd` - 전체 장바구니 조회
- `cart-member.mmd` - 개인 장바구니 조회

### 주문 (1개)
- `order-create.mmd` - 주문 생성 (쿠폰 할인 포함)

## 특징

- RestTemplate 기반 HTTP 통신
- Mock API (localhost:3000) 사용
- 재고/잔액 차감 미포함 (DB 도입 시 추가 예정)
