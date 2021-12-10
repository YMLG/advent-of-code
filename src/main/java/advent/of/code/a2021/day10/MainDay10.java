package advent.of.code.a2021.day10;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay10 {
	

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());	
	
	}

	private static long getReponseQuestion1() {
			return getData()
					.map(MainDay10::resolve)
					.map(l -> l.replaceAll("\\(|\\{|\\[|<", ""))
					.filter(l -> !l.isEmpty())
					.map(l -> l.substring(0, 1))
					.mapToInt(MainDay10::getScore)					
					.sum();
	}

	
	private static long getReponseQuestion2() {
		 List<Long> points = getData()
				.map(MainDay10::resolve)
				.filter(l -> l.replaceAll("\\(|\\{|\\[|<", "").isEmpty())
				.map(l -> Arrays.asList(l.split("")))
				.map(MainDay10::getPoints).collect(Collectors.toList());
		 Collections.sort(points);
		 return points.get(points.size()/2);
	}

	
	private static String resolve(String line) {
		int size = 0;
		while (size != line.length()) {
			size = line.length();
			line = line.replaceAll("\\(\\)|\\{\\}|\\[\\]|<>", "");
		}
		return line;
	}
	
	private static long getPoints(List<String> line) {
		long p = 0;
		Collections.reverse(line);
		for(String s : line) {
			p = 5 * p + getScore(s);
		}
		return p;
	}
	
	private static int getScore(String s) {
		switch (s) {
		case ")":
			return 3;
		case "]":
			return 57;
		case "}":
			return 1197;
		case ">":
			return 25137;
		case "(":
			return 1;
		case "[":
			return 2;
		case "{":
			return 3;
		case "<":
			return 4;
		default:
			return 0;
		}
	}

	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input10.txt");
	}
	
}
