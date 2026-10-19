package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Hovorí springu, že táto trieda reprezentuje tabuľku v databáze.
@Table(name="kontakt") //Definuje presný názov tabuľky v MySgl Workbench.

public class Kontakt {

    @Id//Každá tabuľka v springu JPA musí mať unikátny primárny kľúč(ID).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Nastaví automatické generovanie ID(1, 2, 3...) ako AUTO:INCREMENT v SQL.
    private Long id;

    private String meno;
    private String telefon;
    private String email;

    /*Bezargumentový konštruktor.Spring(Hibernate) ho striktne vyžaduje!
     Najskôr si cez neho vytvorí prázdny objekt a až potom doňho naleje dáta z dazabázy.*/
    public Kontakt(){
    }
    /* Môj pôvodný konštruktor, ktorý už poznám.
    Budeme ho používať pri vytváraní kontaktu v konzolovom menu.*/
    public Kontakt(String meno, String telefon, String email){
        this.meno = meno;
        this.telefon = telefon;
        this.email = email;
    }

    //Verejné gettery a settery -> Spring ich nutne potrebuje na čítanie a zápis dát v premenných.
    public Long getId() { return id; }
    public void setID(Long id){this.id = id; }

    public String GetMeno() {return meno;}
    public void setMeno(String meno){this.meno = meno;}

    public String getTelefon(){return telefon;}
    public void setTelefon(String telefon){this.telefon = telefon;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    /* Prekrytie metódy: Aby sa nám v konzole
    namiesto adresy v pamäti vypísal pekný text.*/
    @Override
    public String toString(){
        return "ID: " + id + " | Meno: " + meno+ " | Telefon: "+ telefon + " | E-mail: " + email;
    }

}