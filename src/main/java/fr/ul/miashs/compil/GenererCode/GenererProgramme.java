package fr.ul.miashs.compil.GenererCode;
import java.util.ArrayList;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.*;
import fr.ul.miashs.compil.traduction.*;

public class GenererProgramme {
    public static void main(String[] args) {
        // Création d'une TDS d'exemple
        Tds tds = new Tds(new ArrayList<>());
        Generateur gen = new Generateur(tds);
         
        Prog prog = new Prog();
        Fonction fonction = new Fonction("main");
        prog.ajouterUnFils(fonction);
        
        tds.getSymboles().add(new Symbole("main", "void", Categorie.FONCTION));
        System.out.println(gen.generer_programme(prog));
        
    }
    
}
