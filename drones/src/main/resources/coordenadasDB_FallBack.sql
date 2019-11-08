BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "ciudades"(
	"id"	INTEGER NOT NULL,
	"ciudad"	TEXT NOT NULL,
	"coordenadasX"	INTEGER NOT NULL,
	"coordenadasY"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);
INSERT INTO "ciudades" ("id","ciudad","coordenadasX","coordenadasY") VALUES (1,'Bilbao',100,100),
 (2,'Madrid',200,200);
COMMIT;
