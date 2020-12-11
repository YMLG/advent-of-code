package advent.of.code.a2020.day11;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GrilleRules1 extends Grille {

	public GrilleRules1(List<List<String>> datas) {
		super(datas);
	}

	@Override
	public boolean haveToLeave(int i, int j) {
		return nbAdjacent(i,j)>3;
	}

	@Override
	public boolean haveToSit(int i, int j) {
		return nbAdjacent(i,j)==0;
	}


	private long nbAdjacent(int i, int j) {
		return Arrays.asList(get(i-1, j-1),
				get(i-1, j),
				get(i-1, j+1),
				get(i, j-1),
				get(i, j+1),
				get(i+1, j-1),
				get(i+1, j),
				get(i+1, j+1)).stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(s -> s.equals("#"))
				.count()
				;
		
	}
}
