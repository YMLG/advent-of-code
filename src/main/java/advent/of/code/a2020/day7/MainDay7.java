package advent.of.code.a2020.day7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay7 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		Map<String, List<String>> rules = getData();
		return rules.keySet().stream().filter(b -> canContain(rules, rules.get(b), "shiny gold")).count();
	}

	
	private static boolean canContain(Map<String, List<String>> rules, List<String> bags , String bag) {
		if(bags.size() == 1 && bags.get(0).equals("no other")) {
			return false;
		}
		return  bags.stream()
				.map(b -> b.split("\\s", 2)[1])
				.anyMatch(b -> b.equals(bag) || canContain(rules, rules.get(b), bag));
	}


	private static long getReponseQuestion2() {
		Map<String, List<String>> rules = getData();
		return nbContain(rules, rules.get("shiny gold"));
									
		
	}
	
	private static long nbContain(Map<String, List<String>> rules, List<String> bags) {
		if(bags.size() == 1 && bags.get(0).equals("no other")) {
			return 0;
		}
		return  bags.stream()
				.map(b -> b.split("\\s", 2))
				.mapToLong(b -> Integer.valueOf(b[0]) * (1 + nbContain(rules, rules.get(b[1]))))
				.sum();
	}
	

	private static Map<String, List<String>> getData() {
		return LectureFichiersUtils.getData("2020/input7.txt").map(s -> s.replaceAll("\\sbag(s)?(\\.)?", ""))
				.map(s -> s.split("\\scontain\\s"))
				.collect(Collectors.toMap(s -> s[0], s-> Arrays.asList(s[1].split(",\\s"))));
	}
}
