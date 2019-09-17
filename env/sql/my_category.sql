CREATE TABLE
  my_category (
    username varchar(10) NOT NULL
    , id int NOT NULL
    , PRIMARY KEY (username)
    , FOREIGN KEY (id) REFERENCES category (id)
  )
;

