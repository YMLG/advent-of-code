package advent.of.code.day6;

public class Position {
  private int x;
  
  private int y;

  public Position(String s) {
    String[] p = s.split(", ");
    this.x = Integer.parseInt(p[0]);
    this.y = Integer.parseInt(p[1]);
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String getIndent() {
    return x+"#"+y;
  }
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
  
  public int distance(Position p) {
    return Math.abs(x - p.getX()) + Math.abs(y - p.getY());
  }
}
