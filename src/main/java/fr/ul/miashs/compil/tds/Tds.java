package fr.ul.miashs.compil.tds;

import java.util.ArrayList;

public class Tds {
private final ArrayList<Symbole> tds;

    public Tds(ArrayList<Symbole> tds) {
        this.tds = tds;
        for (int i = 0; i < tds.size(); i++) {
            if (tds.get(i).getNom().equals("main")) {
                tds.add(0, tds.remove(i));
                break;
            }
        }
    }

    public ArrayList<Symbole> getSymboles() {
        return tds;
    }

    public Symbole getSymbole(String nom) {
        for (Symbole symbole : tds) {
            if (symbole.getNom().equals(nom)) {
                return symbole;
            }
        }
        return null;
    }

}
