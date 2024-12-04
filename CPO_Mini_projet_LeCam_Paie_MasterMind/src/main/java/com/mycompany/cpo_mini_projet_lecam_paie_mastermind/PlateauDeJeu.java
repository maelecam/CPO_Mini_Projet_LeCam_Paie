package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;
import java.util.List;

public class PlateauDeJeu {
    private Combinaison combinaisonSecrete;
    private List<Combinaison> tentatives;
    private List<String> reponses;
    private int nbToursMax;

    public PlateauDeJeu(Combinaison combinaisonSecrete, int nbToursMax) {
        if (combinaisonSecrete == null || nbToursMax <= 0) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
        this.combinaisonSecrete = combinaisonSecrete;
        this.nbToursMax = nbToursMax;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
    }

    public void proposerCombinaison(Combinaison tentative) {
        if (tentative == null) {
            throw new IllegalArgumentException("Tentative ne peut pas être nulle");
        }
        tentatives.add(tentative);
        String indices = calculerIndices(tentative);
        reponses.add(indices);
    }

    private String calculerIndices(Combinaison tentative) {
        int noirs = combinaisonSecrete.calculerPionsBienPlaces(tentative);
        int blancs = combinaisonSecrete.calculerPionsMalPlaces(tentative);
        return noirs + " noirs, " + blancs + " blancs";
    }

    public void afficherPlateau() {
        System.out.println("État du plateau :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tentative " + (i + 1) + ": " 
                    + tentatives.get(i).afficherCombinaisonLisible() 
                    + " -> Réponse: " + reponses.get(i));
        }
    }

    public boolean estVictoire() {
        if (tentatives.isEmpty()) {
            return false;
        }
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return combinaisonSecrete.calculerPionsBienPlaces(derniereTentative) == combinaisonSecrete.getPions().length;
    }

    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }

    public Combinaison getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public void afficherHistorique() {
        System.out.println("Historique des tentatives :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tour " + (i + 1) + ": " + tentatives.get(i).afficherCombinaisonLisible() 
                                + " - " + reponses.get(i));
        }
    }

    public void ajouterTentative(Combinaison proposition, int noirs, int blancs) {
        if (proposition == null) {
            throw new IllegalArgumentException("Proposition ne peut pas être nulle");
        }
        tentatives.add(proposition);
        reponses.add(noirs + " noirs, " + blancs + " blancs");
    }
}


