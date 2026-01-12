DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS body_type CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS celestial_bodies CASCADE;
DROP TABLE IF EXISTS stars CASCADE;
DROP TABLE IF EXISTS planets CASCADE;
DROP TABLE IF EXISTS moons CASCADE;
DROP TABLE IF EXISTS asteroids CASCADE;
DROP TABLE IF EXISTS galaxies CASCADE;
DROP TABLE IF EXISTS black_holes CASCADE;
DROP TABLE IF EXISTS comments CASCADE;


CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
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
    'GALAXY',
    'BLACK_HOLE',
    'UNKNOWN'
);


CREATE TABLE IF NOT EXISTS images(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    url VARCHAR NOT NULL UNIQUE,
    caption VARCHAR
);


CREATE TABLE IF NOT EXISTS celestial_bodies (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR,
    body_type body_type NOT NULL,
    discovery_time timestamp without time zone,
    image_id BIGINT REFERENCES images(id),
    right_ascension numeric(10, 6),
    declination numeric(10, 6)
);


CREATE TABLE IF NOT EXISTS stars (
    id BIGSERIAL PRIMARY KEY,
    body_id BIGINT NOT NULL REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE,
    constellation character varying(50),
    apparent_magnitude numeric(6,3),
    absolute_magnitude numeric(6,3),
    mass_solar numeric(10,5),
    radius_solar numeric(10,5),
    luminosity_solar numeric(10,5),
    temperature real,
    spectral_class character varying(10)
);


CREATE TABLE IF NOT EXISTS planets (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  star_id BIGINT REFERENCES stars(id) ON DELETE CASCADE ON UPDATE CASCADE,
  orbital_period_days FLOAT,
  earth_mean_radius DOUBLE PRECISION,
  earth_volume DOUBLE PRECISION,
  mean_density DOUBLE PRECISION,
  surface_gravity DOUBLE PRECISION,
  surface_temperature_kelvin DOUBLE PRECISION
);


CREATE TABLE IF NOT EXISTS moons (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  planet_id BIGINT REFERENCES planets(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  orbital_period_days FLOAT,
  earth_mean_radius DOUBLE PRECISION,
  earth_volume DOUBLE PRECISION,
  mean_density DOUBLE PRECISION,
  surface_gravity DOUBLE PRECISION,
  surface_temperature_kelvin DOUBLE PRECISION
);


CREATE TABLE IF NOT EXISTS asteroids (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  spectral_type VARCHAR(20),
  composition_type VARCHAR(50),
  mean_diameter_km NUMERIC(10,3),
  mass_kg NUMERIC(20,5),
  density_g_cm3 NUMERIC(6,3)
);


CREATE TABLE IF NOT EXISTS galaxies (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  hubble_type VARCHAR(20),
  stars_number BIGINT,
  size_kpc NUMERIC(9, 6)
);


CREATE TABLE IF NOT EXISTS black_holes (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  black_hole_type VARCHAR(20),
  mass_solar NUMERIC(15, 6),
  schwarzschild_radius_km NUMERIC(10, 6)
);


CREATE TABLE IF NOT EXISTS comments (
  id BIGSERIAL PRIMARY KEY,
  body_id BIGINT REFERENCES celestial_bodies(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  contents VARCHAR NOT NULL,
  timestamp TIMESTAMP NOT NULL
);