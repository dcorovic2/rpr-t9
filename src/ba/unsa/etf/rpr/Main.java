package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static String ispisiGradove(){
        String s = "";
        GeografijaDAO gd = new GeografijaDAO();
        ArrayList<Grad> gradovi = gd.gradovi();

        for(Grad g: gradovi){
            System.out.println(g.getNaziv() + "(" + ") - " + g.getBroj_stanovnika());
        }

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
      //  ispisiGradove();
    }
}
