/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.List;
import java.util.Random;

/**
 *
 * @author 33604
 */
public class Combinaison {

    static Combinaison genererAleatoire(String[] couleursPossibles, int tailleCombinaison) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Pion[] pions; 
   
    public Combinaison(List<Pion> pionsProposes) {
        pions = new Pion[4];
    }
    
    public Combinaison(Pion[] pions) {
        if (pions.length != 4) {
            throw new IllegalArgumentException("Une combinaison doit contenir exactement 4 pions");
        }
        this.pions = pions;
    }
    
    public static Combinaison genererCombinaisonAleatoire() {
        char[] couleurs = {'R', 'B', 'V', 'J', 'W', 'N'}; 
        Random random = new Random();
        Pion[] pionAleatoires = new Pion[4];
        for (int i = 0; i < 4; i++) {
            pionAleatoires[i] = new Pion(couleurs[random.nextInt(couleurs.length)]);
        }
        return new Combinaison(pionAleatoires);
    }

    
    public int calculerPionsBienPlaces(Combinaison autreCombinaison) {
        int bienPlaces = 0;
        for (int i = 0; i < 4; i++) {
            if (this.pions[i].getCouleur().equals(autreCombinaison.pions[i].getCouleur())) {
                bienPlaces++;
            }
        }
        return bienPlaces;
    }

    public int calculerPionsMalPlaces(Combinaison autreCombinaison) {
        int[] frequenceCette = new int[6];
        int[] frequenceAutre = new int[6];

        for (int i = 0; i < 4; i++) {
            if (!this.pions[i].getCouleur().equals(autreCombinaison.pions[i].getCouleur())) {
                frequenceCette[indexCouleur(this.pions[i].getCouleur())]++;
                frequenceAutre[indexCouleur(autreCombinaison.pions[i].getCouleur())]++;
            }
        }

        int malPlaces = 0;
        for (int i = 0; i < 6; i++) {
            malPlaces += Math.min(frequenceCette[i], frequenceAutre[i]);
        }
        return malPlaces;
    }

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
            case 'W':
                return 4;
            case 'N':
                return 5;
            default:
                throw new IllegalArgumentException("Couleur invalide");
        }
    }

    public String afficherCombinaisonLisible() {
        StringBuilder sb = new StringBuilder();
        for (Pion pion : pions) {
            sb.append(pion.getCouleur()).append(" ");
        }
        return sb.toString().trim();
    }

    public Pion[] getPions() {
        return pions;
    }

    public static void main(String[] args) {

        Combinaison combinaison1 = genererCombinaisonAleatoire();
        System.out.println("Combinaison 1 : " + combinaison1.afficherCombinaisonLisible());

        Combinaison combinaison2 = genererCombinaisonAleatoire();
        System.out.println("Combinaison 2 : " + combinaison2.afficherCombinaisonLisible());

        System.out.println("Pions bien placés : " + combinaison1.calculerPionsBienPlaces(combinaison2));
        System.out.println("Pions mal placés : " + combinaison1.calculerPionsMalPlaces(combinaison2));
    }

    int compterPionsBienPlaces(Combinaison proposition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int compterPionsMalPlaces(Combinaison proposition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String genererIndices(Combinaison tentative) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
