package advent.of.code.a2020.day6;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay6 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		return getData().mapToLong(s -> s.replaceAll("\\n", "").chars().distinct().count()).sum();
	}




	private static long getReponseQuestion2() {
		return getData().mapToInt(r -> Arrays.asList(r.split("\\n"))
									.stream()
									.map(s -> s.chars().boxed().collect(Collectors.toSet()))
									.reduce(null, (s1,s2) -> {
										if(s1 == null ) {
											return s2;
										}
										s1.retainAll(s2);
										return s1;
									}).size())
						.sum();
									
		
	}



	private static Stream<String> getData() {
		return Arrays.asList(LectureFichiersUtils.getStringData("2020/input6.txt").split("\\n\\n")).stream();
	}
}
