package main.java.entities;

import java.util.List;

public class Montagne {
  
private List<Integer> position;
  
  public Montagne() {}

  public Montagne(List<Integer> position) {
    super();
    this.position = position;
  }
  
  public List<Integer> getPosition() {
    return position;
  }

  public void setPosition(List<Integer> position) {
    this.position = position;
  }
  
  public Montagne position(List<Integer> position) {
    this.position = position;
    return this;
  }

}
