package advent.of.code.a2020.day10;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay10 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		List<Integer> adapters = getData();
		Collections.sort(adapters);
		Map<Integer, Long> count = IntStream.range(1, adapters.size())
				.map(i -> adapters.get(i) -  adapters.get(i-1))
				.boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return (count.get(1)+1) * (count.get(3)+1);
	}

	private static long getReponseQuestion2() {
		List<Integer> adapters = getData();
		adapters.add(0);
		Collections.sort(adapters, (a,b) -> b.compareTo(a));
		int max = adapters.get(0);
		adapters.remove(0);
		Map<Integer, Long> nbSteps = new HashMap<>();
		nbSteps.put(max, 1l);
		for(Integer i : adapters) {
			nbSteps.put(i, IntStream.range(i +1 , i +4)
						.filter(nbSteps::containsKey)
						.mapToLong(nbSteps::get)
						.sum());
		}
		return nbSteps.get(0);
									
		
	}

	private static List<Integer> getData() {
		return LectureFichiersUtils.getData("2020/input10.txt").map(Integer::valueOf).collect(Collectors.toList());
	}
}
