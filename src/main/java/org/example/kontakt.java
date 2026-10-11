package org.example;

public class kontakt {
    private String meno;
    private String telefon;
    private String email;
    /* Zapuzdrenie(Encapsulation) -  Všetky dáta(premenné) sú uzamknúte pomocou modifikatóra "private.
Vonkajší svet ich nemôže zmeniť alebo prepísať.*/

    public kontakt(String meno, String telefon, String email) {
        /*Konštruktor(Constructor) - Špeciálna metóda ,ktorá sa spustí iba raz - presne vtedy keď napíšeme new Kontakt().
        Nemá návratový typ ani void a volá sa presne ako samotná metóda.
        Jej úlohou je "porodíť" objekt a hneď mu nastaviť základné hodnoty. */

        this.meno = meno;
        this.telefon = telefon;
        this.email = email;
        /*Kľúčové slovo "this" hovorí zober hodnotu zo zátvorky a ulož ju
        do mojej prívátnej premenej tejto triedy */
    }


    public String getMeno() { return meno; }
    public String getTelefon() { return telefon; }
    public String getEmail() { return email; }
    //Gettery verejné brány k dátam.
    @Override
    public String toString() {
        return "Meno: " + meno + " | Tel: " + telefon + " | E-mail: " + email;
    }
}
/* PREKRYTIE METÓDY (@Override) a toString()
 Každá trieda v Jave automaticky dedí od hlavného predka 'Object'.
 Ten má metódu toString(), ktorá ale v základe vypisuje len nezmysly (adresu v pamäti).
 Anotáciou @Override hovoríme Jave: "Ignoruj pôvodné správanie od predka,
 ja ti tu definujem vlastný a krajší spôsob, ako tento objekt vypísať ako text."*/