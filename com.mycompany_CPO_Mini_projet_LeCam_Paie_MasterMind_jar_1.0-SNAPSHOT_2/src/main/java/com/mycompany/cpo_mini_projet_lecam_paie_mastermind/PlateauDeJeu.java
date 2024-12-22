package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente le plateau de jeu du Mastermind. Gère l'état du jeu, les
 * tentatives des joueurs et les réponses associées.
 */
public class PlateauDeJeu {

    /**
     * Crée un nouveau plateau de jeu avec une combinaison secrète et un nombre
     * maximum de tours.
     *
     * @param combinaisonSecrete La combinaison à deviner
     * @param nbToursMax Le nombre maximum de tentatives autorisées
     * @throws IllegalArgumentException si combinaisonSecrete est null ou si
     * nbToursMax est négatif ou nul
     */
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

    /**
     * Ajoute une nouvelle tentative du joueur et calcule la réponse
     * correspondante.
     *
     * @param tentative La combinaison proposée par le joueur
     * @throws IllegalArgumentException si la tentative est null
     */
    public void proposerCombinaison(Combinaison tentative) {
        if (tentative == null) {
            throw new IllegalArgumentException("Tentative ne peut pas être nulle");
        }
        tentatives.add(tentative);
        String indices = calculerIndices(tentative);
        reponses.add(indices);
    }

    /**
     * Calcule les indices (pions noirs et blancs) pour une tentative.
     *
     * @param tentative La combinaison à évaluer
     * @return Une chaîne décrivant le nombre de pions noirs (bien placés) et
     * blancs (mal placés)
     */
    private String calculerIndices(Combinaison tentative) {
        int noirs = combinaisonSecrete.calculerPionsBienPlaces(tentative);
        int blancs = combinaisonSecrete.calculerPionsMalPlaces(tentative);
        return noirs + " noirs, " + blancs + " blancs";
    }

    /**
     * Affiche l'état actuel du plateau avec toutes les tentatives et leurs
     * réponses.
     */
    public void afficherPlateau() {
        System.out.println("État du plateau :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tentative " + (i + 1) + ": "
                    + tentatives.get(i).afficherCombinaisonLisible()
                    + " -> Réponse: " + reponses.get(i));
        }
    }

    /**
     * Vérifie si le joueur a gagné en trouvant la combinaison secrète.
     *
     * @return true si la dernière tentative correspond à la combinaison
     * secrète, false sinon
     */
    public boolean estVictoire() {
        if (tentatives.isEmpty()) {
            return false;
        }
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return combinaisonSecrete.calculerPionsBienPlaces(derniereTentative) == combinaisonSecrete.getPions().length;
    }

    /**
     * Vérifie si le joueur a perdu en épuisant toutes ses tentatives.
     *
     * @return true si le nombre maximum de tentatives est atteint sans
     * victoire, false sinon
     */
    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }

    /**
     * Retourne la combinaison secrète utilisée dans la partie.
     *
     * @return La combinaison secrète.
     */
    public Combinaison getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    /**
     * Affiche l'historique des tentatives et des réponses associées dans la
     * console.
     */
    public void afficherHistorique() {
        System.out.println("Historique des tentatives :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tour " + (i + 1) + ": " + tentatives.get(i).afficherCombinaisonLisible()
                    + " - " + reponses.get(i));
        }
    }

    /**
     * Ajoute une tentative avec ses résultats au plateau.
     *
     * @param proposition La combinaison proposée
     * @param noirs Le nombre de pions bien placés
     * @param blancs Le nombre de pions mal placés
     * @throws IllegalArgumentException si la proposition est null
     */
    public void ajouterTentative(Combinaison proposition, int noirs, int blancs) {
        if (proposition == null) {
            throw new IllegalArgumentException("Proposition ne peut pas être nulle");
        }
        tentatives.add(proposition);
        reponses.add(noirs + " noirs, " + blancs + " blancs");
    }
}
