package main.java.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.entities.Aventurier;
import main.java.entities.Carte;
import main.java.entities.Montagne;
import main.java.entities.Tresor;
import main.java.exceptions.MovementException;
import main.java.mappers.CarteMapper;

public class CarteService {

  public CarteService() {
  }

  public Carte carteApresExploration(String path, String fileName) {
    Carte carte = CarteMapper.readFile(new File(path + fileName));
    List<Montagne> montagnes = carte.getMontagnes();
    List<Tresor> tresors = carte.getTresors();
    List<Aventurier> aventuriers = carte.getAventuriers();

    int nombreTours = aventuriers.get(0).getSequenceMouvements().length();
    // On vérifie que tout le monde a le même nombre de mouvements
    try {
      for (Aventurier aventurier : aventuriers) {
        if (aventurier.getSequenceMouvements().length() != nombreTours) {
          throw new MovementException("Le nombre de mouvements doit être le même pour tous les aventuriers");
        }
      }
    } catch (MovementException me) {
      System.err.println(me.getMessage());
    }

    // Map pour définir si l'aveturier peut bouger
    Map<Aventurier, Boolean> aventuriersPeutBouger = new HashMap<>();
    
    Map<Aventurier, Integer[]> positionsInitialesAventuriers = new HashMap<>();

    for (int i = 0; i < nombreTours; i++) {
      aventurier: for (Aventurier aventurier : aventuriers) {
        Integer[] positionInitialeAventurier = {aventurier.getAbsciss(), aventurier.getOrdinate()};
        positionsInitialesAventuriers.put(aventurier, positionInitialeAventurier);
        aventuriersPeutBouger.put(aventurier, false);
        char mouvement = aventurier.getSequenceMouvements().charAt(i);

        // On compare la position envisagées avec celles des montagnes et des autres
        // aventuriers
        List<Integer> eventuellePositonSuivante = aventurier.eventuellePositionSuivante(mouvement);

        // On ne peut pas aller au-délà des frontières
        if (eventuellePositonSuivante.get(0) < 0 || eventuellePositonSuivante.get(0) >= carte.getSize()[0]
            || eventuellePositonSuivante.get(1) < 0 || eventuellePositonSuivante.get(1) >= carte.getSize()[1]) {
          continue aventurier;
        }

        // On vérifie qu'il n'y pas de montagne dans la prochaine position envisagée
        for (Montagne montagne : montagnes) {
          if (eventuellePositonSuivante.equals(montagne.getPosition())) {
            // on passe à l'aventurier suivant si celui ne peut pas bouger
            continue aventurier;
          }
        }

        // S'il y'a un seul aventurier, pas besoin de gérer les conflits
        if (aventuriers.size() == 1) {
          aventuriersPeutBouger.put(aventurier, true);
        }

        for (Aventurier autreAventurier : aventuriers) {
          if (autreAventurier != aventurier) {
            char mouvementAutre = aventurier.getSequenceMouvements().charAt(i);

            List<Integer> autreAventurierPositonSuivante = autreAventurier.eventuellePositionSuivante(mouvementAutre);
            // On vérifie qu'il n'y a bien personne sur la position envisagée
            if ((!eventuellePositonSuivante.equals(autreAventurierPositonSuivante)
                || mouvementAutre == 'A' && eventuellePositonSuivante.equals(autreAventurierPositonSuivante)
                    && aventurier.getOrdreApparition() < autreAventurier.getOrdreApparition())) {
              aventuriersPeutBouger.put(aventurier, true);
            } else {
              // Si conflit avec autre aventurier à l'ordre d'apparition inférieur alors
              // on ignore le mouvement et passe à l'aventurier suivant
              continue aventurier;
            }
          }
        }
      }

      // après toutes vérifications faites, on exécute les mouvements
      for (Aventurier aventurier : aventuriers) {
        char mouvement = aventurier.getSequenceMouvements().charAt(i);
        // On fait avancer les aventuriers qui le peuvent selon les contraintes
        if (aventuriersPeutBouger.get(aventurier)) {
          try {
            if (mouvement == 'A') {
              aventurierAvance(aventurier, mouvement);
            } else if (mouvement == 'D' || mouvement == 'G') {
              aventurierPivote(aventurier, mouvement);
            } else {
              throw new MovementException("Mouvement inconnu, les seuls mouvements autorisées sont A, G et D");
            }
          } catch (MovementException me) {
            System.err.println(me.getMessage());
          }
        }

        // Si l'aventurier trouve un trésor sur sa nouvelle place, il en prend un
        // et le nombre de trésors sur place diminue de un
        for (Tresor tresor : tresors) {
          if (tresor.getNombre() > 0) {
            if (tresor.getPosition().get(0).equals(aventurier.getAbsciss())
                && tresor.getPosition().get(1).equals(aventurier.getOrdinate())) {
              // On vérifie enfin que l'aventurier n'a pas été immobile sur la même case
              if (!positionsInitialesAventuriers.get(aventurier)[0].equals(aventurier.getAbsciss())
                  || !positionsInitialesAventuriers.get(aventurier)[0].equals(aventurier.getOrdinate())) {
                int tresorsAventurierAvantRamassage = aventurier.getNombreTresorsRamasses();
                int nombreTresorsAvantRamassage = tresor.getNombre();

                aventurier.setNombreTresorsRamasses(tresorsAventurierAvantRamassage + 1);
                tresor.setNombre(nombreTresorsAvantRamassage - 1);
              }
            }
          }
        }
      }
    }
    return carte;
  }

  private void aventurierAvance(Aventurier aventurier, char mouvement) {
    if (aventurier.getOrientation().toString().equals("N")) {
      aventurier.unPasNord();
    } else if (aventurier.getOrientation().toString().equals("E")) {
      aventurier.unPasEst();
    } else if (aventurier.getOrientation().toString().equals("S")) {
      aventurier.unPasSud();
    } else if (aventurier.getOrientation().toString().equals("O")) {
      aventurier.unPasOuest();
    }
  }

  private void aventurierPivote(Aventurier aventurier, char mouvement) {
    if (mouvement == 'D') {
      aventurier.setOrientation(Aventurier.orientationByCode((aventurier.getOrientation().getCode() + 1) % 4));
    } else if (mouvement == 'G') {
      aventurier.setOrientation(Aventurier.orientationByCode((aventurier.getOrientation().getCode() + 3) % 4));
    }
  }
}
