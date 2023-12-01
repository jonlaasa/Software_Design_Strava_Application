/* DELETE 'strava_user' database*/
DROP SCHEMA IF EXISTS StravaDB;
/* DELETE USER 'strava_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'strava_user'@'%';

/* CREATE ''StravaDB' DATABASE */
CREATE SCHEMA IF NOT EXISTS StravaDB;
/* CREATE THE USER 'strava_user' AT LOCAL SERVER WITH PASSWORD 'strava_user' */
CREATE USER IF NOT EXISTS 'strava_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'StravaDB' FOR THE USER 'strava_user' AT LOCAL SERVER*/
GRANT ALL ON StravaDB.* TO 'strava_user'@'%';