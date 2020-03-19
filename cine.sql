--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7
-- Dumped by pg_dump version 11.7

-- Started on 2020-03-19 14:16:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16465)
-- Name: pelicula; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pelicula (
    id_pelicula integer NOT NULL,
    titulo_pelicula text NOT NULL,
    descripcion_pelicula text NOT NULL,
    tipo_pelicula text NOT NULL,
    imagen_foto text,
    raptin integer
);


ALTER TABLE public.pelicula OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16463)
-- Name: pelicula_id_pelicula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pelicula_id_pelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pelicula_id_pelicula_seq OWNER TO postgres;

--
-- TOC entry 2817 (class 0 OID 0)
-- Dependencies: 196
-- Name: pelicula_id_pelicula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pelicula_id_pelicula_seq OWNED BY public.pelicula.id_pelicula;


--
-- TOC entry 2686 (class 2604 OID 16468)
-- Name: pelicula id_pelicula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pelicula ALTER COLUMN id_pelicula SET DEFAULT nextval('public.pelicula_id_pelicula_seq'::regclass);


--
-- TOC entry 2811 (class 0 OID 16465)
-- Dependencies: 197
-- Data for Name: pelicula; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pelicula(
	id_pelicula, titulo_pelicula, descripcion_pelicula, tipo_pelicula, imagen_foto, raptin)
	VALUES (1, 'El tarzan', 'El hombre mono', 'Serie', 'cafe.png', 5);
	
INSERT INTO public.pelicula(
	id_pelicula, titulo_pelicula, descripcion_pelicula, tipo_pelicula, imagen_foto, raptin)
	VALUES (2, 'El coco', 'El hombre coco', 'Serie', 'campesino1.JPG', 3);
--
-- TOC entry 2818 (class 0 OID 0)
-- Dependencies: 196
-- Name: pelicula_id_pelicula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pelicula_id_pelicula_seq', 3, true);


--
-- TOC entry 2688 (class 2606 OID 16473)
-- Name: pelicula pelicula_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT pelicula_pkey PRIMARY KEY (id_pelicula);


-- Completed on 2020-03-19 14:16:57

--
-- PostgreSQL database dump complete
--