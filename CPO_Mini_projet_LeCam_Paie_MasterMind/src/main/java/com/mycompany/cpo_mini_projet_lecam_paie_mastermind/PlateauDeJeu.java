/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

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
        this.tentatives = tentatives;
        this.reponses = reponses;
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
        if (tentatives.size() < nbToursMax) {
            tentatives.add(tentative);

            // Calcul des indices directement
            int noirs = combinaisonSecrete.compterBienPlaces(tentative);
            int blancs = combinaisonSecrete.compterMalPlaces(tentative);

            // Ajout de la réponse au format texte
            reponses.add(noirs + " noirs, " + blancs + " blancs");
        } else {
            System.out.println("Nombre maximal de tours atteint !");
        }
    }
    public void AfficherPlateau() {

    }
    public void estVictoire() {

    }

    public void estDefaite() {

    }

    @Override
    public String toString() {
        return "PlateauDeJeu{" + "combinaisonSecrete=" + combinaisonSecrete + ", tentatives=" + tentatives + ", reponses=" + reponses + ", nbToursMax=" + nbToursMax + '}';
    }
   
}
