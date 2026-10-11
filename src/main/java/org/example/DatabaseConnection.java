package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/kontakty_db";
    /* URL adresa nám hovorí, že prepájame Javu na domací počítač.
     Keď chceme pracovať s konkrétnou databázou -> kontakty_db. */

    private static final String USER = "root";
    // toto je predvolený  používateľ v MySql -> root.

    private static final String PASSWORD = "Julka310121Patko";
    // toto je heslo do MySql.

    public static Connection getConnection() throws SQLException {
        // metóda, ktorou zavoláme vždy keď potrebujeme "ovtoriť potrubie" do Databázy.

        return DriverManager.getConnection(URL, USER, PASSWORD);
        // DriverManager je ústredňa -> zoberie adresu, meno, heslo.
        // prebudí stiahnutý ovládač a vráti nám živé prepojenie.
    }
}
