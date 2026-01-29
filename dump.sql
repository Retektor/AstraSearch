--
-- PostgreSQL database dump
--

\restrict YL4Ba54IUUY41VN3PLx9WahcegRvmDYPkOose1XZIYoo3aU88hvx7iGozc27gvb

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-01-29 21:59:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 874 (class 1247 OID 16999)
-- Name: body_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.body_type AS ENUM (
    'STAR',
    'PLANET',
    'MOON',
    'ASTEROID',
    'COMET',
    'GALAXY',
    'BLACK_HOLE',
    'SATELLITE',
    'UNKNOWN'
);


ALTER TYPE public.body_type OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 17515)
-- Name: asteroids; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asteroids (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    spectral_type character varying(20),
    composition_type character varying(50),
    mean_diameter_km numeric(10,3),
    mass_kg numeric(20,5),
    density_g_cm3 numeric(6,3)
);


ALTER TABLE public.asteroids OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17514)
-- Name: asteroids_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.asteroids_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.asteroids_id_seq OWNER TO postgres;

--
-- TOC entry 5021 (class 0 OID 0)
-- Dependencies: 225
-- Name: asteroids_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asteroids_id_seq OWNED BY public.asteroids.id;


--
-- TOC entry 230 (class 1259 OID 17543)
-- Name: black_holes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.black_holes (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    black_hole_type character varying(20),
    mass_solar numeric(15,6),
    schwarzschild_radius_km numeric(10,6)
);


ALTER TABLE public.black_holes OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17542)
-- Name: black_holes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.black_holes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.black_holes_id_seq OWNER TO postgres;

--
-- TOC entry 5022 (class 0 OID 0)
-- Dependencies: 229
-- Name: black_holes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.black_holes_id_seq OWNED BY public.black_holes.id;


--
-- TOC entry 224 (class 1259 OID 17299)
-- Name: celestial_bodies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.celestial_bodies (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying,
    body_type public.body_type NOT NULL,
    discovery_time timestamp without time zone,
    image_id bigint,
    right_ascension numeric(10,6),
    declination numeric(10,6)
);


ALTER TABLE public.celestial_bodies OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17298)
-- Name: celestial_bodies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.celestial_bodies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.celestial_bodies_id_seq OWNER TO postgres;

--
-- TOC entry 5023 (class 0 OID 0)
-- Dependencies: 223
-- Name: celestial_bodies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.celestial_bodies_id_seq OWNED BY public.celestial_bodies.id;


--
-- TOC entry 238 (class 1259 OID 17784)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    user_id bigint NOT NULL,
    contents character varying NOT NULL,
    "timestamp" timestamp without time zone NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 17783)
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comments_id_seq OWNER TO postgres;

--
-- TOC entry 5024 (class 0 OID 0)
-- Dependencies: 237
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- TOC entry 228 (class 1259 OID 17529)
-- Name: galaxies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.galaxies (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    hubble_type character varying(20),
    stars_number bigint,
    size_kpc numeric(3,6)
);


ALTER TABLE public.galaxies OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17528)
-- Name: galaxies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.galaxies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.galaxies_id_seq OWNER TO postgres;

--
-- TOC entry 5025 (class 0 OID 0)
-- Dependencies: 227
-- Name: galaxies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.galaxies_id_seq OWNED BY public.galaxies.id;


--
-- TOC entry 222 (class 1259 OID 17115)
-- Name: images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images (
    id integer NOT NULL,
    url character varying NOT NULL,
    caption character varying
);


ALTER TABLE public.images OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17114)
-- Name: images_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.images ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.images_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 232 (class 1259 OID 17575)
-- Name: moons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.moons (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    planet_id bigint NOT NULL,
    orbital_period_days double precision,
    earth_mean_radius double precision,
    earth_volume double precision,
    mean_density double precision,
    surface_gravity double precision,
    surface_temperature_kelvin double precision
);


ALTER TABLE public.moons OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17574)
-- Name: moons_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.moons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.moons_id_seq OWNER TO postgres;

--
-- TOC entry 5026 (class 0 OID 0)
-- Dependencies: 231
-- Name: moons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.moons_id_seq OWNED BY public.moons.id;


--
-- TOC entry 234 (class 1259 OID 17595)
-- Name: planets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planets (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    star_id bigint NOT NULL,
    orbital_period_days double precision,
    earth_mean_radius double precision,
    earth_volume double precision,
    mean_density double precision,
    surface_gravity double precision,
    surface_temperature_kelvin double precision
);


ALTER TABLE public.planets OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17594)
-- Name: planets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.planets_id_seq OWNER TO postgres;

--
-- TOC entry 5027 (class 0 OID 0)
-- Dependencies: 233
-- Name: planets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planets_id_seq OWNED BY public.planets.id;


--
-- TOC entry 236 (class 1259 OID 17615)
-- Name: stars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stars (
    id bigint NOT NULL,
    body_id bigint NOT NULL,
    constellation character varying(50),
    apparent_magnitude numeric(6,3),
    absolute_magnitude numeric(6,3),
    mass_solar numeric(10,5),
    radius_solar numeric(10,5),
    luminosity_solar numeric(10,5),
    temperature real,
    spectral_class character varying(10)
);


ALTER TABLE public.stars OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17614)
-- Name: stars_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.stars_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.stars_id_seq OWNER TO postgres;

--
-- TOC entry 5028 (class 0 OID 0)
-- Dependencies: 235
-- Name: stars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.stars_id_seq OWNED BY public.stars.id;


--
-- TOC entry 220 (class 1259 OID 16984)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(25) NOT NULL,
    password character varying NOT NULL,
    first_name character varying(25),
    last_name character varying(25),
    email character varying(25)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16983)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4804 (class 2604 OID 17518)
-- Name: asteroids id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asteroids ALTER COLUMN id SET DEFAULT nextval('public.asteroids_id_seq'::regclass);


--
-- TOC entry 4806 (class 2604 OID 17546)
-- Name: black_holes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.black_holes ALTER COLUMN id SET DEFAULT nextval('public.black_holes_id_seq'::regclass);


--
-- TOC entry 4803 (class 2604 OID 17302)
-- Name: celestial_bodies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies ALTER COLUMN id SET DEFAULT nextval('public.celestial_bodies_id_seq'::regclass);


--
-- TOC entry 4810 (class 2604 OID 17787)
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- TOC entry 4805 (class 2604 OID 17532)
-- Name: galaxies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galaxies ALTER COLUMN id SET DEFAULT nextval('public.galaxies_id_seq'::regclass);


--
-- TOC entry 4807 (class 2604 OID 17578)
-- Name: moons id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moons ALTER COLUMN id SET DEFAULT nextval('public.moons_id_seq'::regclass);


--
-- TOC entry 4808 (class 2604 OID 17598)
-- Name: planets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planets ALTER COLUMN id SET DEFAULT nextval('public.planets_id_seq'::regclass);


--
-- TOC entry 4809 (class 2604 OID 17618)
-- Name: stars id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stars ALTER COLUMN id SET DEFAULT nextval('public.stars_id_seq'::regclass);


--
-- TOC entry 5003 (class 0 OID 17515)
-- Dependencies: 226
-- Data for Name: asteroids; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asteroids (id, body_id, spectral_type, composition_type, mean_diameter_km, mass_kg, density_g_cm3) FROM stdin;
\.


--
-- TOC entry 5007 (class 0 OID 17543)
-- Dependencies: 230
-- Data for Name: black_holes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.black_holes (id, body_id, black_hole_type, mass_solar, schwarzschild_radius_km) FROM stdin;
\.


--
-- TOC entry 5001 (class 0 OID 17299)
-- Dependencies: 224
-- Data for Name: celestial_bodies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.celestial_bodies (id, user_id, name, description, body_type, discovery_time, image_id, right_ascension, declination) FROM stdin;
15	1	Солнце	Звезда Солнечной системы, основной источник тепла на Земле	STAR	2024-12-31 23:59:59	17	286.130000	63.870000
18	1	Земля	Родная планета человечества, третья планета от Солнца	PLANET	2026-01-01 00:00:00	20	0.000000	0.000000
22	1	Меркурий	Наименьшая планета Солнечной системы, первая планета от Солнца	PLANET	2026-02-01 00:14:00	24	281.010000	61.410000
\.


--
-- TOC entry 5015 (class 0 OID 17784)
-- Dependencies: 238
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, body_id, user_id, contents, "timestamp") FROM stdin;
\.


--
-- TOC entry 5005 (class 0 OID 17529)
-- Dependencies: 228
-- Data for Name: galaxies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.galaxies (id, body_id, hubble_type, stars_number, size_kpc) FROM stdin;
\.


--
-- TOC entry 4999 (class 0 OID 17115)
-- Dependencies: 222
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.images (id, url, caption) FROM stdin;
17	https://upload.wikimedia.org/wikipedia/commons/b/b4/The_Sun_by_the_Atmospheric_Imaging_Assembly_of_NASA%27s_Solar_Dynamics_Observatory_-_20100819.jpg	Солнце с жёлтым фильтром света
20	https://upload.wikimedia.org/wikipedia/commons/0/0d/Africa_and_Europe_from_a_Million_Miles_Away.png	Планета Земля на расстоянии в миллион миль
24	https://upload.wikimedia.org/wikipedia/commons/3/30/Mercury_in_color_-_Prockter07_centered.jpg	Изображение Меркурия, полученное во время первого пролёта космического аппарата «Мессенджер»
25	https://upload.wikimedia.org/wikipedia/commons/3/30/Mercury_in_color_-_Prockter07_centered.jpg	Изображение Меркурия, полученное во время первого пролёта космического аппарата «Мессенджер»
\.


--
-- TOC entry 5009 (class 0 OID 17575)
-- Dependencies: 232
-- Data for Name: moons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.moons (id, body_id, planet_id, orbital_period_days, earth_mean_radius, earth_volume, mean_density, surface_gravity, surface_temperature_kelvin) FROM stdin;
\.


--
-- TOC entry 5011 (class 0 OID 17595)
-- Dependencies: 234
-- Data for Name: planets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planets (id, body_id, star_id, orbital_period_days, earth_mean_radius, earth_volume, mean_density, surface_gravity, surface_temperature_kelvin) FROM stdin;
1	18	1	365.25	1	1	5.510000228881836	9.807000160217285	288.1499938964844
4	22	1	87.96910095214844	0	0.0560000017285347	5.427000045776367	3.700000047683716	440.1499938964844
\.


--
-- TOC entry 5013 (class 0 OID 17615)
-- Dependencies: 236
-- Data for Name: stars; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stars (id, body_id, constellation, apparent_magnitude, absolute_magnitude, mass_solar, radius_solar, luminosity_solar, temperature, spectral_class) FROM stdin;
1	15	\N	-26.740	4.830	1.00000	1.00000	1.00000	1.57e+07	G
\.


--
-- TOC entry 4997 (class 0 OID 16984)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, password, first_name, last_name, email) FROM stdin;
1	Mr_Retektor	admin	Evgeniy	Volokitin	zhenya.volokitin@list.ru
\.


--
-- TOC entry 5029 (class 0 OID 0)
-- Dependencies: 225
-- Name: asteroids_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asteroids_id_seq', 1, false);


--
-- TOC entry 5030 (class 0 OID 0)
-- Dependencies: 229
-- Name: black_holes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.black_holes_id_seq', 1, false);


--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 223
-- Name: celestial_bodies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.celestial_bodies_id_seq', 24, true);


--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 237
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);


--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 227
-- Name: galaxies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.galaxies_id_seq', 1, false);


--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 221
-- Name: images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_id_seq', 26, true);


--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 231
-- Name: moons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.moons_id_seq', 1, false);


--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 233
-- Name: planets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planets_id_seq', 5, true);


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 235
-- Name: stars_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stars_id_seq', 1, true);


--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- TOC entry 4826 (class 2606 OID 17522)
-- Name: asteroids asteroids_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asteroids
    ADD CONSTRAINT asteroids_pkey PRIMARY KEY (id);


--
-- TOC entry 4830 (class 2606 OID 17550)
-- Name: black_holes black_holes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.black_holes
    ADD CONSTRAINT black_holes_pkey PRIMARY KEY (id);


--
-- TOC entry 4820 (class 2606 OID 17814)
-- Name: celestial_bodies celestial_bodies_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies
    ADD CONSTRAINT celestial_bodies_id_key UNIQUE (id);


--
-- TOC entry 4822 (class 2606 OID 17812)
-- Name: celestial_bodies celestial_bodies_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies
    ADD CONSTRAINT celestial_bodies_name_key UNIQUE (name);


--
-- TOC entry 4824 (class 2606 OID 17442)
-- Name: celestial_bodies celestial_bodies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies
    ADD CONSTRAINT celestial_bodies_pkey PRIMARY KEY (id);


--
-- TOC entry 4838 (class 2606 OID 17796)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 4828 (class 2606 OID 17536)
-- Name: galaxies galaxies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galaxies
    ADD CONSTRAINT galaxies_pkey PRIMARY KEY (id);


--
-- TOC entry 4818 (class 2606 OID 17123)
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id);


--
-- TOC entry 4832 (class 2606 OID 17583)
-- Name: moons moons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moons
    ADD CONSTRAINT moons_pkey PRIMARY KEY (id);


--
-- TOC entry 4834 (class 2606 OID 17603)
-- Name: planets planets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planets
    ADD CONSTRAINT planets_pkey PRIMARY KEY (id);


--
-- TOC entry 4836 (class 2606 OID 17622)
-- Name: stars stars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stars
    ADD CONSTRAINT stars_pkey PRIMARY KEY (id);


--
-- TOC entry 4812 (class 2606 OID 16997)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4814 (class 2606 OID 16993)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4816 (class 2606 OID 16995)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4841 (class 2606 OID 17523)
-- Name: asteroids asteroids_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asteroids
    ADD CONSTRAINT asteroids_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4843 (class 2606 OID 17551)
-- Name: black_holes black_holes_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.black_holes
    ADD CONSTRAINT black_holes_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4839 (class 2606 OID 17331)
-- Name: celestial_bodies celestial_bodies_image_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies
    ADD CONSTRAINT celestial_bodies_image_id_fkey FOREIGN KEY (image_id) REFERENCES public.images(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4840 (class 2606 OID 17326)
-- Name: celestial_bodies celestial_bodies_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.celestial_bodies
    ADD CONSTRAINT celestial_bodies_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4847 (class 2606 OID 17797)
-- Name: comments comments_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4848 (class 2606 OID 17802)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4842 (class 2606 OID 17537)
-- Name: galaxies galaxies_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galaxies
    ADD CONSTRAINT galaxies_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4844 (class 2606 OID 17584)
-- Name: moons moons_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moons
    ADD CONSTRAINT moons_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4845 (class 2606 OID 17604)
-- Name: planets planets_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planets
    ADD CONSTRAINT planets_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4846 (class 2606 OID 17623)
-- Name: stars stars_body_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stars
    ADD CONSTRAINT stars_body_id_fkey FOREIGN KEY (body_id) REFERENCES public.celestial_bodies(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2026-01-29 21:59:17

--
-- PostgreSQL database dump complete
--

\unrestrict YL4Ba54IUUY41VN3PLx9WahcegRvmDYPkOose1XZIYoo3aU88hvx7iGozc27gvb

