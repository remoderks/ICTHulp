USE `icthulp`;

-- Drop the table if it already exists
DROP TABLE IF EXISTS dorpsgenotenInfo;

-- Create the Geboortedatums table if it doesn't already exist
CREATE TABLE IF NOT EXISTS geboortedatums (
    geboortedatum DATE NOT NULL,
    PRIMARY KEY (geboortedatum)  -- Primary key for birthdate
);

-- Create the Besturingssystemen table if it doesn't already exist
CREATE TABLE IF NOT EXISTS besturingssystemen(
    besturingssysteem VARCHAR(100) NOT NULL,
    PRIMARY KEY (besturingssysteem)
);

-- Create the Aanwezigheidsdatums table if it doesn't already exist
CREATE TABLE IF NOT EXISTS aanwezigheidsdatums (
    aanwezigheidsdatum DATE NOT NULL,
    PRIMARY KEY (aanwezigheidsdatum)  -- Primary key for attendance date
);

-- Create the DorpsgenotenInfo table if it doesn't already exist
CREATE TABLE IF NOT EXISTS dorpsgenotenInfo (
    dorpsgenoot VARCHAR(100) NOT NULL,
    geboortedatum DATE NOT NULL,                       -- Gebortedatum (Birthdate)
    email VARCHAR(255) NOT NULL,                       -- Email Address (Unique)
    besturingssysteem VARCHAR(100) NOT NULL,           -- Operating System (Foreign Key)
    telefoonnummer VARCHAR(15) NOT NULL,               -- Phone Number (Unique)
    postcode VARCHAR(10) NOT NULL,                     -- Postal Code
    huisnummer VARCHAR(10) NOT NULL,                   -- House Number
    aanwezigheidsdatum DATE NOT NULL,                  -- Attendance Date

    PRIMARY KEY (dorpsgenoot),                         -- Primary Key for dorpsgenoot

    -- Foreign Key to Geboortedatums
    FOREIGN KEY (geboortedatum) REFERENCES geboortedatums(geboortedatum),  
    
    -- Foreign Key to Aanwezigheidsdatums
    FOREIGN KEY (aanwezigheidsdatum) REFERENCES aanwezigheidsdatums(aanwezigheidsdatum),

    -- Foreign Key to Besturingssystemen
    FOREIGN KEY (besturingssysteem) REFERENCES besturingssystemen(besturingssysteem),  

    -- Defining Alternate Keys (AK)
    UNIQUE (email),                 -- AK1001: Enforcing uniqueness on Email
    UNIQUE (telefoonnummer),        -- AK for Telefoonnummer
    UNIQUE (postcode, huisnummer)   -- Combined AK for Postcode and Huisnummer
);

-- Check the table structure
DESCRIBE dorpsgenoteninfo;
