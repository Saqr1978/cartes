package main.java.entities;

import java.util.List;

public class Tresor {
  
  private List<Integer> position;
  
  private int nombre;
  
  public Tresor() {}

  public Tresor(List<Integer> position, int nombre) {
    this.position = position;
    this.nombre = nombre;
  }

  public List<Integer> getPosition() {
    return position;
  }

  public void setPosition(List<Integer> position) {
    this.position = position;
  }
  
  public Tresor position(List<Integer> position) {
    this.position = position;
    return this;
  }

  public int getNombre() {
    return nombre;
  }

  public void setNombre(int nombre) {
    this.nombre = nombre;
  }
  
  public Tresor nombre(int nombre) {
    this.nombre = nombre;
    return this;
  }
}
