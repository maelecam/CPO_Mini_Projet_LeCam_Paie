/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

/**
 * Classe représentant un pion avec une couleur associés.
 */
public class Pion {

    private Character couleur;

    /**
     * Constructeur qui initialise un pion avec une couleur donnée.
     *
     * @param couleur La couleur du pion (caractère unique, ex : 'R' pour
     * rouge).
     */
    public Pion(Character couleur) {
        this.couleur = couleur;
    }

    Pion(String couleur) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Getter pour obtenir la couleur du pion.
     *
     * @return La couleur du pion.
     */
    public Character getCouleur() {
        return couleur;
    }

    /**
     * Méthode principale pour tester la classe Pion.
     */
    public static void main() {
        Pion PionRouge = new Pion('R');
        Pion PionBleue = new Pion('B');
        Pion PionJaune = new Pion('Y');
        Pion PionVert = new Pion('V');
        Pion PionBlanc = new Pion('W');
        Pion PionNoir = new Pion('N');
        System.out.println("Pion rouge :" + PionRouge);
        System.out.println("Couleur du pion rouge : " + PionRouge.getCouleur());
        System.out.println("Pion bleue :" + PionBleue);
        System.out.println("Pion de couleur bleue : " + PionBleue.getCouleur());
        System.out.println("Pion jaune :" + PionJaune);
        System.out.println("Pion de couleur Jaune : " + PionJaune.getCouleur());
        System.out.println("Pion Vert :" + PionBleue);
        System.out.println("Pion de couleur vert : " + PionVert.getCouleur());
        System.out.println("Pion blanc :" + PionBlanc);
        System.out.println("Pion de couleur bleue : " + PionBlanc.getCouleur());
        System.out.println("Pion noire : " + PionNoir);
        System.out.println("Pion d ecouleur noire : " + PionNoir.getCouleur());
    }

    /**
     * Redéfinition de la méthode toString pour retourner la couleur du pion.
     *
     * @return Une représentation en chaîne de la couleur du pion.
     */
    @Override
    public String toString() {
        return couleur.toString();
    }
}
