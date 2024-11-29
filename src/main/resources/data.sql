DELETE FROM member;
DELETE FROM cultivation_report;

INSERT INTO member (name, birth_date, address, phone_number, home_phone_number) VALUES
    ('김철수', '1980-01-15', '제주시 조천읍 와흘리 123', '010-1234-5678', '064-756-1234');

INSERT INTO cultivation_report (
    member_id, status, crop, district, village,
    land_category, total_area, cultivated_area, ownership_type, created_at
)
SELECT
    1,
    'APPROVED',
    CASE MOD(ROWNUM, 12)
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
    CASE MOD(ROWNUM, 4)
        WHEN 0 THEN 'PADDY_FIELD'
        WHEN 1 THEN 'FIELD'
        WHEN 2 THEN 'ORCHARD'
        ELSE 'MEADOW'
        END,
    75,
    50,
    CASE MOD(ROWNUM, 2)
        WHEN 0 THEN 'SELF_OWNED'
        ELSE 'PURCHASED'
        END,
    DATEADD('SECOND', CAST(RAND() * 31536000 AS INT), CURRENT_TIMESTAMP)
FROM system_range(1, 12);