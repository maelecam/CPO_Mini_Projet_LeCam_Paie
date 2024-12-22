/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.util.Scanner;

public class CPO_Mini_projet_LeCam_Paie_MasterMind {

    /**
     * Point d'entrée principal du jeu MasterMind. Initialise et gère une partie
     * complète du jeu, incluant la création de la combinaison secrète, la
     * gestion des tentatives du joueur et la possibilité de rejouer.
     *
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
        PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 10);

        try ( Scanner scanner = new Scanner(System.in)) {
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

// suite 
            boolean continuerJeu = true;

            while (continuerJeu) {
                Partie(scanner);

                System.out.println("\nVoulez-vous rejouer ? (O/N)");
                String reponse = scanner.nextLine().toUpperCase();

                while (!reponse.equals("O") && !reponse.equals("N")) {
                    System.out.println("Veuillez répondre par O (Oui) ou N (Non)");
                    reponse = scanner.nextLine().toUpperCase();
                }

                if (reponse.equals("N")) {
                    continuerJeu = false;
                    System.out.println("Merci d'avoir joué ! Au revoir !");
                }
            }
        }
    }
////////////////// FINALISATION 18 DÉCEMBRE DU MODE AVANCÉ 

    /**
     * Gère une partie unique de MasterMind en permettant au joueur de choisir
     * entre le mode classique et le mode avancé.
     *
     * @param scanner Scanner pour lire les entrées utilisateur
     */
    private static void Partie(Scanner scanner) {
        System.out.println("\n--- MODES DE JEU ---");
        System.out.println("1. Mode Classic");
        System.out.println("2. Mode Avancé");
        System.out.print("Choisissez un mode : ");

        String choixMode = scanner.nextLine();

        switch (choixMode) {
            case "1":
                // Mode de jeu classique  (code actuel)
                Combinaison combinaisonSecrete = Combinaison.genererCombinaisonAleatoire();
                PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 10);
                break;
            case "2":
                menuModeAvance(scanner);
                break;
            default:
                System.out.println("Choix invalide. Mode classic par défaut.");
        }
    }

    /**
     * Affiche et gère le menu du mode avancé permettant de personnaliser
     * différents aspects du jeu.
     *
     * @param scanner Scanner pour lire les entrées utilisateur
     */
    private static void menuModeAvance(Scanner scanner) {
        boolean continuerMode = true;
        while (continuerMode) {
            System.out.println("\n--- MODE AVANCÉ ---");
            System.out.println("1. Taille variable");
            System.out.println("2. Mode sans répétitions");
            System.out.println("3. Personnalisation des couleurs");
            System.out.println("4. Retour menu principal");

            System.out.print("Choisissez une option : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    choixTailleVariable(scanner);
                    break;
                case "2":
                    modeAvecOuSansRepetitions(scanner);
                    break;
                case "3":
                    personnalisationCouleurs(scanner);
                    break;
                case "4":
                    continuerMode = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Permet au joueur de choisir la taille de la combinaison à deviner.
     * Vérifie que la taille est comprise entre 3 et 8, sinon utilise la valeur
     * par défaut de 4.
     *
     * @param scanner Scanner pour lire les entrées utilisateur
     */
    private static void choixTailleVariable(Scanner scanner) {
        System.out.print("Entrez la longueur de la combinaison (3-8) : ");
        int taille = scanner.nextInt();
        if (taille < 3 || taille > 8) {
            System.out.println("Longueur invalide. Choix par défaut : 4");
            taille = 4;
        }
        scanner.nextLine(); // Nettoie le buffer
        System.out.println("Nouvelle longueur de combinaison : " + taille);
    }

    /**
     * Permet au joueur de choisir si les couleurs peuvent être répétées dans la
     * combinaison. Propose deux modes : avec ou sans répétitions de couleurs.
     *
     * @param scanner Scanner pour lire les entrées utilisateur
     */
    private static void modeAvecOuSansRepetitions(Scanner scanner) {
        System.out.println("Mode de répétition des couleurs :");
        System.out.println("1. Avec répétitions");
        System.out.println("2. Sans répétitions");
        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                System.out.println("Mode avec répétitions activé");
                break;
            case "2":
                System.out.println("Mode sans répétitions activé");
                break;
            default:
                System.out.println("Choix invalide. Mode par défaut : Avec répétitions");
        }
    }

    /**
     * Permet au joueur de personnaliser les couleurs utilisées dans le jeu.
     * Vérifie que le nombre de couleurs est compris entre 3 et 8. Les couleurs
     * doivent être entrées sous forme de caractères uniques sans séparateur.
     *
     * @param scanner Scanner pour lire les entrées utilisateur
     */
    private static void personnalisationCouleurs(Scanner scanner) {
        System.out.println("Couleurs disponibles : R (Rouge), B (Bleu), V (Vert), J (Jaune), W (Blanc), N (Noir)");
        System.out.print("Entrez vos couleurs personnalisées (sans séparateur) : ");
        String couleurs = scanner.nextLine().toUpperCase();

        // Validation simple
        if (couleurs.length() < 3 || couleurs.length() > 8) {
            System.out.println("Sélection invalide. Couleurs par défaut conservées.");
        } else {
            System.out.println("Nouvelles couleurs : " + couleurs);
        }
    }
}
