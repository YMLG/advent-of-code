package advent.of.code.a2019.day4;

import java.util.stream.IntStream;

public class MainDay4 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
    return IntStream.range(156218,652527).filter(MainDay4::isMdPValid).count();
  }
  
  private static long getReponseQuestion2() {

    return IntStream.range(156218,652527).filter(MainDay4::isMdPValid2).count();
  }


  private static boolean isMdPValid(Integer mdp) {
    int[] digits = String.valueOf(mdp).chars().toArray();
    boolean haveDouble = IntStream.range(1, digits.length).boxed()
        .anyMatch(i -> digits[i] == digits[i-1]);
    return haveDouble && increase(digits);
  }

  private static boolean isMdPValid2(Integer mdp) {
    int[] digits = String.valueOf(mdp).chars().toArray();
    boolean haveDouble = IntStream.range(1, digits.length).boxed()
        .anyMatch(i ->  {
          return digits[i] == digits[i-1] && (i==1 || digits[i-1] != digits[i-2]) && (i==digits.length-1 || digits[i] != digits[i+1]);
        });
    return haveDouble && increase(digits);
  }
  
  private static boolean increase(int[] digits) {
    return IntStream.range(1, digits.length).boxed()
        .allMatch(i -> digits[i] >= digits[i-1]);
  }
}
