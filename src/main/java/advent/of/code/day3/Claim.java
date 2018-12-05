package advent.of.code.day3;

public class Claim {
  private int id;

  private int x;

  private int y;

  private int l;

  private int h;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getL() {
    return l;
  }

  public void setL(int l) {
    this.l = l;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public Claim(String ligne) {
    String[] datas = ligne.split("#| @ | |,|: |x");
    this.id = Integer.parseInt(datas[1]);
    this.x = Integer.parseInt(datas[2]);
    this.y = Integer.parseInt(datas[3]);
    this.l = Integer.parseInt(datas[4]);
    this.h = Integer.parseInt(datas[5]);
  }


}
