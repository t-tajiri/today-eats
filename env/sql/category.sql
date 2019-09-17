CREATE TABLE
  category (
    id int NOT NULL AUTO_INCREMENT
    , name varchar(100)
    , PRIMARY KEY(id)
  )
;

INSERT INTO
  category (name)
VALUES
  ('全て')
  , ('洋風')
  , ('和風')
  , ('中華')
;
