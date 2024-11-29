/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 33604
 */
public class PlateauDeJeu {

    private Combinaison combinaisonSecrete;
    private List<Combinaison> tentatives;
    private List<String> reponses;
    private int nbToursMax;

    public PlateauDeJeu(Combinaison combinaisonSecrete, List<Combinaison> tentatives, List<String> reponses, int nbToursMax) {
        this.combinaisonSecrete = combinaisonSecrete;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.nbToursMax = nbToursMax;
    }

    public void SetcombinaisonSecrete(Combinaison combinaisonSecrete) {
        this.combinaisonSecrete = combinaisonSecrete;
    }

    public List<Combinaison> getTentatives() {
        return tentatives;
    }

    public List<String> getReponses() {
        return reponses;
    }

    public int getNbToursMax() {
        return nbToursMax;
    }

    public void setNbToursMax(int nbToursMax) {
        this.nbToursMax = nbToursMax;
    }

    public void proposerCombinaison(Combinaison tentative) {
        if (tentatives.size() >= nbToursMax) {
            System.out.println("Nombre maximal de tours atteint !");
            return;
        }

        // Ajouter la tentative à l'historique
        tentatives.add(tentative);

        // Générer et stocker les indices pour la tentative
        String indice = combinaisonSecrete.genererIndices(tentative);
        reponses.add(indice);

        System.out.println("Tentative ajoutée : " + tentative + " | Indices : " + indice);
    }

    public boolean estVictoire() {
        if (!tentatives.isEmpty()) {
            Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
            return combinaisonSecrete.equals(derniereTentative);
        }
        return false;
    }

    // Méthode pour vérifier si le joueur a perdu
    public boolean estDefaite() {
        return !estVictoire() && tentatives.size() >= nbToursMax;
    }
}
