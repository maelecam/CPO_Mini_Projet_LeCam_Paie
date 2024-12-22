package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

/**
 * Gère le déroulement d'une partie de Mastermind. Coordonne les interactions
 * entre le joueur et le plateau de jeu.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {

    private final PlateauDeJeu plateau;
    private final ArrayList<Character> couleursDisponibles;
    private final int tailleCombinaison;

    /**
     * Crée une nouvelle partie avec les paramètres spécifiés.
     *
     * @param tailleCombinaison Nombre de pions dans la combinaison
     * @param nbToursMax Nombre maximum de tentatives autorisées
     * @param couleursDisponibles Liste des couleurs utilisables dans le jeu
     */
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        this.tailleCombinaison = tailleCombinaison;
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }

    /**
     * Démarre et gère le déroulement de la partie. Boucle principale du jeu qui
     * : - Affiche l'état du plateau - Demande et valide les entrées du joueur -
     * Vérifie les conditions de fin de partie
     */
    public void lancerPartie() {
        try ( Scanner scanner = new Scanner(System.in)) {
            afficherRegles();
            while (true) {
                plateau.afficherPlateau();

                if (plateau.estVictoire()) {
                    terminerPartie(true);
                    break;
                } else if (plateau.estDefaite()) {
                    System.out.println("Voulez-vous voir la solution ? (O/N)");
                    String reponse = scanner.nextLine().toUpperCase();

                    if (reponse.equals("O")) {
                        System.out.println("La solution était : "
                                + ((Combinaison) plateau.getCombinaisonSecrete()).afficherCombinaisonLisible());
                    } else {
                        System.out.println("Au revoir.");
                    }
                    terminerPartie(false);
                    break;
                }

                System.out.println("Entrez une combinaison de " + tailleCombinaison + " couleurs (ex : R B V Y) :");
                String entreeUtilisateur = scanner.nextLine().toUpperCase().replace(" ", "");

                if (entreeUtilisateur.length() != tailleCombinaison) {
                    System.out.println("Erreur : entrez exactement " + tailleCombinaison + " couleurs.");
                    continue;
                }

                Pion[] pionsProposes = new Pion[entreeUtilisateur.length()];
                try {
                    for (int i = 0; i < entreeUtilisateur.length(); i++) {
                        char couleur = entreeUtilisateur.charAt(i);
                        if (!couleursDisponibles.contains(couleur)) {
                            throw new IllegalArgumentException("Couleur invalide : " + couleur);
                        }
                        pionsProposes[i] = new Pion(couleur);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                Combinaison tentative = new Combinaison(pionsProposes);
                plateau.proposerCombinaison(tentative);
            }
        }
    }

    /**
     * Gère la fin de la partie et affiche le message approprié.
     *
     * @param victoire true si le joueur a gagné, false s'il a perdu
     */
    public void terminerPartie(boolean victoire) {
        System.out.println("Fin de la partie !");
        if (victoire) {
            System.out.println("Félicitations ! Vous avez deviné la combinaison secrète !");
        }
    }

    /**
     * Point d'entrée pour démarrer une nouvelle partie avec les paramètres par
     * défaut.
     *
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        List<Character> couleurs = List.of('R', 'B', 'V', 'J', 'O', 'M');
        Partie partie = new Partie(4, 10, couleurs);
        partie.lancerPartie();
    }

    /**
     * Affiche les règles du jeu et les instructions au joueur. Appelé au début
     * de chaque partie.
     */
    private void afficherRegles() {
        System.out.println("Bienvenue au jeu Mastermind !");
        System.out.println("Devinez la combinaison secrète en 10 tours maximum.");
        System.out.println("Utilisez les couleurs : R (Rouge), B (Bleu), V (Vert), J (Jaune), O (Orange), M (Marron)");
    }
}
