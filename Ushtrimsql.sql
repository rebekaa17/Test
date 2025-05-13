
CREATE TABLE kursi (
  id SERIAL PRIMARY KEY,
  emri_kursit VARCHAR(100),
  kohezgjatja INTEGER,
  create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO kursi (emri_kursit, kohezgjatja)
VALUES
  ('java', 3),
  ('.net', 3),
  ('angular', 5),
  ('python', 6),
  ('JavaScript', 8),
  ('Ruby', 4),
  ('Go', 3),
  ('C++', 5),
  ('Swift', 6),
  ('Dart', 4);


SELECT * FROM kursi;


ALTER TABLE kursi
ADD programming_language VARCHAR(100);


UPDATE kursi
SET programming_language = 'springboot'
WHERE emri_kursit = 'java';


DELETE FROM kursi
WHERE id = 3;


CREATE TABLE student (
  ID SERIAL PRIMARY KEY,
  student_key INTEGER,
  emri VARCHAR(100),
  email VARCHAR(100),
  birth_date TIMESTAMP,
  phone VARCHAR(20),
  pike INTEGER,
  FOREIGN KEY (student_key) REFERENCES kursi (id)
);

INSERT INTO student (emri, email, birth_date, phone, pike, student_key) 
VALUES
  ('RebekaH', 'rebekabeka@gmail.com', '2004-04-17', '123456789', 100, 1),
  ('AlesiaUku', 'alesiauku@gmail.com', '2004-08-10', '123456789', 100, 2),
  ('BlerinaT', 'blerinat@gmail.com', '2003-12-01', '0691111111', 95, 1),
  ('ErionD', 'eriond@gmail.com', '2002-05-15', '0682222222', 88, 2),
  ('JonidaM', 'jonidam@gmail.com', '2004-03-22', '0693333333', 92, 3),
  ('KleviR', 'klevi.r@gmail.com', '2005-01-05', '0674444444', 78, 2),
  ('EliraK', 'elira.kola@gmail.com', '2003-07-18', '0695555555', 85, 4),
  ('LorisB', 'lorisb@gmail.com', '2001-11-30', '0686666666', 90, 5),
  ('AnisaQ', 'anisaq@gmail.com', '2004-09-09', '0697777777', 100, 5),
  ('GranitV', 'granitv@gmail.com', '2003-02-14', '0678888888', 82, 3),
  ('AndiT', 'andit@example.com', '2003-05-12', '0670000000', 85, 1),
  ('DionaP', 'dionap@example.com', '2002-07-14', '0681111111', 90, 2),
  ('FatonB', 'fatonb@example.com', '2004-02-21', '0692222222', 92, 3),
  ('IldaM', 'ildam@example.com', '2001-12-28', '0673333333', 75, 4),
  ('ElioR', 'elior@example.com', '2000-10-30', '0684444444', 88, 5),
  ('MiraL', 'miral@example.com', '2003-01-19', '0695555555', 95, 6),
  ('JonidaK', 'jonidak@example.com', '2002-09-10', '0676666666', 80, 7),
  ('ArberI', 'arberi@example.com', '2004-06-23', '0697777777', 78, 8);


SELECT * FROM student;


ALTER TABLE student
RENAME COLUMN name TO emri;


SELECT * FROM student
WHERE emri LIKE 'M%';


SELECT * FROM kursi
WHERE create_date BETWEEN '2023-01-01' AND '2025-12-31';


SELECT *
FROM student
WHERE EXTRACT(YEAR FROM AGE(birth_date)) < 2000;

SELECT 
    s.emri,
    s.email,
    k.emri_kursit,
    k.programming_language
FROM 
    student s
JOIN 
    kursi k ON s.student_key = k.id;


SELECT 
    k.emri_kursit,
    AVG(s.pike) AS mesatarja_pikeve
FROM 
    kursi k
JOIN 
    student s ON k.id = s.student_key
GROUP BY 
    k.emri_kursit;

SELECT 
    k.emri_kursit,
    COUNT(s.id) AS numri_studentave
FROM 
    kursi k
LEFT JOIN 
    student s ON s.student_key = k.id
GROUP BY 
    k.emri_kursit;


SELECT 
    emri, email, pike
FROM 
    student
ORDER BY 
    pike DESC;


SELECT 
    s.emri,
    k.emri_kursit,
    k.kohezgjatja
FROM 
    student s
JOIN 
    kursi k ON s.student_key = k.id
WHERE 
    k.kohezgjatja > 5;


SELECT *
FROM student
WHERE EXTRACT(YEAR FROM birth_date) > 2003;


SELECT * FROM student
WHERE emri LIKE 'A%';

SELECT *
FROM kursi
WHERE create_date BETWEEN '2024-01-01' AND '2025-12-31';


CREATE TABLE student_kurs (
    student_id INTEGER,
    kurs_id INTEGER,
    PRIMARY KEY (student_id, kurs_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (kurs_id) REFERENCES kursi(id)
);


SELECT 
    s.emri,
    k.emri_kursit
FROM 
    student s
JOIN 
    student_kurs sk ON s.id = sk.student_id
JOIN 
    kursi k ON k.id = sk.kurs_id;
