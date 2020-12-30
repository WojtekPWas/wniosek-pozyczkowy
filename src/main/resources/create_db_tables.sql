USE wniosekpozyczkowy;

CREATE TABLE definicje
( id INT NOT NULL AUTO_INCREMENT,
  min_kwota INT NOT NULL,
  max_kwota INT NOT NULL,
  min_okres SMALLINT NOT NULL,
  max_okres SMALLINT NOT NULL,
  okres_przedl SMALLINT NOT NULL,
  CONSTRAINT definicje_pk PRIMARY KEY (id)
);

CREATE TABLE wnioski
( id INT NOT NULL AUTO_INCREMENT,
  imie_nazwisko VARCHAR(100) NOT NULL,
  data_zlozenia_wniosku DATE NOT NULL,
  kwota_pozyczki INT NOT NULL,
  okres_kredytowania SMALLINT NOT NULL,
  czy_przedluzony BOOLEAN NOT NULL,
  okres_przedluzenia SMALLINT NOT NULL,
  CONSTRAINT wnioski_pk PRIMARY KEY (id)
);

CREATE TABLE splaty
( id INT NOT NULL AUTO_INCREMENT,
  wniosek_id INT NOT NULL,
  rok SMALLINT NOT NULL,
  kwota_splaty INT NOT NULL,
  CONSTRAINT wnioski_pk PRIMARY KEY (id),
  FOREIGN KEY (wniosek_id)
  REFERENCES wnioski(id)
  ON DELETE CASCADE
);

