DROP SCHEMA IF EXISTS "public";

CREATE SCHEMA "DBLP";

ALTER SCHEMA "DBLP" OWNER TO postgres;


CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = "DBLP", pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE "DBLP"."Article" (
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


ALTER TABLE "DBLP"."Article" OWNER TO postgres;

CREATE TABLE "DBLP"."Authors" (
  key character varying,
  author character varying,
  "authorsID" integer NOT NULL
);


ALTER TABLE "DBLP"."Authors" OWNER TO postgres;

CREATE SEQUENCE "DBLP"."Authors_authorsID_seq"
START WITH 1
INCREMENT BY 1
CACHE 1;


ALTER TABLE "DBLP"."Authors_authorsID_seq" OWNER TO postgres;

CREATE SEQUENCE "DBLP"."Authors_authorsID_seq1"
START WITH 1
INCREMENT BY 1
CACHE 1;


ALTER TABLE "DBLP"."Authors_authorsID_seq1" OWNER TO postgres;


ALTER SEQUENCE "DBLP"."Authors_authorsID_seq1" OWNED BY "Authors"."authorsID";


CREATE TABLE "DBLP"."Book" (
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


ALTER TABLE "DBLP"."Book" OWNER TO postgres;

CREATE TABLE "DBLP"."Incollection" (
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


ALTER TABLE "DBLP"."Incollection" OWNER TO postgres;

CREATE TABLE "DBLP"."Inproceedings" (
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


ALTER TABLE "DBLP"."Inproceedings" OWNER TO postgres;


CREATE TABLE "DBLP"."Mastersthesis" (
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


ALTER TABLE "DBLP"."Mastersthesis" OWNER TO postgres;


CREATE TABLE "DBLP"."Phdthesis" (
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


ALTER TABLE "DBLP"."Phdthesis" OWNER TO postgres;

CREATE TABLE "DBLP"."Proceedings" (
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


ALTER TABLE "DBLP"."Proceedings" OWNER TO postgres;

CREATE TABLE "DBLP"."Www" (
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


ALTER TABLE "DBLP"."Www" OWNER TO postgres;

ALTER TABLE ONLY "DBLP"."Authors" ALTER COLUMN "authorsID" SET DEFAULT nextval('"Authors_authorsID_seq1"'::regclass);



ALTER TABLE ONLY "DBLP"."Article"
ADD CONSTRAINT key_article PRIMARY KEY (key);


ALTER TABLE ONLY "DBLP"."Authors"
ADD CONSTRAINT key_authors PRIMARY KEY ("authorsID");

ALTER TABLE ONLY "DBLP"."Book"
ADD CONSTRAINT key_book PRIMARY KEY (key);

ALTER TABLE ONLY "DBLP"."Incollection"
ADD CONSTRAINT key_incollection PRIMARY KEY (key);


ALTER TABLE ONLY "DBLP"."Inproceedings"
ADD CONSTRAINT key_inproceedings PRIMARY KEY (key);


ALTER TABLE ONLY "DBLP"."Mastersthesis"
ADD CONSTRAINT key_mastersthesis PRIMARY KEY (key);


ALTER TABLE ONLY "DBLP"."Phdthesis"
ADD CONSTRAINT key_phdthesis PRIMARY KEY (key);

ALTER TABLE ONLY "DBLP"."Proceedings"
ADD CONSTRAINT key_proceedings PRIMARY KEY (key);


ALTER TABLE ONLY "DBLP"."Www"
ADD CONSTRAINT key_www PRIMARY KEY (key);

