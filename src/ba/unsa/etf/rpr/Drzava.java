package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String Naziv;
    private Grad GlavniGrad;

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

    public Grad getGlavniGrad() {
        return GlavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.GlavniGrad = glavniGrad;
    }
}
