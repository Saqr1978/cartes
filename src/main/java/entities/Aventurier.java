package main.java.entities;

import java.util.ArrayList;
import java.util.List;

import main.java.exceptions.OrientationException;
import main.java.exceptions.UnknownIdentifierException;

public class Aventurier {
  public enum Orientation {
    N(0), E(1), S(2), O(3);

    int code;

    Orientation(int code) {
      this.code = code;
    }

    public int getCode() {
      return this.code;
    }
  }

  private String nom;

  private int absciss;

  private int ordinate;

  private Orientation orientation;

  private String sequenceMouvements;

  private int ordreApparition;
  
  private int nombreTresorsRamasses;

  public Aventurier() {
    super();
  }

  public Aventurier(String nom, int absciss, int ordinate, Orientation orientation, String sequenceMouvements,
      int ordreApparition, int nombreTresorsRamasses) {
    super();
    this.nom = nom;
    this.absciss = absciss;
    this.ordinate = ordinate;
    this.orientation = orientation;
    this.sequenceMouvements = sequenceMouvements;
    this.ordreApparition = ordreApparition;
    this.nombreTresorsRamasses = nombreTresorsRamasses;
  }

  public int[] positionPostMouvement(String sequenceMouvements) {
    int[] position = new int[2];
    return position;
  }

  /**
   * Un pas vers le Nord
   */
  public void unPasNord() {
    this.ordinate--;
  }

  /**
   * Un pas vers le Sud
   */
  public void unPasSud() {
    this.ordinate++;
  }

  /**
   * Un pas vers l'Est
   */
  public void unPasEst() {
    this.absciss++;
  }

  /**
   * Un pas vers l'Ouest
   */
  public void unPasOuest() {
    this.absciss--;
  }

  /**
   * Retourne sous forme d'une liste à deux éléments la position éventuelle après
   * mouvement afin d'évaluer la possibilité suivant les contraintes, A savoir ni
   * montagne ni frontière ni autre aventurier apparu avant dans le fichier
   * d'entrée
   * 
   * @return liste contenant éventuelles prochaines abscisse (indice 0) et
   *         ordonnée (indice 1)
   */
  public List<Integer> eventuellePositionSuivante(char mouvement) {
    try {
      if (!this.orientation.toString().equals("N") && !this.orientation.toString().equals("E") && !this.orientation.toString().equals("S")
          && !this.orientation.toString().equals("O")) {
        throw new UnknownIdentifierException(
            "Orientation inconnue:\nles seules orientations possibles sont N, E, S et O.");
      }
      if (mouvement != 'D' && mouvement != 'G' && mouvement != 'A') {
        throw new UnknownIdentifierException("Mouvement impossible:\nles seules mouvements possibles sont D, G et A.");
      }
    } catch (UnknownIdentifierException uie) {
      System.err.println(uie.getMessage());
    }
    
    List<Integer> position = new ArrayList<>();
    if (mouvement == 'A') {
      if (this.orientation.toString().equals("N")) {
        position = List.of(this.absciss, this.ordinate - 1);
      } else if (this.orientation.toString().equals("E")) {
        position = List.of(this.absciss + 1, this.ordinate);
      } else if (this.orientation.toString().equals("S")) {
        position = List.of(this.absciss, this.ordinate + 1);
      } else if (this.orientation.toString().equals("O")) {
        position = List.of(this.absciss - 1, this.ordinate);
      }
    } else {
      position = List.of(this.absciss, this.ordinate);
    }
    return position;
  }

  public static Orientation orientationByCode(int code) {
    try {
      if (code < 0 || code > 3) {
        throw new OrientationException(
            "Les seuls codes numériques d'orientation aurotisées sont 0 pour N, 1 pour E, 2 pour S et 3 pour O");
      }
    } catch (OrientationException oce) {
      System.err.println(oce.getMessage());
    }
    Orientation orientationReturn = null;
    for (Orientation orientation : Orientation.values()) {
      if (code == orientation.getCode()) {
        orientationReturn = orientation;
      }
    }
    return orientationReturn;
  }
  
  public static Orientation orientationByName(String name) {
    try {
      if (!name.equals("N") && !name.equals("E") && !name.equals("S") && !name.equals("O")) {
        throw new OrientationException(
            "Les seuls symboles d'orientation autorisées sont N, E, S et O");
      }
    } catch (OrientationException oce) {
      System.err.println(oce.getMessage());
    }
    Orientation orientationReturn = null;
    for (Orientation orientation : Orientation.values()) {
      if (name.equals(orientation.toString())) {
        orientationReturn = orientation;
      }
    }
    return orientationReturn;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Aventurier nom(String nom) {
    this.nom = nom;
    return this;
  }

  public int getAbsciss() {
    return absciss;
  }

  public void setAbsciss(int absciss) {
    this.absciss = absciss;
  }

  public Aventurier absciss(int absciss) {
    this.absciss = absciss;
    return this;
  }

  public int getOrdinate() {
    return ordinate;
  }

  public void setOrdinate(int ordinate) {
    this.ordinate = ordinate;
  }

  public Aventurier ordinate(int ordinate) {
    this.ordinate = ordinate;
    return this;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  public Aventurier orientation(Orientation orientation) {
    this.orientation = orientation;
    return this;
  }

  public String getSequenceMouvements() {
    return sequenceMouvements;
  }

  public void setSequenceMouvements(String sequenceMouvements) {
    this.sequenceMouvements = sequenceMouvements;
  }

  public Aventurier sequenceMouvements(String sequenceMouvements) {
    this.sequenceMouvements = sequenceMouvements;
    return this;
  }

  public int getOrdreApparition() {
    return ordreApparition;
  }

  public void setOrdreApparition(int ordreApparition) {
    this.ordreApparition = ordreApparition;
  }

  public Aventurier ordreApparition(int ordreApparition) {
    this.ordreApparition = ordreApparition;
    return this;
  }

  public int getNombreTresorsRamasses() {
    return nombreTresorsRamasses;
  }

  public void setNombreTresorsRamasses(int nombreTresorsRamasses) {
    this.nombreTresorsRamasses = nombreTresorsRamasses;
  }
  
  public Aventurier nombreTresorsRamasses(int nombreTresorsRamasses) {
    this.nombreTresorsRamasses = nombreTresorsRamasses;
    return this;
  }
}
