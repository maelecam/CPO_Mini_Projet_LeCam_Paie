package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;

public class PlateauDeJeu {
    private Combinaison combinaisonSecrete;
    private ArrayList<Combinaison> tentatives;
    private ArrayList<String> reponses;
    private int nbToursMax;

    // Constructeur
    public PlateauDeJeu(Combinaison combinaisonSecrete, int nbToursMax) {
        this.combinaisonSecrete = combinaisonSecrete;
        this.nbToursMax = nbToursMax;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
    }

    // Ajouter une tentative et générer les indices
    public void proposerCombinaison(Combinaison tentative) {
        tentatives.add(tentative);
        String indices = calculerIndices(tentative);
        reponses.add(indices);
    }
    

    // Calcul des indices pour une tentative
    private String calculerIndices(Combinaison tentative) {
        int noirs = combinaisonSecrete.calculerPionsBienPlaces(tentative);
        int blancs = combinaisonSecrete.calculerPionsMalPlaces(tentative);
        return noirs + " noirs, " + blancs + " blancs";
    }

    // Afficher l'état actuel du plateau
    public void afficherPlateau() {
        System.out.println("État du plateau :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tentative " + (i + 1) + ": " 
                    + tentatives.get(i).afficherCombinaisonLisible() 
                    + " -> Réponse: " + reponses.get(i));
        }
    }

    // Vérifier si le joueur a gagné
    public boolean estVictoire() {
        if (tentatives.isEmpty()) {
            return false;
        }
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return combinaisonSecrete.calculerPionsBienPlaces(derniereTentative) == combinaisonSecrete.getPions().length;
    }

    // Vérifier si le joueur a perdu
    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }

    // Main pour les tests
    public static void main(String[] args) {
        // Générer une combinaison secrète
        Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
        System.out.println("Combinaison secrète : " + combinaisonSecrete.afficherCombinaisonLisible());

        // Créer un plateau avec un maximum de 10 tours
        PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 10);

        // Ajouter quelques tentatives
        plateau.proposerCombinaison(new Combinaison(new Pion[]{
            new Pion('R'), new Pion('B'), new Pion('V'), new Pion('J')
        }));
        plateau.proposerCombinaison(new Combinaison(new Pion[]{
            new Pion('J'), new Pion('O'), new Pion('M'), new Pion('B')
        }));

        // Afficher le plateau
        plateau.afficherPlateau();

        // Vérifier victoire ou défaite
        if (plateau.estVictoire()) {
            System.out.println("Victoire ! Vous avez deviné la combinaison secrète.");
        } else if (plateau.estDefaite()) {
            System.out.println("Défaite ! Vous avez épuisé vos tours.");
        } else {
            System.out.println("La partie continue.");
        }
    }
}
