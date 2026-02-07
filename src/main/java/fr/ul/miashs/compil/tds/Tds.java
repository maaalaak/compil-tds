package fr.ul.miashs.compil.tds;

import java.util.ArrayList;
import java.util.List;

public class Tds {

    private List<Item> items;

    public Tds() {
        items = new ArrayList<>();
    }
public void ajouter(Item item) {
    items.add(item);
}
public void afficher() {
    for (Item item : items) {
        System.out.println(item.getNom() + " - " + item.getCategorie());
    }
}


}
