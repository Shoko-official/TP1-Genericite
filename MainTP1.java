package TP1;

import java.util.*;
import java.util.function.Predicate;

public class MainTP1 {

    public static final String RESET = "\033[0m";
    public static final String BLEU_CLAIR = "\033[94m";
    public static final String VERT_CLAIR = "\033[92m";

    // --- Méthodes génériques ---
    public static <T> void afficherListe(List<T> liste) {
        for (T element : liste) {
            System.out.println(element);
        }
    }

    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T element : liste) {
            if (critere.test(element)) {
                resultat.add(element);
            }
        }
        return resultat;
    }

    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {
        // --- Étape 3 : Collections ---
        List<Media> medias = new ArrayList<>();
        Set<Membre> membres = new HashSet<>();
        Map<Membre, List<Media>> emprunts = new HashMap<>();

        // --- Création de médias ---
        medias.add(new Livre("Le JAVA pour les NULS", 2019, "John Doe", 1232));
        medias.add(new Livre("1984", 1949, "George Orwell", 328));
        medias.add(new CD("Thriller", 1982, "Michael Jackson", 42));
        medias.add(new CD("Symphonie", 1808, "Ludwig Beethoven", 75));

        // --- Création de membres ---
        Membre alice = new Membre("Jamy", 1);
        Membre bob = new Membre("Bob", 2);
        membres.add(alice);
        membres.add(bob);

        // --- Gestion des emprunts ---
        alice.emprunterMedia(medias.get(0));
        alice.emprunterMedia(medias.get(2));
        bob.emprunterMedia(medias.get(1));
        emprunts.put(alice, alice.getMediasEmpruntes());
        emprunts.put(bob, bob.getMediasEmpruntes());

        // --- Affichage complet ---
        System.out.println(BLEU_CLAIR + "\nTous les médias :" + RESET);
        afficherListe(medias);

        // --- Filtrage par année ---
        System.out.println(BLEU_CLAIR + "\nMédias publiés après 1950 :" + RESET);
        List<Media> mediasRecents = filtrer(medias, m -> m.getAnneePublication() > 1950);
        afficherListe(mediasRecents);

        // --- Tri par année décroissante puis titre ---
        System.out.println(BLEU_CLAIR + "\nTri par année décroissante puis par titre :" + RESET);
        medias.sort(Comparator
                .comparing(Media::getAnneePublication).reversed()
                .thenComparing(Media::getTitre));
        afficherListe(medias);

        // --- Comparateur spécifique Livre ---
        System.out.println(BLEU_CLAIR + "\nTri des livres par auteur puis titre :" + RESET);
        List<Livre> livres = new ArrayList<>(filtrer(medias, m -> m instanceof Livre)
                .stream().map(m -> (Livre) m).toList());
        livres.sort(Comparator
                .comparing(Livre::getAuteur)
                .thenComparing(Livre::getTitre));
        afficherListe(livres);


        // --- Test polymorphisme ---
        System.out.println(BLEU_CLAIR + "\nTest du polymorphisme :" + RESET);
        for (Media m : medias) {
            m.afficherDetails();
        }

        // --- Test interface Empruntable ---
        System.out.println(BLEU_CLAIR + "\nTest de l'interface Empruntable :" + RESET);
        for (Media m : medias) {
            if (m instanceof Empruntable e) {
                e.emprunter();
            }
        }

        // --- Copie de collection ---
        System.out.println(BLEU_CLAIR + "\nCopie de la collection de médias :" + RESET);
        List<Media> copieMedias = new ArrayList<>();
        copierCollection(medias, copieMedias);
        afficherListe(copieMedias);

        // --- Médias empruntés sans doublons ---
        System.out.println(BLEU_CLAIR + "\nMédias empruntés (sans doublons) :" + RESET);
        Set<Media> tousLesEmprunts = new HashSet<>();
        for (List<Media> liste : emprunts.values()) {
            tousLesEmprunts.addAll(liste);
        }
        afficherListe(new ArrayList<>(tousLesEmprunts));

    }
}



// source utilisée (pour comprendre le fonctionnement de la méthode .sort) : https://stackoverflow.com/questions/5393254/java-comparator-class-to-sort-arrays