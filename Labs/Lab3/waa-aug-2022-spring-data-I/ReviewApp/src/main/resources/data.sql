--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4 (Debian 14.4-1.pgdg110+1)

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

ALTER TABLE ONLY public.review DROP CONSTRAINT fkiyof1sindb9qiqr9o8npj8klt;
ALTER TABLE ONLY public.address DROP CONSTRAINT fk6i66ijb8twgcqtetl8eeeed6v;
ALTER TABLE ONLY public.review DROP CONSTRAINT fk6cpw2nlklblpvc7hyt7ko6v3e;
ALTER TABLE ONLY public.product DROP CONSTRAINT fk1mtsbur82frn64de7balymq9s;
ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
ALTER TABLE ONLY public.users DROP CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7;
ALTER TABLE ONLY public.category DROP CONSTRAINT uk_46ccwnsi9409t36lurvtyljak;
ALTER TABLE ONLY public.review DROP CONSTRAINT review_pkey;
ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
ALTER TABLE ONLY public.address DROP CONSTRAINT address_pkey;
DROP TABLE public.users;
DROP TABLE public.review;
DROP TABLE public.product;
DROP SEQUENCE public.hibernate_sequence;
DROP TABLE public.category;
DROP TABLE public.address;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id integer NOT NULL,
    city character varying(255),
    zip character varying(255),
    user_id integer
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255),
    price real NOT NULL,
    rating integer NOT NULL,
    category_id integer
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    id integer NOT NULL,
    comment character varying(255),
    product_id integer,
    user_id integer
);


ALTER TABLE public.review OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address VALUES (8, 'Fairfield', '52557', 4);
INSERT INTO public.address VALUES (9, 'Espoo', '00740', 5);
INSERT INTO public.address VALUES (10, 'Frisco, Texas', '89283', 6);
INSERT INTO public.address VALUES (11, 'Jefferson City', '89283', 7);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category VALUES (12, 'Vegetables');
INSERT INTO public.category VALUES (13, 'Clothes');
INSERT INTO public.category VALUES (14, 'Electronics');
INSERT INTO public.category VALUES (15, 'Groceries');


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (16, 'Cauliflower', 2, 5, 12);
INSERT INTO public.product VALUES (17, 'Carrot', 1.7, 10, 12);
INSERT INTO public.product VALUES (18, 'Jalepeno', 5.4, 9, 12);
INSERT INTO public.product VALUES (19, 'Brocollo', 4.4, 5, 12);
INSERT INTO public.product VALUES (20, 'Men''s Shrt', 40, 10, 13);
INSERT INTO public.product VALUES (21, 'Men''s Tshirts', 35, 9, 13);
INSERT INTO public.product VALUES (22, 'Trousers', 19, 8, 13);
INSERT INTO public.product VALUES (23, 'Samsung Ultra HD Television', 685, 12, 14);
INSERT INTO public.product VALUES (24, 'iPhone 13 Pro', 999, 20, 14);
INSERT INTO public.product VALUES (25, 'Samsung S22', 850, 15, 14);
INSERT INTO public.product VALUES (26, 'Flour', 1.5, 19, 15);
INSERT INTO public.product VALUES (27, 'Rice', 2, 22, 15);
INSERT INTO public.product VALUES (28, 'Lentils', 3.8, 21, 15);


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.review VALUES (48, 'I found some stones in this, I''m not gonna buy these again', 28, 7);
INSERT INTO public.review VALUES (43, 'S22 are the best phone in the market', 25, 4);
INSERT INTO public.review VALUES (34, 'The best brocollis in the town', 19, 5);
INSERT INTO public.review VALUES (44, 'I have owned this phone for almost 6 months and I love it', 25, 5);
INSERT INTO public.review VALUES (37, 'Good trousers for outgoing', 22, 7);
INSERT INTO public.review VALUES (32, 'Too expensive for just 1/2 kg', 17, 7);
INSERT INTO public.review VALUES (38, 'The best television I''ve ever had, best quality. I love samsungs', 23, 4);
INSERT INTO public.review VALUES (40, 'The black in this TV is perfect', 23, 6);
INSERT INTO public.review VALUES (47, 'I cannot go rice without Lentils and these are awesome', 28, 6);
INSERT INTO public.review VALUES (39, 'I am forever samsung, love the TV', 23, 5);
INSERT INTO public.review VALUES (45, 'I cannot live without flours. Best for making cakes', 26, 5);
INSERT INTO public.review VALUES (29, 'These Cauliflowers are too good', 16, 6);
INSERT INTO public.review VALUES (30, 'Not as tasty as previous ones', 16, 7);
INSERT INTO public.review VALUES (35, 'Good looking shirts!', 20, 4);
INSERT INTO public.review VALUES (36, 'Bought for my husband and the size is too big, but good looking!', 20, 5);
INSERT INTO public.review VALUES (41, 'iPhone are best', 24, 6);
INSERT INTO public.review VALUES (42, 'I love my iPhone''s. Thank you apple!', 24, 7);
INSERT INTO public.review VALUES (33, 'These are really hot, oops', 18, 4);
INSERT INTO public.review VALUES (46, 'These Basmati rice are so delicious. Thumbs up!', 27, 7);
INSERT INTO public.review VALUES (49, 'I found some stones in this, I''m not gonna buy these again', 28, 7);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (4, 'sndpkrl007@gmail.com', 'Sandeep', 'Koirala', 'secret');
INSERT INTO public.users VALUES (5, 'yuvika.paudel@gmail.com', 'Yuvika', 'Paudel', 'secret');
INSERT INTO public.users VALUES (6, 'sbnkrl@gmail.com', 'Sabina', 'Koirala', 'secret');
INSERT INTO public.users VALUES (7, 'sbtkrl9@gmail.com', 'Sabita', 'Koirala', 'secret');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 49, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: category uk_46ccwnsi9409t36lurvtyljak; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT uk_46ccwnsi9409t36lurvtyljak UNIQUE (name);


--
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: review fk6cpw2nlklblpvc7hyt7ko6v3e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk6cpw2nlklblpvc7hyt7ko6v3e FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: address fk6i66ijb8twgcqtetl8eeeed6v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk6i66ijb8twgcqtetl8eeeed6v FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: review fkiyof1sindb9qiqr9o8npj8klt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fkiyof1sindb9qiqr9o8npj8klt FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- PostgreSQL database dump complete
--

