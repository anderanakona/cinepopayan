PGDMP         4                x            cine    11.7    11.7     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16386    cine    DATABASE     �   CREATE DATABASE cine WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE cine;
             postgres    false            �            1259    16465    pelicula    TABLE     �   CREATE TABLE public.pelicula (
    id_pelicula integer NOT NULL,
    titulo_pelicula text NOT NULL,
    descripcion_pelicula text NOT NULL,
    tipo_pelicula text NOT NULL,
    imagen_foto text,
    raptin integer
);
    DROP TABLE public.pelicula;
       public         postgres    false            �            1259    16463    pelicula_id_pelicula_seq    SEQUENCE     �   CREATE SEQUENCE public.pelicula_id_pelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.pelicula_id_pelicula_seq;
       public       postgres    false    197                       0    0    pelicula_id_pelicula_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.pelicula_id_pelicula_seq OWNED BY public.pelicula.id_pelicula;
            public       postgres    false    196            ~
           2604    16468    pelicula id_pelicula    DEFAULT     |   ALTER TABLE ONLY public.pelicula ALTER COLUMN id_pelicula SET DEFAULT nextval('public.pelicula_id_pelicula_seq'::regclass);
 C   ALTER TABLE public.pelicula ALTER COLUMN id_pelicula DROP DEFAULT;
       public       postgres    false    197    196    197            �
          0    16465    pelicula 
   TABLE DATA               z   COPY public.pelicula (id_pelicula, titulo_pelicula, descripcion_pelicula, tipo_pelicula, imagen_foto, raptin) FROM stdin;
    public       postgres    false    197   U                  0    0    pelicula_id_pelicula_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.pelicula_id_pelicula_seq', 161, true);
            public       postgres    false    196            �
           2606    16473    pelicula pelicula_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT pelicula_pkey PRIMARY KEY (id_pelicula);
 @   ALTER TABLE ONLY public.pelicula DROP CONSTRAINT pelicula_pkey;
       public         postgres    false    197            �
   `   x�=�1
�0 �9yE_P(���Ipu�5j�MJu��:y󅶇*��D�r��lb�`�*�v�Eh1tn�)|봼Vv����_��8����0#�     