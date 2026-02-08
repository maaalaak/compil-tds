package fr.ul.miashs.compil.GenererCode;

import java.util.ArrayList;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.*;
import fr.ul.miashs.compil.traduction.Generateur;

public class GenererRecursivite {
    public static void main(String[] args) {

        Tds tds = new Tds(new ArrayList<>());
        Generateur generateur = new Generateur(tds);

        Prog prog = new Prog();

        Fonction f = new Fonction("f");
        Si si = new Si();
        InferieurEgal inf = new InferieurEgal();
        Idf a1 = new Idf("a");
        Const c0 = new Const(0);

        Bloc blocSi = new Bloc();
        Retour retour0 = new Retour("f");
        Const c02 = new Const(0);

        Retour retourFinal = new Retour("f");
        Plus plus = new Plus();
        Idf a2 = new Idf("a");
        Appel appelRec = new Appel("f");
        Moins moins = new Moins();
        Idf a3 = new Idf("a");
        Const c1 = new Const(1);

        Fonction main = new Fonction("main");
        Ecrire ecrire = new Ecrire();
        Appel appelMain = new Appel("f");
        Const c5 = new Const(5);

        prog.ajouterUnFils(f);

        f.ajouterUnFils(si);
        si.ajouterUnFils(inf);
        inf.setFilsGauche(a1);
        inf.setFilsDroit(c0);

        si.ajouterUnFils(blocSi);
        blocSi.ajouterUnFils(retour0);
        retour0.setLeFils(c02);

        f.ajouterUnFils(retourFinal);
        retourFinal.setLeFils(plus);
        plus.setFilsGauche(a2);
        plus.setFilsDroit(appelRec);

        appelRec.ajouterUnFils(moins);
        moins.setFilsGauche(a3);
        moins.setFilsDroit(c1);

        prog.ajouterUnFils(main);
        main.ajouterUnFils(ecrire);
        ecrire.ajouterUnFils(appelMain);
        appelMain.ajouterUnFils(c5);

        tds.getSymboles().add(new Symbole("main", "void", Categorie.FONCTION));
        tds.getSymboles().add(new Symbole("f", "int", Categorie.FONCTION, 0, 1, 0, 0, null));
        tds.getSymboles().add(new Symbole("a", "int", Categorie.PARAMETRE, 0, 0, 0, 0, "f"));

        System.out.println(generateur.generer_programme(prog));
    }
}
