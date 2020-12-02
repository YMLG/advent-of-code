package advent.of.code.a2020.day2;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;
import advent.of.code.utils.StringUtils;

public class MainDay2 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
	return getData().filter(MainDay2::isValide).count();
  }
  
  private static long getReponseQuestion2() {
		return getData().filter(MainDay2::isValide2).count();
  }

  private static boolean isValide(String ligne) {
	  String[] elements = ligne.split("(:\\s|\\s|-)");
	  int min = Integer.parseInt(elements[0]);
	  int max = Integer.parseInt(elements[1]);
	  char letter = elements[2].charAt(0);
	  String mdp = elements[3];
	  long count = mdp.chars().filter(c -> c == letter).count();
	  return min <= count && max >= count;
  }
  
  private static boolean isValide2(String ligne) {
	  String[] elements = ligne.split("(:\\s|\\s|-)");
	  int min = Integer.parseInt(elements[0]);
	  int max = Integer.parseInt(elements[1]);
	  char letter = elements[2].charAt(0);
	  String mdp = elements[3];
	  return mdp.charAt(min-1) == letter ^ mdp.charAt(max-1) == letter;
  }

  private static Stream<String> getData() {
    return LectureFichiersUtils.getData("2020/input2.txt");
  }
}
