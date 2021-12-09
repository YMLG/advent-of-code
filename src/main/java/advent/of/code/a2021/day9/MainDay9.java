package advent.of.code.a2021.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay9 {
	
	private static List<List<Integer>> MAP;

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());		
	}

	private static long getReponseQuestion1() {
		MAP = getData().map(l -> Arrays.asList(l.split("")).stream()
																	.map(Integer::parseInt)
																	.collect(Collectors.toList()))
											.collect(Collectors.toList());
		return IntStream.range(0, MAP.size())
					.map(i -> IntStream.range(0, MAP.get(i).size())
								.filter(j -> isLower(i, j))
								.map(j -> MAP.get(i).get(j) +1)
								.sum())
					.sum();
	}

	private static boolean isLower(int i, int j) {
		return getVoisines(i, j).stream()
						.map(Cellule::getValue)
						.filter(v -> v < MAP.get(i).get(j))
						.findAny().isEmpty();
	}

	private static List<Cellule> getVoisines(int i, int j){
		List<Cellule> voisines = new ArrayList<>();
		if(i > 0) {
			voisines.addAll(getVoisinesLigne(i-1, j, MAP.get(i-1)));
		}
		if(j > 0) {
			voisines.add(new Cellule(i, j-1, MAP.get(i).get(j-1)));
		}
		if(i < MAP.size()-1) {
			voisines.addAll(getVoisinesLigne(i+1, j, MAP.get(i+1)));
		}
		if(j < MAP.get(i).size()-1) {
			voisines.add(new Cellule(i, j+1, MAP.get(i).get(j+1)));	
		}
		return voisines;
	}

	private static List<Cellule> getVoisinesLigne(int i, int j, List<Integer> ligne) {
		List<Cellule> voisines = new ArrayList<>();
		voisines.add(new Cellule(i , j ,ligne.get(j)));
		if(j > 0) {
			voisines.add(new Cellule(i , j-1 ,ligne.get(j-1)));
		}
		if(j < ligne.size()-1) {
			voisines.add(new Cellule(i , j+1 ,ligne.get(j+1)));
		}
		return voisines;
	}
	
	
	private static long getReponseQuestion2() {
		List<Long> bassins = IntStream.range(0, MAP.size())
		.mapToObj(i -> IntStream.range(0, MAP.get(i).size())
					.filter(j -> isLower(i, j))
					.mapToLong(j -> getTailleBasins(i, j))
					)
			.flatMap(LongStream::boxed)
			.collect(Collectors.toList());
		Collections.sort(bassins);
		return bassins.get(bassins.size()-1)*bassins.get(bassins.size()-2)*bassins.get(bassins.size()-3);
	}

	private static List<Cellule> getVoisinesHV(int i, int j){
		List<Cellule> voisines = new ArrayList<>();
		if(i > 0) {
			voisines.add(new Cellule(i-1, j, MAP.get(i-1).get(j)));
		}
		if(j > 0) {
			voisines.add(new Cellule(i, j-1, MAP.get(i).get(j-1)));
		}
		if(i < MAP.size()-1) {
			voisines.add(new Cellule(i+1, j, MAP.get(i+1).get(j)));
		}
		if(j < MAP.get(i).size()-1) {
			voisines.add(new Cellule(i, j+1, MAP.get(i).get(j+1)));	
		}
		return voisines;
	}
	
	private static long getTailleBasins(int i, int j){
		return getVoisinesPlusGrandes(new Cellule(i,j,MAP.get(i).get(j))).size();
	}

	private static Set<Cellule> getVoisinesPlusGrandes(Cellule cellule) {
		Set<Cellule> voisines = new HashSet<>();
		if(cellule.getValue()<9) {
			voisines.add(cellule);
		}
		voisines.addAll(getVoisinesHV(cellule.getX(), cellule.getY()).stream()
					.filter(c -> c.getValue() > cellule.getValue())
					.map(c -> getVoisinesPlusGrandes(c))
					.flatMap(Set::stream)
					.collect(Collectors.toSet()));
		return voisines;
	}
	
	

	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input9.txt");
	}
	
}
