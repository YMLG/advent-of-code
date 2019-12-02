package advent.of.code.a2018.day4;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay4 {
	public static void main(String[] args) {
		List<Event> data = LectureFichiersUtils.getData("2018/fichier4.txt")
		                        .map(Event::new)
		                        .sorted()
		                        .collect(Collectors.toList());
		Map<Integer, List<Event>> parGarde = getEtatsParGarde(data);
		
		System.out.println("Question 1 - "+ getReponseQuestion1(parGarde));
		System.out.println("Question 2 - "+ getReponseQuestion2(parGarde));
	}


	private static long getReponseQuestion1(Map<Integer, List<Event>> parGarde ) {
		Entry<Integer, List<Event>> gardeMaxSleep =  parGarde.entrySet()
															.stream()
															.max(Comparator.comparing(e -> getNbSleep(e.getValue())))
															.orElse(null);
		Entry<Integer, Long> maxMinuteSleep = getMaxMiniuteSleep(gardeMaxSleep.getValue());
		return gardeMaxSleep.getKey() * maxMinuteSleep.getKey();
	}

	private static Map<Integer, List<Event>> getEtatsParGarde(List<Event> data) {
		Map<Integer, List<Event>> parGarde = new HashMap<>();
		Integer id = null;
		for(Event d : data) {
			if(d.getEtat().contains("Guard #")) {
				id = Integer.parseInt(d.getEtat().split("Guard #")[1].split(" ")[0]);
			}else {
				if(parGarde.containsKey(id)) {
					parGarde.get(id).add(d);
				}else {
					List<Event> l = new ArrayList<>();
					l.add(d);
					parGarde.put(id, l);
				}
			}
		}
		return parGarde;
	}
	

	private static int getReponseQuestion2(Map<Integer, List<Event>> parGarde) {
		SimpleEntry<Integer, Entry<Integer, Long>> maxMinuteSleepGarde = parGarde.entrySet()
																	.stream()
																	.map(e -> new SimpleEntry<>(e.getKey(), getMaxMiniuteSleep(e.getValue())))
																	.max(Comparator.comparing(e -> e.getValue().getValue()))
																	.orElse(null);
		return maxMinuteSleepGarde.getKey() * maxMinuteSleepGarde.getValue().getKey();
	}
	
	
	
	private static int getNbSleep(List<Event> d) {
		int nb = 0;
		for(int i=0; i < d.size()-1; i=i+2) {
			nb += d.get(i+1).getDate().getMinute()-d.get(i).getDate().getMinute();
		}
		return nb;
	}
	
	private static Entry<Integer, Long> getMaxMiniuteSleep(List<Event> d) {
		List<Integer> minutes = new ArrayList<>();
		for(int i=0; i < d.size()-1; i=i+2) {
			for(int j= d.get(i).getDate().getMinute(); j < d.get(i+1).getDate().getMinute(); j++) {
				minutes.add(j);
			}
		}
		Map<Integer, Long> count = minutes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return count.entrySet()
					.stream()
					.max(Comparator.comparing(Entry::getValue))
					.orElse(null);
	}
}
