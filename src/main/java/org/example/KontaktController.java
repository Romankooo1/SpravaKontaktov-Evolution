package org.example;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController //Táto anotácia povie Spring Bootu, že trieda funguje ako REST API koncový bod.
@RequestMapping("/api/kontakty") //Nastavuje základnú webovú adresu pre tento kontrolér.
public class KontaktController {

    @Autowired
    private KontaktRepository kontaktRepository;
    // Zapína "Dependency Injection" (vstrekovanie závislostí).
    // Hovorí Springu: „Nájdi v pamäti hotové databázové pripojenie pre KontaktRepository a automaticky ho sem dosaď.“

    // 1. Fáza zobrazenie GET
    @GetMapping
    public List<Kontakt> ziskajVsetkyKontakty() {
        // Zavoláme databázový repozitár a metódu .findAll().
        // Na pozadí Spring preloží tento riadok do SQL príkazu "SELECT * FROM kontakt;",
        return kontaktRepository.findAll();
    }

    // 2. Fáza prídavanie POST
    @PostMapping  // Táto anotácia mapuje HTTP POST požiadavky. Používa sa vtedy, keď na server posielame nové dáta.
    public Kontakt pridajNovyKontakt(@RequestBody Kontakt novyKontakt) {
        /* Anotácia @RequestBody robí obrovské kúzlo. Vezme surový JSON text,
         ktorý nám prišiel z internetu, rozbalí ho a automaticky ho preloží do Java objektu "Kontakt".*/
        return kontaktRepository.save(novyKontakt);
        // Pomocou repozitára a metódy .save() zapíšeme do MySQL databázy
    }
}
