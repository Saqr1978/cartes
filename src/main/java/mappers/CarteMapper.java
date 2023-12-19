package main.java.mappers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.entities.Aventurier;
import main.java.entities.Carte;
import main.java.entities.Montagne;
import main.java.entities.Tresor;
import main.java.exceptions.UnknownIdentifierException;

public class CarteMapper {

  public static Carte readFile(File file) {
    Carte carte = new Carte().aventuriers(new ArrayList<Aventurier>())
        .montagnes(new ArrayList<Montagne>()).tresors(new ArrayList<Tresor>());
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      int ordreApparition = 0;
      while((line = br.readLine()) != null) {
        String[] lineInfos = line.split(" - ");
        String firstChar = lineInfos[0];
        if ("C".equals(firstChar)) {
          int[] size = new int[2];
          size[0] = Integer.parseInt(lineInfos[1]);
          size[1] = Integer.parseInt(lineInfos[2]);
          carte.setSize(size);
        } else if ("M".equals(firstChar)) {
          List<Integer> position = List.of(Integer.parseInt(lineInfos[1]),Integer.parseInt(lineInfos[2]));
          
          carte.getMontagnes().add(
              new Montagne().position(position)
          );
        } else if ("T".equals(firstChar)) {
          List<Integer> position = List.of(Integer.parseInt(lineInfos[1]), Integer.parseInt(lineInfos[2]));
          carte.getTresors().add(
              new Tresor().position(position)
                          .nombre(Integer.parseInt(lineInfos[3]))
          );
        } else if ("A".equals(firstChar)) {
          carte.getAventuriers().add(
              new Aventurier().absciss(Integer.parseInt(lineInfos[2]))
                              .ordinate(Integer.parseInt(lineInfos[3]))
                              .nom(lineInfos[1])
                              .orientation(Aventurier.orientationByName(lineInfos[4]))
                              .sequenceMouvements(lineInfos[5])
                              .ordreApparition(ordreApparition++)
          );
        } else {
          throw new UnknownIdentifierException("La première lettre de la ligne ne pointe sur aucun élément connu,\n"
              + "voici les premières lettres autorisées:\n"
              + "- C comme Carte\n"
              + "- M comme Montagne\n"
              + "- T comme Trésor\n"
              + "- A comme Aventurier\n");
        }
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return carte;
  }
}
