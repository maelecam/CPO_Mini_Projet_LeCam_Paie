/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Représente une combinaison de pions dans le jeu Mastermind. Une combinaison
 * est constituée d'un tableau de 4 pions de différentes couleurs. Les couleurs
 * possibles sont : R (Rouge), B (Bleu), V (Vert), J (Jaune), W (Blanc), N
 * (Noir).
 */
public class Combinaison {

    private Pion[] elements;

    /**
     * Construit une nouvelle combinaison à partir d'un tableau de pions.
     *
     * @param elements Tableau de 4 pions formant la combinaison
     * @throws IllegalArgumentException si le tableau est null ou ne contient
     * pas exactement 4 pions
     */
    public Combinaison(Pion[] elements) {
        if (elements == null || elements.length != 4) {
            throw new IllegalArgumentException("Une combinaison doit contenir exactement 4 pions");
        }
        this.elements = elements;
    }

    /**
     * Génère une combinaison aléatoire de 4 pions. Utilise les couleurs
     * disponibles : R, B, V, J, W, N Les couleurs peuvent être répétées dans la
     * combinaison.
     *
     * @return Une nouvelle Combinaison composée de 4 pions de couleurs
     * aléatoires
     */
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

    /**
     * Convertit la combinaison en une chaîne de caractères lisible. Les
     * couleurs des pions sont séparées par des espaces.
     *
     * @return Une chaîne représentant la combinaison (ex: "R B V N")
     */
    public String afficherCombinaisonLisible() {
        StringBuilder sb = new StringBuilder();
        for (Pion p : elements) {
            sb.append(p.getCouleur()).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Retourne le tableau de pions constituant la combinaison.
     *
     * @return Un tableau contenant les 4 pions de la combinaison
     */
    public Pion[] getPions() {
        return elements;
    }

    /**
     * Calcule le nombre de pions bien placés par rapport à une autre
     * combinaison. Un pion est considéré bien placé s'il a la même couleur au
     * même index dans les deux combinaisons.
     *
     * @param autreCombinaison La combinaison à comparer
     * @return Le nombre de pions bien placés (de 0 à 4)
     */
    public int calculerPionsBienPlaces(Combinaison autreCombinaison) {
        int bienPlaces = 0;
        for (int i = 0; i < 4; i++) {
            if (this.elements[i].getCouleur().equals(autreCombinaison.elements[i].getCouleur())) {
                bienPlaces++;
            }
        }
        return bienPlaces;
    }

    /**
     * Calcule le nombre de pions mal placés par rapport à une autre
     * combinaison. Un pion est considéré mal placé s'il existe dans l'autre
     * combinaison mais pas au même index, et s'il n'a pas déjà été compté comme
     * bien placé. Utilise des tableaux de fréquence pour compter les
     * occurrences de chaque couleur.
     *
     * @param autreCombinaison La combinaison à comparer
     * @return Le nombre de pions mal placés
     */
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

    /**
     * Convertit un caractère de couleur en son index correspondant. Les
     * couleurs sont indexées dans l'ordre : R=0, B=1, V=2, J=3, W=4, N=5
     *
     * @param couleur Le caractère représentant la couleur ('R', 'B', 'V', 'J',
     * 'W' ou 'N')
     * @return L'index de la couleur dans la chaîne "RBVJWN" (de 0 à 5)
     */
    private int indexCouleur(Character couleur) {
        return "RBVJWN".indexOf(couleur);
    }
}
