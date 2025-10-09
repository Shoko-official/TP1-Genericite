package TP1;

public abstract class Media {
    private String titre;
    private int anneePublication;

    public Media() {
        this.titre = "";
        this.anneePublication = 0;
    }

    public Media(String titre, int anneePublication) {
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public abstract String getDescription();

    public void afficherDetails() {
        System.out.println(getDescription());
    }

    @Override
    public String toString() {
        return titre + " (" + anneePublication + ")";
    }
}