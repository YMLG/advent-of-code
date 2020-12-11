package advent.of.code.a2020.day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay11 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		Grille grille = new GrilleRules1(getData());
		grille.run();
		return grille.nbOccupied();
	}
		
	private static long getReponseQuestion2() {
		Grille grille = new GrilleRules2(getData());
		grille.run();
		return grille.nbOccupied();
	}

	
	
	private static List<List<String>> getData() {
		return LectureFichiersUtils.getData("2020/input11.txt").map(s -> Arrays.asList(s.split(""))).collect(Collectors.toList());
	}
}
