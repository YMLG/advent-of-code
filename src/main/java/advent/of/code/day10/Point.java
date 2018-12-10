package advent.of.code.day10;

public class Point {
  
    private int x;
    
    private int y;
    
    private int v1;
    
    private int v2;

    
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    public Point(String s) {
      String[] split = s.split("> velocity=<");
      String[] c = split[0].replaceAll("position=<", "").split(", ");
      String[] v = split[1].replaceAll(">", "").split(", ");
      this.x = Integer.valueOf(c[0].trim());
      this.y = Integer.valueOf(c[1].trim());
      this.v1 = Integer.valueOf(v[0].trim());
      this.v2 = Integer.valueOf(v[1].trim());
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

    public int getV1() {
      return v1;
    }

    public void setV1(int v1) {
      this.v1 = v1;
    }

    public int getV2() {
      return v2;
    }

    public void setV2(int v2) {
      this.v2 = v2;
    }

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + ", v1=" + v1 + ", v2=" + v2 + "]";
    }
    
    public void run() {
      x += v1;
      y += v2;
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
      Point other = (Point) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }
    
    
    
}
