DROP SCHEMA IF EXISTS "public";

CREATE SCHEMA dblp;

ALTER SCHEMA dblp OWNER TO postgres;


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


ALTER TABLE dblp."article" OWNER TO postgres;

CREATE TABLE dblp."authors" (
  key character varying NOT NULL,
  author character varying NOT NULL
);


ALTER TABLE dblp."authors" OWNER TO postgres;

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


ALTER TABLE dblp."book" OWNER TO postgres;

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


ALTER TABLE dblp."incollection" OWNER TO postgres;

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


ALTER TABLE dblp."inproceedings" OWNER TO postgres;


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


ALTER TABLE dblp."mastersthesis" OWNER TO postgres;


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


ALTER TABLE dblp."phdthesis" OWNER TO postgres;

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


ALTER TABLE dblp."proceedings" OWNER TO postgres;

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


ALTER TABLE dblp."www" OWNER TO postgres;


ALTER TABLE ONLY dblp."article"
ADD CONSTRAINT key_article PRIMARY KEY (key);


ALTER TABLE ONLY dblp."authors"
ADD CONSTRAINT key_author PRIMARY KEY (key, author);

ALTER TABLE ONLY dblp."book"
ADD CONSTRAINT key_book PRIMARY KEY (key);

ALTER TABLE ONLY dblp."incollection"
ADD CONSTRAINT key_incollection PRIMARY KEY (key);


ALTER TABLE ONLY dblp."inproceedings"
ADD CONSTRAINT key_inproceedings PRIMARY KEY (key);


ALTER TABLE ONLY dblp."mastersthesis"
ADD CONSTRAINT key_mastersthesis PRIMARY KEY (key);


ALTER TABLE ONLY dblp."phdthesis"
ADD CONSTRAINT key_phdthesis PRIMARY KEY (key);

ALTER TABLE ONLY dblp."proceedings"
ADD CONSTRAINT key_proceedings PRIMARY KEY (key);


ALTER TABLE ONLY dblp."www"
ADD CONSTRAINT key_www PRIMARY KEY (key);

