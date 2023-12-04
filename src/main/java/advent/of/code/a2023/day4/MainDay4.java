package advent.of.code.a2023.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay4 {
	
	private static final List<String> data = getData();

	private static final Map<Integer, Double> points = new HashMap<>();
	
	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());
	}

	private static double getReponseQuestion1() {
		return data.stream()
			.map(MainDay4::getNumeroGagnants)
			.filter(Predicate.not(List::isEmpty))
			.map(List::size)
			.mapToDouble(s -> Math.pow(2,s-1))
			.sum();
	}	


	private static long getReponseQuestion2() {
		long sum = 0;
		for(int i = 0; i< data.size(); i++) {
			sum += getNbCarte(i);
		}
		return sum;

	}

	
	private static double getNbCarte(int i) {
		if(!points.containsKey(i)) {
			points.put(i, calculeNbCartes(i));
		}
		return points.get(i);
	}

	private static double calculeNbCartes(int i) {
		int nbNumeros = getNumeroGagnants(data.get(i)).size();
		double nbCartes = 1;
		for(int n = 1; n < nbNumeros +1; n++) {
			nbCartes += getNbCarte(i +n);
		}
		return nbCartes;
	}

	
	
	private static List<String> getNumeroGagnants(String ligne) {
		String[] data = ligne.split("(:\\s)|(\\s\\|\\s)");
		List<String> numerosGagnants = Arrays.asList(data[1].split("\\s+"));
		List<String> numeros = new ArrayList<>(Arrays.asList(data[2].split("\\s+")));
		numeros.retainAll(numerosGagnants);
		return numeros;
	}

	private static List<String> getData() {
		return LectureFichiersUtils.getData("2023/input4.txt").collect(Collectors.toList());
	}
}
