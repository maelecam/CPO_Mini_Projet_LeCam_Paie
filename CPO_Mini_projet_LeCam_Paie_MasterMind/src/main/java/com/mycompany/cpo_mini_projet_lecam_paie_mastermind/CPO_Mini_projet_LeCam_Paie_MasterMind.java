/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

/**
 *
 * @author 33604
 */
  import java.util.Scanner;

public class CPO_Mini_projet_LeCam_Paie_MasterMind {

    public static void main(String[] args) {

        try ( 
                Scanner scanner = new Scanner(System.in)) {

            String[] couleursPossibles = {"R", "B", "V", "J", "O", "M"};
            
            int tailleCombinaison = 4;
            int maxTours = 10;
            
            System.out.println("=== MASTERMIND ===");
            System.out.println("1. Nouvelle partie");
            System.out.println("2. Quitter");
            System.out.print("Choisissez une option : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choix == 1) { 

                Partie partie = new Partie(maxTours, couleursPossibles, tailleCombinaison);
                partie.jouer();
            } else {
                System.out.println("Au revoir !");
            }
        }
    }
}
