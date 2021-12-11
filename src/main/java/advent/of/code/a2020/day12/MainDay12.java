package advent.of.code.a2020.day12;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay12 {
	
	private static final List<Character> DIRECTIONS = Arrays.asList('N', 'E', 'S', 'W');

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		Map<Character, Long> positions = new HashMap<>();
		DIRECTIONS.forEach(d -> positions.put(d, 0l));
		char direction = 'E';
		
		for(String instruction : getData()) {
			char dir = instruction.charAt(0);
			int nb = Integer.valueOf(instruction.substring(1));
			if(dir == 'F') {
				dir = direction;
			}
			if(DIRECTIONS.contains(dir)) {
				positions.put(dir, positions.get(dir) + nb);
			}else {
				direction = getNextDirection(direction, dir == 'R' ? nb : -nb);
			}
			
		}
		return Math.abs(positions.get('E') - positions.get('W')) + Math.abs(positions.get('N') - positions.get('S'));
	}

	private static char getNextDirection(char direction, int angle) {
		int i = DIRECTIONS.indexOf(direction);
		return DIRECTIONS.get((i + angle/90 + 4) % 4);
	}
	
	
	private static long getReponseQuestion2() {
		Map<Character, Long> positions = new HashMap<>();
		DIRECTIONS.forEach(d -> positions.put(d, 0l));
		Map<Character, Long> waypoint  = new HashMap<>(positions);
		waypoint.put('N', 1l);
		waypoint.put('E', 10l);
		for(String instruction : getData()) {
			char dir = instruction.charAt(0);
			int nb = Integer.valueOf(instruction.substring(1));
			if(dir == 'F') {
				DIRECTIONS.forEach(d ->{
					positions.put(d, positions.get(d) + nb * waypoint.get(d));
				});
			}
			if(DIRECTIONS.contains(dir)) {
				waypoint.put(dir, waypoint.get(dir) + nb);
			}else {
				Map<Character, Long>  oldWaypoint = new HashMap<>(waypoint);
				DIRECTIONS.forEach(d ->{
					waypoint.put(getNextDirection(d, dir == 'R' ? nb : -nb), oldWaypoint.get(d));
				});
			}
			System.out.println(waypoint);
		}
		return Math.abs(positions.get('E') - positions.get('W')) + Math.abs(positions.get('N') - positions.get('S'));
									
		
	}

	private static List<String> getData() {
		//return Arrays.asList("F10","N3","F7","R90","F11");
		return LectureFichiersUtils.getData("2020/input12.txt").collect(Collectors.toList());
	}
}
