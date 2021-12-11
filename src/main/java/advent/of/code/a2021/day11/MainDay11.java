package advent.of.code.a2021.day11;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.Grille;
import advent.of.code.utils.LectureFichiersUtils;

public class MainDay11 {
	

	private static Grille<Dumbo> grille;


	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());	
	
	}

	private static long getReponseQuestion1() {
		grille = new Grille<Dumbo>(getData().map(l -> Arrays.asList(l.split("")).stream()
				.map(Integer::parseInt)
				.map(Dumbo::new)
				.collect(Collectors.toList()))
				.collect(Collectors.toList()));
		for(int i = 0; i < 100; i++) {
			grille.getAll().peek(Dumbo::unFlash).forEach(Dumbo::increment);
			flash();
		}
		return Dumbo.getNbFash();
	}

	
	private static void flash() {
		boolean flash = true;
		while(flash) {
			flash = false;
			for(int i = 0; i < grille.getMap().size(); i++) {
				for(int j =0; j < grille.getMap().get(i).size(); j++) {
					if(grille.get(i, j).get().flash()) {
						grille.getVoisines(i, j).forEach(Dumbo::increment);
						flash = true;
					}
				}
			}
		}
	}
	
	private static long getReponseQuestion2() {
		grille = new Grille<Dumbo>(getData().map(l -> Arrays.asList(l.split("")).stream()
				.map(Integer::parseInt)
				.map(Dumbo::new)
				.collect(Collectors.toList()))
				.collect(Collectors.toList()));
		int i =0;
		while(!grille.getAll().allMatch(Dumbo::isFlashed)) {
			grille.getAll().peek(Dumbo::unFlash).forEach(Dumbo::increment);
			flash();
			i++;
		}
		return i;
	}




	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input11.txt");
	}
	
}
