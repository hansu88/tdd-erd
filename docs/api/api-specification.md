# API 명세서

## 목차
- [1. 상품 관리](#1-상품-관리)
- [2. 장바구니](#2-장바구니)
- [3. 주문/결제](#3-주문결제)
- [4. 쿠폰](#4-쿠폰)
- [5. 공통 응답 형식](#5-공통-응답-형식)

---

## 1. 상품 관리

### 1.1 전체 상품 조회
**GET** `/api/goods`

전체 상품 목록을 조회합니다.

**Request**
- Query Parameters: 없음

**Response** `200 OK`
```json
{
  "goods": [
    {
      "gid": 1,
      "title": "기본 티셔츠",
      "info": "편안한 기본 티셔츠",
      "state": "ACTIVE",
      "ctime": "2025-01-15T10:00:00"
    }
  ]
}
```

| 필드 | 타입 | 설명 |
|------|------|------|
| gid | Integer | 상품 ID |
| title | String | 상품명 |
| info | String | 상품 설명 |
| state | String | 상품 상태 (ACTIVE, INACTIVE) |
| ctime | DateTime | 등록일시 |

---

### 1.2 상품 상세 조회
**GET** `/api/goods/{gid}`

특정 상품의 상세 정보와 옵션을 조회합니다.

**Request**
- Path Parameters
  - `gid` (Integer, required): 상품 ID

**Response** `200 OK`
```json
{
  "gid": 1,
  "title": "기본 티셔츠",
  "info": "편안한 기본 티셔츠",
  "state": "ACTIVE",
  "ctime": "2025-01-15T10:00:00",
  "variants": [
    {
      "vid": 101,
      "vname": "색상",
      "vval": "화이트",
      "price": 29000,
      "stock": 50
    },
    {
      "vid": 102,
      "vname": "색상",
      "vval": "블랙",
      "price": 29000,
      "stock": 30
    }
  ]
}
```

**Error Response** `404 Not Found`
```json
{
  "error": "P001",
  "message": "상품을 찾을 수 없습니다."
}
```

---

### 1.3 인기 상품 조회
**GET** `/api/goods/popular`

최근 N일간 판매량 기준 Top N 상품을 조회합니다.

**Request**
- Query Parameters
  - `days` (Integer, optional, default=3): 집계 기간 (일)
  - `limit` (Integer, optional, default=5): 조회 개수

**Response** `200 OK`
```json
{
  "period": "3days",
  "goods": [
    {
      "gid": 1,
      "title": "기본 티셔츠",
      "salesCount": 150,
      "revenue": 4350000
    }
  ]
}
```

---

## 2. 장바구니

### 2.1 장바구니 담기
**POST** `/api/cart`

상품 옵션을 장바구니에 담습니다.

**Request Body**
```json
{
  "uid": 1,
  "vid": 101,
  "cnt": 2
}
```

| 필드 | 타입 | 필수 | 설명 | 제약조건 |
|------|------|------|------|----------|
| uid | Integer | Y | 사용자 ID | > 0 |
| vid | Integer | Y | 옵션 ID | > 0 |
| cnt | Integer | Y | 수량 | 1 ~ 99 |

**Response** `200 OK`
```json
{
  "cid": 1,
  "uid": 1,
  "vid": 101,
  "cnt": 2,
  "goodsTitle": "기본 티셔츠",
  "variantName": "화이트",
  "price": 29000,
  "totalPrice": 58000
}
```

**Error Response** `400 Bad Request`
```json
{
  "error": "P002",
  "message": "재고가 부족합니다."
}
```

---

### 2.2 장바구니 조회
**GET** `/api/cart`

사용자의 장바구니 목록을 조회합니다.

**Request**
- Query Parameters
  - `uid` (Integer, required): 사용자 ID

**Response** `200 OK`
```json
{
  "items": [
    {
      "cid": 1,
      "vid": 101,
      "cnt": 2,
      "goodsTitle": "기본 티셔츠",
      "variantName": "화이트",
      "price": 29000,
      "subtotal": 58000
    }
  ],
  "totalAmount": 58000
}
```

---

### 2.3 장바구니 삭제
**DELETE** `/api/cart/{cid}`

장바구니에서 특정 항목을 삭제합니다.

**Request**
- Path Parameters
  - `cid` (Integer, required): 장바구니 항목 ID

**Response** `204 No Content`

---

## 3. 주문/결제

### 3.1 주문 생성
**POST** `/api/order`

장바구니 상품을 기반으로 주문을 생성합니다.

**Request Body**
```json
{
  "uid": 1,
  "items": [
    {
      "vid": 101,
      "qty": 2
    }
  ],
  "couponId": 1
}
```

| 필드 | 타입 | 필수 | 설명 | 제약조건 |
|------|------|------|------|----------|
| uid | Integer | Y | 사용자 ID | > 0 |
| items | Array | Y | 주문 상품 목록 | 최소 1개 |
| items[].vid | Integer | Y | 옵션 ID | > 0 |
| items[].qty | Integer | Y | 수량 | 1 ~ 99 |
| couponId | Integer | N | 쿠폰 ID | - |

**Response** `200 OK`
```json
{
  "oid": 1,
  "uid": 1,
  "items": [
    {
      "vid": 101,
      "goodsTitle": "기본 티셔츠",
      "variantName": "화이트",
      "qty": 2,
      "price": 29000,
      "subtotal": 58000
    }
  ],
  "subtotalAmount": 58000,
  "discountAmount": 5800,
  "finalAmount": 52200,
  "status": "PENDING"
}
```

**Error Response** `400 Bad Request`
```json
{
  "error": "O001",
  "message": "잘못된 수량입니다."
}
```

---

### 3.2 주문 결제
**POST** `/api/order/{oid}/pay`

생성된 주문을 결제 처리합니다.

**Request**
- Path Parameters
  - `oid` (Integer, required): 주문 ID

**Request Body**
```json
{
  "uid": 1,
  "method": "BALANCE"
}
```

| 필드 | 타입 | 필수 | 설명 | 제약조건 |
|------|------|------|------|----------|
| uid | Integer | Y | 사용자 ID | > 0 |
| method | String | Y | 결제 수단 | BALANCE, CARD |

**Response** `200 OK`
```json
{
  "pid": 1,
  "oid": 1,
  "amt": 52200,
  "method": "BALANCE",
  "status": "SUCCESS",
  "paidAt": "2025-01-20T14:30:00",
  "remainingBalance": 447800
}
```

**Error Response** `402 Payment Required`
```json
{
  "error": "PAY001",
  "message": "잔액이 부족합니다."
}
```

---

## 4. 쿠폰

### 4.1 쿠폰 발급
**POST** `/api/coupon/{cid}/issue`

선착순 쿠폰을 발급받습니다.

**Request**
- Path Parameters
  - `cid` (Integer, required): 쿠폰 ID

**Request Body**
```json
{
  "uid": 1
}
```

| 필드 | 타입 | 필수 | 설명 | 제약조건 |
|------|------|------|------|----------|
| uid | Integer | Y | 사용자 ID | > 0 |

**Response** `200 OK`
```json
{
  "ucid": 1,
  "uid": 1,
  "cid": 1,
  "couponName": "10% 할인 쿠폰",
  "discountType": "RATE",
  "discountValue": 10,
  "state": "ACTIVE",
  "issuedAt": "2025-01-20T14:00:00",
  "remainingQuantity": 95
}
```

**Error Response** `409 Conflict`
```json
{
  "error": "C001",
  "message": "쿠폰이 모두 소진되었습니다."
}
```

---

### 4.2 내 쿠폰 조회
**GET** `/api/coupon/my`

사용자의 쿠폰 목록을 조회합니다.

**Request**
- Query Parameters
  - `uid` (Integer, required): 사용자 ID
  - `state` (String, optional): 쿠폰 상태 (ACTIVE, USED, EXPIRED)

**Response** `200 OK`
```json
{
  "coupons": [
    {
      "ucid": 1,
      "couponName": "10% 할인 쿠폰",
      "discountType": "RATE",
      "discountValue": 10,
      "state": "ACTIVE",
      "issuedAt": "2025-01-20T14:00:00",
      "usedAt": null
    }
  ]
}
```

---

## 5. 공통 응답 형식

### 성공 응답
```json
{
  "data": { ... },
  "timestamp": "2025-01-20T14:30:00"
}
```

### 에러 응답
```json
{
  "error": "ERROR_CODE",
  "message": "상세 에러 메시지",
  "timestamp": "2025-01-20T14:30:00"
}
```

### HTTP 상태 코드

| 상태 코드 | 설명 |
|-----------|------|
| 200 OK | 요청 성공 |
| 204 No Content | 요청 성공 (응답 본문 없음) |
| 400 Bad Request | 잘못된 요청 |
| 402 Payment Required | 결제 실패 |
| 404 Not Found | 리소스를 찾을 수 없음 |
| 409 Conflict | 리소스 충돌 (쿠폰 소진 등) |
| 500 Internal Server Error | 서버 에러 |

### 에러 코드

| 코드 | 설명 |
|------|------|
| P001 | 상품을 찾을 수 없음 |
| P002 | 재고 부족 |
| O001 | 잘못된 수량 |
| O002 | 주문을 찾을 수 없음 |
| PAY001 | 잔액 부족 |
| PAY002 | 결제 실패 |
| C001 | 쿠폰 소진 |
| C002 | 잘못된 쿠폰 |
| C003 | 쿠폰 만료 |
| C004 | 이미 사용된 쿠폰 |
