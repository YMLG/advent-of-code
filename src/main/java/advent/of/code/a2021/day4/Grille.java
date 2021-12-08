package advent.of.code.a2021.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Grille {

	
	private String[][] grille;

	public Grille(List<List<String>> input) {
		grille = new String[input.size()][input.size()];
		for(int i = 0; i < input.size(); i++) {
			for(int j = 0; j < input.size(); j++) {
				grille[i][j] = input.get(i).get(j);
			}
		}
	}
	
	public void remove(String number) {
		for(int i = 0; i < grille.length; i++) {
			for(int j = 0; j < grille.length; j++) {
				if(number.equals(grille[i][j])) {
					grille[i][j] = null;
				}
			}
		}
	}

	
	public boolean isTermine() {
		return IntStream.range(0, grille.length)
			.mapToObj(i -> IntStream.range(0, grille.length)
						.mapToObj(j -> grille[i][j])
						.filter(Objects::nonNull)
						.findAny())
			.filter(Optional::isEmpty)
			.findAny()
			.isPresent()
		|| 
		IntStream.range(0, grille.length)
		.mapToObj(i -> IntStream.range(0, grille.length)
					.mapToObj(j -> grille[j][i])
					.filter(Objects::nonNull)
					.findAny())
		.filter(Optional::isEmpty)
		.findAny().isPresent();
	}

	public long somme() {
		return Arrays.asList(grille).stream()
			.map(Arrays::asList)
			.flatMap(List::stream)
			.filter(Objects::nonNull)
			.mapToInt(Integer::parseInt)
			.sum();
	}

}
