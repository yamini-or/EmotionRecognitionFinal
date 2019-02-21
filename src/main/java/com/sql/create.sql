/*

DROP TABLE IF EXISTS VIDEO;
CREATE TABLE VIDEO(ID INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), link VARCHAR(255) UNIQUE, recruiter VARCHAR(255), candidate VARCHAR(255), faceCount INT, neutral INT, anger INT, disgust INT, happy INT, surprise INT, comments VARCHAR(255));
INSERT INTO VIDEO VALUES( 1,'vid1', 'loc/vid1', 'rec', 'cand', 50, 20, 5, 10, 5, 10);
select * from VIDEO;

INSERT INTO VIDEO(title, link, recruiter, candidate, facecount,neutral,anger,disgust,happy,surprise) VALUES( 'vid2', 'loc/vid2', 'rec1', 'cand1', 50, 20, 5, 10, 5, 10);
*/