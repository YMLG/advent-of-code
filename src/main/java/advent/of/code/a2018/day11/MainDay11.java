package advent.of.code.a2018.day11;

public class MainDay11 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());
  }

  
  private static String getReponseQuestion1() {
    int serial = 5235;
    Grid g = new Grid(serial);
    Cell maxCell = null;
    int maxP = 0;
    for(int x=1; x<299; x++) {
      for(int y=1; y<299; y++) {
        int p = 0;
        for(int i=0; i<3;i++) {
          for(int j=0; j<3;j++) {
            p += g.getCell(x+i,y+j).getPower();
          }
        }
        if(p>maxP) {
          maxP = p;
          maxCell = g.getCell(x,y);
        }
      }

    }

    return maxCell.toString();
  }
  
  
  private static String getReponseQuestion2() {
    int serial = 5235;
    Grid g = new Grid(serial);
    Square maxSquare = null;
    int maxP = 0;
    for(int x=1; x<301; x++) {
      for(int y=1; y<301; y++) {
        Cell corner = g.getCell(x,y);
        int powerN =0;
        for(int size=1; size<301-Integer.max(x,y)+1;size++) {
          powerN = powerSquareNPlusUn(powerN, corner, size-1, g);
          if(powerN>maxP) {
            maxP = powerN;
            maxSquare = new Square(g.getCell(x,y), size);
          }
        }
      }

    }
    return  maxSquare.toString();
  }

  private  static int powerSquareNPlusUn(int powerSquareN, Cell corner, int n, Grid g) {
    for(int i=0; i<n;i++) {
      powerSquareN += g.getCell(corner.getX()+n, corner.getY()+i).getPower() + g.getCell(corner.getX()+i, corner.getY()+n).getPower();
    }
    powerSquareN += g.getCell(corner.getX()+n, corner.getY()+n).getPower();
    return powerSquareN;
  }


}
