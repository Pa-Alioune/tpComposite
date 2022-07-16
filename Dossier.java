import java.util.ArrayList;

public class Dossier extends Composant {

    ArrayList<Composant>  composants= new ArrayList<Composant>();

    public Dossier(String nom, int count) {
        super(nom, count);
    }

    public void ajouter(Composant c) {
        composants.add(c);
    }

    public void afficher() {

        int count = getCount();
        for (int i = 0; i < count; i++) {
            System.out.print("│\t");
        } 
        System.out.println("├───" + getNom());
        for (Composant c : composants) {
            c.afficher();
        }
    }
}

