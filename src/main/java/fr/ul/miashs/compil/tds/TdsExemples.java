package fr.ul.miashs.compil.tds;

public class TdsExemples  {

    // Programme 1
    public static Tds prog1() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));

        return tds;
    }

    // Programme 2
    public static Tds prog2() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));

        tds.ajouter(new Item("i", "int", "global", "global"));
        tds.ajouter(new Item("j", "int", "global", "global"));
        tds.ajouter(new Item("k", "int", "global", "global"));
        tds.ajouter(new Item("l", "int", "global", "global"));

        return tds;
    }

    // Programme 3
    public static Tds prog3() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));

        tds.ajouter(new Item("z", "int", "global", "global"));
        tds.ajouter(new Item("a", "int", "global", "global"));
        tds.ajouter(new Item("b", "int", "global", "global"));

        return tds;
    }

    // Programme 4
    public static Tds prog4() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));
        tds.ajouter(new Item("res", "int", "global", "global"));

        return tds;
    }

    // Programme 5
    public static Tds prog5() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));
        tds.ajouter(new Item("res", "int", "global", "global"));

        return tds;
    }

    // Programme 6
    public static Tds prog6() {

        Tds tds = new Tds();

        tds.ajouter(new Item("main", "void", "fonction", "global"));

        tds.ajouter(new Item("f", "int", "fonction", "global"));

        tds.ajouter(new Item("a", "int", "param", "f"));
        tds.ajouter(new Item("b", "int", "param", "f"));
        tds.ajouter(new Item("res", "int", "local", "f"));

        return tds;
    }
    public static Tds prog7() {
    return prog6();
}
public static Tds prog8() {
    return prog6();
}


}