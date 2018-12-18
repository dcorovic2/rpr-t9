package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO ourInstance = new GeografijaDAO();
    private Connection conn;

    public static GeografijaDAO getInstance() {
        return ourInstance;
    }

    public GeografijaDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Dalija\\IdeaProjects\\rpr-t9tut\\src\\baza.db");

            String upit1 = "INSERT INTO main.grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (1, 'Pariz', 2200000, 1)";

            String upit2 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (2, 'London', 8136000, 2)";

            String upit3 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (3, 'Bec', 1868000, 3)";

            String upit4 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (4, 'Manchester', 510746, 2)";

            String upit5 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (5, 'Graz',  283869, 3)";

            String upit6 = "insert into drzava(id, naziv, glavni_grad) " +
                    "values (1, 'Francuska', 1)";
            String upit7 = "insert into drzava(id, naziv, glavni_grad)" +
                    "values(2, 'United Kingdom', 2)";
            String upit8 = "insert into drzava(id, naziv, glavni_grad)" +
                    "values(3, 'Austrija', 3)";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(upit1);
            stmt.executeUpdate(upit2);
            stmt.executeUpdate(upit3);
            stmt.executeUpdate(upit4);
            stmt.executeUpdate(upit5);
            stmt.executeUpdate(upit6);
            stmt.executeUpdate(upit7);
            stmt.executeUpdate(upit8);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Grad glavniGrad(String drzava)  {
        Grad g1 = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * from main.drzava where naziv = " + drzava);
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
            stmt.executeUpdate("delete from drzava where main.drzava.naziv = " + drzava);
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
            stm.executeUpdate("insert into grad (id, naziv, broj_stanovnika, drzava) values (" + grad.getId() +", " + grad.getNaziv() + ", " + grad.getBroj_stanovnika() +
                    ", " + grad.getDrzava() + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public  void dodajDrzavu(Drzava drzava){
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("insert into drzava(id, naziv, glavni_grad) values (" + drzava.getId() + ", " + drzava.getNaziv() + ", " + drzava.getGlavni_grad() + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad){
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("update main.grad set main.grad.naziv = " +  grad.getNaziv() +
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
