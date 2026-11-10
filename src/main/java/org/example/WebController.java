package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
/*Používame @Controller (bez Rest!). To hovorí Springu,že nejdeme vracať surové dáta,
ale ideme vracať celú HTML stránku z priečinku templates.*/

public class WebController {

    @Autowired
    private KontaktRepository kontaktRepository;

    @GetMapping("/")
    public String zobrazDomovskuStranku(Model model){

        //1. Vytiahne všetky kontakty z databázy.
        var vsetkyKontakty = kontaktRepository.findAll();

        //2.Pribalíme ich do "balíka" (Model), ktorý doručíme na frontend pod názvom "kontaktyNaWebe"
        model.addAttribute("kontaktyNaWebe", vsetkyKontakty);

        return "index";
    }
    @PostMapping("/pridat")
    public String spracujNovyKontakt(@ModelAttribute Kontakt novyKontakt) {
        // Uložíme nový kontakt priamo do MySQL databázy
        kontaktRepository.save(novyKontakt);

        // REDIRECT (Presmerovanie)
        return "redirect:/";

    }
    @PostMapping("/upravit/{id}")
    public String spracujUpravuKontaktu(@PathVariable Long id, @ModelAttribute Kontakt upraveneData) {
        // Vytiahneme pôvodný kontakt, prepíšeme ho a uložíme do MySQL
        kontaktRepository.findById(id).map(kontakt -> {
            kontakt.setMeno(upraveneData.getMeno());
            kontakt.setTelefon(upraveneData.getTelefon());
            kontakt.setEmail(upraveneData.getEmail());
            return kontaktRepository.save(kontakt);
        }).orElseThrow(() -> new RuntimeException("Kontakt nenašiel"));

        // Návrat na domovskú stránku (obnovenie zoznamu)
        return "redirect:/";
        }
    }