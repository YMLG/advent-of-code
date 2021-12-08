package advent.of.code.a2021.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay4 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		String[] data = getData().split("\n\n", 2);
		String[] numbers = data[0].split(",");
		List<Grille> grilles = getGrilles(data[1]);
		int i = 0;
		Optional<Grille> gagnant = Optional.empty();
		while(gagnant.isEmpty() && i++ < numbers.length ) {
			for(Grille g : grilles) {
				g.remove(numbers[i]);
			}
			gagnant = grilles.stream().filter(Grille::isTermine).findAny();

		}
		if(gagnant.isPresent()) {
			return gagnant.get().somme() * Integer.parseInt(numbers[i]);
		}
		return  0;
	}

	
	private static long getReponseQuestion2() {
		String[] data = getData().split("\n\n", 2);
		String[] numbers = data[0].split(",");
		List<Grille> grilles = getGrilles(data[1]);
		int i = 0;
		Optional<Grille> gagnant = Optional.empty();
		while(grilles.size() > 0 && i++ < numbers.length ) {
			for(Grille g : grilles) {
				g.remove(numbers[i]);
			}
			grilles.removeIf(Grille::isTermine);
			if(grilles.size() == 1) {
				gagnant = Optional.of(grilles.get(0));
			}

		}
		if(gagnant.isPresent()) {
			return gagnant.get().somme() * Integer.parseInt(numbers[i]);
		}
		return  0;

	}

	private static List<Grille> getGrilles(String data) {
		return Arrays.asList(data.split("\n\n")).stream()
				.map(s -> Arrays.asList(s.split("\n")).stream()
						   .map(l ->  Arrays.asList(l.trim().split("\\s+")))
						.collect(Collectors.toList()))
				.map(Grille::new)
				.collect(Collectors.toList());
	}
	


	private static String getData() {
		return LectureFichiersUtils.getStringData("2021/input4.txt");
	}
}
