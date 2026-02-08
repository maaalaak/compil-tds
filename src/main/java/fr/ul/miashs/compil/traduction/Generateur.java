/**
 * Exercice d'introduction à la génération de code Beta
 * @author Azim Roussanaly
 * Created at 25 févr. 2026
 */
package fr.ul.miashs.compil.traduction;
import java.util.Objects;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.Categorie;
import fr.ul.miashs.compil.tds.Tds;
import fr.ul.miashs.compil.tds.Symbole;
/**
 * Générateur de code pour un arbre d'affectation
 */
public class Generateur {

    private final Tds tds;
    public Generateur(Tds tds) {
        this.tds = tds;
    }
    /**
     * Générer le code pour une affectation
     * @param aff : noeud d'affectation
     * @return code généré
     */
    public String genererAffectation(Affectation aff) {
        StringBuffer code = new StringBuffer();
        code.append(genererExpression(aff.getFilsDroit()));
        code.append("\tPOP(R0)\n");
        Idf var = (Idf) aff.getFilsGauche();
        code.append("\tST(R0, " + var.getValeur() + ")\n");
        return code.toString();
    }
    /**
     * Générer le code pour une expression
     * @param expr : noeud d'expression
     * @return code généré
     */
    public String genererExpression(Noeud expr) {
        StringBuffer code = new StringBuffer();
        switch (expr.getCat()){
            case CONST:
                Const c = (Const) expr;
                code.append("\tCMOVE(R0, " + c.getValeur() + ")\n");
                code.append("\tPUSH(R0)\n");
                break;
            case IDF:
                Idf i = (Idf) expr;
                code.append("\tCMOVE(R0, " + i.getValeur() + ")\n");
                code.append("\tPUSH(R0)\n");
                break;
            case PLUS:
                Plus p = (Plus) expr;
                Noeud gaucheP = p.getFilsGauche();
                Noeud droitP = p.getFilsDroit();
                code.append(genererExpression(gaucheP));
                code.append(genererExpression(droitP));
                code.append("\tPOP(R2)\n");
                code.append("\tPOP(R1)\n");
                code.append("\tADD(R1, R2, R3)\n");
                code.append("\tPUSH(R3)\n");
                break;
            case MOINS:
                Moins m = (Moins) expr;
                Noeud gaucheM = m.getFilsGauche();
                Noeud droitM = m.getFilsDroit();
                code.append(genererExpression(gaucheM));
                code.append(genererExpression(droitM));
                code.append("\tPOP(R2)\n");
                code.append("\tPOP(R1)\n");
                code.append("\tSUB(R1, R2, R3)\n");
                code.append("\tPUSH(R3)\n");
                break;
            case MUL:
                Multiplication mul = (Multiplication) expr;
                Noeud gaucheMul = mul.getFilsGauche();
                Noeud droitMul = mul.getFilsDroit();
                code.append(genererExpression(gaucheMul));
                code.append(genererExpression(droitMul));
                code.append("\tPOP(R2)\n");
                code.append("\tPOP(R1)\n");
                code.append("\tMUL(R1, R2, R3)\n");
                code.append("\tPUSH(R3)\n");
                break;
            case DIV:
                Division div = (Division) expr;
                Noeud gaucheD = div.getFilsGauche();
                Noeud droitD = div.getFilsDroit();
                code.append(genererExpression(gaucheD));
                code.append(genererExpression(droitD));
                code.append("\tPOP(R2)\n");
                code.append("\tPOP(R1)\n");
                code.append("\tDIV(R1, R2, R3)\n");
                code.append("\tPUSH(R3)\n");
                break;
            default:
                break;
        }
        return code.toString();
    }

    //a revoir 
    public String genererData() {
        StringBuffer code = new StringBuffer();
        code.append("  BR(").append(this.tds.getSymboles().get(0).getNom()).append(")\n");
        for (Symbole sym : this.tds.getSymboles()) {
            if (sym.getCategorie().equals("global") && Objects.equals(sym.getType(), "int")) {
                code.append(sym.getNom()).append(": LONG(").append(sym.getValeur()).append(")\n");
            }
        }
        code.append("\n");
        return code.toString();
    }
    public String generer_programme(Prog porg) {
        StringBuffer code = new StringBuffer();
        code.append (".include beta.uasm\n.include intio.uasm\nCMOVE (pile, SP)\n.BR(debut)");
        code.append(genererData());
        code.append("CALL(main)\nHALT");
        for (Noeud fonction : porg.getFils()) {
            code.append(genererFonction((Fonction) fonction));
        }        
        code.append("pile:");
        return code.toString();
    }

    public String genererFonction(Fonction f) {
        StringBuffer code = new StringBuffer();
        code.append(f.getValeur()).append(":\n");
        code.append("PUSH(LP)\n")
            .append("PUSH(BP)\n")
            .append("MOVE(SP, BP)\n")
            .append("ALLOCATE(").append(this.tds.getSymbole(f.getValeur().toString()).getNbVariables()).append(")\n");
        for (Noeud fils : f.getFils()) {
            code.append(genererInstruction(fils));
        }
        code.append("ret_").append(f.getValeur()).append(":\n");
            code.append("  DEALLOCATE(").append(this.tds.getSymbole(f.getValeur().toString()).getNbVariables()).append(")\n")
                    .append("  POP(BP)\n")
                    .append("  POP(LP)\n")
                    .append("  RTN()\n");

        
        return code.toString();
    }

    public String genererEcriture(Ecrire e) {
        StringBuffer code = new StringBuffer();
        code.append(genererExpression(e.getFils()));
        code.append("POP(R0)\n");
        code.append("WRINT\n");
        return code.toString();
    }

    public String genererAppel(Appel a) {
        StringBuffer code = new StringBuffer();
        if(this.tds.getSymbole(a.getValeur().toString()).getCategorie().equals("int")){ {
            code.append("ALLOCATE(1)\n");
        }
        for(Noeud fils : a.getFils()){
            code.append(genererExpression(fils));
        }
        code.append("CALL(").append(a.getValeur().toString()).append(")\n")
          .append("DEALLOCATE(").append(this.tds.getSymbole(a.getValeur().toString()).getNbParametres()).append(")\n");
        }
        return code.toString();
    }
    
    public String genererRetour(Retour r) {
        StringBuffer code = new StringBuffer();
        code.append(genererExpression(r.getFils()));
        code.append("POP(R0)\n");
        if (!r.getFils().isEmpty()) {
            code.append(genererExpression(r.getFils().get(0)));
            int offsetResultat = (2 + this.tds.getSymbole(r.getValeur().toString()).getNbVariables()) * -4;
            code.append("  POP(R0)\n")
                    .append("  PUTFRAME(R0, ").append(offsetResultat).append(")\n");
        }
        code.append("  BR(ret_").append(r.getValeur().toString()).append(")\n");
        return code.toString();
    }


    public String genererInstruction(Noeud instruction) {
        if (instruction instanceof Affectation a) {
            return genererAffectation(a);
        } else if (instruction instanceof Si s) {
            return genererConditionnel(s);
        } else if (instruction instanceof TantQue tantQue) {
            return genererIteration(tantQue);
        } else if (instruction instanceof Appel a) {
            return genererAppel(a);
        } else if (instruction instanceof Retour r) {
            return genererRetour(r);
        } else if (instruction instanceof Ecrire e) {
            return genererEcriture(e);
        } else if (instruction instanceof Bloc b) {
            StringBuilder code = new StringBuilder();
            for (Noeud fils : b.getFils()) {
                code.append(genererInstruction(fils));
            }
            return code.toString();
        }
        return "";
    }
}
