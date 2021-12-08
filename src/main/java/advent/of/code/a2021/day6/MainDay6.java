package advent.of.code.a2021.day6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay6 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());		
	}

	private static long getReponseQuestion1() {
		List<Integer> crabes = getData().boxed().collect(Collectors.toList());
		return IntStream.range(Collections.min(crabes), Collections.max(crabes)).map(c -> crabes.stream().mapToInt(i -> Math.abs(i-c)).sum())
				.min().getAsInt();
	}

	private static int cost(int a, int b) {
		return IntStream.range(1, Math.abs(a-b)+1).sum();
	}
	
	private static long getReponseQuestion2() {
		List<Integer> crabes = getData().boxed().collect(Collectors.toList());
		return IntStream.range(Collections.min(crabes), Collections.max(crabes)).map(c -> crabes.stream().mapToInt(i -> cost(i, c)).sum())
				.min().getAsInt();
	}

	
	

	private static IntStream getData() {
		return LectureFichiersUtils.getData("2021/input7.txt")
					.map(s -> s.split(","))
					.map(Arrays::asList)
					.flatMap(List::stream)
					.mapToInt(Integer::parseInt);
	}
}
