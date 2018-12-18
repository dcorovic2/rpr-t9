package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

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

    public Grad glavniGrad(String drzava)  {
        Grad g1 = new Grad();
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT naziv from main.drzava where naziv = " + drzava);
            if(result == null) return null;

            int id1 = result.findColumn("glavni_grad");
            ResultSet res2 = stmt.executeQuery("select * from main.grad where id = " + id1);
            Grad g = new Grad(res2.getInt("id"), res2.getString("naziv"), res2.getInt("broj_stanovnika"), res2.getInt("drzava"));

            g1 = g;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return g1;
    }

    public void obrisiDrzavu(String drzava){

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            //id drzava
            ResultSet result = stmt.executeQuery("select id from main.drzava where naziv = " + drzava);
            stmt.executeQuery("delete from drzava where main.drzava.naziv = " + drzava);
/*
            while (result.next()){

            }

            stmt.executeQuery("delete from grad where ");*/

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

   /* public ArrayList<Grad> gradovi(){

    }*/

    public void dodajGrad(Grad grad) {
        try {
            Statement stm = conn.createStatement();
            stm.executeQuery("insert into grad (id, naziv, broj_stanovnika, drzava) values (" + grad.getId() +", " + grad.getNaziv() + ", " + grad.getBroj_stanovnika() +
                    ", " + grad.getDrzava() + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public  void dodajDrzavu(Drzava drzava){
        try {
            Statement stm = conn.createStatement();
            stm.executeQuery("insert into drzava(id, naziv, glavni_grad) values (" + drzava.getId() + ", " + drzava.getNaziv() + ", " + drzava.getGlavni_grad() + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad){
        try {
            Statement stm = conn.createStatement();
            stm.executeQuery("update main.grad set main.grad.naziv = " +  grad.getNaziv() +
                    ", main.grad.broj_stanovnika = " + grad.getBroj_stanovnika() + ", main.grad.drzava = " + grad.getDrzava() +
                    "where id = " + grad.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava){
        Drzava d = new Drzava();

        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT naziv from main.drzava where naziv = " + drzava);
            if(result == null) return null;

            d.setId(result.getInt("id"));
            d.setNaziv(result.getString("naziv"));
            d.setGlavni_grad(result.getInt("glavni_grad"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return d;
    }
}
