package fr.ul.miashs.compil.tds;

public class TdsExemples {

    public static Tds prog1() {
        Tds tds = new Tds();
        tds.ajouter(new Item("main", "void", "fonction"));
        return tds;
    }

    public static void main(String[] args) {
        Tds tds = prog1();
        tds.afficher();
    }
}
