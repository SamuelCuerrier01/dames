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

    /**
     * La couleur du joueur dont c'est le tour de jouer.
     */
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
     * <p><b>Attention :</b> la liste retournée est une copie de la liste interne.
     * Toute modification de cette copie n'affecte pas directement l’état du damier.
     * </p>
     *
     * @return une copie de la liste des {@link Pion}, où {@code null} représente une case vide
     */
    public List<Pion> getPions() {
        return new ArrayList<>(pions);
    }

    /**
     * Retourne la couleur du joueur dont c'est le tour de jouer.
     *
     * @return la couleur du joueur courant ({@link Pion.Couleur#Blanc} ou {@link Pion.Couleur#Noir})
     */
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

    /**
     * Déplace un pion d'une position initiale vers une position finale, si le mouvement est valide.
     *
     * <p>
     * Cette méthode effectue plusieurs vérifications :
     * </p>
     * <ul>
     *     <li>Vérifie qu'il y a bien un pion à la position initiale,</li>
     *     <li>Vérifie que le pion appartient au joueur courant,</li>
     *     <li>Vérifie que le déplacement est valide selon les règles des dames,</li>
     *     <li>Gère la capture éventuelle d’un pion adverse,</li>
     *     <li>Met à jour le joueur courant si le déplacement est accepté.</li>
     * </ul>
     *
     * <p>
     *
     * les pions atteignant la dernière rangée et affiche le damier mis à jour.
     * </p>
     *
     * @param posInitial la position de départ (1 à 50)
     * @param posFinal   la position d’arrivée (1 à 50)
     */
    public void deplacer(int posInitial, int posFinal) {
        DeplacementPion dp = new DeplacementPion(this);
        DamierAfficher da = new DamierAfficher();
        List<Integer> ligneHaut = List.of(1, 2, 3, 4, 5);
        List<Integer> ligneBas = List.of(46, 47, 48, 49, 50);
        try {
            if (dp.getToutesLesPossibilites(posInitial).contains(posFinal) &&
                    getPions().get(posInitial - 1).getCouleur() == joueurCourant) {
                if(ligneHaut.contains(posFinal) && getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Blanc)
                {ajouterPion(posFinal, new Dame(Pion.Couleur.Blanc));
                } else if(ligneBas.contains(posFinal) && getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Noir) {
                    ajouterPion(posFinal, new Dame(Pion.Couleur.Noir));
                } else {
                    ajouterPion(posFinal, getPions().get(posInitial - 1));
                }
                ajouterPion(posInitial, null);

                if (joueurCourant == Pion.Couleur.Blanc) {
                    joueurCourant = Pion.Couleur.Noir;
                } else {
                    joueurCourant = Pion.Couleur.Blanc;
                }
            }
        } catch(Exception e) {}
        System.out.println(da.afficher(this));
        System.out.println(dp.getPosCapturables());
    }
}
