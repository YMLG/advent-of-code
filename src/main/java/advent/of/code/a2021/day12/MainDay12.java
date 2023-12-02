package advent.of.code.a2021.day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay12 {
	
	private static Map<String, List<String>> map;


	public static void main(String[] args) {
		map = getData().map(s -> s.split("-")).collect(Collectors.groupingBy(s -> s[0], Collectors.mapping(s -> s[1], Collectors.toList())));
		getData()
			.map(s -> s.split("-"))
			.collect(Collectors.groupingBy(s -> s[1], Collectors.mapping(s -> s[0], Collectors.toList())))
			.forEach((k,v) -> {
				if(map.containsKey(k)) {
					 map.get(k).addAll(v);
				}else{
					map.put(k, v);
				}
			});
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());	
	
	}

	private static long getReponseQuestion1() {
		return getAllPath("", "start").size();
	}

	
	private static List<String> getAllPath(String debut, String intersection) {
		if(intersection.equals("end") ) {
			return Collections.singletonList(debut);
		}
		if(map.get(intersection) == null || (intersection.toLowerCase().equals(intersection) && debut.contains(intersection))) {
			return new ArrayList<>();
		}
		return map.get(intersection).stream().map(s -> getAllPath(debut+intersection, s)).flatMap(List::stream).collect(Collectors.toList());
	}
	
	
	private static long getReponseQuestion2() {
		return getAllPath2("", "start").size();
	}

	private static List<String> getAllPath2(String debut, String intersection) {
		if(intersection.equals("end") ) {
			return Collections.singletonList(debut);
		}
		if(map.get(intersection) == null || (intersection.toLowerCase().equals(intersection) && containTwiceOrStartEnd(debut, intersection))) {
			return new ArrayList<>();
		}
		return map.get(intersection).parallelStream().map(s -> getAllPath2(debut+intersection, s)).flatMap(List::stream).collect(Collectors.toList());
	}

	
	private static  boolean containTwiceOrStartEnd(String debut, String intersection) {
		if(intersection.equals("end") ||  intersection.equals("start") ) {
			return debut.contains(intersection);
		}
		return debut.split(intersection).length == 3;
	}

	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2021/input12.txt");
	}
	
}
