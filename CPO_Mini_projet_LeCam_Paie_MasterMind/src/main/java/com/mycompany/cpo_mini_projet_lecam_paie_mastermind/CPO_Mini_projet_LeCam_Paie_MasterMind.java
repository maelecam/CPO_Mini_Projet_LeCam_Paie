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
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;
    import java.util.Scanner;

public class CPO_Mini_projet_LeCam_Paie_MasterMind {

    public static void main(String[] args) {

        Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
        PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 10);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bienvenue dans le jeu MasterMind !");
            System.out.println("Essayez de deviner la combinaison secrète.");
            System.out.println("La combinaison contient 4 pions et utilise des couleurs suivantes : R (Rouge), B (Bleu), V (Vert), J (Jaune), W (Blanc), N (Noir).");
            System.out.println("Pour chaque tentative, entrez 4 couleurs sous la forme d'une chaîne de caractères.");
            System.out.println("Exemple de tentative : RVJN");
            System.out.println("Vous avez 10 tentatives pour trouver la combinaison secrète.");
            
            int nbTentatives = 0;
            
            while (!plateau.estVictoire() && !plateau.estDefaite()) {
                plateau.afficherPlateau();
                System.out.print("Tentative " + (nbTentatives + 1) + " : Entrez votre combinaison : ");
                String entreeUtilisateur = scanner.nextLine().toUpperCase();
                
                if (entreeUtilisateur.length() != 4 || !entreeUtilisateur.matches("[RBVJWN]+")) {
                    System.out.println("Entrée invalide. Veuillez entrer 4 lettres parmi : R, B, V, J, W, N.");
                    continue;
                }
                
                Pion[] pionsProposes = new Pion[4];
                for (int i = 0; i < 4; i++) {
                    pionsProposes[i] = new Pion(entreeUtilisateur.charAt(i));
                }
                
                Combinaison tentative = new Combinaison(pionsProposes);
                plateau.proposerCombinaison(tentative);
                nbTentatives++;
            }
            
            if (plateau.estVictoire()) {
                System.out.println("Félicitations, vous avez deviné la combinaison secrète !");
            } else if (plateau.estDefaite()) {
                System.out.println("Désolé, vous avez épuisé vos tentatives. La combinaison secrète était : "
                        + combinaisonSecrete.afficherCombinaisonLisible());
            }
        }
    }
}
