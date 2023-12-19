package main.java.entities;

import java.util.List;

public class Carte {
  
  private int[] size;
  
  private List<Montagne> montagnes;
  
  private List<Tresor> tresors;
  
  private List<Aventurier> aventuriers;
  
  public Carte() {}

  public Carte(int[] size, List<Montagne> montagnes, List<Tresor> tresors, List<Aventurier> aventuriers) {
    super();
    this.size = size;
    this.montagnes = montagnes;
    this.tresors = tresors;
    this.aventuriers = aventuriers;
  }

  public int[] getSize() {
    return size;
  }

  public void setSize(int[] size) {
    this.size = size;
  }
  
  public Carte size(int[] size) {
    this.size = size;
    return this;
  }

  public List<Montagne> getMontagnes() {
    return montagnes;
  }

  public void setMontagnes(List<Montagne> montagnes) {
    this.montagnes = montagnes;
  }
  
  public Carte montagnes(List<Montagne> montagnes) {
    this.montagnes = montagnes;
    return this;
  }

  public List<Tresor> getTresors() {
    return tresors;
  }

  public void setTresors(List<Tresor> tresors) {
    this.tresors = tresors;
  }
  
  public Carte tresors(List<Tresor> tresors) {
    this.tresors = tresors;
    return this;
  }

  public List<Aventurier> getAventuriers() {
    return aventuriers;
  }

  public void setAventuriers(List<Aventurier> aventuriers) {
    this.aventuriers = aventuriers;
  }
  
  public Carte aventuriers(List<Aventurier> aventuriers) {
    this.aventuriers = aventuriers;
    return this;
  }
  
}
