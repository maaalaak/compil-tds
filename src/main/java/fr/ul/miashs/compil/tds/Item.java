package fr.ul.miashs.compil.tds;

public class Item {

    private String nom;
    private String type;
    private String categorie;
    private String scope;

    public Item(String nom, String type, String categorie, String scope) {
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.scope = scope;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return nom + " - " + type + " - " + categorie + " - " + scope;
    }
}
