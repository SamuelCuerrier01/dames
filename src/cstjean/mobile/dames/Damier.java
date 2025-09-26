package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un damier pour le jeu de dames.
 *
 * <p>
 * Le damier est modélisé comme une liste de 50 cases jouables,
 * chacune pouvant contenir un pion ({@link Pion}) ou être vide ({@code null}).
 * Les positions sont numérotées de 1 à 50 inclusivement et
 * correspondent aux cases foncées d’un damier standard 10 × 10.
 * </p>
 *
 * <p>
 * Lorsqu’un {@code Damier} est créé, toutes les cases sont vides.
 * </p>
 *
 * <p>
 * Cette classe fournit des opérations de base pour :
 * </p>
 * <ul>
 *     <li>Ajouter un pion à une position,</li>
 *     <li>Récupérer le pion présent à une position,</li>
 *     <li>Compter le nombre total de pions,</li>
 *     <li>Déléguer l’initialisation et l’affichage
 *         de la configuration standard à {@link DamierAfficher}.</li>
 * </ul>
 *
 * @author Samuel Cuerrier
 * @version 1.0
 */
public class Damier {

    /**
     * La liste interne représentant les 50 cases jouables du damier.
     *
     * <p>
     * Chaque élément est soit :
     * </p>
     * <ul>
     *     <li>{@code null} → la case est vide,</li>
     *     <li>Un objet {@link Pion} → un pion est présent.</li>
     * </ul>
     *
     * <p>
     * La liste contient toujours exactement 50 éléments.
     * </p>
     */
    private final List<Pion> pions;

    private Pion.Couleur joueurCourant;

    /**
     * Construit un damier vide.
     *
     * <p>
     * Les 50 cases sont initialisées à {@code null}.
     * </p>
     */
    public Damier() {
        this.pions = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            pions.add(null);
        }
        joueurCourant = Pion.Couleur.Blanc;
    }

    /**
     * Retourne la liste interne représentant les cases du damier.
     *
     * <p><b>Attention :</b> la liste retournée est la référence interne.
     * Toute modification de cette liste affecte directement
     * l’état du damier.
     * </p>
     *
     * @return une liste de {@link Pion}, où {@code null} représente une case vide
     */
    public List<Pion> getPions() {
        return new ArrayList<>(pions);
    }

    public Pion.Couleur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Place un pion à la position spécifiée.
     *
     * <p>
     * Si un pion se trouve déjà à cette position, il est remplacé.
     * </p>
     *
     * @param position la position (1 à 50) où placer le pion
     * @param pion     le pion à ajouter
     * @throws IndexOutOfBoundsException si {@code position} n’est pas comprise entre 1 et 50
     */
    public void ajouterPion(int position, Pion pion) {
        pions.set(position - 1, pion);
    }

    /**
     * Retourne le pion situé à une position donnée.
     *
     * @param position la position (1 à 50)
     * @return le pion en cette position, ou {@code null} si la case est vide
     * @throws IndexOutOfBoundsException si {@code position} n’est pas comprise entre 1 et 50
     */
    public Pion recupererPion(int position) {
        return pions.get(position - 1);
    }

    /**
     * Retourne le nombre total de pions actuellement présents sur le damier.
     *
     * @return le nombre de cases non vides
     */
    public int grandeur() {
        int compteur = 0;
        for (int i = 0; i < 50; i++) {
            if (pions.get(i) != null) {
                compteur++;
            }
        }
        return compteur;
    }

    /**
     * Initialise le damier dans sa configuration de départ standard.
     *
     * <p>
     * Cette méthode délègue l’initialisation à {@link DamierAfficher},
     * qui se charge de :
     * </p>
     * <ul>
     *     <li>Placer les 20 pions noirs dans la partie supérieure,</li>
     *     <li>Laisser les 10 cases centrales vides,</li>
     *     <li>Placer les 20 pions blancs dans la partie inférieure,</li>
     *     <li>Afficher le damier en console sous forme de grille 10 × 10.</li>
     * </ul>
     *
     * <p>
     * Le damier affiché utilise les symboles suivants :
     * </p>
     * <ul>
     *     <li>{@code 'P'} → pion noir</li>
     *     <li>{@code 'p'} → pion blanc</li>
     *     <li>{@code '-'} → case vide ou non jouable</li>
     * </ul>
     */
    public void initialiser() {
        List<Pion> pions = this.getPions();

        // Placer les pions noirs et blancs
        for (int i = 0; i < 20; i++) {
            this.ajouterPion(i + 1, new Pion(Pion.Couleur.Noir));
        }
        // 10 cases centrales vides (indices 20-29) -> déjà null
        // 20 pions blancs
        for (int i = 30; i < 50; i++) {
            this.ajouterPion(i + 1, new Pion(Pion.Couleur.Blanc));
        }

        DamierAfficher d = new DamierAfficher();
        System.out.println(d.afficher(this));
        joueurCourant = Pion.Couleur.Blanc;
    }

    public void deplacer(int posInitial, int posFinal) {
        DamierAfficher d = new DamierAfficher();
        try {

            Pion pion = this.recupererPion(posInitial);

            //vérification qu'il y a un pion a posInitial
            if (pion == null) {
                System.out.println("Aucun pion a cette position");
                return;
            }
            if (pion.getCouleur() != this.getJoueurCourant()) {
                System.out.println("Ce n'est pas a votre tour de jouer");
                return;
            }
            if(!estDeplacementValide(posInitial, posFinal)) {
                System.out.println("Déplacement invalide");
                return;
            }
            ajouterPion(posInitial, null);
            ajouterPion(posFinal, pion);
            if(joueurCourant == Pion.Couleur.Blanc) {
                joueurCourant = Pion.Couleur.Noir;
            }
            else {
                joueurCourant = Pion.Couleur.Blanc;
            }

        }catch(Exception e) {
                System.out.println(e);
        }
        verifierDame();
        d.afficher(this);

    }

    public boolean estDeplacementValide(int posInitial, int posFinal) {
        List<Integer> nbBordureDroite = List.of(5, 15, 25, 35, 45);
        List<Integer> nbBordureGauche = List.of(6, 16, 26, 36, 46);
        Pion pion = this.recupererPion(posInitial);
        if(pion == null) {
            return false;
        }

        //vérif des bordures
        if (nbBordureDroite.contains(posInitial) || nbBordureGauche.contains(posInitial)) {
            //pour les mouvements de bordure de joueur blanc
            if(pion.getCouleur() == Pion.Couleur.Blanc) {
                if(posFinal - posInitial != -5) {
                    return false;
                }
            }
            //pour les mouvements de bordure de joueur noir
            if(pion.getCouleur() == Pion.Couleur.Noir) {
                if(posFinal - posInitial != 5) {
                    return false;
                }
            }
        }

        System.out.println((((posInitial - 1)/5)+1));
        //vérifier mouvements générals pions blancs
        if(pion.getCouleur() == Pion.Couleur.Blanc) {
            if ((((posInitial - 1)/5)+1) % 2 == 0) {
                if(!(posFinal - posInitial == -6 || posFinal - posInitial == -5)) {
                    return false;
                }
                else {
                    return true;
                }
            }
            if((((posInitial - 1)/5)+1) % 2 != 0) {
                if(!(posFinal - posInitial == -5 || posFinal - posInitial == -4)) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        if(pion.getCouleur() == Pion.Couleur.Noir) {
            if ((((posInitial - 1)/5)+1) % 2 == 0) {
                if(!(posFinal - posInitial == 5 || posFinal - posInitial == 4)) {
                    return false;
                }
                else {
                    return true;
                }
            }
            if((((posInitial - 1)/5)+1) % 2 != 0) {
                if(!(posFinal - posInitial == 6 || posFinal - posInitial == 5)) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }


        return true;

    }

    public void verifierDame() {
        DamierAfficher d = new DamierAfficher();
        try {
            for (int i = 1; i <= 5; i++) {
                if (this.recupererPion(i).getCouleur() == Pion.Couleur.Blanc) {
                    this.ajouterPion(i, new Dame(Pion.Couleur.Blanc));
                }
            }
            for (int i = 46; i <= 50; i++) {
                if (this.recupererPion(i).getCouleur() == Pion.Couleur.Noir) {
                    this.ajouterPion(i, new Dame(Pion.Couleur.Noir));
                }
            }
            System.out.println(d.afficher(this));
        } catch (Exception e) {}
    }
}
