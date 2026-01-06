DROP TABLE IF EXISTS celestial_bodies CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS body_type CASCADE;


CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    email VARCHAR(25) UNIQUE
);


CREATE TYPE body_type AS ENUM (
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


CREATE TABLE IF NOT EXISTS images(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    url VARCHAR NOT NULL UNIQUE,
    caption VARCHAR
);


CREATE TABLE IF NOT EXISTS celestial_bodies (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT REFERENCES users(id) NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR,
    body_type public.body_type NOT NULL,
    discovery_time timestamp without time zone,
    image_id BIGINT REFERENCES images(id),
    right_ascension numeric(10, 6),
    declination numeric(10, 6)
);


CREATE TABLE IF NOT EXISTS stars (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    body_id BIGINT REFERENCES celestial_bodies(id) NOT NULL,
    constellation character varying(50),
    apparent_magnitude numeric(6,3),
    absolute_magnitude numeric(6,3),
    mass_solar numeric(10,5),
    radius_solar numeric(10,5),
    luminosity_solar numeric(10,5),
    temperature real,
    spectral_class character varying(10)
);