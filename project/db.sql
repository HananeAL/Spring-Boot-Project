CREATE DATABASE IF NOT EXISTS sb_db;
USE sb_db;

/* represent a user in general */
CREATE TABLE IF NOT EXISTS User(
    id INT UNSIGNED AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    user_type ENUM('candidate', 'company'),
    remeber_me_code VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (remeber_me_code)
);
/* represent a social media account of a user */
CREATE TABLE IF NOT EXISTS SocialMedia(
    id INT UNSIGNED AUTO_INCREMENT,
    name ENUM('facebook', 'instagram', 'twitter', 'linkedin'),
    user_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS City(
    id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS Address(
    id INT UNSIGNED AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    city_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (city_id) REFERENCES City(id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    UNIQUE (street, city_id)
);

/* represent a candidate */
CREATE TABLE IF NOT EXISTS Candidate(
    id INT UNSIGNED,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    photo LONGBLOB NOT NULL,
    cv LONGBLOB,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE
);
/* represent an experience of a candidate */
CREATE TABLE IF NOT EXISTS Experience(
    id INT UNSIGNED AUTO_INCREMENT,
    start_date Date NOT NULL,
    end_date Date NOT NULL,
    position VARCHAR(50),
    company_name VARCHAR(50),
    sector VARCHAR(50),
    candidate_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE
);

/* represent specialty of a candidate */
/*CREATE TABLE IF NOT EXISTS CandSpec(
    candidate_id INT UNSIGNED,
    specialty_id INT UNSIGNED,
    level ENUM('junior', 'mid-level', 'senior'),
    PRIMARY KEY (candidate_id, specialty_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES Specialty(id)
);*/
/* represent a skill */
CREATE TABLE IF NOT EXISTS Skill(
    id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    level ENUM('beginner', 'intermidate', 'advanced'),
    candidate_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE
);
/* represent a skill of a candidate in a specialty */
/*CREATE TABLE IF NOT EXISTS CandSpecSkill(
    candidate_id INT UNSIGNED,
    specialty_id INT UNSIGNED,
    skill_id INT UNSIGNED,
    level ENUM('beginner', 'intermidate', 'advanced'),
    PRIMARY KEY (candidate_id, specialty_id, skill_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES Specialty(id),
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);*/
/* represent a skill of a candidate, this skill does not belong to a specialty */
/*CREATE TABLE IF NOT EXISTS CandSkill(
    candidate_id INT UNSIGNED,
    skill_id INT UNSIGNED,
    level ENUM('beginner', 'intermidate', 'advanced'),
    PRIMARY KEY (candidate_id, skill_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);*/

CREATE TABLE IF NOT EXISTS Diploma(
    id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    level TINYINT UNSIGNED NOT NULL, /* bac+1, bac+2 .... */
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS School(
    id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(name)
);
/* represent a diploma of a candidate */
/*CREATE TABLE IF NOT EXISTS CandDip(
    candidate_id INT UNSIGNED,
    diploma_id INT UNSIGNED,
    date DATE NOT NULL,
    specialty_id INT UNSIGNED NOT NULL,
    school_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (candidate_id, diploma_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES Specialty(id),
    FOREIGN KEY (diploma_id) REFERENCES Diploma(id),
    FOREIGN KEY (school_id) REFERENCES School(id)
);*/
/* represent a preference of a candidate */
CREATE TABLE IF NOT EXISTS Preference(
    id INT UNSIGNED,
    min_exp TINYINT UNSIGNED,
    max_exp TINYINT UNSIGNED,
    candidate_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    CHECK (min_exp <= max_exp)
);

CREATE TABLE IF NOT EXISTS OfferType(
    id INT UNSIGNED AUTO_INCREMENT,
    name ENUM('cdi', 'cdd', 'stage', 'pfa', 'pfe'),
    PRIMARY KEY (id),
    UNIQUE (name)
);
/*represent an offer type of a preference */ 
CREATE TABLE IF NOT EXISTS PrefOfferType(
    preference_id INT UNSIGNED,
    offer_type_id INT UNSIGNED,
    PRIMARY KEY (preference_id, offer_type_id),
    FOREIGN KEY (preference_id) REFERENCES Preference(id) ON DELETE CASCADE,
    FOREIGN KEY (offer_type_id) REFERENCES OfferType(id)
);
/* represent a city of a preference */
CREATE TABLE IF NOT EXISTS PrefCity(
    preference_id INT UNSIGNED,
    city_id INT UNSIGNED,
    PRIMARY KEY (preference_id, city_id),
    FOREIGN KEY (preference_id) REFERENCES Preference(id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES City(id)
);
/* represent a specialty of a preference */
/*CREATE TABLE IF NOT EXISTS PrefSpec(
    preference_id INT UNSIGNED,
    specialty_id INT UNSIGNED,
    PRIMARY KEY (preference_id, specialty_id),
    FOREIGN KEY (preference_id) REFERENCES Preference(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES Specialty(id)
);*/

CREATE TABLE IF NOT EXISTS Company(
    id INT UNSIGNED,
    name VARCHAR(100) NOT NULL,
    size INT UNSIGNED,
    foundation_date DATE NOT NULL,
    logo LONGBLOB NOT NULL,
    wallpaper LONGBLOB,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE
);

/* represent a specialty */
CREATE TABLE IF NOT EXISTS CompanySpecialty(
    id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    company_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES Company(id) ON DELETE CASCADE 
);
/* represent an address of a company */
CREATE TABLE IF NOT EXISTS CompAddr(
    company_id INT UNSIGNED,
    address_id INT UNSIGNED,
    is_main BOOLEAN NOT NULL, /* main address or not */ 
    PRIMARY KEY (company_id, address_id),
    FOREIGN KEY (company_id) REFERENCES Company(id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES Address(id)
);
/* represent a specialty of a company */
/*CREATE TABLE IF NOT EXISTS CompSpec(
    company_id INT UNSIGNED,
    specialty_id INT UNSIGNED,
    PRIMARY KEY (company_id, specialty_id),
    FOREIGN KEY (company_id) REFERENCES Company(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES Specialty(id)
);*/

CREATE TABLE IF NOT EXISTS Offer(
    id INT UNSIGNED AUTO_INCREMENT,
    position VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    min_exp TINYINT UNSIGNED,
    max_exp TINYINT UNSIGNED,
    min_salary DECIMAL,
    max_salary DECIMAL,
    start_date DATE NOT NULL,
    end_date DATE,
    creation_date DATE NOT NULL,
    closing_date DATE, /* date in which the offer will be closed automatically */
    is_open BOOLEAN NOT NULL,
    is_public BOOLEAN NOT NULL,
    receive_recomm BOOLEAN NOT NULL,
    offer_type_id INT UNSIGNED,
    company_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (offer_type_id) REFERENCES OfferType(id),
    FOREIGN KEY (company_id) REFERENCES Company(id) ON DELETE CASCADE,
    CHECK (min_exp <= max_exp),
    CHECK (min_salary <= max_salary)
);
/* represent a responsability in an offer */
CREATE TABLE IF NOT EXISTS OfferResp(
    id INT UNSIGNED AUTO_INCREMENT,
    resp TEXT NOT NULL,
    offer_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE
);
/* represent a skill in an offer */
CREATE TABLE IF NOT EXISTS OfferSkill(
    offer_id INT UNSIGNED,
    skill_id INT UNSIGNED,
    min_level ENUM('beginner', 'intermidate', 'advanced'),
    PRIMARY KEY (offer_id, skill_id),
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);

CREATE TABLE IF NOT EXISTS EducationLevel(
    id INT UNSIGNED AUTO_INCREMENT,
    level TINYINT UNSIGNED NOT NULL,/* bac +1, bac +2, ... */
    PRIMARY KEY (id),
    UNIQUE (level)
);
/* represent an education level in an offer */
CREATE TABLE IF NOT EXISTS OfferEduLevel(
    offer_id INT UNSIGNED,
    educ_level_id INT UNSIGNED,
    PRIMARY KEY (offer_id, educ_level_id),
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE,
    FOREIGN KEY (educ_level_id) REFERENCES EducationLevel(id)
);
/* represent a city in an offer */
CREATE TABLE IF NOT EXISTS OfferCity(
    offer_id INT UNSIGNED,
    city_id INT UNSIGNED,
    PRIMARY KEY (offer_id, city_id),
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES City(id)
);

CREATE TABLE IF NOT EXISTS Notification(
    preference_id INT UNSIGNED,
    offer_id INT UNSIGNED,
    PRIMARY KEY (preference_id, offer_id),
    FOREIGN KEY (preference_id) REFERENCES Preference(id) ON DELETE CASCADE,
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE
);
/* represent company followed by a candidate */
CREATE TABLE IF NOT EXISTS Subscription(
    candidate_id INT UNSIGNED,
    company_id INT UNSIGNED,
    PRIMARY KEY (candidate_id, company_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES Company(id) ON DELETE CASCADE
);
/* represent offer saved/liked by a candidate */
CREATE TABLE IF NOT EXISTS SavedOffer(
    candidate_id INT UNSIGNED,
    offer_id INT UNSIGNED,
    PRIMARY KEY (candidate_id, offer_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (offer_id) REFERENCES Offer(id) ON DELETE CASCADE
);

/* mysql user that we will use to connect to the database */
CREATE USER IF NOT EXISTS 'sb_user'@'localhost' IDENTIFIED BY 'sb';
GRANT ALL ON sb_db.* TO 'sb_user'@'localhost';


/* we had to do it, because by default SPRING_SESSION_ATTRIBUTES.ATTRIBUTE_BYTES has type BLOB 
wich is not enough, so we had to changed to LONGBLOB */
CREATE TABLE IF NOT EXISTS SPRING_SESSION (
  PRIMARY_ID char(36) NOT NULL,
  SESSION_ID char(36) NOT NULL,
  CREATION_TIME bigint NOT NULL,
  LAST_ACCESS_TIME bigint NOT NULL,
  MAX_INACTIVE_INTERVAL int NOT NULL,
  EXPIRY_TIME bigint NOT NULL,
  PRINCIPAL_NAME varchar(100) DEFAULT NULL,
  PRIMARY KEY (PRIMARY_ID),
  UNIQUE KEY SPRING_SESSION_IX1 (`SESSION_ID`),
  KEY SPRING_SESSION_IX2 (`EXPIRY_TIME`),
  KEY SPRING_SESSION_IX3 (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;


CREATE TABLE SPRING_SESSION_ATTRIBUTES (
  SESSION_PRIMARY_ID char(36) NOT NULL,
  ATTRIBUTE_NAME varchar(200) NOT NULL,
  ATTRIBUTE_BYTES longblob NOT NULL, /* changed from blob to longblob */
  PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
  CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;


