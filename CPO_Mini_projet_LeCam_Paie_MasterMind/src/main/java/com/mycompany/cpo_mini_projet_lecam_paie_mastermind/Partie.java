/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 33604
 */
public class Partie {
private final Combinaison combinaisonSecrete;
    private final PlateauDeJeu plateauDeJeu;
    private final int maxTours;

    public Partie(int maxTours, String[] couleursPossibles, int tailleCombinaison) {
        this.maxTours = maxTours;
        this.combinaisonSecrete = Combinaison.genererAleatoire(couleursPossibles, tailleCombinaison);
        this.plateauDeJeu = new PlateauDeJeu();
    }

    public void jouer() {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("Bienvenue dans le jeu MasterMind !");
        System.out.println("Devinez la combinaison secrète. Les couleurs possibles sont : Rouge, Bleu, Vert, Jaune, Noir, Blanc.");
        System.out.println("Chaque combinaison contient 4 couleurs, et vous avez " + maxTours + " tours pour deviner.");

        boolean victoire = false;

        for (int tour = 1; tour <= maxTours; tour++) {
            System.out.println("\nTour " + tour + " : Entrez votre combinaison (ex: Rouge Bleu Vert Jaune) :");
            String entree = scanner.nextLine();
            String[] couleursProposees = entree.split(" ");
            List<Pion> pionsProposes = new ArrayList<>();

            for (String couleur : couleursProposees) {
                pionsProposes.add(new Pion(couleur));
            }

            Combinaison proposition = new Combinaison(pionsProposes);
            int noirs = combinaisonSecrete.compterPionsBienPlaces(proposition);
            int blancs = combinaisonSecrete.compterPionsMalPlaces(proposition);

            plateauDeJeu.ajouterTentative(proposition, noirs, blancs);

            System.out.println("Résultat : " + noirs + " bien placés (noirs), " + blancs + " mal placés (blancs).");
            System.out.println("Historique des tentatives :");
            plateauDeJeu.afficherHistorique();

            if (noirs == combinaisonSecrete.toString().split(" ").length) {
                victoire = true;
                break;
            }
        }

        if (victoire) {
            System.out.println("\nFélicitations ! Vous avez deviné la combinaison secrète : " + combinaisonSecrete);
        } else {
            System.out.println("\nDommage ! Vous avez épuisé vos tentatives. La combinaison secrète était : " + combinaisonSecrete);
        }
    }
    }
}
// pour commit