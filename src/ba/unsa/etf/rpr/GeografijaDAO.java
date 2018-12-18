package ba.unsa.etf.rpr;

import java.sql.*;

public class GeografijaDAO {
    private static GeografijaDAO ourInstance = new GeografijaDAO();
    private Connection conn;

    public static GeografijaDAO getInstance() {
        return ourInstance;
    }

    private GeografijaDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:sqlite/baza.db");

            String upit1 = "INSER INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (1, Pariz, 2200000, Francuska)";

            String upit2 = "INSER INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (2, London, 8136000, United Kingdom)";

            String upit3 = "INSER INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (3, Bec, 1868000, Austija)";

            String upit4 = "INSER INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (4, Manchester, 510746, United Kingdom)";

            String upit5 = "INSER INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (5, Graz,  283869, Austrija)";

            Statement stmt = conn.createStatement();
            stmt.executeQuery(upit1);
            stmt.executeQuery(upit2);
            stmt.executeQuery(upit3);
            stmt.executeQuery(upit4);
            stmt.executeQuery(upit5);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public Grad glavniGrad(String drzava) {

    }*/

}
