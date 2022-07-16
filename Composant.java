public abstract class Composant {

    private  int count;
    private String nom;

    public Composant(String nom, int count) {
        this.nom = nom;
        this.count = count;
    }

    public abstract void afficher();
    public void ajouter(Composant c) {}

    public String getNom() {return this.nom;}

    public int getCount(){return this.count;}

}