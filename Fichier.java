public class Fichier extends Composant {

    public Fichier(String nom, int count) {
        super(nom, count);
    }

    public void afficher() {
        int count = getCount();
        for (int i = 0; i < count; i++) {
            System.out.print("│\t");
        }
        System.out.println(getNom());
    }
}
