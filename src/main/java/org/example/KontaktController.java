package org.example;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping
    public List<Kontakt> ziskajVsetkyKontakty() {
        // Zavoláme náš databázový repozitár a metódu .findAll().
        // Na pozadí Spring preloží tento riadok do SQL príkazu "SELECT * FROM kontakt;",


        return kontaktRepository.findAll();
    }
}
