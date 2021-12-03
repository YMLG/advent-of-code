package advent.of.code.a2021.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay3 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		List<String> data = getData();
		int nb = data.size();
		int size = data.get(0).length();
		Integer[] sum = data.stream().map(s -> s.split("")).reduce(new Integer[size], MainDay3::sumArray, (a,b) -> a);

		String gamma = "";
		String epsilon = "";
		for(Integer i : sum) {
			if(i < nb/2) {
				gamma += 0;
				epsilon += 1;
			}else {
				gamma += 1;
				epsilon += 0;			
			}
		}
		return  Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	private static Integer[] sumArray(Integer[] a, String[] b) {
		for(int i = 0; i< a.length; i++) {
			if( a[i] == null) {
				a[i] = Integer.parseInt(b[i]); 
			}else {
				a[i] += Integer.parseInt(b[i]); 
			}
		}
		return a;
	}

	private static long getReponseQuestion2() {
		List<String[]> data = getData().stream().map(s -> s.split("")).collect(Collectors.toList());
		
		List<String[]> dataO2 = new ArrayList<>(data);
		List<String[]> dataCO2 = new ArrayList<>(data);
		IntStream.range(0, data.get(0).length).forEach( i -> {
			if(dataO2.size() > 1) {
				int fewerO2 = getFewerBit(dataO2, i);
				dataO2.removeIf(d -> Integer.parseInt(d[i]) != fewerO2);
			}
			if(dataCO2.size() > 1) {
				int fewerCO2 = getFewerBit(dataCO2, i);
				dataCO2.removeIf(d -> Integer.parseInt(d[i]) == fewerCO2);
			}
		});
		String o2 = Arrays.asList(dataO2.get(0)).stream().reduce("", (a,b) -> a+b);
		String co2 = Arrays.asList(dataCO2.get(0)).stream().reduce("", (a,b) -> a+b);
		return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);

	}
	private static int getFewerBit(List<String[]> data, int position) {
		float halfSize = data.size() / 2f;
		return data.stream().mapToInt(s -> Integer.parseInt(s[position])).sum() >= halfSize ? 1 : 0;
	}


	private static List<String> getData() {
		return LectureFichiersUtils.getData("2021/input3.txt").collect(Collectors.toList());
	}
}
