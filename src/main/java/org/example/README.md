# Správa Kontaktov (Evolučný Projekt)

Tento projekt slúži ako ukážka môjho osobného rozvoja, štúdia Java ekosystému a prípravy na technický pohovor. Aplikácia prechádza postupnou architektonickou evolúciou.

> 💡 **Poznámka ku kódu:** Kód zámerne obsahuje podrobné študijné komentáre v slovenčine. Slúžia mi na detailné pochopenie JDBC mechaniky, životného cyklu objektov a architektúry pod kapotou.

## 🚀 Fázy vývoja projektu:
1. **Java & ArrayList (In-Memory)**: Prvá verzia s textovým menu a ukladaním dát čisto v operačnej pamäti RAM.
2. **Java + MySQL 8.0 (Čisté JDBC)**: Prerobenie aplikácie na trvalé ukladanie dát do SQL databázy. Implementovaná bezpečnosť proti SQL Injection (`PreparedStatement`), práca s `DriverManager`, `Connection` a cyklické spracovanie dát cez `ResultSet`.
3. **Spring Boot & Spring Data JPA**: *[AKTUÁLNA FÁZA]* Migrácia na modernú trojvrstvovú architektúru. Ručný SQL kód bude kompletne nahradený pomocou automatizovaného ORM (Hibernate) a inteligentných repozitárov.

## 🛠️ Použité technológie:
- Java 17 / OpenJDK
- Maven (správa závislostí)
- MySQL 8.0 (lokálny databázový server)