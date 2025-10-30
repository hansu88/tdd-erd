# 시퀀스 다이어그램

HHPlus 이커머스 API 10개의 시퀀스 다이어그램입니다.

## 파일 목록

### 상품 관리 (3개)
1. [01-goods-list.mmd](./01-goods-list.mmd) - GET /api/goods
2. [02-goods-detail.mmd](./02-goods-detail.mmd) - GET /api/goods/{gid}
3. [03-goods-popular.mmd](./03-goods-popular.mmd) - GET /api/goods/popular

### 장바구니 (3개)
4. [04-cart-add.mmd](./04-cart-add.mmd) - POST /api/cart
5. [05-cart-list.mmd](./05-cart-list.mmd) - GET /api/cart
6. [06-cart-delete.mmd](./06-cart-delete.mmd) - DELETE /api/cart/{cid}

### 주문/결제 (2개)
7. [07-order-create.mmd](./07-order-create.mmd) - POST /api/order
8. [08-order-pay.mmd](./08-order-pay.mmd) - POST /api/order/{oid}/pay

### 쿠폰 (2개)
9. [09-coupon-issue.mmd](./09-coupon-issue.mmd) - POST /api/coupon/{cid}/issue
10. [10-coupon-my.mmd](./10-coupon-my.mmd) - GET /api/coupon/my

## 특징

- ✅ H2 + JPA 기반 구현
- ✅ 정상 흐름 + 에러 케이스 포함
- ✅ 동시성 제어 표시 (Pessimistic Lock)
- ✅ 트랜잭션 경계 표시 (activate/deactivate)
- ✅ 에러 코드 포함

## 에러 케이스

| 다이어그램 | 에러 케이스 |
|-----------|-------------|
| 02-goods-detail | P001 - 상품을 찾을 수 없음 |
| 04-cart-add | P002 - 재고 부족 |
| 06-cart-delete | CART001 - 장바구니 항목 없음 |
| 07-order-create | P002 - 재고 부족 |
| 08-order-pay | O002 - 주문 없음<br/>PAY001 - 잔액 부족 |
| 09-coupon-issue | C001 - 쿠폰 소진 |

## Mermaid 렌더링

GitHub, GitLab, VS Code 등에서 자동으로 렌더링됩니다.

또는 [Mermaid Live Editor](https://mermaid.live)에서 직접 확인할 수 있습니다.
