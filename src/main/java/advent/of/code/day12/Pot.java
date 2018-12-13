package advent.of.code.day12;

public class Pot {

  private boolean havePlant;
  
  private Boolean willHavePlant = null ;  
  
  public Pot(char c) {
    havePlant = c == '#';
  }
  public Pot() {
    havePlant = false;
  }
  public boolean isHavePlant() {
    return havePlant;
  }

  public void setHavePlant(boolean havePlant) {
    this.havePlant = havePlant;
  }

  public Boolean isWillHavePlant() {
    return willHavePlant;
  }

  public void setWillHavePlant(Boolean willHavePlant) {
    this.willHavePlant = willHavePlant;
  }
  
  public void run() {
    if(willHavePlant != null) {
      havePlant = willHavePlant;
      willHavePlant = null;
    }
  }
  
  @Override
  public String toString() {
    return havePlant ? "#" : ".";
  }
  
  
  
}
