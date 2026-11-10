package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component // Povieme springu, že táto trieda je súčasťou našej aplikácie.
public class SpravaKontaktov implements CommandLineRunner {

    @Autowired // Dependency Injection: Spring sám nájde KontaktRepository.
    private KontaktRepository kontaktRepository;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        // Táto nahradí moju starú metódu main() - Spring spustí hneď po štarte
        Scanner scan = new Scanner(System.in);
        int volba;

        do {
            System.out.println("\n *** MENU ***");
            System.out.println("1. Pridať kontakt");
            System.out.println("2. Zobraziť všetky kontakty");
            System.out.println("3. Vymazať kontakty podľa ID");
            System.out.println("4. Upraviť kontakt");
            System.out.println("5. Koniec");
            System.out.println("Vyberte možnosť: ");
            volba = scan.nextInt();
            scan.nextLine(); // Vyčistenie Enteru

            switch (volba) {
                case 1 -> pridatKontakt(scan);
                case 2 -> zobraziťKontakt();
                case 3 -> vymazaťKontakt(scan);
                case 4 -> upravKontakt(scan);

                case 5 -> System.out.println("Ukončuje sa program.");
                default -> System.out.println("Neplatná voľba");
            }
        } while (volba != 5);
    }

    // Pridanie kontaktu(Samo urobí INSERT do MySql.)
    private void pridatKontakt(Scanner scan) {
        System.out.println("Zadajte meno:  ");
        String meno = scan.nextLine();
        System.out.println("Zadajte svoje telefónne číslo: ");
        String telefon = scan.nextLine();
        System.out.println("Zadajte svoj E-mail: ");
        String email = scan.nextLine();

        Kontakt novyKontakt = new Kontakt(meno, telefon, email);
        kontaktRepository.save(novyKontakt);
        System.out.println("Kontakt bol úspešne uložený pomocou Spring Dáta JPA!");
    }

    // Zobrazenie kontaktov (Samo urobí SELECT *)
    private void zobraziťKontakt() {
        System.out.println("\n --- Zoznam kontaktov ---");
        List<Kontakt> kontakty = kontaktRepository.findAll();

        if (kontakty.isEmpty()) {
            System.out.println("V databáze nie sú žiadne kontakty");
        } else {
            for (Kontakt k : kontakty) {
                System.out.println(k);
            }
        }
    }

    // Vymazanie kontaktu (Samo urobí DELETE podľa ID)
    private void vymazaťKontakt(Scanner scan) {
        System.out.println("Zadajte ID kontaktu na vymazanie: ");
        Long id = scan.nextLong();

        if (kontaktRepository.existsById(id)) {
            kontaktRepository.deleteById(id);
            System.out.println("Kontakt s ID " + id + " bol úspešne vymazaný.");
        } else {
            System.out.println("Kontakt s týmto ID neexistuje! ");
        }
    }
    // Úprava kontaktu
    private void upravKontakt(Scanner scan) {
        System.out.print("Zadajte ID kontaktu, ktorý chcete upraviť: ");
        Long idNaUpravu = scan.nextLong();
        scan.nextLine(); // Vyčistenie riadku po čísle

        // Overenie kontaktu v MySQL databáze
        var najdenyKontakt = kontaktRepository.findById(idNaUpravu);

        if (najdenyKontakt.isPresent()) {
            Kontakt kontakt = najdenyKontakt.get();

            System.out.println("Zadajte nové meno (pôvodné: " + kontakt.getMeno() + "): ");
            String noveMeno = scan.nextLine();

            System.out.println("Zadajte nový telefón (pôvodný: " + kontakt.getTelefon() + "): ");
            String novyTelefon = scan.nextLine();

            System.out.println("Zadajte nový e-mail (pôvodný: " + kontakt.getEmail() + "): ");
            String novyEmail = scan.nextLine();

            // Prepísanie starých dát novými hodnotami (ak používateľ niečo zadal)
            if (!noveMeno.isEmpty()) kontakt.setMeno(noveMeno);
            if (!novyTelefon.isEmpty()) kontakt.setTelefon(novyTelefon);
            if (!novyEmail.isEmpty()) kontakt.setEmail(novyEmail);

            // Uložíme zmeny natvrdo do MySQL databázy
            kontaktRepository.save(kontakt);
            System.out.println("Kontakt bol v databáze úspešne upravený!");
        } else {
            System.out.println("Kontakt s takýmto ID neexistuje.");
        }
    }
}
