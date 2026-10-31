# 📱 Správa Kontaktov (Evolučný Projekt)

Tento projekt slúži ako ukážka môjho osobného rozvoja, štúdia Java ekosystému a poctivej prípravy na technický pohovor. Aplikácia prešla kompletnou architektonickou evolúciou od konzolového menu až po modernú webovú aplikáciu.

> 💡 **Poznámka ku kódu:** Kód zámerne obsahuje podrobné študijné komentáre v slovenčine. Slúžia mi na detailné pochopenie životného cyklu objektov, JDBC mechaniky a webovej architektúry pod kapotou.

---

## 🚀 Fázy vývoja projektu (Roadmap)

1. **Java & ArrayList (In-Memory):** Prvá verzia s textovým menu a ukladaním dát čisto v operačnej pamäti RAM.
2. **Java + MySQL 8.0 (Čisté JDBC):** Prerobenie aplikácie na trvalé ukladanie dát do SQL databázy. Implementovaná bezpečnosť proti SQL Injection (`PreparedStatement`), práca s `DriverManager`, `Connection` a cyklické spracovanie dát cez `ResultSet`.
3. **Spring Boot REST API (Kompletné CRUD):** Migrácia na trojvrstvovú architektúru. Ručný SQL kód bol nahradený automatizovaným ORM (Hibernate) a inteligentnými repozitármi (`JpaRepository`). Implementoval som plnú obojsmernú komunikáciu:
    * 📥 **GET** (`@GetMapping`) - Čítanie kontaktov
    * 📤 **POST** (`@PostMapping`) - Pridávanie nových dát
    * 🔄 **PUT** (`@PutMapping`) - Aktualizácia a úprava
    * ❌ **DELETE** (`@DeleteMapping`) - Bezpečné mazanie podľa ID
4. **Frontend Integrácia (Thymeleaf & HTML):** Prepojenie backendového servera s vizuálnou vrstvou. Dáta sa dynamicky ťahajú z MySQL databázy a vykresľujú sa do čistej a responzívnej HTML tabuľky na adrese `http://localhost:8080`.

---

## 🛠️ Použité technológie

* **Jazyk:** Java 17 / OpenJDK
* **Framework:** Spring Boot 3.2.5 (Spring Web, Spring Data JPA)
* **Šablónovací motor:** Thymeleaf (Frontend integrácia)
* **Nástroj na správu projektu:** Maven (správa závislostí a životného cyklu)
* **Databáza:** MySQL 8.0 (Lokálny databázový server)