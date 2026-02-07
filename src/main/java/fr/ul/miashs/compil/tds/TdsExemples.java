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
    public static Tds prog2() {

    Tds tds = new Tds();

    tds.ajouter(new Item("main", "void", "fonction"));

    tds.ajouter(new Item("i", "int", "global"));
    tds.ajouter(new Item("j", "int", "global"));
    tds.ajouter(new Item("k", "int", "global"));
    tds.ajouter(new Item("l", "int", "global"));

    return tds;
}
public static Tds prog3() {

    Tds tds = new Tds();

    tds.ajouter(new Item("main", "void", "fonction"));

    tds.ajouter(new Item("z", "int", "global"));
    tds.ajouter(new Item("a", "int", "global"));
    tds.ajouter(new Item("b", "int", "global"));

    return tds;
}
public static Tds prog4() {

    Tds tds = new Tds();

    tds.ajouter(new Item("main", "void", "fonction"));

    tds.ajouter(new Item("res", "int", "global"));

    return tds;
}

}
