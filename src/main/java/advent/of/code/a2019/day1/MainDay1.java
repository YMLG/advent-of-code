package advent.of.code.a2019.day1;

import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay1 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static int getReponseQuestion1() {
    return getIntData().mapToInt(MainDay1::getFuelNeeded).sum();
  }
  
  private static int getReponseQuestion2() {
    return getIntData().mapToInt(MainDay1::getRealFuelNeeded).sum();
  }

  private static int getFuelNeeded(int mass) {
    return (mass/3)-2;
  }

  private static int getRealFuelNeeded(int mass) {
    int res =getFuelNeeded(mass);
    int total = 0;
    while(res > 0) {
      total += res;
      res = getFuelNeeded(res);
    }
    return total;
  }
  
  private static Stream<Integer> getIntData() {
    return LectureFichiersUtils.getData("2019/input1.txt").map(Integer::valueOf);
  }
}
