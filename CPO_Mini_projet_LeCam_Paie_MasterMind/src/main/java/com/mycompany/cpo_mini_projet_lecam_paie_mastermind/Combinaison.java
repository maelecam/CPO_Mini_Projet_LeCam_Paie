/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.Random;

/**
 *
 * @author 33604
 */
public class Combinaison {
    private Pion[] pions; // initialisation du tableau
    // Constructeur par défaut
    public Combinaison() {
        pions = new Pion[4];
    }
    // Constructeur avec des pions spécifiques
    public Combinaison(Pion[] pions) {
        if (pions.length != 4) {
            throw new IllegalArgumentException("Une combinaison doit contenir exactement 4 pions");
        }
        this.pions = pions;
    }
    // Générer une combinaison aléatoire
    public static Combinaison genererCombinaisonAleatoire() {
        char[] couleurs = {'R', 'B', 'V', 'J', 'O', 'M'}; // Rouge, Bleu, Vert, Jaune, Orange, Magenta
        Random random = new Random();
        Pion[] pionAleatoires = new Pion[4];
        for (int i = 0; i < 4; i++) {
            pionAleatoires[i] = new Pion(couleurs[random.nextInt(couleurs.length)]);
        }
        return new Combinaison(pionAleatoires);
    }
    // Calculer les pions bien placés (noirs)
    public int calculerPionsBienPlaces(Combinaison autreCombinaison) {
        int bienPlaces = 0;
        for (int i = 0; i < 4; i++) {
            if (this.pions[i].getCouleur().equals(autreCombinaison.pions[i].getCouleur())) {
                bienPlaces++;
            }
        }
        return bienPlaces;
    }
    // Calculer les pions de bonne couleur mais mal placés (blancs)
    public int calculerPionsMalPlaces(Combinaison autreCombinaison) {
        int[] frequenceCette = new int[6];
        int[] frequenceAutre = new int[6];
        // Compter les fréquences pour chaque combinaison
        for (int i = 0; i < 4; i++) {
            if (!this.pions[i].getCouleur().equals(autreCombinaison.pions[i].getCouleur())) {
                frequenceCette[indexCouleur(this.pions[i].getCouleur())]++;
                frequenceAutre[indexCouleur(autreCombinaison.pions[i].getCouleur())]++;
            }
        }
        // Calculer les pions mal placés
        int malPlaces = 0;
        for (int i = 0; i < 6; i++) {
            malPlaces += Math.min(frequenceCette[i], frequenceAutre[i]);
        }
        return malPlaces;
    }
    // Méthode utilitaire pour obtenir l'index d'une couleur
    private int indexCouleur(Character couleur) {
        switch (couleur) {
            case 'R':
                return 0;
            case 'B':
                return 1;
            case 'V':
                return 2;
            case 'J':
                return 3;
            case 'O':
                return 4;
            case 'M':
                return 5;
            default:
                throw new IllegalArgumentException("Couleur invalide");
        }
    }
    // Afficher la combinaison
    public String afficherCombinaisonLisible() {
        StringBuilder sb = new StringBuilder();
        for (Pion pion : pions) {
            sb.append(pion.getCouleur()).append(" ");
        }
        return sb.toString().trim();
    }
    // Getter pour les pions
    public Pion[] getPions() {
        return pions;
    }
    // Méthode main de test
    public static void main(String[] args) {
        // Générer une combinaison aléatoire
        Combinaison combinaison1 = genererCombinaisonAleatoire();
        System.out.println("Combinaison 1 : " + combinaison1.afficherCombinaisonLisible());
        // Générer une deuxième combinaison pour test
        Combinaison combinaison2 = genererCombinaisonAleatoire();
        System.out.println("Combinaison 2 : " + combinaison2.afficherCombinaisonLisible());
        // Tester les pions bien et mal placés
        System.out.println("Pions bien placés : " + combinaison1.calculerPionsBienPlaces(combinaison2));
        System.out.println("Pions mal placés : " + combinaison1.calculerPionsMalPlaces(combinaison2));
    }
}
