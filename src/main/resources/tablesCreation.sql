DROP SCHEMA IF EXISTS "public";

CREATE SCHEMA dblp;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = dblp, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;


CREATE TABLE "dblp"."article" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  reviewid character varying,
  rating character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."article_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."book" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."book_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."incollection" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."incollection_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."inproceedings" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."inproceedings_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."mastersthesis" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."mastersthesis_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."phdthesis" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."phdthesis_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."proceedings" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."proceedings_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

CREATE TABLE dblp."www" (
  key character varying NOT NULL,
  mdate character varying,
  publtype character varying,
  editor character varying,
  title character varying,
  booktitle character varying,
  pages character varying,
  year character varying,
  address character varying,
  journal character varying,
  volume character varying,
  number character varying,
  month character varying,
  url character varying,
  ee character varying,
  cdrom character varying,
  cite character varying,
  publisher character varying,
  note character varying,
  crossref character varying,
  isbn character varying,
  series character varying,
  school character varying,
  chapter character varying
);

CREATE TABLE dblp."www_author" (
  key character varying NOT NULL,
  author character varying NOT NULL
);

ALTER TABLE ONLY dblp."article"
ADD CONSTRAINT key_article PRIMARY KEY (key);

ALTER TABLE ONLY dblp."article_author"
ADD CONSTRAINT key_article_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."book"
ADD CONSTRAINT key_book PRIMARY KEY (key);

ALTER TABLE ONLY dblp."book_author"
ADD CONSTRAINT key_book_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."incollection"
ADD CONSTRAINT key_incollection PRIMARY KEY (key);

ALTER TABLE ONLY dblp."incollection_author"
ADD CONSTRAINT key_incollection_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."inproceedings"
ADD CONSTRAINT key_inproceedings PRIMARY KEY (key);

ALTER TABLE ONLY dblp."inproceedings_author"
ADD CONSTRAINT key_inproceedings_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."mastersthesis"
ADD CONSTRAINT key_mastersthesis PRIMARY KEY (key);

ALTER TABLE ONLY dblp."mastersthesis_author"
ADD CONSTRAINT key_mastersthesis_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."phdthesis"
ADD CONSTRAINT key_phdthesis PRIMARY KEY (key);

ALTER TABLE ONLY dblp."phdthesis_author"
ADD CONSTRAINT key_phdthesis_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."proceedings"
ADD CONSTRAINT key_proceedings PRIMARY KEY (key);

ALTER TABLE ONLY dblp."proceedings_author"
ADD CONSTRAINT key_proceedings_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."www"
ADD CONSTRAINT key_www PRIMARY KEY (key);

ALTER TABLE ONLY dblp."www_author"
ADD CONSTRAINT key_www_author PRIMARY KEY (key, author);