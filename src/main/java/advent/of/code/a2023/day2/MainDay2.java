package advent.of.code.a2023.day2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay2 {

	private static final String BLUE = "blue";
	private static final String GREEN = "green";
	private static final String RED = "red";

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());
	}

	private static long getReponseQuestion1() {
		return  getData().stream().filter(MainDay2::isValid).mapToInt(Game::getId).sum();
	}	


	private static boolean isValid(Game game) {
		return Arrays.asList(game.getData().split("; ")).stream().allMatch(MainDay2::isValid);
	}

	private static boolean isValid(String tirage) {
		return Arrays.asList(tirage.split(", ")).stream().allMatch(MainDay2::isPossible);
	}
	private static boolean isPossible(String tirageCouleur) {
		switch (tirageCouleur.split(" ")[1]) {
		case RED:
			return Integer.parseInt(tirageCouleur.split(" ")[0]) < 13;
		case GREEN:
			return Integer.parseInt(tirageCouleur.split(" ")[0]) < 14;
		case BLUE:
			return Integer.parseInt(tirageCouleur.split(" ")[0]) < 15;
		default:
			return false;
		}
	}


	private static long getReponseQuestion2() {
		return getData().stream().mapToLong(MainDay2::getPuissance).sum();

	}

	private static long getPuissance(Game game) {
		Map<String, Integer> min = new HashMap<>(Map.of(RED, 0, GREEN, 0, BLUE, 0));
		Arrays.asList(game.getData().split("[;,] ")).stream().forEach(tirageCouleur ->{
			String couleur = tirageCouleur.split(" ")[1];
			Integer nombre = Integer.parseInt(tirageCouleur.split(" ")[0]);
			if(min.get(couleur)< nombre) {
				min.put(couleur, nombre);
			}
		});
		return min.values().stream().reduce((a,b) -> a * b).orElse(0);
	}

	private static List<Game> getData() {
		return LectureFichiersUtils.getData("2023/input2.txt").map(Game::new).collect(Collectors.toList());
	}
}
