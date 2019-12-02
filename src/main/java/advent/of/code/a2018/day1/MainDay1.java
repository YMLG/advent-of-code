package advent.of.code.a2018.day1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay1 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());
  }

  public static int getReponseQuestion1() {
    return getIntData().mapToInt(Integer::intValue).sum();
  }

  public static int getReponseQuestion2() {
    List<Integer> data = getIntData().collect(Collectors.toList());
    int sum = data.get(0);
    Set<Integer> listSum = new HashSet<>();
    int i=1;
    while(listSum.add(sum)) {
      sum += data.get(i++ % data.size());
    }
    return sum;
  }


  private static Stream<Integer> getIntData() {
    return LectureFichiersUtils.getData("2018/fichier1.txt").map(Integer::valueOf);
  }


}
