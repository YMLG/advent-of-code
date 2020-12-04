package advent.of.code.a2020.day3;

import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay3 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
	return nbArbreDeplacement(1, 3);
  }

private static long nbArbreDeplacement(int ligne, int colonne) {
	List<String> data = getData() ;
	long nbArbre = 0;
	int i = 0;
	int j = 0;
	while (i< data.size()) {
		nbArbre += get(i,j, data)=='#' ? 1 : 0;
		i+=ligne;
		j+=colonne;
	}
	return nbArbre;
}
  
  private static long getReponseQuestion2() {
	  return nbArbreDeplacement(1, 3) 
			  * nbArbreDeplacement(1, 1)
			  * nbArbreDeplacement(1, 5)
			  * nbArbreDeplacement(1, 7)
			  * nbArbreDeplacement(2, 1);
  }

  private static char get(int i, int j, List<String> data) {
	  String ligne =  data.get(i);
	  return ligne.charAt(j % ligne.length());
  }

  private static List<String> getData() {
    return LectureFichiersUtils.getData("2020/input3.txt").collect(Collectors.toList());
  }
}
