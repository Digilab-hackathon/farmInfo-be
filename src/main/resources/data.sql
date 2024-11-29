-- Member 초기 데이터
INSERT INTO member (name, birth_date, address, phone_number, home_phone_number) VALUES
                                                                                    ('김철수', '1980-01-15', '제주시 조천읍 와흘리 123', '010-1234-5678', '064-756-1234'),
                                                                                    ('이영희', '1975-03-22', '제주시 구좌읍 세화리 456', '010-2345-6789', '064-756-2345'),
                                                                                    ('강농부3', '1982-05-15', '제주시 조천읍 369번지', '010-3456-7890', '064-756-3456'),
                                                                                    ('박농부4', '1978-08-20', '제주시 구좌읍 492번지', '010-4567-8901', '064-756-4567');

-- 더 많은 Member 데이터 추가 (약 20개씩 추가)
INSERT INTO member (name, birth_date, address, phone_number, home_phone_number)
SELECT
    CASE MOD(ROWNUM, 5)
        WHEN 0 THEN '강농부'
        WHEN 1 THEN '이농부'
        WHEN 2 THEN '박농부'
        WHEN 3 THEN '김농부'
        ELSE '정농부'
        END || ROWNUM,
    DATEADD('DAY', RAND() * 7300, DATE '1960-01-01'),
    CASE MOD(ROWNUM, 4)
        WHEN 0 THEN '제주시 조천읍'
        WHEN 1 THEN '제주시 구좌읍'
        WHEN 2 THEN '서귀포시 성산읍'
        ELSE '서귀포시 표선면'
        END || ' ' || (ROWNUM * 123) || '번지',
    '010-' || LPAD(CAST(RAND() * 9999 AS INT), 4, '0') || '-' || LPAD(CAST(RAND() * 9999 AS INT), 4, '0'),
    '064-' || LPAD(CAST(RAND() * 999999 AS INT), 6, '0')
FROM system_range(5, 100);

-- CultivationReport 데이터 생성
INSERT INTO cultivation_report (
    member_id, status, crop, district, village,
    land_category, total_area, cultivated_area, ownership_type, created_at
)
SELECT
    id,
    CASE MOD(ROWNUM, 3)
        WHEN 0 THEN 'PENDING'
        WHEN 1 THEN 'APPROVED'
        ELSE 'REJECTED'
        END,
    CASE MOD(ROWNUM, 11)
        WHEN 0 THEN 'CABBAGE'
        WHEN 1 THEN 'RADISH'
        WHEN 2 THEN 'CARROT'
        WHEN 3 THEN 'GARLIC'
        WHEN 4 THEN 'ONION'
        WHEN 5 THEN 'BROCCOLI'
        WHEN 6 THEN 'GREEN_ONION'
        WHEN 7 THEN 'KOHLRABI'
        WHEN 8 THEN 'WINTER_CABBAGE'
        WHEN 9 THEN 'RED_CABBAGE'
        ELSE 'CHERRY_TOMATO'
        END,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN '제주시'
        ELSE '서귀포시'
        END,
    CASE MOD(ROWNUM, 4)
        WHEN 0 THEN '조천읍'
        WHEN 1 THEN '구좌읍'
        WHEN 2 THEN '성산읍'
        ELSE '표선면'
        END,
    CASE MOD(ROWNUM, 4)
        WHEN 0 THEN 'PADDY_FIELD'
        WHEN 1 THEN 'FIELD'
        WHEN 2 THEN 'ORCHARD'
        ELSE 'MEADOW'
        END,
    RAND() * 1000 + 100,
    RAND() * 500 + 50,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN 'SELF_OWNED'
        ELSE 'PURCHASED'
        END,
    DATEADD('SECOND', CAST(RAND() * 31536000 AS INT), CURRENT_TIMESTAMP)
FROM member;

-- ShipmentReport 데이터 생성
INSERT INTO shipment_report (
    member_id, status, expected_ship_date, wholesale_company, trade_type,
    trading_method, producer_name, production_location, crop, packaging,
    unit, grade, created_at
)
SELECT
    id,
    CASE MOD(ROWNUM, 3)
        WHEN 0 THEN 'PENDING'
        WHEN 1 THEN 'APPROVED'
        ELSE 'REJECTED'
        END,
    DATEADD('DAY', CAST(RAND() * 30 AS INT), CURRENT_DATE),
    CASE MOD(ROWNUM, 4)
        WHEN 0 THEN 'JEJUNET'
        WHEN 1 THEN 'JEJU_NONGHYUP'
        WHEN 2 THEN 'JEJU_AGRICULTURE'
        ELSE 'JEJU_WHOLESALE'
        END,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN 'CONSIGNMENT'
        ELSE 'PURCHASE'
        END,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN 'AUCTION'
        ELSE 'FIXED_PRICE'
        END,
    name,
    CASE MOD(ROWNUM, 3)
        WHEN 0 THEN 'GREENHOUSE'
        WHEN 1 THEN 'OUTDOOR'
        ELSE 'VINYL_HOUSE'
        END,
    CASE MOD(ROWNUM, 11)
        WHEN 0 THEN 'CABBAGE'
        WHEN 1 THEN 'RADISH'
        WHEN 2 THEN 'CARROT'
        WHEN 3 THEN 'GARLIC'
        WHEN 4 THEN 'ONION'
        WHEN 5 THEN 'BROCCOLI'
        WHEN 6 THEN 'GREEN_ONION'
        WHEN 7 THEN 'KOHLRABI'
        WHEN 8 THEN 'WINTER_CABBAGE'
        WHEN 9 THEN 'RED_CABBAGE'
        ELSE 'CHERRY_TOMATO'
        END,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN 'PACKAGED'
        ELSE 'UNPACKAGED'
        END,
    RAND() * 1000 + 100,
    CASE MOD(ROWNUM, 3)
        WHEN 0 THEN 'PREMIUM'
        WHEN 1 THEN 'STANDARD'
        ELSE 'SECONDARY'
        END,
    DATEADD('SECOND', CAST(RAND() * 31536000 AS INT), CURRENT_TIMESTAMP)
FROM member;