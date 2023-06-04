SELECT * FROM books WHERE data->>'title' like '%a69f04a94698066ffecd%';

SELECT * FROM books WHERE data->'title' = 'Sleeping Beauties';

CREATE INDEX ON books((data->>'title'));



INSERT INTO books (data) VALUES ('{"title": "' || MD5(random()::text) ||'", "genres": ["Fiction", "Spirituality"], "published": true}');


;
INSERT INTO books (data)
SELECT jsonb_build_object('title', MD5(random()::text)) as title FROM generate_series(1,10000000);

select * from books where book_id = 5000000






-- select * from random()::text