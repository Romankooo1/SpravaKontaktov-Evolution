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
    public void run(String...args){
        // Táto nahradí moju starú metódu main() - Spring spustí hneď po štarte
        Scanner scan = new Scanner(System.in);
        int volba;

        do{
            System.out.println("\n *** MENU ***");
            System.out.println("1. Pridať kontakt");
            System.out.println("2. Zobraziť všetky kontakty");
            System.out.println("3. Vymazať kontakty podľa ID");
            System.out.println("4. Koniec");
            System.out.println("Vyberte možnosť: ");
            volba = scan.nextInt();
            scan.nextLine(); // Vyčistenie Enteru

             switch(volba){
                 case 1 -> pridatKontakt(scan);
                 case 2 -> zobraziťKontakt();
                 case 3 -> vymazaťKontakt(scan);
                 case 4 -> System.out.println("Ukončuje sa program.");
                 default -> System.out.println("Neplatná voľba");
             }
        }while(volba != 4);
    }

    // Pridanie kontaktu(Samo urobí INSERT do MySql.)
    private void pridatKontakt(Scanner scan){
        System.out.println("Zadajte meno:  ");
        String meno = scan.nextLine();
        System.out.println("Zadajte svoje telefónne číslo: ");
        String telefon = scan.nextLine();
        System.out.println("Zadajte svoj E-mail: ");
        String email = scan.nextLine();

        Kontakt novyKontaktr = new Kontakt(meno, telefon, email);
        kontaktRepository.save(novyKontaktr);
        System.out.println("Kontakt bol úspešne uložený pomocou Spring Dáta JPA!");
    }

    // Zobrazenie kontaktov (Samo urobí SELECT *)
    private void zobraziťKontakt(){
        System.out.println("\n --- Zoznam kontaktov ---");
        List<Kontakt> kontakty = kontaktRepository.findAll();

        if (kontakty.isEmpty()){
            System.out.println("V databáze nie sú žiadne kontakty");
        }else{
            for (Kontakt k : kontakty){
                System.out.println(k);
            }
        }
    }
    // Vymazanie kontaktu (Samo urobí DELETE podľa ID)
    private void vymazaťKontakt(Scanner scan){
        System.out.println("Zadajte ID kontaktu na vymazanie: ");
        Long id = scan.nextLong();

        if (kontaktRepository.existsById(id)) {
            kontaktRepository.deleteById(id);
            System.out.println("Kontakt s ID " + id + " bol úspešne vymazaný.");
        } else {
            System.out.println("Kontakt s týmto ID neexistuje! ");
        }
    }
}