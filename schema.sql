-- =========================================================
--  FindMyRoomie — Full Database Schema (Updated)
--  Run this in MySQL Workbench or CLI before launching app
-- =========================================================

CREATE DATABASE IF NOT EXISTS findmyroomie;
USE findmyroomie;

-- Drop in order (respect foreign keys)
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS users;

-- ── USERS ─────────────────────────────────────────────────
CREATE TABLE users (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100)  NOT NULL,
    email           VARCHAR(150)  NOT NULL UNIQUE,
    password        VARCHAR(255)  NOT NULL,
    phone           VARCHAR(15),
    role            ENUM('HOST','SEEKER') NOT NULL,

    -- New roommate-matching fields
    aadhar          VARCHAR(12),
    smoking_habit   ENUM('NEVER','OCCASIONALLY','REGULARLY') DEFAULT 'NEVER',
    drinking_habit  ENUM('NEVER','OCCASIONALLY','REGULARLY') DEFAULT 'NEVER',
    occupation      VARCHAR(100),
    gender          ENUM('MALE','FEMALE','OTHER'),
    preferred_city  VARCHAR(100),
    age             INT DEFAULT 0,
    bio             TEXT
);

-- ── ROOMS ─────────────────────────────────────────────────
CREATE TABLE rooms (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(200)  NOT NULL,
    city             VARCHAR(100)  NOT NULL,
    rent             DOUBLE        NOT NULL,
    furnished        BOOLEAN       DEFAULT FALSE,
    host_id          INT           NOT NULL,
    description      TEXT,

    -- New room detail fields
    image_path       VARCHAR(500),
    bedrooms         INT           DEFAULT 1,
    bathrooms        INT           DEFAULT 1,
    room_type        ENUM('SINGLE','SHARED','ENTIRE_APARTMENT') DEFAULT 'SINGLE',
    pets_allowed     BOOLEAN       DEFAULT FALSE,
    smoking_allowed  BOOLEAN       DEFAULT FALSE,
    amenities        VARCHAR(500),
    available_from   VARCHAR(20),

    FOREIGN KEY (host_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ── APPLICATIONS ──────────────────────────────────────────
CREATE TABLE applications (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    seeker_id        INT  NOT NULL,
    room_id          INT  NOT NULL,
    message          TEXT,
    status           ENUM('PENDING','ACCEPTED','REJECTED') DEFAULT 'PENDING',

    -- New application fields
    applied_on       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    move_in_date     VARCHAR(20),
    duration_months  INT DEFAULT 0,

    FOREIGN KEY (seeker_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id)   REFERENCES rooms(id)  ON DELETE CASCADE
);
