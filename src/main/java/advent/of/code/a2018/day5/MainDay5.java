package advent.of.code.a2018.day5;

import java.util.Comparator;

import advent.of.code.utils.LectureFichiersUtils;
import advent.of.code.utils.StringUtils;

public class MainDay5 {

  public static void main(String[] args) {
    String input = LectureFichiersUtils.getData("2018/fichier5.txt").findFirst().orElse(null);
    
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
    StringBuilder out = StringUtils.toCharStream(input)
      .collect(StringBuilder::new, 
                    (s, c) -> {
                      if(s.length()>0 && react(s.charAt(s.length()-1), c)) {
                        s.deleteCharAt(s.length()-1);
                      }else{
                        s.append(c);
                      }
                    },
                    (s1, s2) -> {
                      while (s1.length() > 0 && s2.length() > 0 && react(s1.charAt(s1.length() - 1), s2.charAt(0))) {
                        s1.deleteCharAt(s1.length()-1);;
                        s2.deleteCharAt(0);
                      }
                      s1.append(s2);
                    });
    return out.toString();
  }
 
  private static boolean react(char a, char b) {
    return a != b && Character.toUpperCase(a) == Character.toUpperCase(b);
  }
}
