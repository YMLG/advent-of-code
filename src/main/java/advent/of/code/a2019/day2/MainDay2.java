package advent.of.code.a2019.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay2 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static int getReponseQuestion1() {
    return produce(12, 2);
  }
  
  private static int getReponseQuestion2() {
    for(int i =0; i< 100; i++) {
      for(int j =0; j< 100; j++) {
        if(produce(i, j) ==19690720) {
          return 100*i+j;
        }
      }
    }
    return 0;
  }


  private static int produce(int noun, int verb) {
    Integer[] tableau = getIntData().toArray(Integer[]::new);
    tableau[1] = noun;
    tableau[2] = verb;    
    for(int i = 0; i <tableau.length; i = i+4) {
      if(tableau[i] == 99) {
        break;
      }
      tableau[tableau[i+3]] = getValue(tableau[i], tableau[tableau[i+1]], tableau[tableau[i+2]]);
    }
    return tableau[0];
  }
  
  private static Integer getValue(int code, int v1, int v2){
    switch (code) {
    case 1:
      return v1 + v2;
    case 2:
      return v1 * v2;
    default:
      return null;
    }
  }
  
  private static Stream<Integer> getIntData() {
    return LectureFichiersUtils.getData("2019/input2.txt")
        .map(l -> Arrays.asList(l.split(",")))
        .flatMap(List::stream)
        .map(Integer::valueOf)
        ;
  }
}
