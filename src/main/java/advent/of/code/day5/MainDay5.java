package advent.of.code.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;
import advent.of.code.utils.StringUtils;

public class MainDay5 {

  public static void main(String[] args) {
    String input = LectureFichiersUtils.getData("fichier5.txt").findFirst().orElse(null);
    
    System.out.println("Question 1 - "+ getReponseQuestion1(input));
    System.out.println("Question 2 - "+ getReponseQuestion2(input));

  }

  private static int getReponseQuestion1(String input) {
    return resolve(input).length();
  }

  
  private static int getReponseQuestion2(String input) {
    return StringUtils.toCharStream(input)
        .map(Character::toLowerCase)
        .distinct()
        .map(l -> input.replaceAll("(?i)"+l, ""))
        .map(MainDay5::resolve)
        .map(String::length)
        .min(Comparator.naturalOrder())
        .orElse(null)
        ;
  }
  
  private static String resolve(String input) {
    ArrayList<Character> out = StringUtils.toCharStream(input)
      .collect(ArrayList<Character>::new, 
                    (l, c) -> {
                      if(l.size()>0 && react(l.get(l.size() - 1), c)) {
                        l.remove(l.size() - 1);
                      }else{
                        l.add(c);
                      }
                    },
                    (l1, l2) -> {
                      if(l1.size()>0 && react(l1.get(l1.size() - 1), l2.get(0))) {
                        l1.remove(l1.size() - 1);
                        l2.remove(0);
                      }
                      l1.addAll(l2);
                    });
    return out.stream().map(String::valueOf).collect(Collectors.joining(""));
  }
 
  private static boolean react(char a, char b) {
    return a != b && Character.toUpperCase(a) == Character.toUpperCase(b);
  }
}
