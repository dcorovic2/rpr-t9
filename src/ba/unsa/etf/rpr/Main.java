package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {
    static String ispisiGradove(){
        String s = "";

        return s;
    }

    static void glavniGrad(){
        System.out.println("Unesite naziv drzave: ");
        String unos;
        Scanner ulaz = new Scanner(System.in);
        unos = ulaz.nextLine();

        GeografijaDAO gd = new GeografijaDAO();

        Grad g = gd.glavniGrad(unos);
        if(g == null) System.out.println("Nepostojeća država");
        else System.out.println("Glavni grad države " + unos + " je " + g.getNaziv());
    }

    public static void main(String[] args) {
        glavniGrad();
    }
}
