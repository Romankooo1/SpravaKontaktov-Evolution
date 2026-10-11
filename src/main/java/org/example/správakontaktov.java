package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class správakontaktov {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int volba;
        do {
            System.out.println("\n*** SPRÁVA KONTAKTOV ***");
            System.out.println("1. Pridať kontakt");
            System.out.println("2. Zobraziť všetky kontakty");
            System.out.println("3. Vyhľadať kontakt podľa mena");
            System.out.println("4. Vymazať kontakt podľa mena");
            System.out.println("5. Koniec");
            System.out.print("Vyberte možnosť (1-5): ");

            volba = scanner.nextInt();
            scanner.nextLine();

            switch (volba) {
                case 1 -> pridatKontakt();
                case 2 -> zobrazitKontakty();
                case 3 -> vyhladatKontakt();
                case 4 -> vymazatKontakt();
                case 5 -> System.out.println("Program sa ukončuje. Dovidenia!");
                default -> System.out.println("Neplatná voľba, skúste to znova.");
            }
        } while (volba != 5);
    }

    private static void pridatKontakt() {
        System.out.print("Zadajte meno: ");
        String meno = scanner.nextLine();
        System.out.print("Zadajte telefón: ");
        String telefon = scanner.nextLine();
        System.out.print("Zadajte e-mail: ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO kontakty (Meno, Telefon, Email) VALUES (?, ?, ?)";
        /* KÚZLO OTÁZNIKOV (Obrana pred hackermi):
         Namiesto priameho vkladania textu do SQL príkazu použijeme otázniky (?, ?, ?).
         Slúžia ako bezpečné "držiaky miesta". Java ich pred odoslaním do databázy očistí
         od nebezpečných znakov a zabráni útoku zvanému SQL Injection.*/

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            /* TRY-WITH-RESOURCES (Automatické upratovanie):
             Tento zápis v zátvorke try zaistí, že hneď po vykonaní kódu (alebo pri chybe)
            sa pripojenie (Connection) aj príkaz (PreparedStatement) automaticky ZATVORIA.
            Vďaka tomu nám neuniká pamäť v počítači.*/

            pstmt.setString(1, meno);
            pstmt.setString(2, telefon);
            pstmt.setString(3, email);

            pstmt.executeUpdate();
            /* SPUSTENIE PRÍKAZU:
             executeUpdate() povie databáze: "Vykonaj zmenu (zápis/zmazanie)".
             Táto metóda nevracia tabuľku s dátami, iba zápis uskutoční.*/

            System.out.println("Kontakt bol úspešne uložený do MySQL!");

        } catch (SQLException e) {
            /* Ak napríklad vypadne spojenie s databázou, catch zachytí chybu
             a vypíše nám presný dôvod, prečo zápis zlyhal. */
            System.out.println("Chyba pri ukladaní: " + e.getMessage());
        }
    }

    private static void zobrazitKontakty() {
        // Metóda na zobrazenie kontaktov.
        String sql = "SELECT * FROM kontakty";
        // Jednoduchý SQL dopyt -> ak chceme vytiahnúť všetky stĺpce.

        try (Connection conn = DatabaseConnection.getConnection();
             // Bezpečnostné potrubie (try-with-resources).
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* executeQuery() spustí dopyt a vráti nám objekt ReslutSet.
            ResultSet je ako virtuálna Excel tabuľka plná dát, ktorou nám posiela MySql server.*/

            System.out.println("\n--- Zoznam kontaktov z MySQL ---");

            boolean prazdne = true;
            //Pomocný maják - na začiatku predpokladáme, že databáza je prázdna.

            while (rs.next()) {
                /* CYKLUS WHILE A KÚZLO rs.next():
                 rs.next() posunie virtuálny kurzor (ukazovateľ) na ďalší riadok v tabuľke.
                 Ak na tom riadku sú nejaké dáta, vráti TRUE a cyklus beží.
                 Ak prejdeme na koniec tabuľky a už tam nič nie je, vráti FALSE a cyklus skončí.*/
                prazdne = false;

                String meno = rs.getString("meno");
                String telefon = rs.getString("telefon");
                String email = rs.getString("email");
                // rs.getString("názov_stĺpca") vytiahne text z konkrétneho stĺpca na aktuálnom riadku.

                System.out.println("Meno: " + meno + " | Tel: " + telefon + " | E-mail: " + email);
                // Vypíšeme dáta pekne do konzoly.
            }
                if (prazdne) {
                    System.out.println("V databáze nie sú žiadne kontakty.");
                    /* Ak cyklus while ani raz neprebehol (rs.next() bol hneď na štarte false),
                     premenná 'prázdne' zostala true a vypíšeme oznam používateľovi.*/
                }

            } catch(SQLException e){
                System.out.println("Chyba pri načítaní: " + e.getMessage());
                // Ak zlyhá sieť alebo prepíšeme slovo v názve stĺpca, vypíše sa tu chyba.

            }
        }

    private static void vyhladatKontakt() {
        System.out.print("Zadajte meno na vyhľadanie: ");
        String hladaneMeno = scanner.nextLine();

        String sql = "SELECT * FROM kontakty WHERE LOWER(meno) = LOWER(?)";
        /*KÚZLO FUKNCIE LOWER() V SQL
        LOWER(meno) zmení všetky mená v databáze  na malé písmená.
        LOWER(?) zmení text, ktorý zadal používateľ, tiež na malé písmená.
        Týmto dosiahneme, že roman = roman a tým zostanú malé písmena.*/

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Použijeme PreparedStatement, lebo do SQL príkazu vkládame premennú (ten ?).

            pstmt.setString(1, hladaneMeno);
            // Nahradí prvý otáznik v SQL príkaze.

            ResultSet rs = pstmt.executeQuery();
            //Očakávame dáta z databázy (SELECT) použijeme executeQuery() a ResultSet().

            boolean najdeny = false;
            // Maják, ktorý nám povie či sme niečo našli.
            while (rs.next()) {
                // Tu databáza vracia riadky,ktoré zodpovedajú menu.
                najdeny = true;
                //Prepli sme maják - kontakt existuje.

                // Vytiahneme dáta z aktuálneho nájdeného riadku.
                String meno = rs.getString("meno");
                String telefon = rs.getString("telefon");
                String email = rs.getString("email");

                System.out.println("Nájdený -> Meno: " + rs.getString("meno") + " | Tel: " + rs.getString("telefon"));
            }

            if (!najdeny) System.out.println("Kontakt se nenašel.");
            // Ak cyklus while ani raz neprebehol (nájdený zostáva false).
        } catch (SQLException e) {
            System.out.println("Chyba pri vyhľadávaní: " + e.getMessage());
        }
    }

    private static void vymazatKontakt() {
        System.out.print("Zadajte meno na vymazanie: ");
        String menoNaVymazanie = scanner.nextLine();
        String sql = "DELETE FROM kontakty WHERE LOWER(meno) = LOWER(?)";
        /* SQL príkaz na zmazanie riadku. Znova použijeme LOWER()
        aby sme zmazali kontakt, aj keď používateľ napíše meno malým písmenom. */

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // PreparedStatement používame kvôli bezpečnosti a dosadeniu otáznika.

            pstmt.setString(1, menoNaVymazanie);
            /* zmena executeUpdate() namiesto xecuteQuery()
             executeUpdate používame INSERT,UPDATE A DELETE.
             Nevracia tabuľku dát(ResultSet), iba celé číslo -> počet riadkov, ktoré sa zmenili/zmazali.*/

            int zmazane = pstmt.executeUpdate();

            if (zmazane > 0) {
                //Ak našla číslo väčšie ako 0 znamená, že sa našla a zmazala daný riadok.
                System.out.println("Kontakt bol vymazaný z MySQL.");
            }else{
                //Ak sa vrátila 0, kontakt neexzistoval v databáze.
                System.out.println("Kontakt sa nenašiel.");
            }

        } catch (SQLException e) {
            System.out.println("Chyba pri mazaní: " + e.getMessage());
        }
    }
}
