package main.java;

import java.io.File;

import main.java.services.CarteService;
import main.java.view.ConsolePrint;

public class Application {

  public static void main(String[] args) {
    String directory = "../../cartes/";
    ConsolePrint consolePrint = new ConsolePrint(new CarteService());

    File[] fichiersTest = new File(directory).listFiles();
    for (File fichier : fichiersTest) {
      String nomFichier = fichier.getName();
      System.out.println("----------------------------------------------------\n"
      + "---   Fichier au nom: " + nomFichier + "\n"
      + "----------------------------------------------------\n");
      consolePrint.fichierDepart(directory, nomFichier);
      System.out.println("Après exécution des mouvements, voici le résultat:\n");
      consolePrint.carteResultat(directory, nomFichier);
    }
  }
}
