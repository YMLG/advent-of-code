package advent.of.code.a2021.day6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay6 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());		
	}

	private static long getReponseQuestion1() {
		return getData().mapToLong(i -> getFamillyCount(i, 80)).sum();
	}

	
	private static long getReponseQuestion2() {
		Map<Integer, Long> fishes = getData().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return fishes.keySet().parallelStream().mapToLong(f -> fishes.get(f) * getFamillyCount(f, 256)).sum();
	}

	private static long getFamillyCount(Integer first, int nbTurn){
		if(nbTurn == 0 || first >= nbTurn) {
			return 1;
		}
		return getFamillyCount(6, nbTurn - 1 - first) + getFamillyCount(8, nbTurn - 1 - first);
	
	}
	

	private static IntStream getData() {
		return LectureFichiersUtils.getData("2021/input6.txt")
					.map(s -> s.split(","))
					.map(Arrays::asList)
					.flatMap(List::stream)
					.mapToInt(Integer::parseInt);
	}
}
