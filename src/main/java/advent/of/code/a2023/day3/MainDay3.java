package advent.of.code.a2023.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay3 {


	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());
	}

	private static long getReponseQuestion1() {
		Schema schema = getData();
		long somme = 0;
		for(int i = 0; i < schema.getData().size(); i ++) {
			List<String> ligne = schema.getData().get(i);
			for(int j = 0; j < ligne.size(); j ++) {
				if(schema.isNumber(i,j)) {
					String nombre = "";
					List<Optional<String>> voisines = new ArrayList<>();
					while(schema.isNumber(i,j)) {
						nombre += schema.getCase(i, j).get();
						voisines.addAll(schema.getVoisines(i, j));
						j++;
					}
					if(containChar(voisines)) {
						somme += Integer.parseInt(nombre);
					}

				}
			}
		}
		return  somme;
	}

	private static boolean containChar(List<Optional<String>> voisines) {
		return voisines.stream()
				.flatMap(Optional::stream)
				.anyMatch(c -> c.matches("\\D"));
	}	



	private static long getReponseQuestion2() {
		Schema schema = getData();
		long somme = 0;
		for(int i = 0; i < schema.getData().size(); i ++) {
			List<String> ligne = schema.getData().get(i);
			for(int j = 0; j < ligne.size(); j ++) {
				if(schema.isEngrenage(i,j)) {
					List<Long> voisines = schema.getNombresVoisins(i, j);
					if(voisines.size() == 2) {
						somme += (voisines.get(0) * voisines.get(1));
					}
				}
			}
		}
		return  somme;

	}



	private static Schema getData() {
		Schema schema = new Schema();
		LectureFichiersUtils.getData("2023/input3.txt")
		.map(l -> l.split(""))
		.map(Arrays::asList)
		.forEach(schema::addLigne);
		return schema;
	}
}
