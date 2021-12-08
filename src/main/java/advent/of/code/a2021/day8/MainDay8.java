package advent.of.code.a2021.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay8 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());		
	}

	private static long getReponseQuestion1() {
		List<Integer> sizes = Arrays.asList(2, 3, 4, 7);
		return getData().map(l -> l.split("\\|")[1])
			.map(l -> Arrays.asList(l.split(" ")))
			.flatMap(List::stream)
			.filter(s -> sizes.contains(s.trim().length()))
			.count();
	}

	private static long getReponseQuestion2() {
		return getData().mapToInt(MainDay8::getNumberOutput).sum();
	}
	
	private static int getNumberOutput(String line) {
		String[] input = line.split("\\|");
		Rules rules = new Rules(input[0].trim().split(" "));
		return Integer.parseInt(Arrays.asList(input[1].trim().split(" ")).stream()
						 .map(rules::getInt)
						 .reduce("", (a,b) -> a+""+b, (a,b) -> a+""+b));
		
	}

	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input8.txt");
	}
	
}
