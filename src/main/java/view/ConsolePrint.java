package main.java.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import main.java.entities.Aventurier;
import main.java.entities.Carte;
import main.java.entities.Montagne;
import main.java.entities.Tresor;
import main.java.services.CarteService;

public class ConsolePrint {

  CarteService carteService;

  public ConsolePrint() {
  }

  public ConsolePrint(CarteService carteService) {
    this.carteService = carteService;
  }
  
  /**
   * Affiche le fichier d'antrée
   * 
   * @param path
   * @param filename
   */
  public void fichierDepart(String path, String filename) {
    File file = new File(path + filename);
    StringBuilder fichierDepart = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while((line = br.readLine()) != null) {
        fichierDepart.append(line + "\n");
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    System.out.println(fichierDepart);
  }

  /**
   * Imprime en console le résultat final après tous les mouvements des
   * aventuriers
   * 
   * @param path:     chemin du fichier à lire
   * @param fileName: nom du fichier à lire
   */
  public void carteResultat(String path, String fileName) {
    Carte carte = carteService.carteApresExploration(path, fileName);
    List<Montagne> montagnes = carte.getMontagnes();
    List<Tresor> tresors = carte.getTresors();
    List<Aventurier> aventuriers = carte.getAventuriers();
    StringBuilder printedResult = new StringBuilder(
        "C" + " - " + carte.getSize()[0] + " - " + carte.getSize()[1] + "\n");

    for (Montagne montagne : montagnes) {
      printedResult.append("M - " + montagne.getPosition().get(0) + " - " + montagne.getPosition().get(1) + "\n");
    }

    for (Tresor tresor : tresors) {
      printedResult.append(
          "T - " + tresor.getPosition().get(0) + " - " + tresor.getPosition().get(1) + " - " + tresor.getNombre() + "\n");
    }

    for (Aventurier aventurier : aventuriers) {
      printedResult.append("A - " + aventurier.getNom() + " - " +aventurier.getAbsciss() + " - " + aventurier.getOrdinate() + " - "
          + aventurier.getOrientation() + " - " + aventurier.getNombreTresorsRamasses() + "\n");
    }
    
    System.out.println(printedResult);
  }
}
