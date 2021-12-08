package advent.of.code.a2021.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay5 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());		
	}

	private static long getReponseQuestion1() {
		Map<Point, Long> count = getData().map(s -> s.split(",|( -> )"))
				.map(s -> getPointsHV(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])),
						new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]))))
				.flatMap(List::stream)
			    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return count.values().stream().filter(l -> !l.equals(1l)).count();
	}

	
	private static long getReponseQuestion2() {
		Map<Point, Long> count = getData().map(s -> s.split(",|( -> )"))
				.map(s -> getPointsHVD(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])),
						new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]))))
				.flatMap(List::stream)
			    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return count.values().stream().filter(l -> !l.equals(1l)).count();
	}

	private static List<Point> getPointsHV(Point a, Point b){
		if(a.getY() == b.getY()) {
			int min = Integer.min(a.getX(), b.getX());
			int max = Integer.max(a.getX(), b.getX()) +1;
			return IntStream.range(min, max)
				.mapToObj(x -> new Point(x, a.getY()))
				.collect(Collectors.toList());
				
		}
		if(a.getX() == b.getX()) {
			int min = Integer.min(a.getY(), b.getY());
			int max = Integer.max(a.getY(), b.getY()) +1;
			return IntStream.range(min, max)
					.mapToObj(y -> new Point(a.getX(), y))
					.collect(Collectors.toList());
			
		}
		return new ArrayList<>();
	}
	
	private static List<Point> getPointsHVD(Point a, Point b){
		List<Point> points = getPointsHV(a, b);
		if(points.isEmpty() && Math.abs(a.getX() - b.getX()) == Math.abs(a.getY() - b.getY())) {
				int signeX = a.getX() > b.getX() ? -1 : 1;
				int signeY = a.getY() > b.getY() ? -1 : 1;
				return IntStream.range(0, Math.abs(a.getX() - b.getX())+1)
					.mapToObj(i -> new Point(a.getX()+ i*signeX, a.getY()+i*signeY))
					.collect(Collectors.toList());
		}
		return points;
	}

	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input5.txt");
	}
}
