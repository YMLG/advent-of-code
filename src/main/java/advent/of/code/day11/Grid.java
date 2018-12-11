package advent.of.code.day11;

import java.util.HashMap;
import java.util.Map;

public class Grid {

  
  private int serial;
  
  private Map<Integer , Map<Integer, Cell>> cells = new HashMap<>();

  public Grid(int serial) {
    this.serial = serial;
  }
  
  public Cell getCell(Integer x, Integer y) {
    Cell c = new Cell(x, y, serial);
    if(cells.containsKey(x)) {
      Map<Integer, Cell> m = cells.get(x);
      if(m.containsKey(y)) {
        return m.get(y);
      }
      m.put(y, c);
    }else {
      Map<Integer, Cell> m = new HashMap<>();
      m.put(y, c);
      cells.put(x, m);
    }
    return c;
  }
  
  
}
