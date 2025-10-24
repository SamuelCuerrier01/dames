package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

public class DeplacementPion {

    private Damier damier;

    private List<Integer> PosCapturables;

    public DeplacementPion(Damier damier) {
        this.damier = damier;
        PosCapturables = new ArrayList<>();
    }

    public List<Integer> getPosCapturables() {
        return PosCapturables;
    }

    public List<Integer> getToutesLesPossibilites(int position) {
        List<Integer> touteLesCases = new ArrayList<>();
        touteLesCases.addAll(getDeplacementsSimple(position));
        touteLesCases.addAll(getCapturesSimples(position));
        return touteLesCases;
    }

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
            int compteur = 0;
            int i = 0;
            while(true){
                i++;
                compteur = casesValides.size();
                ajouterSiVide(casesValides, ligne - i, col - i);
                ajouterSiVide(casesValides, ligne - i, col + i);
                ajouterSiVide(casesValides, ligne + i, col - i);
                ajouterSiVide(casesValides, ligne + i, col + i);
                if(compteur == casesValides.size()){
                    break;
                }
            }
            System.out.println(casesValides.size());
        }

        return casesValides;
    }

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

    private void ajouterSiVide(List<Integer> casesValides, int ligne, int col) {
        if (!estCoordValide(ligne, col)) {
            return;
        }
        int position = positionDepuisCoord(ligne, col);
        if (damier.recupererPion(position) == null) {
            casesValides.add(position);
        } else if (damier.getPions().get(positionDepuisCoord(ligne, col)) instanceof Dame) {

        }
    }

    private void ajouterCapture(List<Integer> casesCaptures, int ligne, int col, int ligneMilieu, int colMilieu, int ligneFinal, int colFinal, Pion.Couleur couleur) {
        if (!estCoordValide(ligneMilieu, colMilieu)) {
            return;
        }
        if (!estCoordValide(ligneFinal, colFinal)) {
            return;
        }

        int posMilieu = positionDepuisCoord(ligneMilieu, colMilieu);
        int posFinal = positionDepuisCoord(ligneFinal, colFinal);
        PosCapturables.add(posMilieu);
        Pion pionMilieu = damier.recupererPion(posMilieu);
        Pion pionFinal = damier.recupererPion(posFinal);

        if (pionMilieu != null && pionMilieu.getCouleur() != couleur && pionFinal == null) {
            casesCaptures.add(posFinal);
        }
    }

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

    private boolean estCoordValide(int ligne, int col) {
        if (ligne < 0 || ligne >= 10 || col < 0 || col >= 10 || (ligne + col) % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }
}
