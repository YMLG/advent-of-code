package advent.of.code.a2018.day13;

public class Position  implements Comparable<Position>{
  
    private int x;
    
    private int y;
    
    private char c;

    
    
    public Position(int x, int y, char c) {
      this.x = x;
      this.y = y;
      if('v' == c || '^' == c) {
        this.c = '|';
      }else if('>' == c || '<' == c) {
        this.c = '-';
      }else {
        this.c = c;
      }
    }

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
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

    public char getC() {
      return c;
    }

    public void setC(char c) {
      this.c = c;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Position other = (Position) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return x + "," + y;
    }

    @Override
    public int compareTo(Position o) {
      return toString().compareTo(o.toString());
    }
    
    
}
