package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String Naziv;
    private int glavni_grad;

    public Drzava(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        this.Naziv = naziv;
    }

    public int getGlavni_grad() {
        return glavni_grad;
    }

    public void setGlavni_grad(int glavni_grad) {
        this.glavni_grad = glavni_grad;
    }
}
