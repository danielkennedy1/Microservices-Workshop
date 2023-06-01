DROP TABLE IF EXISTS `identities`;

CREATE TABLE identities (
    identity VARCHAR(255) NOT NULL PRIMARY KEY,
    multiplier FLOAT NOT NULL DEFAULT 1.0
);
