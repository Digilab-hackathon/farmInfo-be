DELETE FROM member;
DELETE FROM shipment_report;

INSERT INTO member (name, birth_date, address, phone_number, home_phone_number) VALUES
    ('김철수', '1980-01-15', '제주시 조천읍 와흘리 123', '010-1234-5678', '064-756-1234');

INSERT INTO cultivation_report (
    member_id, status, crop, district, village,
    land_category, total_area, cultivated_area, ownership_type, created_at
)
SELECT 1, 'APPROVED',
       CASE MOD(ROWNUM - 1, 12)
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
           WHEN 10 THEN 'BRUSSELS_SPROUTS'
           ELSE 'BEET'
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
       CASE MOD(ROWNUM, 9)
           WHEN 0 THEN 'PADDY_FIELD'
           WHEN 1 THEN 'FIELD'
           WHEN 2 THEN 'ORCHARD'
           WHEN 3 THEN 'PASTURE'
           WHEN 4 THEN 'MISCELLANEOUS'
           WHEN 5 THEN 'FOREST'
           WHEN 6 THEN 'FARM_HUT'
           WHEN 7 THEN 'VINYL_HOUSE'
           ELSE 'GREENHOUSE'
           END,
       25,
       20,
       CASE MOD(ROWNUM, 2)
           WHEN 0 THEN 'SELF_OWNED'
           ELSE 'PURCHASED'
           END,
       DATEADD('MONTH', MOD(ROWNUM - 1, 36), DATE '2022-01-01')
FROM system_range(1, 36);

INSERT INTO shipment_report (
    member_id, status, expected_ship_date, wholesale_company,
    trade_type, trading_method, producer_name, production_location,
    crop, packaging, unit, grade, created_at
)
SELECT 1, 'APPROVED',
       DATEADD('DAY', ROWNUM * 7, DATE '2022-01-01'),
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
       '김철수',
       CASE MOD(ROWNUM, 3)
           WHEN 0 THEN 'GREENHOUSE'
           WHEN 1 THEN 'OUTDOOR'
           ELSE 'VINYL_HOUSE'
           END,
       CASE MOD(ROWNUM - 1, 12)
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
           WHEN 10 THEN 'BRUSSELS_SPROUTS'
           ELSE 'BEET'
           END,
       CASE MOD(ROWNUM, 2)
           WHEN 0 THEN 'PACKAGED'
           ELSE 'UNPACKAGED'
           END,
       CASE MOD(ROWNUM, 4)
           WHEN 0 THEN 100
           WHEN 1 THEN 200
           WHEN 2 THEN 150
           ELSE 300
           END,
       CASE MOD(ROWNUM, 3)
           WHEN 0 THEN 'PREMIUM'
           WHEN 1 THEN 'INTERMEDIATE'
           ELSE 'BASIC'
           END,
       DATEADD('MONTH', MOD(ROWNUM - 1, 36), DATE '2022-01-01')
FROM system_range(1, 36);