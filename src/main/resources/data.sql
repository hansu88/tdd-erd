-- 상품 데이터
INSERT INTO GOODS (gid, title, description, price, created_at, updated_at)
VALUES (1, '나이키 에어포스 1', '클래식 운동화', 120000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO GOODS (gid, title, description, price, created_at, updated_at)
VALUES (2, '아디다스 슈퍼스타', '인기 스니커즈', 110000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO GOODS (gid, title, description, price, created_at, updated_at)
VALUES (3, '뉴발란스 530', '편안한 러닝화', 130000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 상품 옵션 데이터
INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (1, 1, '블랙 260mm', 0, 50);

INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (2, 1, '블랙 270mm', 0, 100);

INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (3, 1, '화이트 260mm', 5000, 80);

INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (4, 2, '화이트 250mm', 0, 30);

INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (5, 2, '블랙 260mm', 0, 5);  -- 재고 부족 테스트용

INSERT INTO GOODS_VARIANT (vid, gid, name, additional_price, stock)
VALUES (6, 3, '그레이 270mm', 0, 200);

-- 사용자 잔액 데이터
INSERT INTO BALANCE (id, uid, bal)
VALUES (1, 1, 1000000);  -- 100만원

INSERT INTO BALANCE (id, uid, bal)
VALUES (2, 2, 50000);    -- 5만원 (잔액 부족 테스트용)

-- 쿠폰 데이터
INSERT INTO COUPON (cid, name, discount_amount, total_quantity, issued, expired_at, created_at)
VALUES (1, '신규 가입 10% 할인', 10000, 100, 0, '2025-12-31 23:59:59', CURRENT_TIMESTAMP);

INSERT INTO COUPON (cid, name, discount_amount, total_quantity, issued, expired_at, created_at)
VALUES (2, '봄맞이 특가 쿠폰', 5000, 10, 0, '2025-06-30 23:59:59', CURRENT_TIMESTAMP);  -- 소진 테스트용

INSERT INTO COUPON (cid, name, discount_amount, total_quantity, issued, expired_at, created_at)
VALUES (3, 'VIP 전용 쿠폰', 20000, 5, 5, '2025-12-31 23:59:59', CURRENT_TIMESTAMP);  -- 이미 소진됨