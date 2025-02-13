INSERT INTO sensor (magnitud, media, valor, tipo)
SELECT 0, 0, 0, 'TEMPERATURA'
WHERE NOT EXISTS (SELECT 1 FROM sensor WHERE id = 1);

INSERT INTO sensor (magnitud, media, valor, tipo)
SELECT 1, 0, 0, 'HUMEDAD'
WHERE NOT EXISTS (SELECT 1 FROM sensor WHERE id = 2);

INSERT INTO sensor (magnitud, media, valor, tipo)
SELECT 2, 0, 0, 'PRESION'
WHERE NOT EXISTS (SELECT 1 FROM sensor WHERE id = 3);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 8.00, '1', '13-02-2025 20:22:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 1);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 14.00, '1', '13-02-2025 16:12:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 2);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 27.50, '1', '13-02-2025 19:00:00'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 3);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 80.00, '2', '13-02-2025 20:22:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 4);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 42.30, '2', '13-02-2025 16:12:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 5);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 27.50, '2', '13-02-2025 19:00:00'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 6);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 980.00, '3', '13-02-2025 20:22:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 7);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 1002.30, '3', '13-02-2025 16:12:31'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 8);

INSERT INTO historico(valor, sensor_id, fecha)
SELECT 900.50, '3', '13-02-2025 19:00:00'
WHERE NOT EXISTS (SELECT 1 FROM historico WHERE id = 9);