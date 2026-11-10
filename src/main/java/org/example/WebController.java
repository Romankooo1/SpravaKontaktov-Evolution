package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
}