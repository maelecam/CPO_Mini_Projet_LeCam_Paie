/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cpo_mini_projet_lecam_paie_mastermind;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author 33604
 */
public class FenetrePrincipale extends javax.swing.JFrame {

/**
     * Creates new form FenetrePrincipale
     */
        private Pion[] elements;
    private int ligneActuelle = 0; // Garde une trace de la ligne en cours
    private Partie partie;
    JButton[][] matBoutons = new JButton[10][4];

    public FenetrePrincipale() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        int nbLignes = 10;
        int nbColonnes = 4;
        PanneauDeJeu.setLayout(new GridLayout(nbLignes, nbColonnes));
        Color[] couleurs = {Color.RED, Color.YELLOW, Color.BLACK, Color.BLUE, Color.GREEN};
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                JButton bouton_cellule = new JButton(); 
                bouton_cellule.setBackground(Color.WHITE); 
                matBoutons[i][j] = bouton_cellule;
                bouton_cellule.addActionListener(e -> {
                    String[] options = {"Rouge", "Jaune", "Noir", "Bleu", "Vert"};
                    int choix = JOptionPane.showOptionDialog(
                            this,
                            "Choisissez une couleur",
                            "Couleur",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                    if (choix >= 0) {
                        bouton_cellule.setBackground(couleurs[choix]);
                    }
                });
                PanneauDeJeu.add(bouton_cellule); // Ajouter le bouton au panneau
            }
        }
        for (int i = 1; i < nbLignes; i++) { // Commence à 1 pour ne pas cacher la première ligne
            for (int j = 0; j < nbColonnes; j++) {
                matBoutons[i][j].setVisible(false); // Cache les boutons des autres lignes
            }
        }
        /**
         * Création de la grille aléatoire en bas du jeu
         */
        int nbLignes2 = 1;
        int nbColonnes2 = 4;
        CompositionAlea.setLayout(new GridLayout(nbLignes2, nbColonnes2));
        CompositionAlea.setVisible(false);
        Random random = new Random(); // Générateur de nombres aléatoires
        for (int i = 0; i < nbLignes2; i++) {
            for (int j = 0; j < nbColonnes2; j++) {
                JButton bouton_cellule2 = new JButton();

                // Sélectionner une couleur aléatoire
                int couleurIndex = random.nextInt(couleurs.length);
                bouton_cellule2.setBackground(couleurs[couleurIndex]);
                CompositionAlea.add(bouton_cellule2); // Ajouter le bouton au panneau
            }
        }
    }
    private void afficherLigneSuivante() {
        int nbLignes = matBoutons.length;
        int nbColonnes = matBoutons[0].length;

        // Désactiver les boutons de la ligne actuelle
        for (int j = 0; j < nbColonnes; j++) {
            JButton bouton = matBoutons[ligneActuelle][j];
            bouton.setEnabled(false);
            Color couleurActuelle = bouton.getBackground();
            bouton.setBackground(couleurActuelle);
            bouton.setOpaque(true);
            bouton.setContentAreaFilled(true);
        }

        // Si on n'a pas encore atteint la dernière ligne
        if (ligneActuelle < nbLignes - 1) {
            ligneActuelle++;
            // Rendre la nouvelle ligne visible et modifiable
            for (int j = 0; j < nbColonnes; j++) {
                JButton bouton = matBoutons[ligneActuelle][j];
                bouton.setVisible(true);
                bouton.setEnabled(true);
                bouton.setOpaque(true);
                bouton.setContentAreaFilled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous avez atteint la dernière ligne !");
        }
    }

    private Color[] recupererCouleursLigneActuelle() {
        Color[] couleursJoueur = new Color[4];
        for (int j = 0; j < 4; j++) {
            couleursJoueur[j] = matBoutons[ligneActuelle][j].getBackground();
        }
        return couleursJoueur;
    }

    private Color[] recupererCouleursSecretes() {
        Color[] couleursSecretes = new Color[4];
        for (int j = 0; j < 4; j++) {
            JButton bouton = (JButton) CompositionAlea.getComponent(j);
            couleursSecretes[j] = bouton.getBackground();
        }
        return couleursSecretes;
    }

    private int[] comparerCombinaisons(Color[] joueur, Color[] secret) {
        int bienPlaces = 0;
        int malPlaces = 0;

        boolean[] positionsDejaCompte = new boolean[4]; // Pour éviter les doublons
        boolean[] couleursUtilisees = new boolean[4];

        // Étape 1 : Compter les pions bien placés
        for (int i = 0; i < 4; i++) {
            if (joueur[i].equals(secret[i])) {
                bienPlaces++;
                positionsDejaCompte[i] = true;
                couleursUtilisees[i] = true;
            }
        }
        // Étape 2 : Compter les pions mal placés
        for (int i = 0; i < 4; i++) {
            if (!positionsDejaCompte[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!couleursUtilisees[j] && joueur[i].equals(secret[j])) {
                        malPlaces++;
                        couleursUtilisees[j] = true;
                        break;
                    }
                }
            }
        }
        return new int[]{bienPlaces, malPlaces};
    }

    public int calculerBonnesCouleurs(Color[] couleursJoueur, Color[] couleursSecretes) {
        int bonnesCouleurs = 0;

        // Créer un tableau pour compter la fréquence des couleurs dans la combinaison secrète
        int[] frequencesSecretes = new int[6];  // Assumons qu'il y a 6 couleurs possibles (Rouge, Vert, Bleu, etc.)

        // Remplir les fréquences de la combinaison secrète
        for (Color couleur : couleursSecretes) {
            if (couleur == Color.RED) {
                frequencesSecretes[0]++;
            } else if (couleur == Color.GREEN) {
                frequencesSecretes[1]++;
            } else if (couleur == Color.BLUE) {
                frequencesSecretes[2]++;
            } else if (couleur == Color.YELLOW) {
                frequencesSecretes[3]++;
            } else if (couleur == Color.BLACK) {
                frequencesSecretes[4]++;
            } else if (couleur == Color.WHITE) {
                frequencesSecretes[5]++;
            }
        }

        // Comparer les couleurs de l'utilisateur
        for (Color couleur : couleursJoueur) {
            if (couleur == Color.RED && frequencesSecretes[0] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[0]--;  // Décrémenter la fréquence pour éviter de compter plusieurs fois la même couleur
            } else if (couleur == Color.GREEN && frequencesSecretes[1] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[1]--;
            } else if (couleur == Color.BLUE && frequencesSecretes[2] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[2]--;
            } else if (couleur == Color.YELLOW && frequencesSecretes[3] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[3]--;
            } else if (couleur == Color.BLACK && frequencesSecretes[4] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[4]--;
            } else if (couleur == Color.WHITE && frequencesSecretes[5] > 0) {
                bonnesCouleurs++;
                frequencesSecretes[5]--;
            }
        }
        return bonnesCouleurs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        PanneauDeJeu = new javax.swing.JPanel();
        CompositionAlea = new javax.swing.JPanel();
        Fond = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        AfficherCombinaisonSecrete = new javax.swing.JButton();
        CouleursBienPlaces = new javax.swing.JPanel();
        Bravo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CouleursBienPlacés = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        Régles = new javax.swing.JButton();
        BonnesCouleurs = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CouleursPrésentes = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauDeJeu.setBackground(new java.awt.Color(255, 255, 255));
        PanneauDeJeu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout PanneauDeJeuLayout = new javax.swing.GroupLayout(PanneauDeJeu);
        PanneauDeJeu.setLayout(PanneauDeJeuLayout);
        PanneauDeJeuLayout.setHorizontalGroup(
            PanneauDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        PanneauDeJeuLayout.setVerticalGroup(
            PanneauDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauDeJeu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 200, 200));

        CompositionAlea.setBackground(new java.awt.Color(0, 204, 204));
        CompositionAlea.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout CompositionAleaLayout = new javax.swing.GroupLayout(CompositionAlea);
        CompositionAlea.setLayout(CompositionAleaLayout);
        CompositionAleaLayout.setHorizontalGroup(
            CompositionAleaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        CompositionAleaLayout.setVerticalGroup(
            CompositionAleaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(CompositionAlea, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 200, 50));

        Fond.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setText("        MASTERMIND");

        javax.swing.GroupLayout FondLayout = new javax.swing.GroupLayout(Fond);
        Fond.setLayout(FondLayout);
        FondLayout.setHorizontalGroup(
            FondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        FondLayout.setVerticalGroup(
            FondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(Fond, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 200, 50));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AfficherCombinaisonSecrete.setText("Afficher Combinsaion");
        AfficherCombinaisonSecrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfficherCombinaisonSecreteActionPerformed(evt);
            }
        });
        jPanel1.add(AfficherCombinaisonSecrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 70, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 100, 50));

        CouleursBienPlaces.setBackground(new java.awt.Color(204, 153, 255));
        CouleursBienPlaces.setToolTipText("CouleursBienPlacés");
        CouleursBienPlaces.setAutoscrolls(true);

        Bravo.setText("Nombre de ");

        jLabel2.setText("Couleurs bien");

        javax.swing.GroupLayout CouleursBienPlacesLayout = new javax.swing.GroupLayout(CouleursBienPlaces);
        CouleursBienPlaces.setLayout(CouleursBienPlacesLayout);
        CouleursBienPlacesLayout.setHorizontalGroup(
            CouleursBienPlacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CouleursBienPlacesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CouleursBienPlacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bravo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addGroup(CouleursBienPlacesLayout.createSequentialGroup()
                        .addComponent(CouleursBienPlacés)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CouleursBienPlacesLayout.setVerticalGroup(
            CouleursBienPlacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CouleursBienPlacesLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(Bravo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CouleursBienPlacés)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        getContentPane().add(CouleursBienPlaces, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 100, 250));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jButton2.setText("Valider");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 50));

        jPanel5.setBackground(new java.awt.Color(204, 153, 255));

        Régles.setText("Regles");
        Régles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RéglesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Régles, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Régles, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        BonnesCouleurs.setBackground(new java.awt.Color(255, 204, 204));
        BonnesCouleurs.setToolTipText("");

        jLabel3.setText("Nombre de");

        jLabel4.setText("Bonnes Couleurs");

        javax.swing.GroupLayout BonnesCouleursLayout = new javax.swing.GroupLayout(BonnesCouleurs);
        BonnesCouleurs.setLayout(BonnesCouleursLayout);
        BonnesCouleursLayout.setHorizontalGroup(
            BonnesCouleursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BonnesCouleursLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BonnesCouleursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(CouleursPrésentes, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        BonnesCouleursLayout.setVerticalGroup(
            BonnesCouleursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BonnesCouleursLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CouleursPrésentes)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        getContentPane().add(BonnesCouleurs, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RéglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RéglesActionPerformed
        // TODO add your handling code here:
        afficher_regles R = new afficher_regles();
        R.show();
    }//GEN-LAST:event_RéglesActionPerformed

    private void AfficherCombinaisonSecreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfficherCombinaisonSecreteActionPerformed
        // TODO add your handling code here:
        CompositionAlea.setVisible(true);
    }

    @Override
    public String toString() {
        return "FenetrePrincipale{" + "elements=" + elements + '}';

    }//GEN-LAST:event_AfficherCombinaisonSecreteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    Color[] couleursJoueur = recupererCouleursLigneActuelle();

        // Récupérer les couleurs de la combinaison secrète
        Color[] couleursSecretes = recupererCouleursSecretes();

        // Calculer le nombre de bonnes couleurs (même couleur mais pas forcément à la bonne position)
        int bonnesCouleurs = calculerBonnesCouleurs(couleursJoueur, couleursSecretes);

        // Calculer le nombre de couleurs bien placées (à la bonne position)
        int bienPlaces = 0;
        for (int i = 0; i < couleursJoueur.length; i++) {
            if (couleursJoueur[i].equals(couleursSecretes[i])) {
                bienPlaces++;
            }
        }
        CouleursBienPlacés.setText("placés : " + bienPlaces);
        CouleursPrésentes.setText(": " + bonnesCouleurs);
        // Appeler la méthode pour afficher la ligne suivante
        afficherLigneSuivante();
        if (bienPlaces == 4) {
            if (bonnesCouleurs == 4) {               
                Valider V = new Valider();
                V.show();
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipale().setVisible(true);

            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AfficherCombinaisonSecrete;
    private javax.swing.JPanel BonnesCouleurs;
    private javax.swing.JLabel Bravo;
    private javax.swing.JPanel CompositionAlea;
    private javax.swing.JPanel CouleursBienPlaces;
    private javax.swing.JLabel CouleursBienPlacés;
    private javax.swing.JLabel CouleursPrésentes;
    private javax.swing.JPanel Fond;
    private javax.swing.JPanel PanneauDeJeu;
    private javax.swing.JButton Régles;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
