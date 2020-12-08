package advent.of.code.a2020.day8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay8 {
	
	private static Map<String, BiFunction<State, String, State>> rules = new HashMap<>();

	public static void main(String[] args) {
		rules.put("nop", (s,n) -> s.addPos(1));
		rules.put("acc",  (s,n) -> s.addPos(1).addAcc(n));
		rules.put("jmp", (s,n)  -> s.addPos(n));
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		List<String> instructions = getData();
		Set<Integer> visited = new HashSet<>();
		State state = new State();
		while(visited.add(state.getPos())) {
			String[] inst = instructions.get(state.getPos()).split("\\s");
			rules.get(inst[0]).apply(state, inst[1]);
		}
		return state.getAcc();
	}

	
	private static long getReponseQuestion2() {
		List<String> instructions = getData();
		State state = new State();
		Set<Integer> tested = new HashSet<>();
		while (state.getPos() < instructions.size()) {
			state = new State();
			Set<Integer> visited = new HashSet<>();
			boolean test = false;
			while(state.getPos() < instructions.size() && visited.add(state.getPos())) {
				String[] inst = instructions.get(state.getPos()).split("\\s");
				String rule = inst[0];
				if(!test && !rule.equals("acc") && tested.add(state.getPos())){
					test = true;
					rule = rule.equals("jmp") ? "nop" : "jmp";
 				}
				rules.get(rule).apply(state, inst[1]);
			}
		}
		return state.getAcc();
	}
	

	private static List<String> getData() {
		return LectureFichiersUtils.getData("2020/input8.txt").collect(Collectors.toList());
	}
	

}
