/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 33604
 */
public class Pion { 
    // Attribut : couleur du pion
    private Character couleur;

    // Constructeur : initialise la couleur
    public Pion(Character couleur) {
        this.couleur = couleur;
    }

    // Méthode getter pour accéder à la couleur
    public Character getCouleur() {
        return couleur;
    }

    public static void main() {
        Pion PionRouge = new Pion('R');
        Pion PionBleue = new Pion('B');
        System.out.println("Pion rouge :" + PionRouge);
        System.out.println("Couleur du pion rouge : " + PionRouge.getCouleur());
        System.out.println("Pion bleue :" + PionBleue);
        System.out.println("Pion de couleur bleue : " + PionBleue.getCouleur());
    }

    // Redéfinition de la méthode toString pour afficher la couleur
    @Override
    public String toString() {
        return couleur.toString();
    }
}
