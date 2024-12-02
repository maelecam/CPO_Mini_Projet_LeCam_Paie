package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
    private PlateauDeJeu plateau;
    private ArrayList<Character> couleursDisponibles;
    private int tailleCombinaison;  // Attribut pour la taille de la combinaison

    // Constructeur
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        this.tailleCombinaison = tailleCombinaison; // Initialiser la taille
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }

    // Lancer la partie
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        afficherRegles();
        while (true) {
            // Afficher l'état actuel du plateau
            plateau.afficherPlateau();

            // Vérifier si la partie est terminée
            if (plateau.estVictoire()) {
                terminerPartie(true);
                break;
            } else if (plateau.estDefaite()) {
                terminerPartie(false);
                break;
            }

            // Demander une combinaison au joueur
            System.out.println("Entrez une combinaison de " + tailleCombinaison + " couleurs (ex : R B V Y) :");
            String entreeUtilisateur = scanner.nextLine().toUpperCase().replace(" ", "");

            // Valider et convertir l'entrée utilisateur
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

            // Proposer la combinaison au plateau
            Combinaison tentative = new Combinaison(pionsProposes);
            plateau.proposerCombinaison(tentative);
        }

        scanner.close();
    }

    // Terminer la partie
    public void terminerPartie(boolean victoire) {
        System.out.println("Fin de la partie !");
        if (victoire) {
            System.out.println("Félicitations ! Vous avez deviné la combinaison secrète !");
        } else {
            System.out.println("Vous avez perdu. La combinaison secrète était :");
            System.out.println(plateau.getCombinaisonSecrete().afficherCombinaisonLisible());
        }
    }

    public static void main(String[] args) {
        // Exemple de couleurs disponibles
        List<Character> couleurs = List.of('R', 'B', 'V', 'J', 'O', 'M');

        // Lancer une partie avec 4 pions, 10 tours maximum et les couleurs données
        Partie partie = new Partie(4, 10, couleurs);
        partie.lancerPartie();
    }
}