package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.Arrays;
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
                if (Math.abs(posFinal - posInitial) > 6) {
                    ajouterPion(capture(posInitial, posFinal, dp), null);
                    ajouterPion(posFinal, getPions().get(posInitial - 1));
                } else {
                    ajouterPion(posFinal, getPions().get(posInitial - 1));
                }
                if (ligneHaut.contains(posFinal) && getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Blanc) {
                    ajouterPion(posFinal, new Dame(Pion.Couleur.Blanc));
                } else if (ligneBas.contains(posFinal) &&
                        getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Noir) {
                    ajouterPion(posFinal, new Dame(Pion.Couleur.Noir));
                }
                ajouterPion(posInitial, null);

                if (joueurCourant == Pion.Couleur.Blanc) {
                    joueurCourant = Pion.Couleur.Noir;
                } else {
                    joueurCourant = Pion.Couleur.Blanc;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(da.afficher(this));
    }

    /**
     * Calcule la position du pion capturé entre deux positions.
     *
     * @param posInitial position de départ du pion
     * @param posFinal position d’arrivée du pion
     * @param dp outil de conversion des positions en coordonnées
     * @return la position du pion capturé, ou -1 s’il n’y en a pas
     */

    public int capture(int posInitial, int posFinal, DeplacementPion dp) {
        int[] coordInitial = dp.convertirPosition(posInitial);
        int[] coordFinal = dp.convertirPosition(posFinal);
        if (recupererPion(posInitial) instanceof Dame) {
            if (coordFinal[1] - coordInitial[1] < 0) {
                if (coordFinal[0] - coordInitial[0] < 0) {
                    return dp.positionDepuisCoord(coordFinal[0] + 1, coordFinal[1] + 1);
                } else {
                    return dp.positionDepuisCoord(coordFinal[0] - 1, coordFinal[1] + 1);
                }
            } else {
                if (coordFinal[0] - coordInitial[0] < 0) {
                    return dp.positionDepuisCoord(coordFinal[0] + 1, coordFinal[1] - 1);
                } else {
                    return dp.positionDepuisCoord(coordFinal[0] - 1, coordFinal[1] - 1);
                }
            }
        }

        List<Integer> ligne1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 11, 12, 13, 14, 15,
                21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45));
        if (ligne1.contains(posInitial)) {
            if (getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Blanc) {
                if (posFinal - posInitial == -11) {
                    return posFinal + 6;
                }
                if (posFinal - posInitial == -9) {
                    return posFinal + 5;
                }
            }
            if (recupererPion(posInitial).getCouleur() == Pion.Couleur.Noir) {
                if (posFinal - posInitial == 11) {
                    return posFinal - 5;
                }
                if (posFinal - posInitial == 9) {
                    return posFinal - 4;
                }
            }
        } else {
            if (getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Blanc) {
                if (posFinal - posInitial == -9) {
                    return posFinal + 4;
                }
                if (posFinal - posInitial == -11) {
                    return posFinal + 5;
                }
            }
            if (getPions().get(posInitial - 1).getCouleur() == Pion.Couleur.Noir) {
                if (posFinal - posInitial == 11) {
                    return posFinal - 6;
                }
                if (posFinal - posInitial == 9) {
                    return posFinal - 5;
                }
            }
        }
        return -1;
    }
}
