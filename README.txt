WISHCLOUD  (Ønsk. Del. Modtag.)
================================

Hej! Det her er vores miniprojekt fra 2. semester (Gruppe 5).
WishCloud er en hjemmeside, hvor man kan lave en ønskeseddel,
skrive sine ønsker på den og dele den med familie og venner.


HVAD KAN DEN?
-------------
- Oprette en profil
- Logge ind og ud
- Oprette ønskesedler og se dem på en oversigt
- Tilføje, rette og slette ønsker
- Dele sin ønskeseddel med et link (hvis vi når det)
- Reservere ønsker (hvis vi når det)

(Ret listen, så den passer til det vi har lavet, inden vi afleverer)


HVAD ER DET LAVET MED?
----------------------
- Java + Spring Boot
- Thymeleaf (HTML) og CSS
- MySQL
- Maven


SÅDAN FÅR DU DET TIL AT VIRKE
-----------------------------
Du skal bruge: Java, MySQL (gerne med Workbench), IntelliJ og Git.

1. Hent projektet
   git clone <LINK TIL VORES GITHUB>

2. Sørg for at din MySQL kører
   Tjek i Workbench at du kan forbinde (normalt localhost, port 3306).

3. Lav din egen lille config-fil
   Vi vil ikke have vores passwords på GitHub, så alle laver deres egen:

   - Opret filen: src/main/resources/application-local.properties
   - Skriv dine egne oplysninger i den:

       spring.datasource.url=jdbc:mysql://localhost:3306/projekt2_gruppe_5_db?createDatabaseIfNotExist=true
       spring.datasource.username=DIT_BRUGERNAVN
       spring.datasource.password=DIT_PASSWORD

   Filen bliver ikke pushet til GitHub (den står i .gitignore),
   så du skal lave den selv første gang.

4. Start programmet
   Kør Projekt2Gruppe5Application i IntelliJ.
   Så opretter den selv databasen og tabellerne (schema.sql)
   og lægger testdata ind (data.sql).

5. Åbn siden
   Gå ind på http://localhost:8080 i din browser.


LOGIN TIL TEST
--------------
Login-siden er allerede udfyldt, så tryk bare "Log ind".
Hvis du skal skrive det selv:

   E-mail:   test@test.dk
   Password: 1234


HVOR LIGGER TINGENE?
--------------------
src/main/java/.../controller   - tager imod det man klikker på
src/main/java/.../service      - reglerne (fx at e-mail ikke må bruges to gange)
src/main/java/.../repository   - snakker med databasen
src/main/java/.../model        - User, Wishlist osv.

src/main/resources/templates   - HTML-siderne
src/main/resources/static      - CSS og billeder (logo)
src/main/resources/schema.sql  - laver tabellerne
src/main/resources/data.sql    - lægger testdata ind


HVIS DATABASEN GÅR I FISK
-------------------------
Slet databasen i Workbench og start programmet igen,
så bliver alt lavet forfra:

   DROP DATABASE projekt2_gruppe_5_db;


SÅDAN ARBEJDER VI I GRUPPEN
---------------------------
- Én branch pr. user story (fx feature/US-002-opret-onskeseddel)
- Pull request ind i main, så main altid virker
- Husk at hente nyeste main (git pull) før du starter
- Scrum boardet ligger i GitHub Projects


Gruppe 5:
- Oscar
- Gustav
- Nico
- Tor
