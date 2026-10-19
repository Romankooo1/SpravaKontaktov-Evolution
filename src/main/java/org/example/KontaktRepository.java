package org.example;

import org.example.Kontakt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Povieme Springu, že toto je naše databázové centrum(repozitár)
public interface KontaktRepository  extends JpaRepository<Kontakt, Long>{
    // TENTO JEDEN RIADOK JE KÚZLO!
    // Vďaka 'extends JpaRepository' dostane náš projekt úplne ZADARMO tieto schopnosti:
    // .save(kontakt)       -> automaticky urobí INSERT alebo UPDATE
    // .findAll()           -> automaticky vytiahne všetky riadky (SELECT *)
    // .deleteById(id)      -> automaticky vymaže riadok z databázy podľa ID
    // .findById(id)        -> nájde jeden konkrétny kontakt podľa ID
}

/*"Čo je to Spring Data JPA a JpaRepository?"
 "Je to rozhranie, ktoré ma odstrihne od písania manuálneho
SQL kódu. Spring na pozadí sám vygeneruje implementáciu
pre základné CRUD operácie (ukladanie, čítanie, mazanie),
takže sa môžem sústrediť čisto na biznis logiku."*/