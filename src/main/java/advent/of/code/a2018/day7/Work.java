package advent.of.code.a2018.day7;

public class Work  implements Comparable<Work>{

  private String lettre = "";
  
  private Integer time = 0;
  
  public Work() {
    
  }
  
  public Work(String lettre) {
    this.lettre = lettre;
    this.time = lettre.charAt(0) - 'A' + 61;
  }
  
  public Work(String lettre, Integer time) {
    this.lettre = lettre;
    this.time = time;
  }
  
  public String getLettre() {
    return lettre;
  }

  public void setLettre(String lettre) {
    this.lettre = lettre;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }
  
  public void removeTime(int time) {
    this.time = Math.max(0, this.time - time); 
  }

  @Override
  public int compareTo(Work o) {
    // TODO Auto-generated method stub
    return time.compareTo(o.getTime());
  }
  @Override
  public String toString() {
    return " [" + lettre + ", " + time + "]";
  }
  
  
  
}
