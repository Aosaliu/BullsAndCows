DROP DATABASE IF EXISTS BullAndCowDB;
CREATE DATABASE BullAndCowDB;

USE BullAndCowDB;

/* Create Tables */
CREATE TABLE Game(
gameID INT PRIMARY KEY AUTO_INCREMENT,
answer  CHAR(4) NOT NULL, 
finished BOOLEAN DEFAULT false
);

CREATE TABLE Round(
roundID INT PRIMARY KEY  auto_increment,
gameID INT NOT NULL, 
guess CHAR(4) NOT NULL,
guessTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
guessResult VARCHAR(10) NOT NULL,

FOREIGN KEY (gameID) references Game(gameID)
);

/* Insert sample values into Tables */
INSERT INTO Game(gameID, answer, finished) VALUES
	(1, "2971", true),
	(2, "9218", true),
	(3, "2345", true);
    
INSERT INTO Round(roundID, gameID, guessTime, guess, guessResult) VALUES 
    (1, 1, "2019-06-03 07:25:18", "2349", "e:1:p:1"),
    (2, 1, "2019-06-02 09:11:07", "9186", "e:0:p:2"),
    (3, 1, "2019-06-04 09:11:49", "2971", "e:4:p:0"),
    (4, 2, "2019-07-12 17:31:09", "9218", "e:4:p:0"),
    (5, 3, "2019-08-28 02:42:55", "1678", "e:0:p:0"),
    (6, 3, "2019-08-28 02:43:12", "2345", "e:4:p:0");
    
    
/* Display the date within tables*/
SELECT * FROM  Game;
SELECT * FROM  Round;