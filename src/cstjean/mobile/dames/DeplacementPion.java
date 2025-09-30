package cstjean.mobile.dames;

import java.util.List;

public class DeplacementPion {

    List<Integer> nbBordureDroite = List.of(5, 15, 25, 35, 45);
    List<Integer> nbBordureGauche = List.of(6, 16, 26, 36, 46);
    public boolean estDeplacementValide(Damier damier, int posInitial, int posFinal) {
        Pion pion = damier.recupererPion(posInitial);
        if(pion == null) {
            return false;
        }
        if(damier.recupererPion(posFinal) != null) {
            return false;
        }

        // vérif des bordures
        if (nbBordureDroite.contains(posInitial) || nbBordureGauche.contains(posInitial)) {
            // pour les mouvements de bordure de joueur blanc
            if(pion.getCouleur() == Pion.Couleur.Blanc) {
                if(posFinal - posInitial != -5) {
                    return false;
                }
            }
            //  les mouvements de bordure de joueur noir
            if(pion.getCouleur() == Pion.Couleur.Noir) {
                if(posFinal - posInitial != 5) {
                    return false;
                }
            }
        }

        System.out.println((((posInitial - 1) / 5) + 1));
        // vérifier mouvements générals pions blancs
        if (pion.getCouleur() == Pion.Couleur.Blanc) {
            if ((((posInitial - 1)/5)+1) % 2 == 0) {
                if (!(posFinal - posInitial == -6 || posFinal - posInitial == -5)) {
                    return false;
                } else {
                    return true;
                }
            }
            if ((((posInitial - 1) / 5) + 1) % 2 != 0) {
                if (!(posFinal - posInitial == -5 || posFinal - posInitial == -4)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if(pion.getCouleur() == Pion.Couleur.Noir) {
            if ((((posInitial - 1) / 5) + 1) % 2 == 0) {
                if(!(posFinal - posInitial == 5 || posFinal - posInitial == 4)) {
                    return false;
                } else {
                    return true;
                }
            }
            if((((posInitial - 1)/5)+1) % 2 != 0) {
                if(!(posFinal - posInitial == 6 || posFinal - posInitial == 5)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;

    }

    public boolean estCapturable(Damier damier, int posInitial, int posFinal) {
        System.out.println("Méthode capturable activé");
        Pion pion = damier.recupererPion(posInitial);

        if (pion == null) {
            return false;
        }
        if (damier.recupererPion(posFinal) != null) {
            return false;
        }

        if (!contientPionMillieu(damier, posInitial, posFinal)) {
            return false;
        }
        //Vérifier si pion au millieu

        //Vérifier si pion est a la bordure
        if (nbBordureDroite.contains(posInitial)) {
            // pour les mouvements de bordure de joueur blanc
            if(pion.getCouleur() == Pion.Couleur.Blanc) {
                if(posFinal - posInitial != -11) {
                    return false;
                }
            }
            //  les mouvements de bordure de joueur noir
            if(pion.getCouleur() == Pion.Couleur.Noir) {
                if(posFinal - posInitial != 9) {
                    return false;
                }
            }
        } else if (nbBordureGauche.contains(posInitial)) {
            // pour les mouvements de bordure de joueur blanc
            if(pion.getCouleur() == Pion.Couleur.Blanc) {
                if(posFinal - posInitial != -9) {
                    return false;
                }
            }
            //  les mouvements de bordure de joueur noir
            if(pion.getCouleur() == Pion.Couleur.Noir) {
                if(posFinal - posInitial != 11) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean contientPionMillieu(Damier damier, int posInitial, int posFinal) {
        Pion pion = damier.recupererPion(posInitial);
        if(pion == null) {
            return false;
        }
        if(damier.recupererPion(posFinal) != null) {
            return false;
        }
        if (pion.getCouleur() == Pion.Couleur.Blanc) {
            if ((((posInitial - 1)/5)+1) % 2 == 0) {
                if(posFinal - posInitial == -11) {
                    if (damier.recupererPion(posInitial - 6) == null)
                    {
                        return false;
                    }
                }
                if(posFinal - posInitial == -9) {
                    if (damier.recupererPion(posInitial - 5) == null)
                    {
                        return false;
                    }
                }
            }
            if ((((posInitial - 1) / 5) + 1) % 2 != 0) {
                if(posFinal - posInitial == -11) {
                    if (damier.recupererPion(posInitial - 5) == null)
                    {
                        return false;
                    }
                }
                if(posFinal - posInitial == -9) {
                    if (damier.recupererPion(posInitial - 4) == null)
                    {return false;}
                }
            }
        }
        if(pion.getCouleur() == Pion.Couleur.Noir) {
            if (pion.getCouleur() == Pion.Couleur.Blanc) {
                if ((((posInitial - 1)/5)+1) % 2 == 0) {
                    if(posFinal - posInitial == 9) {
                        if (damier.recupererPion(posInitial + 4) == null)
                        {
                            return false;
                        }
                    }
                    if(posFinal - posInitial == 11) {
                        if (damier.recupererPion(posInitial + 5) == null)
                        {
                            return false;
                        }
                    }
                }
                if ((((posInitial - 1) / 5) + 1) % 2 != 0) {
                    if(posFinal - posInitial == 11) {
                        if (damier.recupererPion(posInitial - 6) == null)
                        {
                            return false;
                        }
                    }
                    if(posFinal - posInitial == 9) {
                        if (damier.recupererPion(posInitial + 5) == null)
                        {return false;}
                    }
                }
            }
        }
        return true;
    }
}