SELECT *
from student;

SELECT *
FROM student
WHERE age > 10
  AND age < 20;

SELECT name
FROM student;

SELECT *
FROM student
WHERE name LIKE '%1%';

SELECT *
FROM student
WHERE age < id;

SELECT *
FROM student
ORDER BY age;