package main.java;

import java.util.Scanner;

import main.java.services.CarteService;
import main.java.view.ConsolePrint;

public class Application {
  
  public static void main(String[] args) {
    String directory = "../../cartes/";
    ConsolePrint consolePrint = new ConsolePrint(new CarteService());
    Scanner sc = new Scanner(System.in);
    System.out.println("Entrez le nom du fichier de test (dans le dossier \"cartes\") à lire:");
    String nomFichier = sc.nextLine();
    consolePrint.fichierDepart(directory, nomFichier);
    System.out.println("Après exécution des mouvements, voici le résultat:\n");
    consolePrint.carteResultat(directory, nomFichier);
  }
}