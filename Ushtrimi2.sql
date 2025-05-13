CREATE TABLE klientet (
    id SERIAL PRIMARY KEY,
    emri VARCHAR(100),
    mbiemri VARCHAR(100),
    email VARCHAR(100),
    telefoni VARCHAR(20)
);

CREATE TABLE produktet (
    id SERIAL PRIMARY KEY,
    emer_produkti VARCHAR(100),
    cmimi NUMERIC(10, 2)
);

CREATE TABLE porosite (
    id SERIAL PRIMARY KEY,
    klient_id INTEGER,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (klient_id) REFERENCES klientet(id)
);

CREATE TABLE porosi_produkte (
    porosi_id INTEGER,
    produkt_id INTEGER,
    sasi INTEGER,
    PRIMARY KEY (porosi_id, produkt_id),
    FOREIGN KEY (porosi_id) REFERENCES porosite(id),
    FOREIGN KEY (produkt_id) REFERENCES produktet(id)
);

INSERT INTO klientet (emri, mbiemri, email, telefoni)
VALUES 
    ('Ardit', 'Kola', 'ardit.kola@gmail.com', '0691234567'),
    ('Erisa', 'Dema', 'erisa.dema@gmail.com', '0692345678'),
    ('Blendi', 'Hoxha', 'blendi.hoxha@gmail.com', '0693456789'),
    ('Sara', 'Leka', 'sara.leka@gmail.com', '0694567890');

INSERT INTO produktet (emer_produkti, cmimi)
VALUES 
    ('Laptop', 800),
    ('Telefon', 500),
    ('Tablete', 300),
    ('Kamera', 150);


INSERT INTO porosite (klient_id, data)
VALUES 
    (1, '2025-05-01'),
    (2, '2025-05-02'),
    (3, '2025-05-03'),
    (4, '2025-05-04');


INSERT INTO porosi_produkte (porosi_id, produkt_id, sasi)
VALUES 
    (1, 1, 2),  -- Ardit ka porositur 2 laptopë
    (1, 3, 1),  -- Ardit ka porositur 1 tablet
    (2, 2, 3),  -- Erisa ka porositur 3 telefona
    (3, 4, 1),  -- Blendi ka porositur 1 kamera
    (4, 1, 1);  -- Sara ka porositur 1 laptop
	
SELECT 
    COUNT(DISTINCT klient_id) AS numri_klienteve
FROM 
    porosite;
	
SELECT 
    p.emer_produkti,
    SUM(pp.sasi * pr.cmimi) AS shuma_total
FROM 
    porosi_produkte pp
JOIN 
    produktet pr ON pp.produkt_id = pr.id
JOIN 
    porosite p ON pp.porosi_id = p.id
GROUP BY 
    p.emer_produkti;
	
SELECT 
    pr.emer_produkti,
    AVG(pp.sasi) AS mesatarja_sasive
FROM 
    porosi_produkte pp
JOIN 
    produktet pr ON pp.produkt_id = pr.id
GROUP BY 
    pr.emer_produkti;
	
SELECT 
    k.emri,
    k.mbiemri,
    COUNT(pp.produkt_id) AS numri_produkteve
FROM 
    klientet k
JOIN 
    porosite p ON k.id = p.klient_id
JOIN 
    porosi_produkte pp ON p.id = pp.porosi_id
GROUP BY 
    k.emri, k.mbiemri
ORDER BY 
    numri_produkteve DESC;
	
SELECT 
    k.emri,
    k.mbiemri,
    pr.emer_produkti
FROM 
    klientet k
JOIN 
    porosite p ON k.id = p.klient_id
JOIN 
    porosi_produkte pp ON p.id = pp.porosi_id
JOIN 
    produktet pr ON pp.produkt_id = pr.id
WHERE 
    pr.emer_produkti = 'Laptop';
	
SELECT 
    k.emri,
    k.mbiemri,
    SUM(pp.sasi * pr.cmimi) AS shpenzimet_totale
FROM 
    klientet k
JOIN 
    porosite p ON k.id = p.klient_id
JOIN 
    porosi_produkte pp ON p.id = pp.porosi_id
JOIN 
    produktet pr ON pp.produkt_id = pr.id
GROUP BY 
    k.emri, k.mbiemri
ORDER BY 
    shpenzimet_totale DESC;
