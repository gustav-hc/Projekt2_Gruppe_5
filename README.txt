WISHCLOUD
================================
 
Miniprojekt 2. semester - Gruppe 5
Repository: Projekt2_Gruppe_5
 
WishCloud er en hjemmeside, hvor man kan lave en ønskeseddel,
skrive sine ønsker på den og dele den med andre.
 
Lavet med: Java, Spring Boot, Thymeleaf (HTML/CSS), MySQL og Maven.
 
 
DET SKAL DU HAVE INSTALLERET
----------------------------
- IntelliJ IDEA
- Java JDK (samme version som i pom.xml)
- MySQL Server og MySQL Workbench
- MySQL-brugeren F26A med password 1234 (se trin 1)
 
 
1. OPRET MYSQL-BRUGEREN F26A (hvis den ikke findes)
----------------------------------------------------
Start MySQL Workbench, log ind som root og kør dette i en query tab:
 
   CREATE USER IF NOT EXISTS 'F26A'@'localhost' IDENTIFIED BY '1234';
   GRANT ALL PRIVILEGES ON *.* TO 'F26A'@'localhost';
   FLUSH PRIVILEGES;
 
Findes brugeren allerede, kan du springe trinnet over.
 
 
2. HENT OG ÅBN PROJEKTET
------------------------
- Download projektet fra GitHub (Code -> Download ZIP, eller git clone).
- Pak det ud, og åbn mappen i IntelliJ (File -> Open).
 
 
3. DATABASEN
------------
Databasen hedder projekt2_gruppe_5_db.
Forbindelsen (URL, bruger og password) står i:
 
   src/main/resources/application.properties
 
Scripts ligger i src/main/resources:
   schema.sql  - opretter tabellerne
   data.sql    - lægger testdata ind
 
MULIGHED A - automatisk (det nemmeste)
Når programmet startes, opretter det selv databasen, kører schema.sql
og data.sql. Du behøver ikke gøre mere. Gå videre til trin 4.
 
MULIGHED B - manuelt i Workbench (hvis A ikke virker)
1. Log ind i Workbench med F26A / 1234.
2. Kør:
 
      CREATE DATABASE IF NOT EXISTS projekt2_gruppe_5_db;
      USE projekt2_gruppe_5_db;
 
3. Åbn schema.sql, og kør hele scriptet.
4. Åbn data.sql, og kør hele scriptet.
 
GENDAN DATABASEN FRA START
Vil du nulstille alt, så kør i Workbench:
 
   DROP DATABASE IF EXISTS projekt2_gruppe_5_db;
 
Start derefter programmet igen (eller brug mulighed B).
 
 
4. START PROGRAMMET
-------------------
Åbn Projekt2Gruppe5Application i IntelliJ og tryk på den grønne
afspil-knap ved siden af main-metoden.
 
Åbn derefter http://localhost:8080 i din browser.
 
 
5. LOG IND
----------
Login-siden er forudfyldt, så du kan bare trykke "Log ind".
Hvis du skal skrive det selv:
 
   E-mail:   test@test.dk
   Password: 1234
 
 
HVOR LIGGER TINGENE?
--------------------
src/main/java/.../controller   - tager imod det, man klikker på
src/main/java/.../service      - reglerne (fx at e-mail ikke må bruges to gange)
src/main/java/.../repository   - snakker med databasen
src/main/java/.../model        - User, Wishlist osv.
 
src/main/resources/templates   - HTML-siderne
src/main/resources/static      - CSS og billeder (logo)
src/main/resources/schema.sql  - laver tabellerne
src/main/resources/data.sql    - lægger testdata ind

Gruppe 5:
- Oscar
- Gustav
- Nico
- Tor
 