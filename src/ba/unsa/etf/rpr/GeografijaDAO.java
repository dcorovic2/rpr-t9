package ba.unsa.etf.rpr;

import org.sqlite.SQLiteException;

import java.nio.file.attribute.AclEntryType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GeografijaDAO {
    private static GeografijaDAO ourInstance = new GeografijaDAO();
    private Connection conn;

    public static GeografijaDAO getInstance() {
        return ourInstance;
    }

    public GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/baza.db");

            String upit1 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (1, 'Pariz', 2200000, 6)";

            String upit2 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (2, 'London', 8136000, 7)";

            String upit3 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (3, 'Bec', 1868000, 8)";

            String upit4 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (4, 'Manchester', 510746, 7)";

            String upit5 = "INSERT INTO grad(id, naziv, broj_stanovnika, drzava) " +
                    "VALUES (5, 'Graz',  283869, 8)";

            String upit6 = "insert into drzava(id, naziv, glavni_grad) " +
                    "values (6, 'Francuska', 1)";
            String upit7 = "insert into drzava(id, naziv, glavni_grad)" +
                    "values(7, 'United Kingdom', 2)";
            String upit8 = "insert into drzava(id, naziv, glavni_grad)" +
                    "values(8, 'Austrija', 3)";

            String upitPocetni1 = "delete from drzava";
            String upitPocetni2 = "delete from grad";

            Statement stmt = conn.createStatement();

            try {
                stmt.execute(upitPocetni1);
                stmt.execute(upitPocetni2);

                stmt.executeUpdate(upit1);
                stmt.executeUpdate(upit2);
                stmt.executeUpdate(upit3);
                stmt.executeUpdate(upit4);
                stmt.executeUpdate(upit5);
                stmt.executeUpdate(upit6);
                stmt.executeUpdate(upit7);
                stmt.executeUpdate(upit8);
            } catch (SQLiteException e){

            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Grad glavniGrad(String drzava) {
        Grad g = null;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from drzava where naziv =  ?");
            statement.setString(1, drzava);
            ResultSet result = statement.executeQuery();
            if(!result.next()) return null;

            int id1 = result.getInt(3);
            PreparedStatement srmt2 = conn.prepareStatement("select * from main.grad where id = ?");
            srmt2.setInt(1, id1);
            ResultSet res2 =  srmt2.executeQuery();
            g = new Grad(res2.getInt(1), res2.getString(2), res2.getInt(3), res2.getInt(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return g;
    }

    public void obrisiDrzavu(String drzava){
        Statement stmt = null;
        try {
            PreparedStatement st = conn.prepareStatement("select id from main.drzava where naziv = ?");
            st.setString(1, drzava);
            ResultSet result = st.executeQuery();

            PreparedStatement st1 = conn.prepareStatement("delete from drzava where naziv = ?");
            st1.setString(1, drzava);
            st1.executeUpdate();

            int idDrzave = result.getInt(1);
            PreparedStatement gradovi = conn.prepareStatement("delete from grad where drzava = ?");
            gradovi.setInt(1, idDrzave);
            gradovi.executeUpdate();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
/*
    public ArrayList<Grad> gradovi(){
        Set<Grad> gradovi = new TreeSet<>();
        ArrayList<Grad> pov = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from grad");

            Grad g = null;
            while(result.next()){
                g.setId(result.getInt("id"));
                g.setNaziv(result.getString("naziv"));
                g.setBroj_stanovnika(result.getInt("broj_stanovnika"));
                g.setDrzava(result.getInt("drzava"));
                gradovi.add(g);
            }

            pov.addAll(gradovi);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pov;
    }
*/
    public void dodajGrad(Grad grad) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into grad (id, naziv, broj_stanovnika, drzava) values ( ?, ?, ?, ?)");
            st.setInt(1, grad.getId());
            st.setString(2, grad.getNaziv());
            st.setInt(3, grad.getBroj_stanovnika());
            st.setInt(4, grad.getDrzava());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public  void dodajDrzavu(Drzava drzava){
        try {
            PreparedStatement st = conn.prepareStatement("insert into drzava(id, naziv, glavni_grad) values ( ?, ?, ?)");
            st.setInt(1, drzava.getId());
            st.setString(2, drzava.getNaziv());
            st.setInt(3, drzava.getGlavni_grad());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad){
        try {
            PreparedStatement ps = conn.prepareStatement("update main.grad set main.grad.naziv = ?, broj_stanovnika = ?, drzava = ? where id = ? ");
            ps.setString(1, grad.getNaziv());
            ps.setInt(2, grad.getBroj_stanovnika());
            ps.setInt(3, grad.getDrzava());
            ps.setInt(4, grad.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava){
        Drzava d = new Drzava();

        try {
            PreparedStatement st = conn.prepareStatement("SELECT * from main.drzava where naziv = ?");
            st.setString(1, drzava);
            ResultSet result = st.executeQuery();
            if(!result.next()) return null;

            d.setId(result.getInt(1));
            d.setNaziv(result.getString(2));
            d.setGlavni_grad(result.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return d;
    }
}
