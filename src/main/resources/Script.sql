-- Database: nhsdatabase

--DROP DATABASE nhsdatabase;

CREATE DATABASE nhsdatabase
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_GB.UTF-8'
       LC_CTYPE = 'en_GB.UTF-8'
       CONNECTION LIMIT = -1;

-- Table: public.person

-- DROP TABLE public.person;

-- CREATE SEQUENCE FOR AUTO GENERATION OF ID
CREATE SEQUENCE patient_id_seq;
--ALTER TABLE patient ALTER id SET DEFAULT NEXTVAL('patient_id_seq');


CREATE TABLE public.patient
(
  id integer NOT NULL DEFAULT nextval('patient_id_seq'::regclass),
  name character varying(50) NOT NULL DEFAULT NULL::character varying,
  CONSTRAINT patient_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.patient
  OWNER TO postgres;


