package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe gérant les déplacements et captures possibles d'un pion ou d'une dame sur le damier.
 */
public class DeplacementPion {

    /**
     * Référence vers le damier sur lequel les déplacements sont effectués.
     */
    private final Damier damier;

    /**
     * Liste contenant les positions des pions adverses pouvant être capturés.
     */
    private final List<Integer> posCapturables;

    /**
     * Constructeur du déplacement de pion.
     *
     * @param damier le damier sur lequel les déplacements sont effectués
     */
    public DeplacementPion(Damier damier) {
        this.damier = damier;
        posCapturables = new ArrayList<>();
    }

    /**
     * Retourne la liste des positions des pions capturables.
     *
     * @return la liste des positions capturables
     */
    public List<Integer> getPosCapturables() {
        return posCapturables;
    }

    /**
     * Retourne toutes les positions possibles (déplacements simples et captures) d’un pion.
     *
     * @param position position actuelle du pion
     *
     * @return liste des positions possibles
     */
    public List<Integer> getToutesLesPossibilites(int position) {
        List<Integer> touteLesCases = new ArrayList<>();
        touteLesCases.addAll(getDeplacementsSimple(position));
        touteLesCases.addAll(getCapturesSimples(position));
        return touteLesCases;
    }

    /**
     * Retourne les déplacements simples possibles d’un pion ou d’une dame.
     *
     * @param position position actuelle du pion
     * @return liste des cases valides pour un déplacement simple
     */
    private List<Integer> getDeplacementsSimple(int position) {
        List<Integer> casesValides = new ArrayList<>();
        Pion pion = damier.recupererPion(position);
        if (pion == null) {
            return casesValides;
        }

        boolean estDame = pion instanceof Dame;
        Pion.Couleur couleur = pion.getCouleur();

        int[] coord = convertirPosition(position);
        int ligne = coord[0];
        int col = coord[1];

        if (!estDame && couleur == Pion.Couleur.Blanc) {
            ajouterSiVide(casesValides, ligne - 1, col - 1);
            ajouterSiVide(casesValides, ligne - 1, col + 1);
        }

        if (!estDame && couleur == Pion.Couleur.Noir) {
            ajouterSiVide(casesValides, ligne + 1, col - 1);
            ajouterSiVide(casesValides, ligne + 1, col + 1);
        }

        if (estDame) {
            int compteur;
            int i = 0;
            while (true) {

                i++;
                compteur = casesValides.size();
                ajouterSiVide(casesValides, ligne - i, col - i);
                ajouterSiVide(casesValides, ligne - i, col + i);
                ajouterSiVide(casesValides, ligne + i, col - i);
                ajouterSiVide(casesValides, ligne + i, col + i);
                if (compteur == casesValides.size()) {
                    break;
                }
            }
            System.out.println(casesValides.size());
        }

        return casesValides;
    }

    /**
     * Retourne les positions où une capture simple est possible.
     *
     * @param position position actuelle du pion
     *
     * @return liste des cases où une capture peut être effectuée
     */
    private List<Integer> getCapturesSimples(int position) {
        List<Integer> casesCaptures = new ArrayList<>();
        Pion pion = damier.recupererPion(position);
        if (pion == null) {
            return casesCaptures;
        }

        boolean estDame = pion instanceof Dame;
        Pion.Couleur couleur = pion.getCouleur();

        int[] coord = convertirPosition(position);
        int ligne = coord[0];
        int col = coord[1];

        if (!estDame && couleur == Pion.Couleur.Blanc) {
            ajouterCapture(casesCaptures, ligne, col, ligne - 1, col - 1, ligne - 2, col - 2, couleur);
            ajouterCapture(casesCaptures, ligne, col, ligne - 1, col + 1, ligne - 2, col + 2, couleur);
        }

        if (!estDame && couleur == Pion.Couleur.Noir) {
            ajouterCapture(casesCaptures, ligne, col, ligne + 1, col - 1, ligne + 2, col - 2, couleur);
            ajouterCapture(casesCaptures, ligne, col, ligne + 1, col + 1, ligne + 2, col + 2, couleur);
        }

        if (estDame) {
            ajouterCapture(casesCaptures, ligne, col, ligne - 1, col - 1, ligne - 2, col - 2, couleur);
            ajouterCapture(casesCaptures, ligne, col, ligne - 1, col + 1, ligne - 2, col + 2, couleur);
            ajouterCapture(casesCaptures, ligne, col, ligne + 1, col - 1, ligne + 2, col - 2, couleur);
            ajouterCapture(casesCaptures, ligne, col, ligne + 1, col + 1, ligne + 2, col + 2, couleur);
        }

        return casesCaptures;
    }

    /**
     * Ajoute une case si elle est vide.
     *
     * @param casesValides liste des cases valides
     *
     * @param ligne ligne de la case
     *
     * @param col colonne de la case
     */
    private void ajouterSiVide(List<Integer> casesValides, int ligne, int col) {
        if (!estCoordValide(ligne, col)) {
            return;
        }
        int position = positionDepuisCoord(ligne, col);
        if (damier.recupererPion(position) == null) {
            casesValides.add(position);
        }
    }

    /**
     * Vérifie et ajoute une position de capture possible.
     *
     * @param casesCaptures liste des cases où une capture peut être faite
     * @param ligne ligne du pion
     * @param col colonne du pion
     * @param ligneMilieu ligne du pion adverse
     * @param colMilieu colonne du pion adverse
     * @param ligneFinal ligne d’arrivée
     * @param colFinal colonne d’arrivée
     * @param couleur couleur du pion actif
     */
    private void ajouterCapture(List<Integer> casesCaptures, int ligne, int col, int ligneMilieu,
                                int colMilieu, int ligneFinal, int colFinal, Pion.Couleur couleur) {
        if (!estCoordValide(ligneMilieu, colMilieu)) {
            return;
        }
        if (!estCoordValide(ligneFinal, colFinal)) {
            return;
        }

        int posMilieu = positionDepuisCoord(ligneMilieu, colMilieu);
        int posFinal = positionDepuisCoord(ligneFinal, colFinal);
        posCapturables.add(posMilieu);
        Pion pionMilieu = damier.recupererPion(posMilieu);
        Pion pionFinal = damier.recupererPion(posFinal);

        if (pionMilieu != null && pionMilieu.getCouleur() != couleur && pionFinal == null) {
            casesCaptures.add(posFinal);
        }
    }

    /**
     * Convertit une position (1 à 50) en coordonnées (ligne, colonne).
     *
     * @param position position du pion
     * @return tableau contenant la ligne et la colonne
     */
    public int[] convertirPosition(int position) {
        int compteur = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1) {
                    if (compteur == position) {
                        return new int[]{i, j};
                    }
                    compteur++;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Convertit des coordonnées en position (1 à 50).
     *
     * @param ligne ligne sur le damier
     * @param col colonne sur le damier
     * @return position correspondante
     */
    public int positionDepuisCoord(int ligne, int col) {
        if (ligne < 0 || ligne >= 10 || col < 0 || col >= 10 || (ligne + col) % 2 == 0) {
            return -1;
        }
        int compteur = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1) {
                    if (i == ligne && j == col) {
                        return compteur;
                    }
                    compteur++;
                }
            }
        }
        return -1;
    }

    /**
     * Vérifie si les coordonnées données sont valides.
     *
     * @param ligne ligne sur le damier
     * @param col colonne sur le damier
     * @return vrai si les coordonnées sont valides, faux sinon
     */
    private boolean estCoordValide(int ligne, int col) {
        return ligne >= 0 && ligne < 10 && col >= 0 && col < 10 && (ligne + col) % 2 != 0;
    }

}
