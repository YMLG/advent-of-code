package advent.of.code.a2019.day3;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Point implements Cloneable {

  private int x;
  private int y;
  
  private int distance = 0;
  
  public Point(int x,int y) {
    this.x = x;
    this.y = y;
  }
  public Point(int x,int y, int distance) {
    this.x = x;
    this.y = y;
    this.distance = distance;
  }

  @Override
  protected Point clone() {
    try {
      return (Point) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
  
  public int getDistance() {
    return distance;
  }
  
  public int getDistanceToCenter() {
    return Math.abs(x)+Math.abs(y);
  }
  
  public Set<Point> move(String instruction) {
    return IntStream.range(0, Integer.valueOf(instruction.substring(1)))
        .mapToObj(i-> oneMove(instruction.charAt(0)))
        .collect(Collectors.toSet());
  }
  
  private Point oneMove(char direction){
    distance++;
    switch (direction) {
    case 'R':
      x++;
      break;
    case 'L':
      x--;
      break;
    case 'U':
      y++;
      break;
    case 'D':
      y--;
      break;
    default:
      break;
    }
    return clone();
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

  @Override
  public String toString() {
    return "Point [x=" + x + ", y=" + y + "]";
  }
  
  
  
}
