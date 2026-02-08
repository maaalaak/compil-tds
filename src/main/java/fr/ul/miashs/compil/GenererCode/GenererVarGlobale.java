package fr.ul.miashs.compil.GenererCode;

import java.util.ArrayList;

import fr.ul.miashs.compil.arbre.Fonction;
import fr.ul.miashs.compil.arbre.Prog;
import fr.ul.miashs.compil.tds.Categorie;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.Tds;
import fr.ul.miashs.compil.traduction.Generateur;

public class GenererVarGlobale {
    public static void main(String[] args) {
        Tds tds = new Tds(new ArrayList<>());
        Generateur generateur = new Generateur(tds);
        Prog prog = new Prog();
        Fonction fonction = new Fonction("main");
        prog.ajouterUnFils(fonction);

        tds.getSymboles().add(new Symbole("main","void", Categorie.FONCTION));
        tds.getSymboles().add(new Symbole("i","int" ,Categorie.GLOBAL, 10));
        tds.getSymboles().add(new Symbole("j","int" ,Categorie.GLOBAL, 20));
        tds.getSymboles().add(new Symbole("k","int", Categorie.GLOBAL));
        tds.getSymboles().add(new Symbole("l","int", Categorie.GLOBAL));
        System.out.println(generateur.generer_programme(prog));


    }
    
}
