/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Combinaison {
   private Pion[] elements;
   
   public Combinaison(Pion[] elements) {
       if (elements == null || elements.length != 4) {
           throw new IllegalArgumentException("Une combinaison doit contenir exactement 4 pions");
       }
       this.elements = elements;
   }
   
   public static Combinaison genererCombinaisonAleatoire() {
       List<Character> couleursDisponibles = Arrays.asList('R', 'B', 'V', 'J', 'W', 'N');
       Random random = new Random();
       Pion[] pions = new Pion[4];
       
       for (int i = 0; i < 4; i++) {
           char couleurAleatoire = couleursDisponibles.get(random.nextInt(couleursDisponibles.size()));
           pions[i] = new Pion(couleurAleatoire);
       }
       
       return new Combinaison(pions);
   }
   
   public String afficherCombinaisonLisible() {
       StringBuilder sb = new StringBuilder();
       for (Pion p : elements) {
           sb.append(p.getCouleur()).append(" ");
       }
       return sb.toString().trim();
   }
   
   public Pion[] getPions() {
       return elements;
   }
   
   public int calculerPionsBienPlaces(Combinaison autreCombinaison) {
       int bienPlaces = 0;
       for (int i = 0; i < 4; i++) {
           if (this.elements[i].getCouleur().equals(autreCombinaison.elements[i].getCouleur())) {
               bienPlaces++;
           }
       }
       return bienPlaces;
   }
   
   public int calculerPionsMalPlaces(Combinaison autreCombinaison) {
       int[] frequenceCette = new int[6];
       int[] frequenceAutre = new int[6];
       for (int i = 0; i < 4; i++) {
           if (!this.elements[i].getCouleur().equals(autreCombinaison.elements[i].getCouleur())) {
               frequenceCette[indexCouleur(this.elements[i].getCouleur())]++;
               frequenceAutre[indexCouleur(autreCombinaison.elements[i].getCouleur())]++;
           }
       }
       int malPlaces = 0;
       for (int i = 0; i < 6; i++) {
           malPlaces += Math.min(frequenceCette[i], frequenceAutre[i]);
       }
       return malPlaces;
   }
   
   private int indexCouleur(Character couleur) {
       return "RBVJWN".indexOf(couleur);
   }
}

